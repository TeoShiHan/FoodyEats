package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import Cache.DataHolder;
import Cache.GUI;
import Classes.Buyer;
import Classes.Food;
import Classes.JDBC;
import Classes.Shop;
import Controller.Popup.SeeReviews;
import ListenerInterfaces.FoodItemListeners;
import ListenerInterfaces.NavigationalListeners;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FoodMenu implements Initializable {

    // #region : FXML VARIABLES
    @FXML
    private AnchorPane foodListAnchor;
    @FXML
    private HBox iconHBOX;
    @FXML
    private ImageView orderHistory;
    @FXML
    private ImageView cartIcon;
    @FXML
    private ImageView homeIcon;
    @FXML
    private VBox shopName_category_review_Vbox;
    @FXML
    private Label shopNameLabel;
    @FXML
    private GridPane foodCategoryTagPane;
    @FXML
    private ScrollPane scrollFoodList;
    @FXML
    private GridPane foodListGrid;
    // #endregion

    // #region : Listeners
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
    void viewShopReview(MouseEvent event) {
        Stage myDialog = new Stage();
        myDialog.initModality(Modality.APPLICATION_MODAL); // make user unable to press the original stage/window unless
                                                           // close the current stage/window
        myDialog.initOwner(gui.getStage());

        SeeReviews controller = new SeeReviews();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Popup/SeeReviews.fxml"));
        loader.setController(controller);
        Scene dialogScene = null;
        try {
            dialogScene = new Scene((Parent) loader.load());
        } catch (IOException e2) {
            e2.printStackTrace();
        }

        myDialog.setScene(dialogScene);
        myDialog.setMaximized(false);
        myDialog.show();
    }
    // #endregion

    // #region : PROGRAM VARIABLES
    private ArrayList<String> categoryList = new ArrayList<String>();
    private HashMap<String, ArrayList<Food>> foodArrMappedWithCategory;
    private ArrayList<Food> foodsOf1Category;
    private NavigationalListeners navigationalListeners;
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();
    private JDBC db = new JDBC();
    // #endregion

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
            }

            @Override
            public void showOrderHistory() {
                gui.toNextScene("View/BuyerOrderHistory.fxml");
            }

            @Override
            public void goToProfile() {
                // TODO Auto-generated method stub
                gui.toNextScene("View/Profile.fxml");
            }
        };
        return listeners;
    }

    public FoodItemListeners createFoodItemListeners() {
        FoodItemListeners foodItemListeners = new FoodItemListeners() {
            @Override
            public void addToCart(Food food, int quantity) {
                Buyer buyer = data.getBuyer();
                updateShopIdOfCart(food.getShopID(), buyer.getCartID());                

                if (cartContainFoodFromOtherShop(buyer, food)) {                    
                    comfirmForDeleteOldCartItems(buyer.getCartID(), buyer, food);

                } else if (food.getFoodQtyAddedByUserIntoCart(buyer) != 0) {                    
                    updateCart(quantity, buyer.getCartID(), food.getFoodID(), food.getName());
                } else if (quantity == 0) {
                    waringOfNoFoodQty();
                } else {
                    food.addThisFoodToCart(buyer, quantity);
                    notifySuccessfullAddToCart(food.getName(), quantity);
                }

            }
        };
        return foodItemListeners;
    }

    // #region ： INITIALIZE METHOD
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // #region : OBTAIN DATA
        navigationalListeners = createNavigationalListeners();

        DataHolder data = DataHolder.getInstance();

        Shop shop = data.getShop();

        shop.initializeAvailableFoodCategoryInShop();
        data.setShop(shop);
        shopNameLabel.setText(shop.getName());

        categoryList = shop.getAvailableFoodCategoryInShop();

        foodArrMappedWithCategory = shop.getFoodObjArrThatMapWithCategory();

        // #endregion

        // #region : LOAD CATEGORY TAG

        int gridCol = 1;
        int gridRow = 0;
        try {            

            for (int i = 0; i < categoryList.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("../View/CategoryTag.fxml"));

                Button categoryBtn = fxmlLoader.load();
                FoodCategoryItem foodCategoryItemController = fxmlLoader.getController();

                foodCategoryItemController.setStringToLabel(categoryList.get(i));

                foodCategoryTagPane.add(categoryBtn, gridCol++, gridRow);

                // set grid width
                foodCategoryTagPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                foodCategoryTagPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                foodCategoryTagPane.setMaxWidth(Region.USE_PREF_SIZE);

                // set grid height
                foodCategoryTagPane.setMinHeight(Region.USE_COMPUTED_SIZE);
                foodCategoryTagPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                foodCategoryTagPane.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(categoryBtn, new Insets(5));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // #endregion

        // #region : FOOD LIST
        int secGridCol = 0;
        int secGridRow = 1;

        try {            

            for (int r = 0; r < categoryList.size(); r++) {

                foodsOf1Category = foodArrMappedWithCategory.get(categoryList.get(r));

                secGridRow++;
                secGridCol = 0;

                // #region : LOAD THE CATEGORY LABEL
                FXMLLoader fxmlLoaderOfLabel = new FXMLLoader();
                fxmlLoaderOfLabel.setLocation(getClass().getResource("../View/categoryLabel.fxml"));
                Label categoryLabel = fxmlLoaderOfLabel.load();
                CategoryLabel categoryLabelController = fxmlLoaderOfLabel.getController();
                categoryLabelController.setLabelText(categoryList.get(r));
                foodListGrid.add(categoryLabel, secGridCol, secGridRow++);
                // #endregion

                // #region : LOAD THE FOODS
                for (int i = 0; i < foodsOf1Category.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../View/foodItem.fxml"));
                    HBox foodItemHbox = fxmlLoader.load();

                    FoodItem foodItemController = fxmlLoader.getController();
                    FoodItemListeners tempFoodItemListener = createFoodItemListeners();

                    foodItemController.setDataToFoodItem(foodsOf1Category.get(i), tempFoodItemListener);
                    foodItemController.configureTheSpinner();

                    if (secGridCol == 2) {
                        secGridCol = 0;
                        secGridRow++;
                    }
                    foodListGrid.add(foodItemHbox, secGridCol++, secGridRow);
                    System.out.println("successfully added the grid");
                    GridPane.setMargin(foodItemHbox, new Insets(10));
                }
                // #endregion
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // #endregion
    }
    // #endregion

    public boolean cartContainFoodFromOtherShop(Buyer buyer, Food food) {
        if (buyer.getCart().getShopID() == null || buyer.getCart().getShopID().isEmpty()) {
            return false;
        }
        String cartShopID = buyer.getCart().getShopID().toString();
        String foodShopID = food.getShopID().toString();
        String sqlStmt = String.format(
                "SELECT DISTINCT shopID " + "FROM CartItem C, Food F " + "WHERE C.foodID = F.foodID AND cartID = '%s'",
                buyer.getCartID());        

        int distinctShopQty = db.readAll(sqlStmt).size();        

        return (!cartShopID.equals(foodShopID) || distinctShopQty > 1);
    }

    public void comfirmForDeleteOldCartItems(String cartID, Buyer buyer, Food food) {
        try {
            gui.confirmationPopup("Detected food from other shop!!!",
                    "You cart contain foods from other shop!\n"
                            + "Would you like to clear those foods and add new food to cart?",
                    "Yes", "No", passback -> {
                        if (passback) {
                            clearCart(cartID, buyer, food);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void notifySuccessfullAddToCart(String foodName, int foodQty) {
        String msg = "Successfully added " + foodQty + " " + foodName + " into the cart";
        try {
            gui.informationPopup("Congratulations", msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearCart(String cartID, Buyer buyer, Food food) {
        String sqlStmt = String.format("DELETE FROM CartItem " + "WHERE cartID = '%s';", cartID);
        db.executeCUD(sqlStmt, gui);
        buyer.getCart().setShopID("");
    }

    public void waringOfNoFoodQty() {
        try {
            gui.informationPopup("Warning", "Food quantity should not be 0, please try again!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCart(int quantity, String cartID, String foodId, String foodName) {
        String sqlStmt = String.format(
                "UPDATE CartItem " + "SET quantity = quantity + %s " + "WHERE cartID = '%s' AND foodID = '%s';",
                quantity, cartID, foodId);
                        
        db.executeCUD(sqlStmt, gui);
        notifySuccessfullAddToCart(foodName, quantity);
    }

    public void updateShopIdOfCart(String shopID, String cartID) {
        String sqlStmt = String.format("UPDATE Cart " + "SET shopID = '%s' " + "WHERE cartID = '%s';", shopID, cartID);
        db.executeCUD(sqlStmt, gui);
    }
}
