
import javafx.geometry.Insets;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *
 * @author Nathan Ting
 */
public class TestGUI extends Application {
    private static final double WINDOW_WIDTH = 800 ;
    private static final double WINDOW_HEIGHT = 800 ;
    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("JavaFX Welcome");
        primaryStage.show();
       
        // GridPane grid = new GridPane();
     
        // grid.setAlignment(Pos.CENTER);
        // grid.setHgap(20);
        // grid.setVgap(20);
        // grid.setPadding(new Insets(20, 20, 20, 20));
        // grid.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        // grid.setMinSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        WebView wv = new WebView();
        wv.getEngine().load("http://google.com");
        // WebEngine we = new WebEngine();
        // we = wv.getEngine();
        // we.load("www.google.com");

        VBox vBox = new VBox(wv);        

        Scene scene = new Scene(vBox, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        
        // Text scenetitle = new Text("Registration Details");
        // scenetitle.setFont(Font.font("Roboto", FontWeight.BOLD, 20));
        // grid.add(scenetitle, 0, 0, 2, 1);

        // Label usrNmLbl = new Label("UserName:");
        // grid.add(usrNmLbl, 0, 1);

        // TextField userName = new TextField();
        // grid.add(userName, 1, 1);

        // Label pwLbl = new Label("Password:");
        // grid.add(pwLbl, 0, 2);

        // PasswordField password = new PasswordField();
        // grid.add(password, 1, 2);
        
        // Label nameLbl = new Label("Name:");
        // grid.add(nameLbl, 0, 3);

        // TextField name = new TextField();
        // grid.add(name, 1, 3);

        // Label nricLbl = new Label("NRIC No:");
        // grid.add(nricLbl, 0, 4);

        // TextField nricNo = new TextField();
        // grid.add(nricNo, 1, 4);
        
        // Label addressLbl = new Label("Address:");
        // grid.add(addressLbl, 0, 5);

        // TextField address = new TextField();
        // grid.add(address, 1, 5);

        // Label mobNumLbl = new Label("Mobile Number:");
        // grid.add(mobNumLbl, 0, 6);

        // TextField mobNum = new TextField();
        // grid.add(mobNum, 1, 6);
        
        // Label emailLbl = new Label("Email:");
        // grid.add(emailLbl, 0, 7);

        // TextField email = new TextField();
        // grid.add(email, 1, 7);

        // ClassLoader classLoader = getClass().getClassLoader();
        // System.out.println(classLoader);
        // File file = new File("Images/temp.jpeg");
        // System.out.println(file.exists());
        // System.out.println(file.canExecute());
        // System.out.println(file.canRead());
        // System.out.println(file.canWrite());
        // System.out.println(file.isFile());
        // System.out.println(file.getAbsolutePath());
        // // System.out.println(file.getPath());
        // System.out.println(file.getName());
        // System.out.println("uri - "+file.toURI());
        // System.out.println("path - "+file.toPath());
        // System.out.println();
        // Image img = new Image(getClass().getResourceAsStream(file.toString()));
        // ImageView imgView = new ImageView(img);        
        // grid.add(imgView, 0, 9);

        // Button register = new Button("Register");
        // grid.add(register, 1, 9);
        
        // Label random = new Label("registered");
        
        // Popup popup = new Popup();
        
        // popup.getContent().add(random);
        
        // EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent e)
        //     {
        //     }
        // };
  
        // // when button is pressed
        // register.setOnAction(event);        

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
