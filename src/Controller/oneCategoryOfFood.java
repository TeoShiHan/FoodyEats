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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class oneCategoryOfFood implements Initializable{

    @FXML private GridPane singleCategoryGrid;

    //#region Program Variables
    private ArrayList<Food> foodsOf1Category;
    private String shopID;
    private String keyStr;
    //#endregion

    //#region : GETTER AND SETTER
    public ArrayList<Food> getFoodsOf1Category() {
        return foodsOf1Category;
    }

    public void setFoodsOf1Category(ArrayList<Food> foodsOf1Category) {
        this.foodsOf1Category = foodsOf1Category;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getKeyStr() {
        return keyStr;
    }

    public void setKeyStr(String keyStr) {
        this.keyStr = keyStr;
    }
    
    //#endregion

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        foodsOf1Category = Shop.getFoodObjArrThatMapWithCategory(this.getShopID()).get(this.getKeyStr());
        
        int gridCol = 0;
        int gridRow = 1;

        try{
            System.out.println("go in try clause");
            for(int i = 0 ; i < foodsOf1Category.size() ; i++){
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                    /*DEBUG OUTPUT>>>>>>>*/System.out.println("successfully created the fxml loader");
                
                fxmlLoader.setLocation(getClass().getResource("../View/foodItem.fxml"));
                    /*DEBUG OUTPUT>>>>>>>*/System.out.println("successfully set the location");

                HBox foodItemHbox = fxmlLoader.load();
                
                foodItem foodItemController = fxmlLoader.getController();
                    /*DEBUG OUTPUT>>>>>>>*/System.out.println("Got the FOOD ItemController");
                    /*DEBUG OUTPUT>>>>>>>*/System.out.println("finish output try");
                
                foodItemController.setDataToFoodItem(foodsOf1Category.get(i));
                    /*DEBUG OUTPUT>>>>>>>*/System.out.println("successfully set the data");

                if (gridCol == 2){
                    gridCol = 0;
                    gridRow++;
                }

                singleCategoryGrid.add(foodItemHbox, gridCol++, gridRow);

                System.out.println("successfully added the grid");

                 //set grid width
                 singleCategoryGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                 singleCategoryGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                 singleCategoryGrid.setMaxWidth(Region.USE_PREF_SIZE);
 
                 //set grid height
                 singleCategoryGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                 singleCategoryGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                 singleCategoryGrid.setMaxHeight(Region.USE_PREF_SIZE);
                 
                 GridPane.setMargin(foodItemHbox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
