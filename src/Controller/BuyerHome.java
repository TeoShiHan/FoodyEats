package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Cache.DataHolder;
import Cache.GUI;
import Classes.Shop;
import ListenerInterfaces.BuyerHomeListeners;
import ListenerInterfaces.NavigationalListeners;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class BuyerHome implements Initializable {

    // FXML ITEMS
    @FXML
    private AnchorPane shopList;
    @FXML
    private ScrollPane shopScroll;
    @FXML
    private GridPane shopListGrid;
    @FXML
    private Label logoutText;
    @FXML
    private ImageView orderHistoryIcon;
    @FXML
    private ImageView cartPageIcon;
    @FXML
    private ImageView homePageIcon;
    @FXML
    private Label greeting;

    // Listeners
    @FXML
    public void goToHomePage(MouseEvent mouseEvent) {
        navigationalListeners.goToHomePage();
    }

    @FXML
    void goToCartPage(MouseEvent mouseEvent) {
        navigationalListeners.goToCartPage();
    }

    @FXML
    void showOrderHistory(MouseEvent mouseEvent) {
        navigationalListeners.showOrderHistory();
    }

    @FXML
    void logout(MouseEvent mouseEvent) {
        navigationalListeners.logout();
    }

    @FXML
    void toProfile(MouseEvent event) throws IOException {
        navigationalListeners.goToProfile();
    }

    // VARIABLES FOR PROGRAM
    private static GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();
    private List<Shop> shopObjectCollection = new ArrayList<>();
    private BuyerHomeListeners tempListener;
    private NavigationalListeners navigationalListeners;

    // FUNCTIONS
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        greeting.setText("Welcome " + data.getAccount().getName() + "!");
        navigationalListeners = createNavigationalListeners();

        Shop tempShop = new Shop();
        shopObjectCollection = tempShop.createShopObjectCollection();

        System.out.println("outa the create shop object function");

        int gridCol = 0;
        int gridRow = 1;

        try {

            System.out.println("go in try clause");

            for (int i = 0; i < shopObjectCollection.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("../View/ShopItem.fxml"));

                StackPane shadowPane = fxmlLoader.load();

                shopList.setEffect(new DropShadow(10, Color.BLACK));

                ShopItemController shopItemController = fxmlLoader.getController();

                tempListener = createShopItemListener();

                shopItemController.setData(shopObjectCollection.get(i), tempListener);

                if (gridCol == 2) {
                    gridCol = 0;
                    gridRow++;
                }

                shopListGrid.add(shadowPane, gridCol++, gridRow);

                // set grid width
                shopListGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                shopListGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                shopListGrid.setMaxWidth(Region.USE_PREF_SIZE);

                // set grid height
                shopListGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                shopListGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                shopListGrid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(shadowPane, new Insets(10));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BuyerHomeListeners createShopItemListener() {
        BuyerHomeListeners listener = new BuyerHomeListeners() {
            @Override
            public void showFoodMenuOfChoosenShop(Shop shop) {
                data.setShop(shop);
                switchToShopPage();
            }
        };
        return listener;
    }

    public NavigationalListeners createNavigationalListeners() {
        NavigationalListeners listeners = new NavigationalListeners() {

            @Override
            public void goToHomePage() {
                gui.toNextScene("View/BuyerHome.fxml");
            }

            @Override
            public void goToCartPage() {
                gui.toNextScene("View/BuyerCart.fxml");
            }

            @Override
            public void logout() {
                gui.toNextScene("View/Login.fxml");
                data.clear();
            }

            @Override
            public void showOrderHistory() {
                gui.toNextScene("View/BuyerOrderHistory.fxml");
            }

            @Override
            public void goToProfile() {
                gui.toNextScene("View/Profile.fxml");

            }
        };
        return listeners;
    }

    public void switchToShopPage() {
        gui.toNextScene("View/FoodMenu.fxml");
    }

}