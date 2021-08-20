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
import java.util.HashMap;

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
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();

    @FXML private AnchorPane paneLogin;    
    @FXML private TextField inputUsername,inputPassword;
    @FXML private Button btnLogin;
    @FXML private Label linkToRegister;

    @FXML
    void actionLogin(ActionEvent event) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{        
        HashMap<String,Object> acc = db.readOne(String.format("SELECT * FROM Account WHERE username='%s' AND password='%s' LIMIT 1",inputUsername.getText().strip(),inputPassword.getText().strip()));
        if(acc!=null){                         
            HashMap<String,Object> childAcc = db.readOne(String.format("SELECT * FROM %s WHERE accountID='%s'",acc.get("type"),acc.get("accountID")));            
            if(acc.get("type").equals("Buyer")){                
                Buyer buyer = new Buyer(
                    acc.get("accountID"),acc.get("username"),acc.get("password"),acc.get("name"),
                    acc.get("email"),acc.get("mobileNo"),acc.get("type"),childAcc.get("buyerID"),
                    childAcc.get("address"),childAcc.get("cartID")
                );                
                data.setBuyer(buyer);                                
            }else if(acc.get("type").equals("Rider")){
                Rider rider = new Rider(
                    acc.get("accountID"),acc.get("username"),acc.get("password"),acc.get("name"),
                    acc.get("email"),acc.get("mobileNo"),acc.get("type"),childAcc.get("riderID"),
                    childAcc.get("vehicleID")
                );                      
                data.setRider(rider);
            }else if(acc.get("type").equals("Seller")){  
                Seller seller = new Seller(
                    acc.get("accountID"),acc.get("username"),acc.get("password"),acc.get("name"),
                    acc.get("email"),acc.get("mobileNo"),acc.get("type"),childAcc.get("sellerID"),
                    childAcc.get("address"),childAcc.get("NRIC"),childAcc.get("licenseNumber"),
                    childAcc.get("bankAcc"),childAcc.get("shopID")
                );                      
                data.setSeller(seller);              
                // data.setBuyer(new Buyer(...));                
            }
            gui.toNextScene(String.format("View/%sHome.fxml",acc.get("type")));    
        }   
        // if(Account.login(inputUsername.getText().strip(), inputPassword.getText().strip())){            
        //     gui.toNextScene(String.format("View/%sHome.fxml",data.getObjectHolder("type")));    
        // }else{
        //     customPopupWarning("Warning","Invalid credentials!");
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

    // private <T> T castObject(Class<T> clazz, Object object) {
    //     return (T) object;
    //   }
}