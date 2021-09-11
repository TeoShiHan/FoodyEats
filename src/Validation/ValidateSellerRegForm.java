package Validation;
import java.util.regex.Pattern;
import Controller.Register.RegSeller;
import javafx.scene.control.TextField;

public class ValidateSellerRegForm extends ValidateRegForm{
    
    RegSeller sellerForms;

    public ValidateSellerRegForm(RegSeller sellerForms) {
        super(sellerForms.getInputName(), sellerForms.getInputEmail(), sellerForms.getInputMobileNo());
        this.sellerForms = sellerForms;
    }

    @Override
    public boolean validateForm(){

        TextField NRIC = sellerForms.getInputNRIC();
        TextField licenseNo = sellerForms.getInputLicenseNo();
        TextField bankAcc = sellerForms.getInputBankAccNo();

        isInvalid = super.validateForm();

        if(icIsInvalid()){
            setTextFieldBorderToRed(NRIC);
            isInvalid = true;
        }else{
            setTextFieldBorderToGreen(NRIC);
        }

        if(licenseIsInvalid()){
            setTextFieldBorderToRed(licenseNo);
            isInvalid = true;
        }else{
            setTextFieldBorderToGreen(licenseNo);
        }

        if(bankAccIsInvalid()){
            setTextFieldBorderToRed(bankAcc);
            isInvalid = true;
        }else{
            setTextFieldBorderToGreen(bankAcc);
        }
        return isInvalid;
    }

    public boolean icIsInvalid(){
        String icRegex = "^\\d{6}\\-\\d{2}\\-\\d{4}$";
        String icNo = sellerForms.getInputNRIC().getText();
        return !Pattern.matches(icRegex, icNo);
    }

    public boolean licenseIsInvalid(){
        String licenseRegex = "^[0-9]{3}-[a-zA-Z]{3}-[0-9]{3}$";
        String licenseNo = sellerForms.getInputLicenseNo().getText();
        return !Pattern.matches(licenseRegex, licenseNo);
    }

    public boolean bankAccIsInvalid(){
        //BANK ACCOUNT REFERENCE : https://www.affinonline.com/ibg_listing
        String bankAccRegex = "^\\d{10}|\\d{14}|\\d{15}|\\d{13}|\\d{12}$";
        String bankAccNo = sellerForms.getInputBankAccNo().getText();
        return !Pattern.matches(bankAccRegex, bankAccNo);
    }
}
