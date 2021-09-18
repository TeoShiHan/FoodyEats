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

public class RiderOrderHistory implements Initializable {
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();
    private String currentFXMLPath = "View/RiderOrderHistory.fxml";

    @FXML private AnchorPane paneRiderOrderHistory;
    @FXML private Label lblWelcome;
    @FXML private ImageView iconCart,iconHome;
    @FXML private TableView<Order> tableView;
    @FXML private TableColumn<Order,Object> colOrderId,colDate,colTime;    //colDeliveryFee
    @FXML private TableColumn<Order,Order> colAction;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data.setOrders(new ArrayList<>());
        if(data.getOrders().isEmpty()){ 
            // https://stackoverflow.com/questions/19196475/custom-order-by-in-sql-server-like-p-a-l-h (select data order by the custom sequence)
            ArrayList<HashMap<String,Object>> os = db.readAll(String.format("SELECT * FROM `Order` WHERE riderID='%s' ORDER BY CASE WHEN status='Rider Collected' THEN 1 WHEN status='Seller Ready' THEN 2 WHEN status='Rider Accepted' THEN 3 else 4 END ASC",((Rider)data.getAccount()).getRiderID()));
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
            Button btnCollect = new Button("Collect");
            Button btnComplete = new Button("Complete");
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
                if(order.getStatus().equals("Rider Accepted") || order.getStatus().equals("Seller Ready")){
                    if(order.getStatus().equals("Rider Accepted")){
                        btnCollect.setDisable(true);
                    }
                    pane.getChildren().add(btnCollect);
                }else if(order.getStatus().equals("Rider Collected")){
                    pane.getChildren().add(btnComplete);
                }
                pane.getChildren().add(btnViewDetails);
                setGraphic(pane);
                btnCollect.setOnAction(e->{
                    order.setStatus("Rider Collected");
                    data.getRider().collectOrder(order.getOrderID());
                    tableView.refresh();              
                });
                btnComplete.setOnAction(e->{  
                    order.setStatus("Completed");                  
                    data.getRider().deliveredOrder(order.getOrderID());
                    tableView.refresh();
                });                
                btnViewDetails.setOnAction(e->{          
                    data.setOrder(order);
                    gui.toNextScene("View/RiderOrderDetails.fxml");                              
                });                                       
            }                     
        });

        tableView.setItems(observableList);        
    }    

    @FXML
    void toCart(MouseEvent event) throws IOException {
        gui.toNextScene("View/RiderCart.fxml");
    }

    @FXML
    void toHome(MouseEvent event) throws IOException {
        gui.toNextScene("View/RiderHome.fxml");
    }
}
