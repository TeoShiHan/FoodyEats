package Controller.Register;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Buyer {
    @FXML private TextField inputName,inputNRIC,inputAddress,inputMobileNo,inputEmail;

    public TextField getInputName() {
        return inputName;
    }
    public void setInputName(TextField inputName) {
        this.inputName = inputName;
    }

    public TextField getInputNRIC() {
        return inputNRIC;
    }
    public void setInputNRIC(TextField inputNRIC) {
        this.inputNRIC = inputNRIC;
    }

    public TextField getInputAddress() {
        return inputAddress;
    }
    public void setInputAddress(TextField inputAddress) {
        this.inputAddress = inputAddress;
    }

    public TextField getInputMobileNo() {
        return inputMobileNo;
    }
    public void setInputMobileNo(TextField inputMobileNo) {
        this.inputMobileNo = inputMobileNo;
    }

    public TextField getInputEmail() {
        return inputEmail;
    }
    public void setInputEmail(TextField inputEmail) {
        this.inputEmail = inputEmail;
    }
}
