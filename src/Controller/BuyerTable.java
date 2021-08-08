package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;

import Classes.JDBC;
import Classes.account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class BuyerTable implements Initializable{

    @FXML private AnchorPane Anchor;
    @FXML private AnchorPane btnSet;
    @FXML private Button addBtn;
    @FXML private Button deleteBtn; 
    @FXML private Button updateBtn;

    //INSIDE <> IS "RELATED CLASS"
    @FXML private TableView<account> buyerTable;
    @FXML private TableColumn<account, String> accountID;
    @FXML private TableColumn<account, String> accountType;

    public static ObservableList<account> accountObjList = FXCollections.observableArrayList();

    ArrayList<String> accID = new ArrayList<String>(); // Create an ArrayList object
    ArrayList<String> accType = new ArrayList<String>(); // Create an ArrayList object

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        JDBC accTable = new JDBC("SELECT * FROM Account");
        accTable.copyStringcol("accType", accType);
        accTable.copyStringcol("accountID", accID);
        JDBC.closeConnection();

        for(int i = 0; i < accID.size() ; i++){{
            System.out.println(accID.get(i));
            System.out.println(accType.get(i));
            accountObjList.add(new account(accID.get(i), accType.get(i)));
        }}

        accountType.setCellValueFactory(new PropertyValueFactory<account, String>("accountType"));
        accountID.setCellValueFactory(new PropertyValueFactory<account, String>("accountID"));
        
        buyerTable.setItems(accountObjList);

    }
}


