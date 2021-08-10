package Cache;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class GUI {
  private Stage stage;        
  private final static GUI INSTANCE = new GUI();
  private Parent root;
  
  private GUI() {}
  
  public static GUI getInstance() {
    return INSTANCE;
  }
  
  public void setStage(Stage stage) {
    this.stage = stage;
  }
  
  public Stage getStage() {
    return this.stage;
  }

  public void toNextScene(String filePath) throws IOException{        
    FXMLLoader loader = new FXMLLoader();
    // loader.setLocation(getClass().getResource("/View/BuyerOrder.fxml"));  //this is similar to next line, this line should add '/' infront of the path
    // loader.setLocation(getClass().getClassLoader().getResource("View/BuyerOrder.fxml"));
    loader.setLocation(getClass().getClassLoader().getResource(filePath));
    root = loader.load();                        
        
    // passDataToNextScene(loader);        

    // stage.setUserData(this.p);
    this.stage.setScene(new Scene(root));
  }
}
