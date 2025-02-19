package Controller;

import Cache.*;
import Classes.*;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterAccount {
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();

    @FXML
    private TextField inputUsername, inputPassword, inputConfirmPassword;
    @FXML
    private Button btnRegister, btnBack;

    @FXML
    void actionRegister(ActionEvent event) throws IOException {
        String uname = inputUsername.getText().strip();
        String pwd = inputPassword.getText().strip();
        String confirmPwd = inputConfirmPassword.getText().strip();
        if (!(uname.isEmpty() || pwd.isEmpty() || confirmPwd.isEmpty())) {
            if (pwd.equals(confirmPwd)) {

                if (db.readOne(String.format("SELECT * FROM Account WHERE username='%s'", uname)) == null) {
                    data.getAccount().setUsername(uname);
                    data.getAccount().setPassword(pwd);
                    data.getAccount().register();
                } else {
                    System.out.println("not alloweds");
                    gui.informationPopup("Account Registered",
                            "The username has been used, please use another username");
                }
            } else {
                gui.informationPopup("Warning", "Password aren't match, please try again!");
                // pop up window
            }
        } else {
            gui.informationPopup("Attention", "Please fill in all the blank.");
            // pop up window
        }
    }

    @FXML
    void toBack(ActionEvent event) throws IOException {
        gui.toPrevScene();
    }
}
