package Controller;
import Cache.*;
import Classes.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BuyerOrderDetails implements Initializable {    
    GUI gui = GUI.getInstance();
    DataHolder data = DataHolder.getInstance();
    Stage stage;
    Parent root;
    Scene scene;
    
    @FXML private AnchorPane paneBuyerOrder;
    @FXML private Label linkLogout;
    @FXML private ImageView iconProfile,iconHome,iconCart;    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub          
        
    }    

    @FXML
    void toCart(MouseEvent event) throws IOException {
        gui.toNextScene("View/BuyerCart.fxml");
    }

    @FXML
    void toHome(MouseEvent event) throws IOException {
        gui.toNextScene("View/BuyerHome.fxml");
    }        

        // @FXML
    // void toBuyerHome(MouseEvent event) throws IOException {
    //     stage = (Stage) paneBuyerOrder.getScene().getWindow();
    //     // System.out.println(stage.getUserData());

    //     root = FXMLLoader.load(getClass().getClassLoader().getResource("View/BuyerHome.fxml"));                
    //     stage.setScene(new Scene(root));        
    // }
}
