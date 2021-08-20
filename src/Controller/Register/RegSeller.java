package Controller.Register;
import Cache.*;

import java.util.HashMap;

import javax.imageio.ImageIO;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegSeller {
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();

    @FXML private TextField inputName,inputEmail,inputMobileNo,inputAddress,inputNRIC,inputLicenseNo,inputBankAccNo;

    public TextField getInputName() {
        return inputName;
    }
    public void setInputName(TextField inputName) {
        this.inputName = inputName;
    }
    public TextField getInputEmail() {
        return inputEmail;
    }
    public void setInputEmail(TextField inputEmail) {
        this.inputEmail = inputEmail;
    }
    public TextField getInputMobileNo() {
        return inputMobileNo;
    }
    public void setInputMobileNo(TextField inputMobileNo) {
        this.inputMobileNo = inputMobileNo;
    }
    public TextField getInputAddress() {
        return inputAddress;
    }
    public void setInputAddress(TextField inputAddress) {
        this.inputAddress = inputAddress;
    }        
    public TextField getInputNRIC() {
        return inputNRIC;
    }
    public void setInputNRIC(TextField inputNRIC) {
        this.inputNRIC = inputNRIC;
    }
    public TextField getInputLicenseNo() {
        return inputLicenseNo;
    }
    public void setInputLicenseNo(TextField inputLicenseNo) {
        this.inputLicenseNo = inputLicenseNo;
    }
    public TextField getInputBankAccNo() {
        return inputBankAccNo;
    }
    public void setInputBankAccNo(TextField inputBankAccNo) {
        this.inputBankAccNo = inputBankAccNo;
    }    

    public boolean isFilled(){                
        return !(inputName.getText().isEmpty() || inputEmail.getText().isEmpty() || 
                inputMobileNo.getText().isEmpty() || inputAddress.getText().isEmpty() ||
                inputNRIC.getText().isEmpty() || inputLicenseNo.getText().isEmpty() ||
                inputBankAccNo.getText().isEmpty());
    }

    public void getInfo(){        
        data.addObjectHolder("accountName", inputName.getText());
        data.addObjectHolder("accountEmail", inputEmail.getText());
        data.addObjectHolder("accountMobileNo", inputMobileNo.getText());
        // data.addObjectHolder("accountRegDate", LocalDate.now());
        data.addObjectHolder("sellerAddress", inputAddress.getText());
        data.addObjectHolder("sellerNRIC", inputNRIC.getText());
        data.addObjectHolder("sellerLicenseNo", inputLicenseNo.getText());
        data.addObjectHolder("sellerBankAccNo", inputBankAccNo.getText());        
    }
}
