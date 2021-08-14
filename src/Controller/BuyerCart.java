package Controller;
import java.io.IOException;

import Cache.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class BuyerCart {
    GUI gui = GUI.getInstance();
    DataHolder data = DataHolder.getInstance();

    @FXML private AnchorPane paneBuyerCart;
    @FXML private Label lblCart;
    @FXML private ImageView iconOrderHistory,iconHome;    

    @FXML
    void toHome(MouseEvent event) throws IOException {
        gui.toNextScene("View/BuyerHome.fxml");
    }

    @FXML
    void toOrderHistory(MouseEvent event) throws IOException {
        gui.toNextScene("View/BuyerOrderHistory.fxml");
    }
}
