package Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import Classes.JDBC;
import Classes.Shop;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class shopList implements Initializable {
    
    //  FXML elements
    @FXML private AnchorPane shopList;
    @FXML private ScrollPane shopScroll;
    @FXML private GridPane shopListGrid;

    //  do database stuff
    private JDBC db = new JDBC();
    // Locally store the records in hashmap
    private ArrayList<HashMap<String,Object>> ShopTable = new ArrayList<HashMap<String,Object>>();
    //  List to store 'shop' object
    private List<Shop> ShopObjectCollection = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        // STEP 1: 
        
    }

    //  Store "Shop" instances

}

class shopListDriver{
    public static void main(String[] args) {
        ArrayList<HashMap<String,Object>> ShopTable = new ArrayList<HashMap<String,Object>>();
        JDBC test = new JDBC();
        ShopTable = test.readAll("SELECT * FROM Shop");

        //  Print out the key the hasmap has
        for(int i = 0; i < ShopTable.size(); i++){
                System.out.println(ShopTable.get(i).get("address"));
                System.out.println(ShopTable.get(i).get("deliveryFee"));
                System.out.println(ShopTable.get(i).get("startHour"));
                System.out.println(ShopTable.get(i).get("imgPath"));
                System.out.println(ShopTable.get(i).get("name"));
                System.out.println(ShopTable.get(i).get("shopID"));
                System.out.println(ShopTable.get(i).get("telNo"));
                System.out.println(ShopTable.get(i).get("status"));
        }
    }
}

