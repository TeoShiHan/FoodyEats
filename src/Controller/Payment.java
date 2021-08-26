package Controller;
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

public class Payment implements Initializable{
    private JDBC db = new JDBC();
    private DataHolder data = DataHolder.getInstance();
    private GUI gui = GUI.getInstance();

    @FXML private AnchorPane paneLogin;
    @FXML private Label lblTotal;
    @FXML private ToggleGroup paymentType;
    @FXML private Button btnCancel,btnProceed;
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
    
    @FXML
    void actionProceedPayment(ActionEvent event) {
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);  //make user unable to press the original stage/window unless close the current stage/window
        newStage.initOwner(gui.getStage());
        WebView wv = new WebView();
        wv.getEngine().load(banks.get(dropdownBank.getValue()));        

        Pane pane = new Pane(wv);

        Scene scene = new Scene(pane);        
        newStage.setScene(scene);
        newStage.setMaximized(false);
        newStage.show();

        // link:https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
        PauseTransition delay = new PauseTransition(Duration.seconds(8));
        delay.setOnFinished(e -> {
            newStage.close();
            try {
                gui.toNextScene("View/BuyerOrderHistory.fxml");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        delay.play();
    }

    @FXML
    void toBack(ActionEvent event) throws IOException {
        gui.toPrevScene();
    }
}
