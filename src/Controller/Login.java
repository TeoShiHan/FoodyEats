package Controller;
import Classes.*;
import Cache.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;

public class Login {    
    
    private GUI gui = GUI.getInstance();

    //#region : FXML VARIABLES
    @FXML private AnchorPane paneLogin;    
    @FXML private TextField inputUsername;
    @FXML private PasswordField inputPassword;
    @FXML private Button btnLogin;
    @FXML private Label linkToRegister;
    //#endregion

    //#region : FXML METHODS
    @FXML
    void actionLogin(ActionEvent event) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{        
        Account tempAcc = new Account();        
        if(allFieldsFilled()){
            tempAcc.login(inputUsername.getText().strip(), inputPassword.getText().strip());                            
        }else{            
            gui.informationPopup("Attention", "Please fill all the blank");
        }       
    }

    @FXML
    void toRegister(MouseEvent event) throws Exception{        
        gui.toNextScene("View/RegisterInformation.fxml");        
    }
    //#endregion

    //#region : METHODS
    
    void customRealPopup(ActionEvent event){
        Label lblText = new Label("Login Successfully");
        lblText.setMinHeight(100);
        lblText.setMinWidth(100);
        lblText.setStyle(" -fx-background-color: aqua;");                

        Popup popup = new Popup();
        popup.getContent().add(lblText);        
        
        Stage currentStage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        System.out.println(currentStage);

        popup.show(currentStage);                            
        popup.setY(100);
        popup.setAutoHide(true);

        // link:https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(e -> popup.hide());
        delay.play();
    }
    
    void customPopupMessage(){
        
        //#region : STAGE
        final Stage myDialog = new Stage();
        myDialog.initModality(Modality.APPLICATION_MODAL);  //make user unable to press the original stage/window unless close the current stage/window
        //#endregion

        //#region : FXML ELEMENTS
        Text textInformation = new Text("Login Successfully");            
        Button okButton = new Button("CLOSE");     
        HBox hbox = new HBox(okButton);
        VBox root = new VBox(textInformation,hbox);            
        //#endregion
        
        //#region : LISTENER
        okButton.setOnAction(new EventHandler<ActionEvent>(){ // okButton.setPadding(new Insets(30));  //set padding     
            @Override
            public void handle(ActionEvent arg0) {
                myDialog.close();
            }            
        });
        //#endregion

        //#region : SCENE
        root.setAlignment(Pos.CENTER);
        Scene myDialogScene = new Scene(root,300,150);            
        myDialog.setScene(myDialogScene);
        myDialog.show();
        //#endregion

        //#region : TRANSITION
        // REFERENCE: link:https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(e -> myDialog.close());
        delay.play();
        //#endregion
    }

    void customPopupWarning(String title,String info){
        
        //#region : STAGE
        final Stage myDialog = new Stage();
        myDialog.initModality(Modality.APPLICATION_MODAL);  //make user unable to press the original stage/window unless close the current stage/window
        //#endregion

        //#region ：FXML ELEMENTS
        Text textTitle = new Text(title); 
        Text textInfo = new Text(info);                        
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        HBox hbox = new HBox(yesButton,noButton);
        VBox root = new VBox(textTitle,textInfo,hbox);
        //#endregion
        
        //#region : EVENT LISTENER
        yesButton.setOnAction(new EventHandler<ActionEvent>(){ // okButton.setPadding(new Insets(30)); //set padding            
            @Override
            public void handle(ActionEvent arg0) {
                myDialog.close();
            }            
        });

        noButton.setOnAction(new EventHandler<ActionEvent>(){ // okButton.setPadding(new Insets(30)); //set padding            
            @Override
            public void handle(ActionEvent arg0) {
                myDialog.close();
            }            
        });
        //#endregion

        //#region : POSITION
        hbox.setSpacing(30);
        hbox.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        //#endregion
    
        //#region ：SCENE
        Scene myDialogScene = new Scene(root,300,150);            
        myDialog.setScene(myDialogScene);
        myDialog.show();
        //#endregion
    }
    
    boolean allFieldsFilled(){
        return !(inputUsername.getText().isEmpty() || inputPassword.getText().isEmpty());
    }
    
    //#endregion

}