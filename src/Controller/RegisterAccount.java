package Controller;
import java.io.IOException;

import Cache.*;
import Classes.Account;
import Classes.Buyer;
import Classes.Seller;
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
    void actionRegister(ActionEvent event) throws IOException {
        String uname = inputUsername.getText();
        String pwd = inputPassword.getText();
        String confirmPwd = inputConfirmPassword.getText();
        if(!(uname.isEmpty() || pwd.isEmpty() || confirmPwd.isEmpty())){
            if(pwd.equals(confirmPwd)){                                
                System.out.println(data.getWholeObjectHolder());
                String className = data.getAccount().getClass().getName();
                System.out.println(className.substring(className.indexOf(".")+1));
                switch(className.substring(className.indexOf(".")+1)){
                    case "Buyer":
                        data.getBuyer().setUsername(uname);
                        data.getBuyer().setPassword(pwd);
                        data.getBuyer().register();
                        break;           
                    case "Rider":
                        data.getRider().setUsername(uname);
                        data.getRider().setPassword(pwd);
                        data.getRider().register();
                        break;           
                    case "Seller":
                        data.getSeller().setUsername(uname);
                        data.getSeller().setPassword(pwd);
                        data.getSeller().register();
                        break;  
                    default:

                }
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
