package Controller;
import java.io.IOException;

import Cache.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Profile {
    GUI gui = GUI.getInstance();
    DataHolder data = DataHolder.getInstance();

    @FXML private AnchorPane paneProfile;
    @FXML private Label lblWelcome;    
    @FXML private ImageView iconHome,iconCart;

    @FXML
    void toCart(MouseEvent event) throws IOException {
        gui.toNextScene("View/BuyerCart.fxml");
    }

    @FXML
    void toHome(MouseEvent event) throws IOException {
        gui.toNextScene("View/BuyerHome.fxml");
    }
}
