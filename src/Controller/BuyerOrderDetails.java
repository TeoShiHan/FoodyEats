package Controller;
import Cache.*;
import Classes.*;
import Controller.Popup.WriteReview;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Path;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BuyerOrderDetails implements Initializable {    
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();    
    private DataHolder data = DataHolder.getInstance();    
    
    @FXML private AnchorPane paneBuyerOrder;    
    @FXML private Label linkLogout,lblOrderDetails,lblShopName,lblShopTel,lblShopAddress,lblTotalAmount,lblDateCreated,lblTimeCreated,lblRiderName,lblRiderMobileNo,lblRiderVehicleDetails;
    @FXML private ImageView iconProfile,iconHome,iconCart;            
    @FXML private Button btnWriteReview,btnCancel;        
    @FXML private TableView<OrderItem> tableView;
    @FXML private TableColumn<OrderItem,Number> colNo;
    @FXML private TableColumn<OrderItem,Integer> colQty;
    @FXML private TableColumn<OrderItem,String> colItems;
    private String currentFXMLPath = "/View/BuyerOrderDetails.fxml";

    private BuyerOrder order;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        order = new BuyerOrder(data.getOrder());            
        data.setOrder(order);
        if(order.getOrderItems()==null){            
            order.loadAllDetails();            
        }
        
        lblOrderDetails.setText("Order #"+order.getOrderID());
        lblDateCreated.setText(order.getDateCreated().toString());
        lblTimeCreated.setText(order.getTimeCreated().toString());
        lblShopName.setText(order.getShop().getName());
        lblShopAddress.setText(order.getShop().getAddress());
        lblShopTel.setText(order.getShop().getTel());
        if(data.getRider()!=null && !data.getRider().getName().isEmpty()){
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
                
        btnWriteReview.setDisable(data.getOrder().getStatus().equals("Completed")?false:true);
        btnCancel.setDisable(data.getOrder().getStatus().equals("Pending")?false:true);

        btnWriteReview.setOnAction(event -> {
            Stage myDialog = new Stage();
            gui.alertInProgress(myDialog);
            myDialog.initModality(Modality.APPLICATION_MODAL);  //make user unable to press the original stage/window unless close the current stage/window
            myDialog.initOwner(gui.getStage());
            
            WriteReview controller = new WriteReview();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Popup/WriteReview.fxml"));
            loader.setController(controller);                        
            Scene dialogScene = null;
            try {
                dialogScene = new Scene((Parent)loader.load());                        
            } catch (IOException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            
            controller.getBtnYes().setOnAction(ev->{                                                      
                myDialog.getScene().getRoot().setDisable(true);
                myDialog.getScene().setCursor(Cursor.WAIT);
                Task<Void> task = new Task<Void>() {
                    @Override
                    public Void call() throws IOException, SQLException {
                        Review newReview = new Review(controller.getSpinnerRating().getValue(), controller.getInputComment().getText(), data.getOrder().getOrderID(), data.getOrder().getShopID());
                        newReview.create();
                        return null ;
                    }
                };
                task.setOnSucceeded(e -> {
                    myDialog.close();
                    try {
                        gui.refreshScene(currentFXMLPath);
                        gui.notAlertInProgress(myDialog);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();                        
                    }                    
                });
                new Thread(task).start();                        
            });
            
            controller.getBtnNo().setOnAction(e->{
                myDialog.close();
                gui.notAlertInProgress(myDialog);
            });  
                    
            myDialog.setScene(dialogScene);
            myDialog.setMaximized(false);
            myDialog.show();
        });

        btnCancel.setOnAction(event->{
            try {
                gui.confirmationPopup("Cancel Order", "Are you sure you want to cancel the order? Once you cancel, it will be gone", passback->{
                    if(passback){
                        gui.miniPopup(String.format("RM%.2f has been returned to your Bank Account",data.getOrder().calcTotalAmount()));
                        Task<Void> task = new Task<Void>() {
                            @Override
                            public Void call() throws IOException {  
                                System.out.println(data.getOrders());
                                data.getOrders().remove(data.getOrder());
                                System.out.println(data.getOrders());
                                Order.setOrdersHaveChange(true);
                                data.getOrder().delete();                                
                                return null;
                            }
                        };                        
                        new Thread(task).start();    
                        try {
                            gui.toNextScene("View/BuyerOrderHistory.fxml");
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }            
        });        

        //#region direct select data from database (old method)
        // HashMap<String,Object> buyer = db.readOne(String.format("SELECT * FROM `Buyer` WHERE buyerID='%s'", data.getOrder().getBuyerId()));
        // lblShopAddress.setText((String)buyer.get("address"));  
        // HashMap<String,Object> account = db.readOne(String.format("SELECT * FROM `Account` WHERE accountID='%s'", (String)buyer.get("accountID")));
        // lblShopPhoneNo.setText((String)account.get("mobileNo"));
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