package Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import Classes.Food;
import Classes.Shop;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class foodList implements Initializable {

    // #region : FXML VARIABLES
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
    // #endregion

    // #region : PROGRAM VARIABLES
    private ArrayList<String> categoryList;
    private HashMap<String, ArrayList<Food>> foodArrMappedWithCategory;
    private ArrayList<Food> foodsOf1Category;
    // #endregion

    //#region  ： INITIALIZE METHOD
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // #region : OBTAIN DATA
        categoryList = Shop.getAvailableFoodCategoryInShop("S00001");
        foodArrMappedWithCategory = Shop.getFoodObjArrThatMapWithCategory("S00001");
        // #endregion

        // #region : LOAD CATEGORY TAG

        int gridCol = 1;
        int gridRow = 0;
        try {

            System.out.println("go in try clause");

            for (int i = 0; i < categoryList.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();

                System.out.println("successfully created the fxml loader");

                fxmlLoader.setLocation(getClass().getResource("../View/categoryTag.fxml"));

                System.out.println("successfully set the location");

                Button categoryBtn = fxmlLoader.load();

                foodCategoryItem foodCategoryItemController = fxmlLoader.getController();

                System.out.println("Got the shop ItemController");

                foodCategoryItemController.setStringToLabel(categoryList.get(i));

                System.out.println("successfully set the data");

                foodCategoryTagPane.add(categoryBtn, gridCol++, gridRow);

                System.out.println("successfully added the grid");

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

                    categoryLabel categoryLabelController = fxmlLoaderOfLabel.getController();
                    /* DEBUG OUTPUT>>>>>>> */System.out.println("Got the FOOD ItemController");
                    /* DEBUG OUTPUT>>>>>>> */System.out.println("finish output try");

                    categoryLabelController.setLabelText(categoryList.get(r));
                    /* DEBUG OUTPUT>>>>>>> */System.out.println("successfully set the data");

                    foodListGrid.add(categoryLabel, secGridCol, secGridRow++);
                //#endregion
                
                //#region : LOAD THE FOODS
                for (int i = 0; i < foodsOf1Category.size(); i++) {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    /* DEBUG OUTPUT>>>>>>> */System.out.println("successfully created the fxml loader");

                    fxmlLoader.setLocation(getClass().getResource("../View/foodItem.fxml"));
                    /* DEBUG OUTPUT>>>>>>> */System.out.println("successfully set the location");

                    HBox foodItemHbox = fxmlLoader.load();

                    foodItem foodItemController = fxmlLoader.getController();
                    /* DEBUG OUTPUT>>>>>>> */System.out.println("Got the FOOD ItemController");
                    /* DEBUG OUTPUT>>>>>>> */System.out.println("finish output try");


                    foodItemController.setDataToFoodItem(foodsOf1Category.get(i));
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
}
