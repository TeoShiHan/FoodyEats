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
import Interfaces.FoodItemListeners;
import Interfaces.NavigationalListeners;
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

    //#region : FXML VARIABLES
    @FXML private AnchorPane foodListAnchor;
    @FXML private HBox iconHBOX;
    @FXML private ImageView orderHistory;
    @FXML private ImageView cartIcon;
    @FXML private ImageView homeIcon;
    @FXML private VBox shopName_category_review_Vbox;
    @FXML private Label shopNameLabel;
    @FXML private GridPane foodCategoryTagPane;
    @FXML private ScrollPane scrollFoodList;
    @FXML private GridPane foodListGrid;
    //#endregion

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
        myDialog.initModality(Modality.APPLICATION_MODAL);  //make user unable to press the original stage/window unless close the current stage/window
        myDialog.initOwner(gui.getStage());
        
        SeeReviews controller = new SeeReviews();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Popup/SeeReviews.fxml"));
        loader.setController(controller);                        
        Scene dialogScene = null;
        try {
            dialogScene = new Scene((Parent)loader.load());                        
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
                
        myDialog.setScene(dialogScene);
        myDialog.setMaximized(false);
        myDialog.show();
    }
    // #endregion

    // #region : PROGRAM VARIABLES
    private ArrayList<String>categoryList = new ArrayList<String>();
    private HashMap<String, ArrayList<Food>> foodArrMappedWithCategory;
    private ArrayList<Food> foodsOf1Category;
    private NavigationalListeners navigationalListeners;
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();
    private JDBC db = new JDBC();
    // #endregion

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
                // TODO Auto-generated method stub
                gui.toNextScene("View/Profile.fxml");
            }
        };
        return listeners;
    }

    public FoodItemListeners createFoodItemListeners(){
        FoodItemListeners foodItemListeners = new FoodItemListeners(){
            @Override
            public void addToCart(Food food, int quantity) {
                Buyer buyer = data.getBuyer();

                    /*DEBUG MSG*/System.out.println("buyer.getCart().getShopID() IS >>>>>>" + buyer.getCart().getShopID());
                    /*DEBUG MSG*/System.out.println("food.getShopID() IS >>>>>>" + food.getShopID());

                if(cartContainFoodFromOtherShop(buyer, food)){
                    System.out.println("foreign food Detected");
                    comfirmForDeleteOldCartItems(buyer.getCartID(),buyer,food);

                }else if (food.getFoodQtyAddedByUserIntoCart(buyer) != 0){
                    System.out.println("This food have been added into the database for this user");
                    updateCart(quantity, buyer.getCartID(), food.getFoodID(),food.getName());
                }
                else if(quantity == 0){
                    System.out.println("Quantity should not be 0");
                    waringOfNoFoodQty();
                }
                else{
                    food.addThisFoodToCart(buyer, quantity);
                    notifySuccessfullAddToCart(food.getName(), quantity);
                }
            }
        };
        return foodItemListeners;
    }
    
    //#region  ： INITIALIZE METHOD
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // #region : OBTAIN DATA
        navigationalListeners = createNavigationalListeners();
            /*DEBUG MSG*/System.out.println("GET SQL INSTANCE");

        DataHolder data = DataHolder.getInstance();
            /*DEBUG MSG*/System.out.println("GET DATA HOLDER INSTANCE");

        Shop shop = new Shop();
            /*DEBUG MSG*/System.out.println("CRATED NEW SHOP INSTANCE");
        
        shop.setShopID(data.getSelectedShopID());
        shop.initializeAvailableFoodCategoryInShop();        
        data.setShop(shop);

            /*DEBUG MSG*/System.out.println(shop.getShopID());
                
        categoryList = shop.getAvailableFoodCategoryInShop();
            /*DEBUG MSG*/System.out.println("CATEEGORY LIST : " + categoryList);
        
        foodArrMappedWithCategory = shop.getFoodObjArrThatMapWithCategory();
            /*DEBUG MSG*/System.out.println("FOOD ARRAY : " + foodArrMappedWithCategory);
        
        // #endregion

        // #region : LOAD CATEGORY TAG

        int gridCol = 1;
        int gridRow = 0;
        try {
            System.out.println("go in try clause");

            for (int i = 0; i < categoryList.size(); i++) {
                    /*DEBUG MSG*/System.out.println("FOR LOOP " + i);
                    /*DEBUG MSG*/System.out.println("categoryList.size() " + categoryList.size());

                FXMLLoader fxmlLoader = new FXMLLoader();
                    /*DEBUG MSG*/System.out.println("successfully created the fxml loader");

                fxmlLoader.setLocation(getClass().getResource("../View/CategoryTag.fxml"));
                    /*DEBUG MSG*/ System.out.println("successfully set the location");

                Button categoryBtn = fxmlLoader.load();
                FoodCategoryItem foodCategoryItemController = fxmlLoader.getController();
                    /*DEBUG MSG*/System.out.println("Got the shop ItemController");

                foodCategoryItemController.setStringToLabel(categoryList.get(i));
                    /*DEBUG MSG*/System.out.println("successfully set the data");

                foodCategoryTagPane.add(categoryBtn, gridCol++, gridRow);
                    /*DEBUG MSG*/System.out.println("successfully added the grid");

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
            /*DEBUG MSG*/System.out.println("OUTA THE LOOP");

        // #endregion

        // #region : FOOD LIST
        int secGridCol = 0;
        int secGridRow = 1;

        try {
            System.out.println("go in try clause");

            for (int r = 0; r < categoryList.size(); r++) {

                foodsOf1Category = foodArrMappedWithCategory.get(categoryList.get(r));
                
                secGridRow++;
                secGridCol = 0;

                //#region : LOAD THE CATEGORY LABEL
                FXMLLoader fxmlLoaderOfLabel = new FXMLLoader();
                        /* DEBUG OUTPUT>>>>>>> */System.out.println("successfully created the fxml loader");

                    fxmlLoaderOfLabel.setLocation(getClass().getResource("../View/categoryLabel.fxml"));
                        /* DEBUG OUTPUT>>>>>>> */System.out.println("successfully set the location");

                    Label categoryLabel = fxmlLoaderOfLabel.load();

                    CategoryLabel categoryLabelController = fxmlLoaderOfLabel.getController();
                        /* DEBUG OUTPUT>>>>>>> */System.out.println("Got the FOOD ItemController");
                        /* DEBUG OUTPUT>>>>>>> */System.out.println("finish output try");

                    categoryLabelController.setLabelText(categoryList.get(r));
                        /* DEBUG OUTPUT>>>>>>> */System.out.println("successfully set the data");

                    foodListGrid.add(categoryLabel, secGridCol, secGridRow++);
                //#endregion
                
                //#region : LOAD THE FOODS
                for (int i = 0; i < foodsOf1Category.size(); i++) {
                    /* DEBUG OUTPUT>>>>>>> */System.out.println("foodsOf1Category.size() IS :" + foodsOf1Category.size());

                    FXMLLoader fxmlLoader = new FXMLLoader();
                        /* DEBUG OUTPUT>>>>>>> */System.out.println("successfully created the fxml loader");

                    fxmlLoader.setLocation(getClass().getResource("../View/foodItem.fxml"));
                        /* DEBUG OUTPUT>>>>>>> */System.out.println("successfully set the location");

                    HBox foodItemHbox = fxmlLoader.load();

                    FoodItem foodItemController = fxmlLoader.getController();
                        /* DEBUG OUTPUT>>>>>>> */System.out.println("Got the FOOD ItemController");
                        /* DEBUG OUTPUT>>>>>>> */System.out.println("finish output try");

                    FoodItemListeners tempFoodItemListener = createFoodItemListeners();
                        /* DEBUG OUTPUT>>>>>>> */System.out.println("created food item listener");

                    foodItemController.setDataToFoodItem(foodsOf1Category.get(i), tempFoodItemListener);
                        /* DEBUG OUTPUT>>>>>>> */System.out.println("foodsOf1Category.get(i) IS : " + foodsOf1Category.get(i));

                    foodItemController.configureTheSpinner();
                        /* DEBUG OUTPUT>>>>>>> */System.out.println("successfully set the data");

                    if (secGridCol == 2) {
                        secGridCol = 0;
                        secGridRow++;
                    }
                    foodListGrid.add(foodItemHbox, secGridCol++, secGridRow);
                    System.out.println("successfully added the grid");
                    GridPane.setMargin(foodItemHbox, new Insets(10));
                }
                //#endregion
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //#endregion
    }
    //#endregion

    public boolean cartContainFoodFromOtherShop(Buyer buyer, Food food) {
       if (buyer.getCart().getCartID() == null){
           return false;
       }
        
        String cartShopID = buyer.getCart().getShopID().toString();
        String foodShopID = food.getShopID().toString();

        String sqlStmt = String.format(
                "SELECT DISTINCT shopID " + "FROM CartItem C, Food F " + "WHERE C.foodID = F.foodID AND cartID = '%s'",
                buyer.getCartID());
                /* DEBUG MSG */System.out.println("SQL STATEMENT is : " + sqlStmt);

        int distinctShopQty = db.readAll(sqlStmt).size();
        /* DEBUG MSG */System.out.println("shop Qty is : " + distinctShopQty);

        return (!cartShopID.equals(foodShopID) || distinctShopQty > 1);
    }

    public void comfirmForDeleteOldCartItems(String cartID, Buyer buyer, Food food) {
        try {
            gui.confirmationPopup(
                "Detected food from other shop!!!", 
                "You cart contain foods from other shop!\n" +
                "Would you like to clear those foods and add new food to cart?", "Yes", "No", 
                passback->{
                    if(passback){
                        clearCart(cartID, buyer, food);
                    }
                }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void notifySuccessfullAddToCart(String foodName, int foodQty){
        String msg = "Successfully added " + foodQty + " " + foodName + " into the cart"; 
        try {
            gui.informationPopup("Congratulations", msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearCart(String cartID, Buyer buyer, Food food){
        String sqlStmt = String.format(
            "DELETE FROM CartItem " +
            "WHERE cartID = '%s';",
            cartID
        );
        db.executeCUD(sqlStmt, gui);
        buyer.getCart().setShopID(food.getShopID());
    }

    public void waringOfNoFoodQty(){
        try {
            gui.informationPopup("Warning", "Food quantity should not be 0, please try again!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCart(int quantity, String cartID, String foodId, String foodName){
        String sqlStmt = String.format(
            "UPDATE CartItem " +
            "SET quantity = quantity + %s " +
            "WHERE cartID = '%s' AND foodID = '%s';",
            quantity, cartID, foodId
        );
        /*DEBUG MSG*/System.out.println("SQL STATEMENT IS : " + sqlStmt);
        db.executeCUD(sqlStmt, gui);
        notifySuccessfullAddToCart(foodName, quantity);
    }
}
