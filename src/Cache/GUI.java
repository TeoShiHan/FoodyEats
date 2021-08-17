package Cache;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class GUI {
  private final static GUI INSTANCE = new GUI();
  private Stage stage;        
  private Parent root;
  private ArrayList<Scene> prevScenes = new ArrayList<Scene>();        
  
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

  public void setPrevScenes(ArrayList<Scene> scenes){
    this.prevScenes = scenes;
  }

  public ArrayList<Scene> getPrevScenes(){
    return this.prevScenes;
  }

  public void addPrevScene(Scene scene){
    prevScenes.add(scene);
  }

  public Scene getPrevScene(){
    return prevScenes.get(prevScenes.size()-1);
  }

  public void toPrevScene() throws IOException{        
    stage.setScene(getPrevScene());
    prevScenes.remove(prevScenes.size()-1);
  }

  public void toNextScene(String filePath) throws IOException{        
    Scene currentScene = this.stage.getScene();     
    addPrevScene(currentScene);

    FXMLLoader loader = new FXMLLoader();
    // loader.setLocation(getClass().getResource("/View/BuyerOrder.fxml"));  //this is similar to next line, this line should add '/' infront of the path
    // loader.setLocation(getClass().getClassLoader().getResource("View/BuyerOrder.fxml"));
    loader.setLocation(getClass().getClassLoader().getResource(filePath));    
    root = loader.load();

    Scene nextScene = new Scene(root);
    String css = this.getClass().getResource("/View/App.css").toExternalForm();
    nextScene.getStylesheets().add(css);
    // stage.setUserData(this.p);
    this.stage.setScene(nextScene);
    this.stage.centerOnScreen();
  }
}
