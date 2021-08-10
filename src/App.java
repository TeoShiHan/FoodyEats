import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Controller.Payment;
 
import java.util.ArrayList; // import the ArrayList class

public class App extends Application{
    public static void main(String[] args) throws Exception  {
        System.out.println("Main method start");              
        launch(); 
        // launch(args);  // same with line above
        System.out.println("Main method finished");     
    }

    @Override
    public void start(Stage stage) throws Exception {        
        // <--------------FXMK Loader------------------->
        Parent root = FXMLLoader.load(getClass().getResource("View/Login.fxml"));  
        // String css = this.getClass().getResource("View/BuyerHome.css").toExternalForm();
        
        Scene scene = new Scene(root);
        // scene.getStylesheets().add(css);

        // stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Foody Eats");   
        stage.setWidth(1200);
        stage.setHeight(700);
        stage.setResizable(false);
        stage.setScene(scene);        
        stage.show();           
    }
}
