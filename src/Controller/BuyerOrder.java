package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
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

public class BuyerOrder implements Initializable {    
    Stage stage;
    Parent root;
    Scene scene;
    
    @FXML
    private AnchorPane paneBuyerOrder;

    @FXML
    private ImageView iconProfile;

    @FXML
    private Label linkLogout;

    @FXML
    private ImageView iconHome;

    @FXML
    private ImageView iconCart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }    

    @FXML
    void toBuyerHome(MouseEvent event) throws IOException {
        stage = (Stage) paneBuyerOrder.getScene().getWindow();
        // System.out.println(stage.getUserData());

        root = FXMLLoader.load(getClass().getClassLoader().getResource("View/BuyerHome.fxml"));                
        stage.setScene(new Scene(root));

        // reference link:https://www.youtube.com/watch?v=ZJtXD03-D2I
        // AnchorPane paneBuyerHome = FXMLLoader.load(getClass().getResource("../View/BuyerHome.fxml"));
        // paneBuyerOrder.getChildren().removeAll();
        // paneBuyerOrder.getChildren().setAll(paneBuyerHome);
    }

    @FXML
    void toCart(MouseEvent event) {

    }

    @FXML
    void toHome(MouseEvent event) {

    }

    @FXML
    void toLogin(MouseEvent event) {

    }        
}
