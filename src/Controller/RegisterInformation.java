package Controller;
import Cache.*;
import Classes.*;
import Classes.Shop;
import Controller.Register.*;
import Controller.Register.Account;
import Controller.Register.Buyer;
import Controller.Register.Rider;
import Controller.Register.Seller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class RegisterInformation implements Initializable{
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();

    private Account accountRegisterController;
    private Buyer buyerRegisterController;
    private Rider riderRegisterController;
    private Seller sellerRegisterController;
    private Shop shopRegisterController;

    FXMLLoader fxmlLoader;    

    @FXML private AnchorPane paneRegisterInformation;
    @FXML private RadioButton radioBtnBuyer,radioBtnSeller,radioBtnRider;
    @FXML private ToggleGroup accountType;
    @FXML private VBox containerInformation;
    @FXML private TextField inputShopName,inputEmail,inputContactNo,inputShopAddr,inputBankAccNo,inputLisenceNo;
    @FXML private Button imageChooser;
    @FXML private Button btnRegister;
    @FXML private Label linkToLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            loadInformationFields("View/RegisterView/Buyer.fxml");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void actionRegister(ActionEvent event) {
        
    }
    
    @FXML
    void actionImageChooser(ActionEvent event) {
    }

    @FXML
    void registerAsBuyer(ActionEvent event) throws IOException {
        btnRegister.setText("Register");
        loadInformationFields("View/RegisterView/Buyer.fxml");        
        this.buyerRegisterController = this.fxmlLoader.getController();
        // this.buyerRegisterController.
        System.out.println("You want to register as buyer");
    }

    @FXML
    void registerAsRider(ActionEvent event) throws IOException {        
        btnRegister.setText("Register");
        loadInformationFields("View/RegisterView/Rider.fxml");   
        this.riderRegisterController = this.fxmlLoader.getController();
        // this.riderRegisterController.     
        System.out.println("You want to register as rider");
        // Parent parent = (Parent) gui.getStage().getScene().getRoot();
        // gui.getStage().setScene(new Scene(parent));
    }

    @FXML
    void registerAsSeller(ActionEvent event) throws IOException {
        btnRegister.setText("Next");
        btnRegister.setOnAction(e -> {
            System.out.println("hello");
        });
        loadInformationFields("View/RegisterView/Seller.fxml");
        System.out.println("You want to register as seller");
    }

    @FXML
    void toRegsiter(MouseEvent event) throws IOException {
        gui.toNextScene("View/Login.fxml");        
    }

    public void loadInformationFields(String filePath) throws IOException{
        this.fxmlLoader = new FXMLLoader();
        this.fxmlLoader.setLocation(getClass().getResource("/"+filePath));        
        VBox vbox = (VBox)fxmlLoader.load();                                
        containerInformation.getChildren().setAll(vbox.getChildren());                                
    }
}
