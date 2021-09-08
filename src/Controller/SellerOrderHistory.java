package Controller;
import Cache.*;
import Classes.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class SellerOrderHistory implements Initializable {    
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();
    private String currentFXMLPath = "View/SellerOrderHistory.fxml";

    @FXML private AnchorPane paneRiderOrderHistory;
    @FXML private Label lblWelcome;
    @FXML private ImageView iconCart,iconHome;
    @FXML private TableView<Order> tableView;
    @FXML private TableColumn<Order,Object> colOrderId,colDate,colTime,colStatus;
    @FXML private TableColumn<Order,Order> colAction;

    @Override
    public void initialize(URL location, ResourceBundle resources) {                
        data.getSeller().getShop().loadAcceptedOrders();
        data.setOrders(data.getSeller().getShop().getOrders());

        ObservableList<Order> observableList = FXCollections.observableArrayList(data.getOrders());
        
        tableView.setItems(observableList);

        //to assign which property/attribute of the class to the table column
        colOrderId.setCellValueFactory(new PropertyValueFactory<Order,Object>("orderID"));
        colDate.setCellValueFactory(new PropertyValueFactory<Order,Object>("dateCreated"));        
        colTime.setCellValueFactory(new PropertyValueFactory<Order,Object>("timeCreated")); 
        colStatus.setCellValueFactory(new PropertyValueFactory<Order,Object>("status")); 
        colAction.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));        
        // to set what to display in each table cell, instead string only, because by default it display string (https://stackoverflow.com/questions/32282230/fxml-javafx-8-tableview-make-a-delete-button-in-each-row-and-delete-the-row-a)        
        colAction.setCellFactory(param -> new TableCell<Order,Order>(){            
            Button btnReady = new Button("Ready");
            Button btnViewDetails = new Button("View Details");
            @Override
            public void updateItem(Order order, boolean empty){
                super.updateItem(order, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                } 
                HBox pane = new HBox();
                pane.setSpacing(20);                
                pane.setAlignment(Pos.CENTER);                                    
                if(order.getStatus().equals("Rider Accepted")){                    
                    pane.getChildren().add(btnReady);
                }
                pane.getChildren().add(btnViewDetails);
                setGraphic(pane);                
                btnReady.setOnAction(e->{  
                    order.setStatus("Seller Ready");                  
                    data.getSeller().getShop().readyOrder(order.getOrderID());
                    tableView.refresh();
                });                
                btnViewDetails.setOnAction(e->{          
                    try {
                        data.setOrder(order);
                        gui.toNextScene("View/SellerOrderDetails.fxml");
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }                                
                });                                       
            }                     
        });
          
        // tableView.getColumns().addAll(colOrderId,colDate,colStatus,colAction); //not needed
    }    

    @FXML
    void toHome(MouseEvent event) throws IOException {
        gui.toNextScene("View/SellerHome.fxml");
    }
}
