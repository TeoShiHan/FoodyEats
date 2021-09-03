package Controller.Popup;
import Cache.*;
import Classes.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CheckoutPayment implements Initializable{
    private JDBC db = new JDBC();
    private DataHolder data = DataHolder.getInstance();
    private GUI gui = GUI.getInstance();

    @FXML private AnchorPane paneLogin;
    @FXML private Label lblTotal;
    @FXML private ToggleGroup paymentType;
    @FXML private Button btnYes,btnNo;
    @FXML private ComboBox<String> dropdownBank = new ComboBox<>();    
    private HashMap<String,String> banks = new HashMap<String,String>(){{
        put("Citi Bank", "https://www.citibank.com.my/MYGCB/JSO/username/signon/flow.action?JFP_TOKEN=SBYTWE4R");
        put("RHB Bank", "https://logon.rhb.com.my/default.htm");
        put("Hong Leong Bank", "https://s.hongleongconnect.my/rib/app/fo/login?web=1");
        put("CIMB Bank", "https://www.cimbclicks.com.my/clicks/#/");
        put("Public Bank", "https://www2.pbebank.com/myIBK/apppbb/servlet/BxxxServlet?RDOName=BxxxAuth&MethodName=login");
        put("Maybank", "https://www.maybank2u.com.my/home/m2u/common/login.do");
    }};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        lblTotal.setText(String.format("%.2f",1000.00));
        ObservableList<String> options = FXCollections.observableArrayList(banks.keySet());
        dropdownBank.getItems().addAll(options);        
    }       

    public Button getBtnYes() {
        return btnYes;
    }

    public void setBtnYes(Button btnYes) {
        this.btnYes = btnYes;
    }

    public Button getBtnNo() {
        return btnNo;
    }

    public void setBtnNo(Button btnNo) {
        this.btnNo = btnNo;
    }

    public Label getLblTotal() {
        return lblTotal;
    }

    public void setLblTotal(Label lblTotal) {
        this.lblTotal = lblTotal;
    }

    public ToggleGroup getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(ToggleGroup paymentType) {
        this.paymentType = paymentType;
    }

    public ComboBox<String> getDropdownBank() {
        return dropdownBank;
    }

    public void setDropdownBank(ComboBox<String> dropdownBank) {
        this.dropdownBank = dropdownBank;
    }

    public HashMap<String, String> getBanks() {
        return banks;
    }

    public void setBanks(HashMap<String, String> banks) {
        this.banks = banks;
    }

    
}
