package Controller;
import Cache.*;
import Classes.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterAccount {
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();

    @FXML private TextField inputUsername,inputPassword,inputConfirmPassword;
    @FXML private Button btnRegister;

    @FXML
    void actionRegister(ActionEvent event) {
        String uname = inputUsername.getText();
        String pwd = inputPassword.getText();
        String confirmPwd = inputConfirmPassword.getText();
        if(!(uname.isEmpty() || pwd.isEmpty() || confirmPwd.isEmpty())){
            if(pwd.equals(confirmPwd)){                
                Account.register();
                System.out.println(data.getWholeObjectHolder());
            }else{
                // pop up window
            }
        }else{
            // pop up window
        }
    }

    // public TextField getInputUsername() {
    //     return inputUsername;
    // }

    // public void setInputUsername(TextField inputUsername) {
    //     this.inputUsername = inputUsername;
    // }

    // public TextField getInputPassword() {
    //     return inputPassword;
    // }

    // public void setInputPassword(TextField inputPassword) {
    //     this.inputPassword = inputPassword;
    // }    
}
