package Controller;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import Cache.*;
import Classes.*;
import Controller.Popup.CheckoutPayment;
import javafx.animation.PauseTransition;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BuyerCart implements Initializable {

    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();

    @FXML
    private AnchorPane paneBuyerCart;
    @FXML
    private Label lblCart;
    @FXML
    private ImageView iconOrderHistory, iconHome;
    @FXML
    private Button btnCheckout;
    @FXML
    private TableView<CartItem> tableView;
    @FXML
    private TableColumn<CartItem, String> colImage, colName;
    @FXML
    private TableColumn<CartItem, Number> colNo;
    @FXML
    private TableColumn<CartItem, Double> colPrice;
    @FXML
    private TableColumn<CartItem, CartItem> colAction, colQuantity;
    private boolean anythingChanged = false;
    private String currentFXMLPath = "View/BuyerCart.fxml";

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        data.getBuyer().getCart().loadCartItems();
        data.setCartItems(data.getBuyer().getCart().getCartItems());

        if (data.getCartItems().size() == 0) {
            btnCheckout.setDisable(true);
        }

        ObservableList<CartItem> observableList = FXCollections.observableArrayList(data.getCartItems());

        tableView.setItems(observableList);

        colNo.setCellValueFactory(
                dt -> new ReadOnlyObjectWrapper<Number>(tableView.getItems().indexOf(dt.getValue()) + 1));
        colImage.setCellFactory(param -> {
            // Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(150);
            imageview.setFitWidth(150);

            // Set up the Table
            TableCell<CartItem, String> cell = new TableCell<CartItem, String>() {
                public void updateItem(String imgPath, boolean empty) {
                    if (imgPath != null) {
                        imageview.setImage(new Image(getClass().getResourceAsStream(imgPath)));
                    }
                }
            };
            // Attach the imageview to the cell
            cell.setGraphic(imageview);
            return cell;
        });
        colImage.setCellValueFactory(dt -> new SimpleStringProperty(dt.getValue().getFood().getImgPath()));
        colName.setCellValueFactory(dt -> new SimpleStringProperty(dt.getValue().getFood().getName()));
        colQuantity.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        colQuantity.setCellFactory(param -> new TableCell<CartItem, CartItem>() {
            Button btnDecrease = new Button("-");
            Button btnIncrease = new Button("+");
            SimpleIntegerProperty quantity = new SimpleIntegerProperty();
            Label lblQuantity = new Label();

            @Override
            public void updateItem(CartItem cartItem, boolean empty) {
                super.updateItem(cartItem, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                cartItem.setChanged(false);

                final int initQuantity = cartItem.getQuantity();
                quantity.setValue(initQuantity);
                lblQuantity.textProperty().bind(quantity.asString());

                HBox pane = new HBox(btnDecrease, lblQuantity, btnIncrease);
                pane.setSpacing(20);
                pane.setAlignment(Pos.CENTER);
                setGraphic(pane);

                btnDecrease.setOnAction(event -> {
                    if (quantity.get() > 1) {
                        int newQuantity = quantity.get() - 1;
                        quantity.set(newQuantity);
                        cartItem.setQuantity(newQuantity);
                        if (newQuantity == initQuantity) {
                            cartItem.setChanged(false);
                            anythingChanged = false;
                        } else {
                            cartItem.setChanged(true);
                            anythingChanged = true;
                        }
                    }
                });
                btnIncrease.setOnAction(event -> {
                    if (quantity.get() < 100) {
                        int newQuantity = quantity.get() + 1;
                        quantity.set(newQuantity);
                        cartItem.setQuantity(newQuantity);
                        if (newQuantity == initQuantity) {
                            cartItem.setChanged(false);
                            anythingChanged = false;
                        } else {
                            cartItem.setChanged(true);
                            anythingChanged = true;
                        }
                    } else {
                        gui.miniPopup("You have reached the limit");
                    }
                });
            }
        });
        colPrice.setCellValueFactory(dt -> new SimpleDoubleProperty(dt.getValue().getFood().getPrice()).asObject());
        colAction.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        // to set what to display in each table cell, instead string only, because by
        // default it display string
        // (https://stackoverflow.com/questions/32282230/fxml-javafx-8-tableview-make-a-delete-button-in-each-row-and-delete-the-row-a)
        colAction.setCellFactory(param -> new TableCell<CartItem, CartItem>() {
            ImageView deleteImage = new ImageView(new Image(getClass().getResourceAsStream("/Images/deleteIcon.png")));
            Button btnDelete = new Button();

            @Override
            public void updateItem(CartItem cartItem, boolean empty) {
                super.updateItem(cartItem, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                deleteImage.setFitWidth(50);
                deleteImage.setFitHeight(50);
                btnDelete.setGraphic(deleteImage);
                setGraphic(btnDelete);

                btnDelete.setOnAction(event -> {
                    data.getBuyer().getCart().setShopID("");
                    data.getCartItems().remove(cartItem);
                    tableView.getItems().remove(cartItem);
                    Task<Void> task = new Task<Void>() {
                        @Override
                        public Void call() throws IOException {
                            cartItem.delete();
                            return null;
                        }
                    };
                    task.setOnSucceeded(e -> {
                        try {
                            gui.refreshScene(currentFXMLPath);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    });
                    new Thread(task).start();
                });
            }
        });
    }

    @FXML
    void actionCheckout(ActionEvent event) throws IOException {
        Stage myDialog = new Stage();
        gui.alertInProgress(myDialog);
        myDialog.initModality(Modality.APPLICATION_MODAL); // make user unable to press the original stage/window unless
                                                           // close the current stage/window
        myDialog.initOwner(gui.getStage());

        CheckoutPayment controller = new CheckoutPayment();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Popup/CheckoutPayment.fxml"));
        loader.setController(controller);
        Scene dialogScene = null;
        try {
            dialogScene = new Scene((Parent) loader.load());
        } catch (IOException e2) {
            e2.printStackTrace();
        }

        controller.getBtnYes().setOnAction(ev -> {
            if (controller.isSelectedBank()) {
                Stage myDialog2 = new Stage();
                myDialog2.initModality(Modality.APPLICATION_MODAL); // make user unable to press the original
                                                                    // stage/window unless close the current
                                                                    // stage/window
                myDialog2.initOwner(gui.getStage());

                WebView wv = new WebView();
                wv.getEngine().load(controller.getBanks().get(controller.getDropdownBank().getValue()));

                Pane pane = new Pane(wv);

                myDialog2.setScene(new Scene(pane));
                myDialog2.setMaximized(false);
                myDialog2.show();

                data.setPayment(new Payment(((RadioButton) controller.getPaymentType().getSelectedToggle()).getText()));
                Order order = new Order("Pending", LocalDate.now(), LocalTime.now(), data.getBuyer().getBuyerID(),
                                        data.getBuyer().getCart().getShopID());
                data.getBuyer().getCart().setShopID("");
                Task<Void> task = new Task<Void>() {
                    @Override
                    public Void call() throws IOException, SQLException {
                        order.create();
                        return null;
                    }
                };
                new Thread(task).start();

                // link:https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
                PauseTransition delay = new PauseTransition(Duration.seconds(8));
                delay.setOnFinished(e -> {
                    myDialog.close();
                    myDialog2.close();
                    gui.miniPopup("Payment made successfully, new order added into Order History");
                    gui.toNextScene("View/BuyerOrderHistory.fxml");
                });
                delay.play();
                myDialog2.setOnCloseRequest(e -> {
                    myDialog.close();
                    gui.toNextScene("View/BuyerOrderHistory.fxml");
                });
            } else {
                try {
                    gui.informationPopup("Attention", "Please select a bank in order to proceed");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        controller.getBtnNo().setOnAction(e -> {
            myDialog.close();
            gui.notAlertInProgress(myDialog);
        });

        myDialog.setScene(dialogScene);
        myDialog.setMaximized(false);
        myDialog.show();
    }

    @FXML
    void toHome(MouseEvent event) throws IOException {
        if (anythingChanged) {
            gui.getStage().getScene().getRoot().setCursor(Cursor.WAIT);
            gui.getStage().getScene().getRoot().setDisable(true);
            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() throws IOException {
                    checkForUpdate();
                    return null;
                }
            };
            new Thread(task).start();
        }
        gui.toNextScene("View/BuyerHome.fxml");
    }

    @FXML
    void toOrderHistory(MouseEvent event) throws IOException {
        if (anythingChanged) {
            gui.getStage().getScene().getRoot().setCursor(Cursor.WAIT);
            gui.getStage().getScene().getRoot().setDisable(true);
            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() throws IOException {
                    checkForUpdate();
                    return null;
                }
            };
            new Thread(task).start();
        }
        gui.toNextScene("View/BuyerOrderHistory.fxml");
    }

    public void checkForUpdate() {
        for (CartItem c : data.getCartItems()) {
            if (c.isChanged()) {                
                c.update();
            }
        }
    }
}
