package Controller;
import Classes.*;

import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.animation.PauseTransition;
import javafx.event.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.midi.Receiver;
import javax.swing.plaf.synth.SynthSplitPaneUI;

public class BuyerHome implements Initializable{
    Person pp;
    private Stage stage;
    private Scene scene;
    private Parent root;
    // private Node node;

    @FXML
    private AnchorPane paneBuyerHome;
    @FXML
    private ImageView iconProfile;        
    @FXML
    private Label lblWelcome,linkLogout;
    @FXML
    private ImageView iconCart;   

    @Override
    public void initialize(URL location, ResourceBundle resources) {        
        
    }
    
    public void initData(Object obj) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        // PauseTransition delay = new PauseTransition(Duration.seconds(1));
        // delay.setOnFinished(e -> {            
        //     stage = (Stage) paneBuyerHome.getScene().getWindow();            
        //     Object a = stage.getUserData();
        //     Method method;
        //     String firstName;
        //     try {
        //         method = a.getClass().getMethod("getFirstName");
        //         firstName = (String) method.invoke(a);
        //         lblWelcome.setText(lblWelcome.getText()+" "+firstName);
        //     } catch (NoSuchMethodException | SecurityException e1) {
        //         // TODO Auto-generated catch block
        //         e1.printStackTrace();
        //     } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
        //         // TODO Auto-generated catch block
        //         e1.printStackTrace();
        //     }                                                 
        // });
        // delay.play();
                              
        System.out.println(obj.toString());
        Method method = obj.getClass().getMethod("getFirstName");
        String firstName = (String) method.invoke(obj);
        lblWelcome.setText(lblWelcome.getText()+" "+firstName);

        // This is to detect receive many arguments (if the parameter is (Object... obj))
        // for(Object ob : obj){
        //     if(ob.getClass().getName().equals("Person")){
        //         Method getFirstname = ob.getClass().getMethod("getFirstName");
        //         String firstName= (String)getFirstname.invoke(ob.getClass());
        //         lblWelcome.setText(lblWelcome.getText()+" "+firstName);
        //     }
        // }
               
    }

    @FXML
    void toCart(MouseEvent event) {

    }

    @FXML
    void toLogin(MouseEvent event) {

    }    

    @FXML
    void toBuyerOrder(MouseEvent event) throws IOException {
        stage = (Stage) paneBuyerHome.getScene().getWindow();
        // System.out.println(stage);
        // System.out.println(stage.getUserData());    
        
        root = FXMLLoader.load(getClass().getClassLoader().getResource("View/BuyerOrder.fxml"));        
        stage.setScene(new Scene(root));

        // reference link:https://www.youtube.com/watch?v=ZJtXD03-D2I  (Another way to "switch" scene)
        // AnchorPane paneBuyerOrder = FXMLLoader.load(getClass().getResource("../View/BuyerOrder.fxml"));
        // paneBuyerHome.getChildren().removeAll();
        // paneBuyerHome.getChildren().setAll(paneBuyerOrder);
    }   

    void sentData() throws IOException{
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/BuyerHome.fxml"));
        // Parent root = loader.load();

        // BuyerHome controllerBuyerHome = loader.getController();
        // controllerBuyerHome.
    }

    void receiveData(Person p){
        System.out.println("received the person!");
        this.pp = p;
        System.out.println(this.pp.getFirstName());
    }    
}
