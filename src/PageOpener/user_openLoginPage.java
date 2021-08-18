package PageOpener;
import Cache.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class user_openLoginPage extends Application{
    @Override
    public void start(Stage primaryStage){
        try{

        primaryStage.setTitle("BuyerTable");
        Parent root = FXMLLoader.load(getClass().getResource("../View/Login.fxml"));  
        Scene scene = new Scene(root);                        
        String css = this.getClass().getResource("../View/App.css").toExternalForm();
        scene.getStylesheets().add(css);  
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception  {
        launch(args);    
    }
}
