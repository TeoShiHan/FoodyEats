package Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import Classes.JDBC;
import Classes.Shop;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class shopList implements Initializable {
    
    //  FXML ITEMS
    @FXML private AnchorPane shopList;
    @FXML private ScrollPane shopScroll;
    @FXML private GridPane shopListGrid;

    //  VARIABLES FOR PROGRAM
    private JDBC db = new JDBC();
    private String query = "SELECT shopID, status, shopName, deliveryFee, imgPath FROM Shop";
    private ArrayList<HashMap<String,Object>> shopTable = db.readAll(query);
    private List<Shop> shopObjectCollection = new ArrayList<>();

    //  FUNCTIONS
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        createShopObjectCollection();

        System.out.println("outa the create shop object function");

        int gridCol = 0;
        int gridRow = 1;

        try{
        
        System.out.println("go in try clause");

            for(int i = 0 ; i < shopObjectCollection.size() ; i++){
                
                FXMLLoader fxmlLoader = new FXMLLoader();

                    /*DEBUG OUTPUT*/System.out.println("successfully created the fxml loader");

                fxmlLoader.setLocation(getClass().getResource("../View/shopItem.fxml"));

                    /*DEBUG OUTPUT*/System.out.println("successfully set the location");

                StackPane shadowPane = fxmlLoader.load();

                shopList.setEffect(new DropShadow(10,Color.BLACK));

                    /*DEBUG OUTPUT*/System.out.println("successfully load the hbox");

                shopItemController shopItemController = fxmlLoader.getController();

                shopItemController.getViewShopBtn().setOnAction(e -> {});

                    /*DEBUG OUTPUT*/System.out.println("Got the shop ItemController");
                    /*DEBUG OUTPUT*/System.out.println(shopObjectCollection.get(i).getName());
                    /*DEBUG OUTPUT*/System.out.println(shopObjectCollection.get(i).getImgPath());
                    /*DEBUG OUTPUT*/System.out.println(shopObjectCollection.get(i).getDeliveryFee());
                    /*DEBUG OUTPUT*/System.out.println("finish output try");

                shopItemController.setData(shopObjectCollection.get(i));

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
    public void createShopObjectCollection(){
        for(int i = 0; i < shopTable.size(); i++){

                /*DEBUG OUTPUT*/System.out.println("Runned");

            String shopID      = (String)  shopTable.get(i).get("shopID");
            String shopName    = (String)  shopTable.get(i).get("shopName");
            double deliveryFee = (double)  shopTable.get(i).get("deliveryFee");
            String imgPath     = (String)  shopTable.get(i).get("imgPath");
            System.out.println(imgPath);

            System.out.println("pass through getting value assignatoin");

            Shop shopInstance = new Shop();

                /*DEBUG OUTPUT*/System.out.println("successfully create shop instance");

            shopInstance.setShopID(shopID);
            shopInstance.setName(shopName);
            shopInstance.setDeliveryFee(deliveryFee);
            shopInstance.setImgPath(imgPath);

            shopObjectCollection.add(shopInstance);
            System.out.println(shopObjectCollection);
        }
    }
}
