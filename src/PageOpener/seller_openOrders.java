package PageOpener;
import Classes.*;
import Cache.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class seller_openOrders extends Application{
    private static GUI gui = GUI.getInstance();
    public static void main(String[] args) throws Exception  {
        System.out.println("Main method start");              
        launch(); 
        // launch(args);  // same with line above
        System.out.println("Main method finished");     
    }

    @Override
    public void start(Stage stage) throws Exception {   
        gui.setStage(stage);
        Parent root = FXMLLoader.load(getClass().getResource("../View/SellerOrders.fxml"));  
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("../View/App.css").toExternalForm();
        scene.getStylesheets().add(css);        
        stage.setTitle("Foody Eats");   
        stage.setResizable(false);
        stage.setScene(scene);                
        stage.show();         
        stage.centerOnScreen();             
    }   
}
