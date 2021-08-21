package Cache;
import Controller.Popup.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    Scene nextScene = new Scene(loader.load());
    String css = this.getClass().getResource("/View/App.css").toExternalForm();
    nextScene.getStylesheets().add(css);
    // stage.setUserData(this.p);
    this.stage.setScene(nextScene);
    this.stage.centerOnScreen();
  }

  public void informationPopup(String heading,String message) throws IOException{      
    final Stage myDialog = new Stage();
    myDialog.initModality(Modality.APPLICATION_MODAL);  //make user unable to press the original stage/window unless close the current stage/window
    myDialog.initOwner(stage);

    InformationPopup controller = new InformationPopup();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Popup/InformationPopup.fxml"));
    loader.setController(controller);                        
    Scene dialogScene = new Scene((Parent)loader.load());

    controller.getLblHeading().setText(heading);
    controller.getLblMessage().setText(message);  
    controller.getBtnOK().setOnAction(e -> myDialog.close());  
    
    myDialog.setScene(dialogScene);
    myDialog.setMaximized(false);
    myDialog.show();

    // link:https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
    PauseTransition delay = new PauseTransition(Duration.seconds(15));
    delay.setOnFinished(e -> myDialog.close());
    delay.play();
  }

  public void informationPopup(String heading,String message, String btnOKText) throws IOException{  
    final Stage myDialog = new Stage();  
    myDialog.initModality(Modality.APPLICATION_MODAL);  //make user unable to press the original stage/window unless close the current stage/window
    myDialog.initOwner(stage);
    
    InformationPopup controller = new InformationPopup();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Popup/InformationPopup.fxml"));
    loader.setController(controller);                        
    Scene dialogScene = new Scene((Parent)loader.load());

    controller.getLblHeading().setText(heading);
    controller.getLblMessage().setText(message);
    controller.getBtnOK().setText(btnOKText);
    controller.getBtnOK().setOnAction(e -> {
      myDialog.close();      
    });
    
    myDialog.setScene(dialogScene);
    myDialog.setMaximized(false);
    myDialog.show();

    // link:https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
    PauseTransition delay = new PauseTransition(Duration.seconds(15));
    delay.setOnFinished(e -> myDialog.close());
    delay.play();
  }

  // https://medium.com/@pra4mesh/callback-function-in-java-20fa48b27797
  public void confirmationPopup(String heading, String message, Consumer<Boolean> passback) throws IOException{  
    final Stage myDialog = new Stage();  
    myDialog.initModality(Modality.APPLICATION_MODAL);  //make user unable to press the original stage/window unless close the current stage/window
    myDialog.initOwner(stage);

    ConfirmationPopup controller = new ConfirmationPopup();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Popup/ConfirmationPopup.fxml"));
    loader.setController(controller);                        
    Scene dialogScene = new Scene((Parent)loader.load());

    controller.getLblHeading().setText(heading);
    controller.getLblMessage().setText(message);
    
    controller.getBtnYes().setOnAction(e->{      
      passback.accept(true);
    });
    
    controller.getBtnNo().setOnAction(e->{      
      passback.accept(false);
    });
    
    myDialog.setScene(dialogScene);
    myDialog.setMaximized(false);
    myDialog.show();    
  }

  // https://medium.com/@pra4mesh/callback-function-in-java-20fa48b27797
  public void confirmationPopup(String heading, String message, String btnYesText, String btnNoText, Consumer<Boolean> passback) throws IOException{    
    final Stage myDialog = new Stage();
    myDialog.initModality(Modality.APPLICATION_MODAL);  //make user unable to press the original stage/window unless close the current stage/window
    myDialog.initOwner(stage);

    ConfirmationPopup controller = new ConfirmationPopup();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Popup/ConfirmationPopup.fxml"));
    loader.setController(controller);                        
    Scene dialogScene = new Scene((Parent)loader.load());

    controller.getLblHeading().setText(heading);
    controller.getLblMessage().setText(message);

    controller.getBtnYes().setText(btnYesText);
    controller.getBtnYes().setOnAction(e->{      
      passback.accept(true);
    });

    controller.getBtnNo().setText(btnNoText);
    controller.getBtnNo().setOnAction(e->{      
      passback.accept(false);
    });
    
    myDialog.setScene(dialogScene);
    myDialog.setMaximized(false);
    myDialog.show();    
  }

  public void alertInProgress(){
    stage.setOnCloseRequest(event->{
        event.consume();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Close window");
        alert.setHeaderText("Close Window?");
        alert.setContentText("Are you sure you want to close the window?");

        if(alert.showAndWait().get() == ButtonType.OK){
          stage.close();
        }
    });  
  }

  public void notAlertInProgress(){
    stage.setOnCloseRequest(e->{});
  }
  
}

