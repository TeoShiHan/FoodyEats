package Controller;

import Cache.*;
import Classes.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RiderOrderDetails implements Initializable {
    JDBC db = new JDBC();
    GUI gui = GUI.getInstance();
    DataHolder data = DataHolder.getInstance();
    private String currentFXMLPath = "View/RiderOrderDetails.fxml";

    @FXML
    private AnchorPane paneRiderOrderDetails;
    @FXML
    private Label linkLogout, lblOrderDetails, lblBuyerName, lblBuyerMobileNo, lblBuyerAddress, lblDateCreated,
            lblTimeCreated, lblShopName, lblShopTelNo, lblShopAddress, lblDeliveryFee;
    @FXML
    private ImageView iconProfile, iconHome, iconCart, iconBack;
    @FXML
    private Button btnAction;
    @FXML
    private TableView<OrderItem> tableView;
    @FXML
    private TableColumn<OrderItem, Number> colNo;
    @FXML
    private TableColumn<OrderItem, Integer> colQty;
    @FXML
    private TableColumn<OrderItem, String> colItems;

    private RiderOrder order;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        order = new RiderOrder(data.getOrder());
        data.setOrder(order);
        if (order.getOrderItems() == null) {
            order.loadAllDetails();
        }

        lblOrderDetails.setText("Order #" + order.getOrderID());
        lblDateCreated.setText(order.getDateCreated().toString());
        lblTimeCreated.setText(order.getTimeCreated().toString());
        lblBuyerName.setText(order.getBuyer().getName());
        lblBuyerAddress.setText(order.getBuyer().getAddress());
        lblBuyerMobileNo.setText(order.getBuyer().getMobileNo());
        lblShopName.setText(order.getShop().getName());
        lblShopAddress.setText(order.getShop().getAddress());
        lblShopTelNo.setText(order.getShop().getTel());
        lblDeliveryFee.setText(String.format("RM %.2f", order.getShop().getDeliveryFee()));

        // https://stackoverflow.com/questions/36629522/convert-arraylist-to-observable-list-for-javafx-program
        ObservableList<OrderItem> observableList = FXCollections.observableArrayList(order.getOrderItems());

        tableView.setItems(observableList);

        // to assign which property/attribute of the class to the table column
        // https://stackoverflow.com/questions/16384879/auto-numbered-table-rows-javafx,
        // for the next line (line 75)
        colNo.setCellValueFactory(
                dt -> new ReadOnlyObjectWrapper<Number>(tableView.getItems().indexOf(dt.getValue()) + 1));
        colItems.setCellValueFactory(dt -> new SimpleStringProperty(dt.getValue().getFood().getName()));
        // https://stackoverflow.com/questions/14413040/converting-integer-to-observablevalueinteger-in-javafx/14413339,
        // for the next line (line 80)
        colQty.setCellValueFactory(dt -> new SimpleIntegerProperty(dt.getValue().getQuantity()).asObject());

        if (order.getStatus().equals("Seller Accepted")) {
            btnAction.setOnAction(e -> {
                order.setStatus("Rider Accepted");
                data.getRider().acceptOrder(order.getOrderID());
                try {
                    gui.refreshScene(currentFXMLPath);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });
        } else if (order.getStatus().equals("Seller Ready") || order.getStatus().equals("Rider Accepted")) {
            btnAction.setText("Collect");
            if (order.getStatus().equals("Rider Accepted")) {
                btnAction.setDisable(true);
            }
            btnAction.setOnAction(e -> {
                order.setStatus("Rider Collected");
                data.getRider().collectOrder(order.getOrderID());
                try {
                    gui.refreshScene(currentFXMLPath);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });
        } else if (order.getStatus().equals("Rider Collected")) {
            btnAction.setText("Complete");
            btnAction.setOnAction(e -> {
                order.setStatus("Completed");
                data.getRider().deliveredOrder(order.getOrderID());
                try {
                    gui.refreshScene(currentFXMLPath);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });
        } else {
            btnAction.setText("Completed");
            btnAction.setDisable(true);
        }
    }

    @FXML
    void toHome(MouseEvent event) throws IOException {
        gui.toNextScene("View/RiderHome.fxml");
    }

    @FXML
    void toBack(MouseEvent event) throws IOException {
        gui.toPrevScene();
    }
}