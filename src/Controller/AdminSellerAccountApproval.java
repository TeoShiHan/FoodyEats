package Controller;
import Cache.*;
import Classes.*;

import java.io.IOException;
import java.net.URL;
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

public class AdminSellerAccountApproval implements Initializable {    
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();
    private String currentFXMLPath = "View/AdminSellerAccountApproval.fxml";

    @FXML private AnchorPane paneRiderOrderHistory;
    @FXML private Label lblWelcome;
    @FXML private ImageView iconCart,iconHome;
    @FXML private TableView<Seller> tableView;
    @FXML private TableColumn<Seller,Object> colAccountId,colName,colEmail,colMobileNo,colAccountType;
    @FXML private TableColumn<Seller,Seller> colAction;

    @Override
    public void initialize(URL location, ResourceBundle resources) {                
        data.setSellers(new ArrayList<>());
        ArrayList<HashMap<String,Object>> as = db.readAll(String.format("SELECT s.address AS sellerAddress,sh.address AS shopAddress,s.status AS sellerStatus,sh.status AS shopStatus,a.*,s.*,sh.* FROM `Account` a, `Seller` s, `Shop` sh WHERE a.type='Seller' AND a.accountID=s.accountID AND s.shopID=sh.shopID AND s.status=0"));
        for(HashMap<String,Object> a : as){
            Shop shop = new Shop(a.get("shopID"), a.get("shopName"), a.get("address"), a.get("tel"), a.get("startHour"), a.get("endHour"), a.get("status"), a.get("dateCreated"), a.get("deliveryFee"), a.get("imgPath"));
            Seller seller = new Seller(a.get("accountID"), a.get("username"), a.get("password"), a.get("name"), a.get("email"), a.get("mobileNo"), a.get("type") , a.get("regDate"), a.get("sellerID"), a.get("address"), a.get("NRIC"), a.get("licenseNumber"), a.get("bankAcc"), a.get("shopID"), a.get("status"));        
            seller.setShop(shop);
            data.getSellers().add(seller);            
        }

        ObservableList<Seller> observableList = FXCollections.observableArrayList(data.getSellers());
        
        tableView.setItems(observableList);

        //to assign which property/attribute of the class to the table column
        colAccountId.setCellValueFactory(new PropertyValueFactory<Seller,Object>("accountID"));
        colName.setCellValueFactory(new PropertyValueFactory<Seller,Object>("name"));        
        colEmail.setCellValueFactory(new PropertyValueFactory<Seller,Object>("email")); 
        colMobileNo.setCellValueFactory(new PropertyValueFactory<Seller,Object>("mobileNo")); 
        colAccountType.setCellValueFactory(new PropertyValueFactory<Seller,Object>("accType")); 
        colAction.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));        
        // to set what to display in each table cell, instead string only, because by default it display string (https://stackoverflow.com/questions/32282230/fxml-javafx-8-tableview-make-a-delete-button-in-each-row-and-delete-the-row-a)        
        colAction.setCellFactory(param -> new TableCell<Seller,Seller>(){            
            Button btnApprove = new Button("Approve");
            Button btnViewDetails = new Button("View Details");
            @Override
            public void updateItem(Seller seller, boolean empty){
                super.updateItem(seller, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                } 
                HBox pane = new HBox();
                pane.setSpacing(20);                
                pane.setAlignment(Pos.CENTER);                                                    
                pane.getChildren().add(btnViewDetails);
                setGraphic(pane);                
                btnApprove.setOnAction(e->{  
                    data.getAdmin().verifySeller(seller.getSellerID());
                    tableView.getItems().remove(seller);
                    tableView.refresh();
                });                
                btnViewDetails.setOnAction(e->{          
                    try {
                        data.setSeller(seller);
                        gui.toNextScene("View/AdminSellerAccountDetails.fxml");
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
        gui.toNextScene("View/AdminHome.fxml");
    }
}
