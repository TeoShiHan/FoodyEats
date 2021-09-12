package Controller;

import Cache.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class RiderHome implements Initializable {
    GUI gui = GUI.getInstance();
    DataHolder data = DataHolder.getInstance();

    @FXML
    private AnchorPane paneRiderHome;
    @FXML
    private ImageView iconProfile;
    @FXML
    private Label lblWelcome, linkLogout;
    @FXML
    private HBox toggleSwitch;
    @FXML
    private Pane btnIncomingOrder, btnManageProduct, btnOrderHistory;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lblWelcome.setText("Welcome " + data.getAccount().getUsername());
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
    void toRiderOrders(MouseEvent event) throws IOException {
        gui.toNextScene("View/RiderOrders.fxml");
    }

    @FXML
    void toRiderOrderHistory(MouseEvent event) throws IOException {
        gui.toNextScene("View/RiderOrderHistory.fxml");
    }

    @FXML
    void toRiderManageProduct(MouseEvent event) throws IOException {
        gui.toNextScene("View/RiderVehicleDetails.fxml");
    }

}
