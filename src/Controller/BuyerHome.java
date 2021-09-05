package Controller;
import Classes.*;
import Cache.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuyerHome implements Initializable{
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();        

    @FXML private AnchorPane paneBuyerHome;
    @FXML private ImageView iconProfile;        
    @FXML private Label lblWelcome,linkLogout;
    @FXML private ImageView iconCart;   

    @Override
    public void initialize(URL location, ResourceBundle resources) {                
        lblWelcome.setText("Welcome "+data.getAccount().getUsername());
    }            

    @FXML
    void toProfile(MouseEvent event) throws IOException {
        gui.toNextScene("View/Profile.fxml");        
    }    

    @FXML
    void logout(MouseEvent event) throws IOException {
        gui.toNextScene("View/Login.fxml");
        data.clear();
    }    

    @FXML
    void toBack(MouseEvent event) throws IOException {
        gui.toPrevScene();
    }  

    @FXML
    void toBuyerOrderHistory(MouseEvent event) throws IOException {
        gui.toNextScene("View/BuyerOrderHistory.fxml");          
    }   

    @FXML
    void toCart(MouseEvent event) throws IOException {
        gui.toNextScene("View/BuyerCart.fxml");
    }    
}
