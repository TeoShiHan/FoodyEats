package Controller;
import Cache.*;
import Classes.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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

public class RiderOrders implements Initializable {
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();

    @FXML private AnchorPane paneRiderOrders;
    @FXML private Label lblWelcome;
    @FXML private ImageView iconCart,iconHome;
    @FXML private TableView<Order> tableView;
    @FXML private TableColumn<Order,Object> colOrderId,colDate,colTime;    
    @FXML private TableColumn<Order,Order> colAction;
    private String currentFXMLPath = "View/RiderOrders.fxml";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data.setOrders(new ArrayList<>());
        if(data.getOrders().isEmpty()){ 
            ArrayList<HashMap<String,Object>> os = db.readAll(String.format("SELECT * FROM `Order` WHERE status='Seller Accepted'"));
            for(HashMap<String,Object> o : os){
                data.getOrders().add(new Order(o.get("orderID"),o.get("status"), o.get("dateCreated"),o.get("timeCreated"),o.get("buyerID"),o.get("riderID"),o.get("shopID"),o.get("paymentID"),o.get("reviewID")));
            }
        }   

        ObservableList<Order> observableList = FXCollections.observableArrayList(data.getOrders());
        
        tableView.setItems(observableList);

        //to assign which property/attribute of the class to the table column
        colOrderId.setCellValueFactory(new PropertyValueFactory<Order,Object>("orderID"));
        colDate.setCellValueFactory(new PropertyValueFactory<Order,Object>("dateCreated")); 
        colTime.setCellValueFactory(new PropertyValueFactory<Order,Object>("timeCreated"));        
        colAction.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));        
        // to set what to display in each table cell, instead string only, because by default it display string (https://stackoverflow.com/questions/32282230/fxml-javafx-8-tableview-make-a-delete-button-in-each-row-and-delete-the-row-a)        
        colAction.setCellFactory(param -> new TableCell<Order,Order>(){
            Button btnViewDetails = new Button("View Details");
            Button btnAccept = new Button("Accept");
            @Override
            public void updateItem(Order order, boolean empty){
                super.updateItem(order, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                } 
                HBox pane = new HBox(btnAccept, btnViewDetails);
                pane.setSpacing(20);                
                pane.setAlignment(Pos.CENTER);
                setGraphic(pane);
                btnAccept.setOnAction(e->{
                    tableView.getItems().remove(order);
                    gui.miniPopup("Order Accepted, please go to Home->Order Accepted to see the order");
                    data.getRider().acceptOrder(order.getOrderID());
                });
                btnViewDetails.setOnAction(e->{          
                    data.setOrder(order);
                    System.out.println(data.getOrder().getOrderID());
                    gui.toNextScene("View/RiderOrderDetails.fxml");                          
                });                                      
            }                     
        });             
    }    

    @FXML
    void toCart(MouseEvent event) throws IOException {
        gui.toNextScene("View/RiderCart.fxml");
    }

    @FXML
    void toHome(MouseEvent event) throws IOException {
        gui.toNextScene("View/RiderHome.fxml");
    }

    @FXML
    void toRefreshScene(MouseEvent event) throws IOException {        
        gui.refreshScene(currentFXMLPath);
    }
}
