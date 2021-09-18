package Controller.Popup;
import Cache.*;
import Classes.*;
import Validation.AdminFormValidator;
import Validation.BuyerFormValidator;
import Validation.RiderFormValidator;
import Validation.SellerFormValidator;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class EditProfile implements Initializable{ 
    private DataHolder data = DataHolder.getInstance();

    @FXML private TextField inputUsername,inputPassword,inputName,inputEmail,inputMobileNo,inputAddress,inputBankAccNo,inputNRIC,inputLicenseNo,inputCompanyBranch;
    @FXML private Button btnYes,btnNo;
    @FXML private VBox vboxBankAcc,vboxAddress,vboxNRIC,vboxLicenseNo,vboxCompanyBranch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        inputUsername.setText(data.getAccount().getUsername());
        inputPassword.setText(data.getAccount().getPassword());
        inputName.setText(data.getAccount().getName());
        inputEmail.setText(data.getAccount().getEmail());
        inputMobileNo.setText(data.getAccount().getMobileNo());
        
        if(!(data.getAccount() instanceof Seller)){ 
            vboxAddress.setManaged(false);           
            vboxAddress.setVisible(false);
            vboxBankAcc.setManaged(false);
            vboxBankAcc.setVisible(false);
            vboxNRIC.setManaged(false);
            vboxNRIC.setVisible(false);
            vboxLicenseNo.setManaged(false);
            vboxLicenseNo.setVisible(false);        
        }

        if(data.getAccount() instanceof Buyer){
            vboxAddress.setManaged(true);
            vboxAddress.setVisible(true);
            inputAddress.setText(((Buyer)data.getAccount()).getAddress());
        }

        if(data.getAccount() instanceof Seller){
            vboxAddress.setManaged(true);
            vboxAddress.setVisible(true);
            inputAddress.setText(((Seller)data.getAccount()).getAddress());
            inputBankAccNo.setText(((Seller)data.getAccount()).getBankAcc());
            inputNRIC.setText(((Seller)data.getAccount()).getNRIC());
            inputLicenseNo.setText(((Seller)data.getAccount()).getLicenseNumber());        
        }

        if(data.getAccount() instanceof Admin){
            vboxNRIC.setManaged(true);
            vboxNRIC.setVisible(true);            
            inputNRIC.setText(((Admin)data.getAccount()).getNRIC());
            inputCompanyBranch.setText(((Admin)data.getAccount()).getCompanyBranch());            
        }else{
            vboxCompanyBranch.setManaged(false);
            vboxCompanyBranch.setVisible(false);
        }
    }

    public Button getBtnYes() {
        return btnYes;
    }

    public void setBtnYes(Button btnYes) {
        this.btnYes = btnYes;
    }

    public Button getBtnNo() {
        return btnNo;
    }

    public void setBtnNo(Button btnNo) {
        this.btnNo = btnNo;
    }

    public TextField getInputUsername() {
        return inputUsername;
    }

    public void setInputUsername(TextField inputUsername) {
        this.inputUsername = inputUsername;
    }

    public TextField getInputPassword() {
        return inputPassword;
    }

    public void setInputPassword(TextField inputPassword) {
        this.inputPassword = inputPassword;
    }

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

    public TextField getInputBankAccNo() {
        return inputBankAccNo;
    }

    public void setInputBankAccNo(TextField inputBankAccNo) {
        this.inputBankAccNo = inputBankAccNo;
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

    public TextField getInputCompanyBranch() {
        return inputCompanyBranch;
    }

    public void setInputCompanyBranch(TextField inputCompanyBranch) {
        this.inputCompanyBranch = inputCompanyBranch;
    }

    public boolean detectedInvalidSellerInfo(){
        SellerFormValidator formValidator = new SellerFormValidator(this);
        return formValidator.validateAlterForm();
    }

    public boolean detectedInvalidBuyerInfo(){
        BuyerFormValidator formValidator = new BuyerFormValidator(this);
        System.out.println("DETECTED INVALID BUYER INFO IS : " + formValidator.validateAlterForm());
        return formValidator.validateAlterForm();
    }

    public boolean detectedInvalidAdminInfo(){
        AdminFormValidator formValidator = new AdminFormValidator(this);
        return formValidator.validateForm();
    }

    public boolean detectedInvalidRiderInfo(){
        RiderFormValidator formValidator = new RiderFormValidator(this);
        return formValidator.validateRiderProfile();
    }
}