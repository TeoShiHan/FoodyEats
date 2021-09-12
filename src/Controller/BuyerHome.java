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
    
    //  FXML ITEMS
    @FXML private AnchorPane shopList;
    @FXML private ScrollPane shopScroll;
    @FXML private GridPane shopListGrid;
    @FXML private Label logoutText;
    @FXML private ImageView orderHistoryIcon;
    @FXML private ImageView cartPageIcon;
    @FXML private ImageView homePageIcon;
    @FXML private Label greeting;

    //  Listeners
    @FXML
    public void goToHomePage(MouseEvent mouseEvent){
        navigationalListeners.goToHomePage();
    }

    @FXML void goToCartPage(MouseEvent mouseEvent){
        navigationalListeners.goToCartPage();
    }

    @FXML void showOrderHistory(MouseEvent mouseEvent){
        navigationalListeners.showOrderHistory();
    }

    @FXML void logout(MouseEvent mouseEvent){
        navigationalListeners.logout();
    }

    @FXML void toProfile(MouseEvent event) throws IOException {
        navigationalListeners.goToProfile();
    }

    //  VARIABLES FOR PROGRAM
    private static GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();
    private List<Shop> shopObjectCollection = new ArrayList<>();
    private BuyerHomeListeners tempListener;
    private NavigationalListeners navigationalListeners;

    //  FUNCTIONS
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*DEBUG MSG*/System.out.println("INITIALIZED");
        
        navigationalListeners = createNavigationalListeners();
        
        Shop tempShop = new Shop();
        shopObjectCollection = tempShop.createShopObjectCollection();

            System.out.println("outa the create shop object function");

        int gridCol = 0;
        int gridRow = 1;

        try{
        
        System.out.println("go in try clause");

            for(int i = 0 ; i < shopObjectCollection.size() ; i++){
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                    /*DEBUG OUTPUT*/System.out.println("successfully created the fxml loader");

                fxmlLoader.setLocation(getClass().getResource("../View/ShopItem.fxml"));
                    /*DEBUG OUTPUT*/System.out.println("successfully set the location");

                StackPane shadowPane = fxmlLoader.load();

                shopList.setEffect(new DropShadow(10,Color.BLACK));
                    /*DEBUG OUTPUT*/System.out.println("successfully load the hbox");

                ShopItemController shopItemController = fxmlLoader.getController();
                    /*DEBUG OUTPUT*/System.out.println("Got the shop ItemController");
                    /*DEBUG OUTPUT*/System.out.println(shopObjectCollection.get(i).getName());
                    /*DEBUG OUTPUT*/System.out.println(shopObjectCollection.get(i).getImgPath());
                    /*DEBUG OUTPUT*/System.out.println(shopObjectCollection.get(i).getDeliveryFee());
                    /*DEBUG OUTPUT*/System.out.println("finish output try");

                tempListener = createShopItemListener();
                    /*DEBUG OUTPUT*/System.out.println("CREATED A LISTENER");
                
                    shopItemController.setData(shopObjectCollection.get(i),tempListener);

                    /*DEBUG OUTPUT*/System.out.println("successfully set the data");

                if (gridCol == 2){
                    gridCol = 0;
                    gridRow++;
                }

                 shopListGrid.add(shadowPane, gridCol++, gridRow);
                    /*DEBUG OUTPUT*/System.out.println("successfully added the grid");

                 //set grid width
                 shopListGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                 shopListGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                 shopListGrid.setMaxWidth(Region.USE_PREF_SIZE);
 
                 //set grid height
                 shopListGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                 shopListGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                 shopListGrid.setMaxHeight(Region.USE_PREF_SIZE);
 
                 GridPane.setMargin(shadowPane, new Insets(10));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BuyerHomeListeners createShopItemListener(){
        BuyerHomeListeners listener = new BuyerHomeListeners(){
            @Override
            public void showFoodMenuOfChoosenShop(Shop shop) {
                    /*DEBUG OUTPUT*/System.out.println("THE SHOP ID IS : " + shop.getShopID());
                data.setShop(shop);
                    /*DEBUG OUTPUT*/System.out.println("THE SELECTED SHOP ID IS : " + data.getSelectedShopID());
                switchToShopPage();
            }
        };
        /*DEBUG OUTPUT*/System.out.println("CREATED THE LISTENER");
        return listener;
    }

    public NavigationalListeners createNavigationalListeners(){ 
        NavigationalListeners listeners = new NavigationalListeners(){

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

    public void switchToShopPage(){
        gui.toNextScene("View/FoodMenu.fxml");
    }

}