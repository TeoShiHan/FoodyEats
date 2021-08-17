package Controller;
import Cache.*;
import Classes.*;

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
        loadInformationFields("View/RegisterView/Buyer.fxml");

        // containerInformation
        System.out.println("You want to register as buyer");
    }

    @FXML
    void registerAsRider(ActionEvent event) throws IOException {        
        loadInformationFields("View/RegisterView/Rider.fxml");
        System.out.println("You want to register as rider");
        // Parent parent = (Parent) gui.getStage().getScene().getRoot();
        // gui.getStage().setScene(new Scene(parent));
    }

    @FXML
    void registerAsSeller(ActionEvent event) throws IOException {
        loadInformationFields("View/RegisterView/Seller.fxml");
        System.out.println("You want to register as seller");
    }

    @FXML
    void toRegsiter(MouseEvent event) throws IOException {
        gui.toNextScene("View/Login.fxml");
    }

    void loadInformationFields(String filePath) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/"+filePath));
        VBox vbox = (VBox)fxmlLoader.load();                        

        containerInformation.getChildren().setAll(vbox.getChildren());                
    }
}
