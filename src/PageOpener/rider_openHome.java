package PageOpener;
import Cache.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class rider_openHome extends Application{
    public static void main(String[] args) throws Exception  {
        System.out.println("Main method start");              
        launch(); 
        // launch(args);  // same with line above
        System.out.println("Main method finished");     
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/foodOf_1_category.fxml"));
        primaryStage.setTitle("Fruits Marker");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
