import java.util.ArrayList;
import java.util.HashMap;

import Cache.*;
import Classes.Shop;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import SQL.CreateTableQuery.SQL;

public class App extends Application{
    private static GUI gui = GUI.getInstance();
    
    public static void main(String[] args) throws Exception  {
        
        loadFromDatabase();
        /**DEBUG*/System.out.print("SUCCESSFULLE LOADING");

        ArrayList<HashMap<String, Object>> table = new ArrayList<HashMap<String, Object>>();

        System.out.println(table);

        System.out.println("Main method start");              
        launch(); 
        // launch(args);  // same with line above
        System.out.println("Main method finished");     
    }

    @Override
    public void start(Stage stage) throws Exception {   
        
        gui.setStage(stage);

        // <--------------FXMK Loader------------------->
        Parent root = FXMLLoader.load(getClass().getResource("View/Login.fxml"));  
        String css = this.getClass().getResource("View/App.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);        
        // stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Foody Eats");   
        // stage.setWidth(1215);
        // stage.setHeight(730);
        stage.setResizable(false);
        stage.setScene(scene);                
        stage.show();         
        stage.centerOnScreen();        
        // stage.setOnCloseRequest(event->{
        //     event.consume();
        //     Alert alert = new Alert(AlertType.CONFIRMATION);
        //     alert.setTitle("Close window");
        //     alert.setHeaderText("Close Window?");
        //     alert.setContentText("Are you sure you want to close the window?");

        //     if(alert.showAndWait().get() == ButtonType.OK){
        //         stage.close();
        //     }          
        // });                  
    }

    private static void loadFromDatabase(){
            /*DEBUG MSG*/System.out.println("RUNNED LOAD");

        DataHolder data = DataHolder.getInstance();
            /*DEBUG MSG*/System.out.println("CREATED DATA HOLDER INSTANCE");
        
        SQL sql = SQL.getInstance();
            /*DEBUG MSG*/System.out.println("CREATED SQL INSTANCE");
        
        data.setAccountTable(sql.fetchAccountTable()); 
            /**DEBUG*/System.out.print("SET ACCOUNT TABLE");
        
        data.setBuyerTable(sql.fetchBuyerTable());
        data.setSellerTable(sql.fetchSellerTable());
        data.setAdminTable(sql.fetchAdminTable());
        data.setRiderTable(sql.fetchRiderTable());
        data.setShopTable(sql.fetchShopTable());
        Shop tempShop = new Shop();
            /**DEBUG*/System.out.print("CREATED SHOP OBJ");
        data.setFoodCategoriesTable(sql.fetchFoodCategoriesFromAllShops(tempShop.getAllKeysInTable(data.getShopTable())));
            /*DEBUG MSG*/System.out.println("RUNNED CATEGORY TABLE" + data.getFoodCategoriesTable());
    }
}
