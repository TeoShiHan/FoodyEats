package Controller;
import Cache.*;
import Classes.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RiderOrderDetails implements Initializable {    
    JDBC db = new JDBC();
    GUI gui = GUI.getInstance();    
    DataHolder data = DataHolder.getInstance();    
    private String currentFXMLPath = "View/RiderOrderDetails.fxml";
    
    @FXML private AnchorPane paneRiderOrderDetails;
    @FXML private Label linkLogout,lblOrderDetails,lblBuyerName,lblBuyerMobileNo,lblBuyerAddress,lblDateCreated,lblTimeCreated,lblShopName,lblShopTelNo,lblShopAddress,lblDeliveryFee;
    @FXML private ImageView iconProfile,iconHome,iconCart,iconBack;            
    @FXML private Button btnAction;        
    @FXML private TableView<OrderItem> tableView;
    @FXML private TableColumn<OrderItem,Number> colNo;
    @FXML private TableColumn<OrderItem,Integer> colQty;
    @FXML private TableColumn<OrderItem,String> colItems;  
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {        
        // if(data.getOrder().getOrderItems().isEmpty()){
        //     data.getOrder().loadOrderItems();
        // }        
        if(data.getOrder().getBuyer()==null || data.getOrder().getShop()==null){
            data.getOrder().loadAllDetails();
        }        
        
        lblOrderDetails.setText("Order #"+data.getOrder().getOrderID());
        lblDateCreated.setText(data.getOrder().getDateCreated().toString());
        lblTimeCreated.setText(data.getOrder().getTimeCreated().toString());
        lblBuyerName.setText(data.getOrder().getBuyer()!=null ? data.getOrder().getBuyer().getName() : "");
        lblBuyerAddress.setText(data.getOrder().getBuyer()!=null  ? data.getOrder().getBuyer().getAddress() : "");        
        lblBuyerMobileNo.setText(data.getOrder().getBuyer()!=null ? data.getOrder().getBuyer().getMobileNo() : "");
        lblShopName.setText(data.getOrder().getShop()!=null ? data.getOrder().getShop().getName() : "");
        lblShopAddress.setText(data.getOrder().getShop()!=null ? data.getOrder().getShop().getAddress() : "");        
        lblShopTelNo.setText(data.getOrder().getShop()!=null ? data.getOrder().getShop().getTel() : "");
        lblDeliveryFee.setText(String.format("RM %.2f",data.getOrder().getShop()!=null ? data.getOrder().getShop().getDeliveryFee() : 0.00));   

        // https://stackoverflow.com/questions/36629522/convert-arraylist-to-observable-list-for-javafx-program
        ObservableList<OrderItem> observableList = FXCollections.observableArrayList(data.getOrder().getOrderItems());        
        
        tableView.setItems(observableList);

        //to assign which property/attribute of the class to the table column                    
        // https://stackoverflow.com/questions/16384879/auto-numbered-table-rows-javafx, for the next line (line 75)
        colNo.setCellValueFactory(dt -> new ReadOnlyObjectWrapper<Number>(tableView.getItems().indexOf(dt.getValue())+1));        
        colItems.setCellValueFactory(dt -> new SimpleStringProperty(dt.getValue().getFood().getName()));        
        // https://stackoverflow.com/questions/14413040/converting-integer-to-observablevalueinteger-in-javafx/14413339, for the next line (line 80)
        colQty.setCellValueFactory(dt -> new SimpleIntegerProperty(dt.getValue().getQuantity()).asObject());
                        
        if(data.getOrder().getStatus().equals("Seller Accepted")){
            btnAction.setOnAction(e->{                    
                data.getOrder().setStatus("Rider Accepted");
                data.getRider().acceptOrder(data.getOrder().getOrderID());     
                try {
                    gui.refreshScene(currentFXMLPath);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }                                
            });
        }else if(data.getOrder().getStatus().equals("Seller Ready")){
            btnAction.setText("Collect");
            btnAction.setOnAction(e->{
                data.getOrder().setStatus("Rider Collected");
                data.getRider().collectOrder(data.getOrder().getOrderID());         
                try {
                    gui.refreshScene(currentFXMLPath);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }                            
            });
        }else if(data.getOrder().getStatus().equals("Rider Collected")){            
            btnAction.setText("Complete");
            btnAction.setOnAction(e->{
                data.getOrder().setStatus("Completed");    
                data.getRider().deliveredOrder(data.getOrder().getOrderID());      
                try {
                    gui.refreshScene(currentFXMLPath);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }                               
            });                   
        }else{
            btnAction.setText("Completed");
            btnAction.setDisable(true);
        }

        //#region old method to retrieve the data from mysql database
        // HashMap<String,Object> od = db.readOne(String.format("SELECT o.orderID,o.status,o.dateCreated,o.timeCreated,s.deliveryFee,s.shopName,s.tel,s.address,a.name,a.mobileNo,b.address,f.foodName,oi.quantity FROM `Order` o, `Shop` s, `Buyer` b, `OrderItem` oi, `Food` f, `Account` a WHERE o.orderID='%s' AND o.orderID=oi.orderID AND o.shopID=s.shopID AND o.buyerID=b.buyerID AND oi.foodID=f.foodID AND b.accountID=a.accountID","O00001"));
        // lblOrderDetails.setText("Order #"+od.get("orderID").toString());
        // lblDateCreated.setText(od.get("dateCreated").toString());
        // lblTimeCreated.setText(od.get("timeCreated").toString());
        // lblBuyerName.setText(od.get("buyerName").toString());
        // lblBuyerAddress.setText(od.get("buyerAddress").toString());
        // lblBuyerMobileNo.setText(od.get("mobileNo").toString());
        // lblDeliveryFee.setText(String.format("RM %.2f",od.get("deliveryFee")));   

        // // https://stackoverflow.com/questions/36629522/convert-arraylist-to-observable-list-for-javafx-program
        // ObservableMap<String,Object> observableList = FXCollections.observableHashMap();
        // ObservableList<String> keys = FXCollections.observableArrayList();
        
        // tableView.setItems(keys);

        // //to assign which property/attribute of the class to the table column                    
        // // https://stackoverflow.com/questions/16384879/auto-numbered-table-rows-javafx, for the next line (line 75)
        // colNo.setCellValueFactory(new MapValueFactory<>("no"));
        // colItems.setCellValueFactory(new MapValueFactory<>("no"));
        // // https://stackoverflow.com/questions/14413040/converting-integer-to-observablevalueinteger-in-javafx/14413339, for the next line (line 80)
        // colQty.setCellValueFactory(dt -> new SimpleIntegerProperty(dt.getValue().getQuantity()).asObject());                        
        //#endregion
    }    

    @FXML
    void toHome(MouseEvent event) throws IOException {
        gui.toNextScene("View/RiderHome.fxml");
    }        

    @FXML
    void toBack(MouseEvent event) throws IOException {
        gui.toPrevScene();
    }       
}