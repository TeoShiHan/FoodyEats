package Controller.Register;
import Classes.*;

import Validation.*;

import java.util.regex.Pattern;

import Cache.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegSeller {
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
        return !(inputName.getText().strip().isEmpty() || inputEmail.getText().strip().isEmpty() || 
                inputMobileNo.getText().strip().isEmpty() || inputAddress.getText().strip().isEmpty() ||
                inputNRIC.getText().strip().isEmpty() || inputLicenseNo.getText().strip().isEmpty() ||
                inputBankAccNo.getText().strip().isEmpty());
    }

    public void getInfo(){        
        Seller seller = new Seller();
        seller.setAccType("Seller");
        seller.setName(inputName.getText());
        seller.setEmail(inputEmail.getText());
        seller.setMobileNo(inputMobileNo.getText());
        seller.setAddress(inputAddress.getText());
        seller.setNRIC(inputNRIC.getText());
        seller.setLicenseNumber(inputLicenseNo.getText());
        seller.setBankAcc(inputBankAccNo.getText());
        data.setAccount(seller);
    }

    public boolean detectedInvalidFields(){
        SellerFormValidator formValidator = new SellerFormValidator(this);
        return formValidator.validateForm();
    }
}
