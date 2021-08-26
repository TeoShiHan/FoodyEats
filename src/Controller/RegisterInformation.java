package Controller;
import Cache.*;
import Classes.*;
import Classes.Shop;
import Controller.Register.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class RegisterInformation implements Initializable{
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();
    
    private FXMLLoader fxmlLoader;        
    private RegBuyer buyerRegisterController;
    private RegRider riderRegisterController;
    private RegSeller sellerRegisterController;
    private RegShop shopRegisterController;
    HashMap<String,Object> info = new HashMap<>();

    @FXML private AnchorPane paneRegisterInformation;
    @FXML private RadioButton radioBtnBuyer,radioBtnSeller,radioBtnRider;
    @FXML private ToggleGroup accountType;
    @FXML private VBox containerInformation;
    // @FXML private TextField inputShopName,inputEmail,inputContactNo,inputShopAddr,inputBankAccNo,inputLisenceNo;
    // @FXML private Button imageChooser;
    @FXML private Button btnContinue;
    @FXML private Label linkToLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            registerAsBuyer(new ActionEvent());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }        

    @FXML
    void registerAsBuyer(ActionEvent event) throws IOException {        
        loadInformationFields("View/RegisterView/RegBuyer.fxml");        
        this.buyerRegisterController = this.fxmlLoader.getController();
        btnContinue.setOnAction(evnt -> {
            try {
                if(buyerRegisterController.isFilled()){
                    // data.addObjectHolder("accountType", "Buyer");
                    buyerRegisterController.getInfo();
                    toRegisterAccount();
                    gui.alertInProgress();
                }else{
                    gui.informationPopup("Attention", "Please fill in all the field!");                    
                }  
                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }      
        });                
    }

    @FXML
    void registerAsRider(ActionEvent event) throws IOException {                
        loadInformationFields("View/RegisterView/RegRider.fxml");   
        this.riderRegisterController = this.fxmlLoader.getController();
        btnContinue.setOnAction(evnt -> {
            try {
                if(riderRegisterController.isFilled()){
                    // data.addObjectHolder("accountType", "Rider");
                    riderRegisterController.getInfo();                    
                    toRegisterAccount();
                    gui.alertInProgress();
                }else{
                    gui.informationPopup("Attention", "Please fill in all the field!");
                }                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }      
        });         
    }

    @FXML
    void registerAsSeller(ActionEvent event) throws IOException {          
        loadInformationFields("View/RegisterView/RegSeller.fxml");
        this.sellerRegisterController = this.fxmlLoader.getController();        
        btnContinue.setOnAction(evnt -> {
            try {          
                if(sellerRegisterController.isFilled()){
                    // data.addObjectHolder("accountType", "Seller");
                    sellerRegisterController.getInfo();
                    gui.alertInProgress();
                    loadInformationFields("View/RegisterView/RegShop.fxml");
                    this.shopRegisterController = this.fxmlLoader.getController();                    
                    btnContinue.setOnAction(ev -> {                    
                        try {
                            if(shopRegisterController.isFilled()){                                
                                shopRegisterController.getInfo();                    
                                toRegisterAccount();                                
                            }else{
                                gui.informationPopup("Attention", "Please fill in all the field!");
                            }                             
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }                    
                    });
                }else{
                    gui.informationPopup("Attention", "Please fill in all the field!");
                }                       
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }            
        });        
    }        

    @FXML
    void toLogin(MouseEvent event) throws IOException {
        gui.toNextScene("View/Login.fxml");        
    }

    public void toRegisterAccount() throws IOException{        
        gui.toNextScene("View/RegisterAccount.fxml");
    }

    public void loadInformationFields(String filePath) throws IOException{
        this.fxmlLoader = new FXMLLoader();
        this.fxmlLoader.setLocation(getClass().getResource("/"+filePath));        
        VBox vbox = (VBox)fxmlLoader.load();                                
        containerInformation.getChildren().setAll(vbox.getChildren());                                
    }
}
