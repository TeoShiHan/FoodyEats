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

public class SellerOrderDetails implements Initializable {    
    JDBC db = new JDBC();
    GUI gui = GUI.getInstance();    
    DataHolder data = DataHolder.getInstance();    
    
    @FXML private AnchorPane paneBuyerOrder;    
    @FXML private Label linkLogout,lblOrderDetails,lblBuyerName,lblBuyerMobileNo,lblBuyerAddress,lblTotalAmount,lblDateCreated,lblTimeCreated;
    @FXML private ImageView iconProfile,iconHome;            
    @FXML private Button btnRate,btnCancel;    
    // @FXML private TableView<RowData> tableView;    
    @FXML private TableView<OrderItem> tableView;
    @FXML private TableColumn<OrderItem,Number> colNo;
    @FXML private TableColumn<OrderItem,Integer> colQty;
    @FXML private TableColumn<OrderItem,String> colItems;    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(data.getOrder().getOrderItems().isEmpty()){
            data.getOrder().loadAllDetails();            
        }
        
        lblOrderDetails.setText("Order #"+data.getOrder().getOrderID());
        lblDateCreated.setText(data.getOrder().getDateCreated().toString());
        lblTimeCreated.setText(data.getOrder().getTimeCreated().toString());
        lblBuyerName.setText(data.getBuyer().getName());
        lblBuyerAddress.setText(data.getBuyer().getAddress());        
        lblBuyerMobileNo.setText(data.getBuyer().getMobileNo());        
        lblTotalAmount.setText(String.format("RM %.2f",data.getOrder().getTotalAmount()));

        // https://stackoverflow.com/questions/36629522/convert-arraylist-to-observable-list-for-javafx-program
        ObservableList<OrderItem> observableList = FXCollections.observableArrayList(data.getOrder().getOrderItems());        
        
        tableView.setItems(observableList);

        //to assign which property/attribute of the class to the table column                    
        // https://stackoverflow.com/questions/16384879/auto-numbered-table-rows-javafx, for the next line (line 75)
        colNo.setCellValueFactory(dt -> new ReadOnlyObjectWrapper<Number>(tableView.getItems().indexOf(dt.getValue())+1));        
        colItems.setCellValueFactory(dt -> new SimpleStringProperty(dt.getValue().getFood().getName()));        
        // https://stackoverflow.com/questions/14413040/converting-integer-to-observablevalueinteger-in-javafx/14413339, for the next line (line 80)
        colQty.setCellValueFactory(dt -> new SimpleIntegerProperty(dt.getValue().getQuantity()).asObject());
        
        
        //#region old method to read data from database 
        // HashMap<String,Object> buyer = db.readOne(String.format("SELECT * FROM `Buyer` WHERE buyerID='%s'", data.getOrder().getBuyerId()));
        // lblBuyerAddress.setText((String)buyer.get("address"));  
        // HashMap<String,Object> account = db.readOne(String.format("SELECT * FROM `Account` WHERE accountID='%s'", (String)buyer.get("accountID")));
        // lblBuyerPhoneNo.setText((String)account.get("mobileNo"));
        // ObservableList<Map<String,Object>> observableList = FXCollections.observableArrayList();        
        // ArrayList<HashMap<String,Object>> ois = 


        // db.readAll("SELECT * FROM `Order` AS o INNER JOIN `OrderItem`
        // AS oi ON o.orderID = oi.orderID INNER JOIN `Buyer` as b ON o.buyerID = b.buyerID INNER JOIN `Shop`
        // AS s ON o.shopID = s.shopID INNER JOIN `Payment` AS p ON o.paymentID = p.paymentID INNER JOIN `Food` 
        // AS f ON oi.foodID = f.foodID WHERE o.orderID = 'O00002'");

        // SELECT O.orderID, A.name, A.mobileNo , B.address, O.dateCreated, O.timeCreated, F.foodName, F.price, OI.quantity, (F.price * OI.quantity) AS Total FROM Order O, OrderItems OI, Food F, Buyer B, Account A WHERE O.orderID=OI.orderID AND OI.foodID=f.foodID AND O.buyerID=B.buyerID B.accountID=A.accountID

        // int i = 1;
        // ArrayList<OrderItem> a =  new ArrayList<>();
        // for(HashMap<String,Object> oi : ois){            
        //     HashMap<String,Object> f = db.readOne(String.format("SELECT * FROM `Food` WHERE foodID='%s'", (String)oi.get("foodID")));
        //     Food food = new Food(f.get("foodID"),f.get("foodName"),f.get("foodDesc"),f.get("imgPath"),f.get("price"),f.get("category"),f.get("shopID"));
        //     a.add(new OrderItem(oi.get("orderID"),oi.get("foodID"),oi.get("quantity"),food));
        //     Map<String,Object> orderItems = oi;
        //     orderItems.put("no", i);
        //     observableList.add(orderItems);
        //     i++;
        // }
        // data.getOrder().setOrderItems(a);
        // colNo.setCellValueFactory(new MapValueFactory<>("no"));
        // colItems.setCellValueFactory(new MapValueFactory<>("foodName"));
        // colQty.setCellValueFactory(new MapValueFactory<>("quantity")); 
        //#endregion
    }    

    @FXML
    void toCart(MouseEvent event) throws IOException {
        gui.toNextScene("View/BuyerCart.fxml");
    }

    @FXML
    void toHome(MouseEvent event) throws IOException {
        gui.toNextScene("View/BuyerHome.fxml");
    }        

    @FXML
    void toBack(MouseEvent event) throws IOException {
        gui.toPrevScene();
    }  
}