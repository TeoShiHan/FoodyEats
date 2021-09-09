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

public class AdminRiderAccountApproval implements Initializable {    
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();
    private String currentFXMLPath = "View/AdminRiderAccountApproval.fxml";

    @FXML private AnchorPane paneRiderOrderHistory;
    @FXML private Label lblWelcome;
    @FXML private ImageView iconCart,iconHome;
    @FXML private TableView<Rider> tableView;
    @FXML private TableColumn<Rider,Object> colAccountId,colName,colEmail,colMobileNo,colAccountType;
    @FXML private TableColumn<Rider,Rider> colAction;

    @Override
    public void initialize(URL location, ResourceBundle resources) {                
        data.setRiders(new ArrayList<>());
        ArrayList<HashMap<String,Object>> as = db.readAll(String.format("SELECT r.*,v.*,a.*,v.type AS vehicleType,a.type AS accType FROM `Account` a, `Rider` r, `Vehicle` v WHERE a.type='Rider' AND a.accountID=r.accountID AND r.vehicleID=v.vehicleID AND r.status=0"));            
        for(HashMap<String,Object> a : as){
            Vehicle vehicle = new Vehicle(a.get("vehicleID"), a.get("vehicleType"), a.get("plateNo"), a.get("brand"), a.get("model"), a.get("color"));
            Rider rider = new Rider(a.get("accountID"), a.get("username"), a.get("password"), a.get("name"), a.get("email"), a.get("mobileNo"), a.get("accType"), a.get("regDate"), a.get("riderID"), a.get("vehicleID"),a.get("status"));
            rider.setVehicle(vehicle);
            data.getRiders().add(rider);
        }

        ObservableList<Rider> observableList = FXCollections.observableArrayList(data.getRiders());
        
        tableView.setItems(observableList);

        //to assign which property/attribute of the class to the table column
        colAccountId.setCellValueFactory(new PropertyValueFactory<Rider,Object>("accountID"));
        colName.setCellValueFactory(new PropertyValueFactory<Rider,Object>("name"));        
        colEmail.setCellValueFactory(new PropertyValueFactory<Rider,Object>("email")); 
        colMobileNo.setCellValueFactory(new PropertyValueFactory<Rider,Object>("mobileNo")); 
        colAccountType.setCellValueFactory(new PropertyValueFactory<Rider,Object>("accType")); 
        colAction.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));        
        // to set what to display in each table cell, instead string only, because by default it display string (https://stackoverflow.com/questions/32282230/fxml-javafx-8-tableview-make-a-delete-button-in-each-row-and-delete-the-row-a)        
        colAction.setCellFactory(param -> new TableCell<Rider,Rider>(){            
            Button btnApprove = new Button("Approve");
            Button btnViewDetails = new Button("View Details");
            @Override
            public void updateItem(Rider rider, boolean empty){
                super.updateItem(rider, empty);
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
                    data.getAdmin().verifyRider(rider.getRiderID());
                    tableView.getItems().remove(rider);
                    tableView.refresh();
                });                
                btnViewDetails.setOnAction(e->{          
                        data.setRider(rider);
                        gui.toNextScene("View/AdminRiderAccountDetails.fxml");                          
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
