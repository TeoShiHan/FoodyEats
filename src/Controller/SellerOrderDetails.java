package Controller;
import Cache.*;
import Classes.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class SellerOrderDetails implements Initializable {    
    JDBC db = new JDBC();
    GUI gui = GUI.getInstance();    
    DataHolder data = DataHolder.getInstance();    
    
    @FXML private AnchorPane paneBuyerOrder;    
    @FXML private Label linkLogout,lblOrderDetails,lblBuyerName,lblBuyerMobileNo,lblBuyerAddress,lblTotalAmount,lblDateCreated,lblTimeCreated,lblRiderName,lblRiderMobileNo,lblRiderVehicleDetails,lblStatus;
    @FXML private ImageView iconProfile,iconHome;            
    @FXML private Button btnDecline,btnAccept;    
    @FXML private VBox vboxStatus;
    @FXML private TableView<OrderItem> tableView;
    @FXML private TableColumn<OrderItem,Number> colNo;
    @FXML private TableColumn<OrderItem,Integer> colQty;
    @FXML private TableColumn<OrderItem,String> colItems;  
    
    private SellerOrder order;
    private String currentFXMLPath = "View/SellerOrderDetails.fxml";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        order = new SellerOrder(data.getOrder());            
        data.setOrder(order);
        if(order.getOrderItems()==null){            
            order.loadAllDetails();            
        }
        
        lblOrderDetails.setText("Order #"+order.getOrderID());
        lblDateCreated.setText(order.getDateCreated().toString());
        lblTimeCreated.setText(order.getTimeCreated().toString());
        lblBuyerName.setText(order.getBuyer().getName());
        lblBuyerMobileNo.setText(order.getBuyer().getMobileNo());
        lblBuyerAddress.setText(order.getBuyer().getAddress());  
        if(order.getRider()!=null){
            lblRiderName.setText(order.getRider().getName());
            lblRiderMobileNo.setText(order.getRider().getMobileNo());                
            lblRiderVehicleDetails.setText(order.getRider().getVehicle().toString());
        }
        lblTotalAmount.setText(String.format("RM %.2f",order.calcTotalAmount()));
        
        // https://stackoverflow.com/questions/36629522/convert-arraylist-to-observable-list-for-javafx-program
        ObservableList<OrderItem> observableList = FXCollections.observableArrayList(order.getOrderItems());        
        
        tableView.setItems(observableList);

        //to assign which property/attribute of the class to the table column                    
        // https://stackoverflow.com/questions/16384879/auto-numbered-table-rows-javafx, for the next line (line 75)
        colNo.setCellValueFactory(dt -> new ReadOnlyObjectWrapper<Number>(tableView.getItems().indexOf(dt.getValue())+1));        
        colItems.setCellValueFactory(dt -> new SimpleStringProperty(dt.getValue().getFood().getName()));        
        // https://stackoverflow.com/questions/14413040/converting-integer-to-observablevalueinteger-in-javafx/14413339, for the next line (line 80)
        colQty.setCellValueFactory(dt -> new SimpleIntegerProperty(dt.getValue().getQuantity()).asObject());                        

        vboxStatus.setManaged(false);
        vboxStatus.setVisible(false);
        if(order.getStatus().equals("Pending")){
            btnAccept.setOnAction(e->{
                order.setStatus("Seller Accepted");
                data.getShop().acceptOrder(order.getOrderID());
                try {
                    gui.refreshScene(currentFXMLPath);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
    
            btnDecline.setOnAction(e->{
                order.setStatus("Seller Declined");
                data.getShop().declineOrder(order.getOrderID());
                try {
                    gui.refreshScene(currentFXMLPath);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        }else if(order.getStatus().equals("Rider Accepted")){
            btnDecline.setManaged(false);
            btnDecline.setVisible(false);
            btnAccept.setText("Ready");
            btnAccept.setOnAction(e->{
                order.setStatus("Seller Ready");
                data.getShop().readyOrder(order.getOrderID());
                try {
                    gui.refreshScene(currentFXMLPath);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        }else{
            btnDecline.setManaged(false);
            btnDecline.setVisible(false);
            btnAccept.setManaged(false);
            btnAccept.setVisible(false);
            vboxStatus.setManaged(true);
            vboxStatus.setVisible(true);
            lblStatus.setText(order.getStatus());            
        }
    }    

    @FXML
    void toHome(MouseEvent event) throws IOException {
        gui.toNextScene("View/SellerHome.fxml");
    }        

    @FXML
    void toBack(MouseEvent event) throws IOException {
        gui.toPrevScene();
    }  
}