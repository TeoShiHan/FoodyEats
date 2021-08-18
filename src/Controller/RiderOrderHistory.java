package Controller;
import Cache.*;
import Classes.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RiderOrderHistory implements Initializable {
    GUI gui = GUI.getInstance();
    DataHolder data = DataHolder.getInstance();
    @FXML private AnchorPane paneRiderOrderHistory;
    @FXML private Label lblWelcome;
    @FXML private ImageView iconCart,iconHome;
    @FXML private TableView<Order> tableView;
    @FXML private TableColumn<Order,Object> colOrderId,colAmount,colDate;    
    @FXML private TableColumn<Order,Order> colAction;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub        
        Order order1 = new Order("O1234","Pending","B1233","","S1231","P1245","R1233",LocalDate.now());
        Order order2 = new Order("O2234","Accepted","B7486","","S1111","P8975","R2585",LocalDate.now());
        Order order3 = new Order("O7234","Completed","B7746","R1254","S1255","P8907","R3000",LocalDate.now());
        ObservableList<Order> observableList = FXCollections.observableArrayList(order1,order2,order3);
        
        tableView.setItems(observableList);

        //to assign which property/attribute of the class to the table column
        colOrderId.setCellValueFactory(new PropertyValueFactory<Order,Object>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<Order,Object>("dateCreated"));
        colAmount.setCellValueFactory(new PropertyValueFactory<Order,Object>("amount")); // havent solved
        colAction.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));        
        // to set what to display in each table cell, instead string only, because by default it display string (https://stackoverflow.com/questions/32282230/fxml-javafx-8-tableview-make-a-delete-button-in-each-row-and-delete-the-row-a)        
        colAction.setCellFactory(param -> new TableCell<Order,Order>(){
            Button btnViewDetails = new Button("View Details");            
            @Override
            public void updateItem(Order order, boolean empty){
                super.updateItem(order, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                } 
                setGraphic(btnViewDetails);
                btnViewDetails.setOnAction(e->{          
                    try {
                        gui.toNextScene("View/RiderOrderDetails.fxml");
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    System.out.println("id"+order.getStatus());                    
                });                                       
            }                     
        });     
        // colAction.setCellValueFactory(new PropertyValueFactory<Order,Button>("button"));        

        tableView.setItems(observableList);        
        // tableView.getColumns().addAll(colOrderId,colDate,colStatus,colAction); //not needed
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
