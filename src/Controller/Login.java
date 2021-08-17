package Controller;
import Classes.*;  //invoke the all the class in the Classes package
import Cache.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.Format.Field;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;

public class Login {    
    private static GUI gui = GUI.getInstance();
    private Stage stage;
    // private Scene scene;
    private Parent root;

    @FXML private AnchorPane paneLogin;    
    @FXML private TextField inputUsername,inputPassword;
    @FXML private Button btnLogin;
    @FXML private Label linkToRegister;

    @FXML
    void actionLogin(ActionEvent event) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        // gui.toNextScene("View/BuyerHome.fxml");
        gui.toNextScene("View/SellerHome.fxml");
        // if(inputUsername.getText().strip().equals("khoo") && inputPassword.getText().strip().equals("ce")){            
        //     // BuyerHome controller = new BuyerHome();
        //     // controller.receiveData(this.p);            
        //     // System.out.println(paneLogin.getChildren());                                                                                
            
        //     gui.toNextScene("View/BuyerHome.fxml");

        //     // toNextScene("View/BuyerHome.fxml");
        //     // sendData(event);                        
        // }else{          
        //     customRealPopup(event);
        //     // customPopupMessage();
        //     // customPopupWarning("Warning","Invalid credentials!");
        //     // System.out.println("Invalid credentials!");
        // }         
    }

    @FXML
    void toRegister(MouseEvent event) throws Exception{        
        gui.toNextScene("View/RegisterInformation.fxml");        
    }

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
        final Stage myDialog = new Stage();
        myDialog.initModality(Modality.APPLICATION_MODAL);  //make user unable to press the original stage/window unless close the current stage/window

        Text textInformation = new Text("Login Successfully");            

        Button okButton = new Button("CLOSE");            
        // okButton.setPadding(new Insets(30));  //set padding            
        okButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                myDialog.close();
            }            
        });
        HBox hbox = new HBox(okButton);
        // hbox.setMargin(hbox,new Insets(100,0,0,0));
        // hbox.setSpacing(30);

        VBox root = new VBox(textInformation,hbox);            
        root.setAlignment(Pos.CENTER);
    
        Scene myDialogScene = new Scene(root,300,150);            
        
        // myDialog.setX(500);
        // myDialog.setY(50);
        myDialog.setScene(myDialogScene);
        myDialog.show();

        // link:https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(e -> myDialog.close());
        delay.play();
    }

    void customPopupWarning(String title,String info){
        final Stage myDialog = new Stage();
        myDialog.initModality(Modality.APPLICATION_MODAL);  //make user unable to press the original stage/window unless close the current stage/window

        Text textTitle = new Text(title); 
        Text textInfo = new Text(info);                        

        Button yesButton = new Button("Yes");
        // okButton.setPadding(new Insets(30));  //set padding            
        yesButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                myDialog.close();
            }            
        });
        Button noButton = new Button("No");
        // okButton.setPadding(new Insets(30));  //set padding            
        noButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                myDialog.close();
            }            
        });
        HBox hbox = new HBox(yesButton,noButton);
        // hbox.setMargin(hbox,new Insets(100,0,0,0);
        hbox.setSpacing(30);
        hbox.setAlignment(Pos.CENTER);

        VBox root = new VBox(textTitle,textInfo,hbox);
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
    
        Scene myDialogScene = new Scene(root,300,150);            
        
        // myDialog.setX(500);
        // myDialog.setY(50);
        myDialog.setScene(myDialogScene);
        myDialog.show();

        // PauseTransition delay = new PauseTransition(Duration.seconds(5));
        // delay.setOnFinished(e -> myDialog.close());
        // delay.play();
    }    

    // void sendData(ActionEvent event) throws IOException{
    //     FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/BuyerHome.fxml"));
    //     root = loader.load();

    //     BuyerHome controllerBuyerHome = (BuyerHome)loader.getController();
    //     controllerBuyerHome.receiveData(this.p);

    //     stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    //     scene = new Scene(root);         
    //     stage.setScene(scene);        
    //     stage.show();
    // }    

    // public void toNextScene(String ResourcePath) throws NoSuchMethodException, SecurityException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
    //     stage = (Stage) paneLogin.getScene().getWindow();
        
    //     FXMLLoader loader = new FXMLLoader();
    //     // loader.setLocation(getClass().getResource("/View/BuyerOrder.fxml"));  //this is similar to next line, this line should add '/' infront of the path
    //     // loader.setLocation(getClass().getClassLoader().getResource("View/BuyerOrder.fxml"));
    //     loader.setLocation(getClass().getClassLoader().getResource(ResourcePath));
    //     root = loader.load();                        
            
    //     // passDataToNextScene(loader);        

    //     // stage.setUserData(this.p);
    //     stage.setScene(new Scene(root));   
    // }

    // public void passDataToNextScene(FXMLLoader loader) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{        
    //     // Original way to get the controller of next scene
    //     // BuyerHome ctrl = loader.getController();          
    //     // System.out.println("crtl: "+ctrl);        

    //     // More flexible way to get the controller of next scene (this could only pass 1 object as argument)
    //     Class<?> classType = loader.getController().getClass();
    //     Object controller = classType.cast(loader.getController());        
    //     System.out.println("controller: "+controller.getClass().getMethods());
    //     Method method = controller.getClass().getDeclaredMethod("initData",Object.class);
    //     System.out.println(method);
    //     System.out.println(method.getName());
    //     method.invoke(controller, this.p);
    //     // If want to invoke more than one method
    //     // for(Method mtd : controller.getClass().getMethods()){
    //     //     System.out.println(mtd.getName());
    //     // }         

    //     // // This is to detect passing many different Object as the arguments
    //     // Class<?> classType = loader.getController().getClass();
    //     // Object controller = classType.cast(loader.getController());    
    //     // // System.out.println("controller: "+controller.getClass().getMethods());
    //     // Method method = controller.getClass().getDeclaredMethod("initData",new Class[]{Object[].class});
    //     // System.out.println(method);
    //     // System.out.println(method.getName());
    //     // Object[] arguments = {this.p,this.p};
    //     // method.invoke(controller, arguments);
    //     // // If want to invoke more than one method
    //     // // for(Method mtd : controller.getClass().getMethods()){
    //     // //     System.out.println(mtd.getName());
    //     // // }         
    // }

    // private <T> T castObject(Class<T> clazz, Object object) {
    //     return (T) object;
    //   }
}