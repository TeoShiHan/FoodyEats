
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
       
        GridPane grid = new GridPane();
     
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        grid.setMinSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        Scene scene = new Scene(grid, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        
        Text scenetitle = new Text("Registration Details");
        scenetitle.setFont(Font.font("Roboto", FontWeight.BOLD, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label usrNmLbl = new Label("UserName:");
        grid.add(usrNmLbl, 0, 1);

        TextField userName = new TextField();
        grid.add(userName, 1, 1);

        Label pwLbl = new Label("Password:");
        grid.add(pwLbl, 0, 2);

        PasswordField password = new PasswordField();
        grid.add(password, 1, 2);
        
        Label nameLbl = new Label("Name:");
        grid.add(nameLbl, 0, 3);

        TextField name = new TextField();
        grid.add(name, 1, 3);

        Label nricLbl = new Label("NRIC No:");
        grid.add(nricLbl, 0, 4);

        TextField nricNo = new TextField();
        grid.add(nricNo, 1, 4);
        
        Label addressLbl = new Label("Address:");
        grid.add(addressLbl, 0, 5);

        TextField address = new TextField();
        grid.add(address, 1, 5);

        Label mobNumLbl = new Label("Mobile Number:");
        grid.add(mobNumLbl, 0, 6);

        TextField mobNum = new TextField();
        grid.add(mobNum, 1, 6);
        
        Label emailLbl = new Label("Email:");
        grid.add(emailLbl, 0, 7);

        TextField email = new TextField();
        grid.add(email, 1, 7);

        Button register = new Button("Register");
        grid.add(register, 1, 9);
        
        Label random = new Label("registered");
        
        Popup popup = new Popup();
        
        popup.getContent().add(random);
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e)
            {
            }
        };
  
        // when button is pressed
        register.setOnAction(event);

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
