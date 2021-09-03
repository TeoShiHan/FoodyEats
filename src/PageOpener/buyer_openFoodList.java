package PageOpener;
import Cache.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class buyer_openFoodList extends Application{
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

        // <--------------FXMK Loader------------------->
        Parent root = FXMLLoader.load(getClass().getResource("../View/foodList.fxml"));  
        
        Scene scene = new Scene(root);
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
}
