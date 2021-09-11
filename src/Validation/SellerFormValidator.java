package Validation;
import java.util.regex.Pattern;

import Controller.Popup.EditProfile;
import Controller.Register.RegSeller;
import javafx.scene.control.TextField;

public class SellerFormValidator extends FormValidator{
    
    RegSeller sellerForms;
    EditProfile sellerEditingForm;

    public SellerFormValidator(RegSeller sellerForms) {
        super(sellerForms.getInputName(), sellerForms.getInputEmail(), sellerForms.getInputMobileNo());
        this.sellerForms = sellerForms;
    }

    public SellerFormValidator(EditProfile sellerEditingForm) {
        super(sellerEditingForm.getInputUsername(),sellerEditingForm.getInputName(), sellerEditingForm.getInputEmail(),sellerEditingForm.getInputMobileNo());
        this.sellerEditingForm = sellerEditingForm;
    }

    @Override
    public boolean validateForm(){

        TextField NRIC = sellerForms.getInputNRIC();
        TextField licenseNo = sellerForms.getInputLicenseNo();
        TextField bankAcc = sellerForms.getInputBankAccNo();

        isInvalid = super.validateForm();

        if(icIsInvalid(NRIC)){
            setTextFieldBorderToRed(NRIC);
            isInvalid = true;
        }else{
            setTextFieldBorderToGreen(NRIC);
        }

        if(licenseIsInvalid(licenseNo)){
            setTextFieldBorderToRed(licenseNo);
            isInvalid = true;
        }else{
            setTextFieldBorderToGreen(licenseNo);
        }

        if(bankAccIsInvalid(bankAcc)){
            setTextFieldBorderToRed(bankAcc);
            isInvalid = true;
        }else{
            setTextFieldBorderToGreen(bankAcc);
        }
        return isInvalid;
    }

    public boolean validateAlterForm(){

        TextField NRIC = sellerEditingForm.getInputNRIC();
        TextField licenseNo = sellerEditingForm.getInputLicenseNo();
        TextField bankAcc = sellerEditingForm.getInputBankAccNo();

        isInvalid = super.validateAlterForm();
        isInvalid = super.validateForm();

        if(icIsInvalid(NRIC)){
            setTextFieldBorderToRed(NRIC);
            isInvalid = true;
        }else{
            setTextFieldBorderToGreen(NRIC);
        }

        if(licenseIsInvalid(licenseNo)){
            setTextFieldBorderToRed(licenseNo);
            isInvalid = true;
        }else{
            setTextFieldBorderToGreen(licenseNo);
        }

        if(bankAccIsInvalid(bankAcc)){
            setTextFieldBorderToRed(bankAcc);
            isInvalid = true;
        }else{
            setTextFieldBorderToGreen(bankAcc);
        }
        return isInvalid;
    }

    public boolean icIsInvalid(TextField icField){
        String icRegex = "^\\d{6}\\-\\d{2}\\-\\d{4}$";
        String icNo = icField.getText();
        return !Pattern.matches(icRegex, icNo);
    }

    public boolean licenseIsInvalid(TextField licenseField){
        String licenseRegex = "^[0-9]{3}-[a-zA-Z]{3}-[0-9]{3}$";
        String licenseNo = licenseField.getText();
        return !Pattern.matches(licenseRegex, licenseNo);
    }

    public boolean bankAccIsInvalid(TextField bankAccField){
        //BANK ACCOUNT REFERENCE : https://www.affinonline.com/ibg_listing
        String bankAccRegex = "^\\d{10}|\\d{14}|\\d{15}|\\d{13}|\\d{12}$";
        String bankAccNo = bankAccField.getText();
        return !Pattern.matches(bankAccRegex, bankAccNo);
    }
}
