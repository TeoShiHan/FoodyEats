package Validation;

import java.util.regex.Pattern;

import Controller.Popup.EditProfile;
import javafx.scene.control.TextField;

public class AdminFormValidator extends FormValidator{
    EditProfile adminForm;

    public AdminFormValidator(EditProfile adminForm){
        super(adminForm.getInputUsername(),adminForm.getInputName(), adminForm.getInputEmail(), adminForm.getInputMobileNo());
        this.adminForm = adminForm;
    }

    @Override
    public boolean validateForm() {
        TextField NRIC = adminForm.getInputNRIC();
        TextField companyBranch = adminForm.getInputCompanyBranch();

        super.validateAlterForm();
        isInvalid = super.validateForm();

        if(icIsInvalid(NRIC)){
            setTextFieldBorderToRed(NRIC);
            isInvalid = true;
        }else{
            setTextFieldBorderToGreen(NRIC);
        }

        if(branchIsInvalid(companyBranch)){
            setTextFieldBorderToRed(companyBranch);
            isInvalid = true;
        }else{
            setTextFieldBorderToGreen(companyBranch);
        }
        
        return super.validateForm();
    }

    public boolean icIsInvalid(TextField icField){
        String icRegex = "^\\d{6}\\-\\d{2}\\-\\d{4}$";
        String icNo = icField.getText();
        return !Pattern.matches(icRegex, icNo);
    }

    public boolean branchIsInvalid(TextField branchNoField){
        String branchNoRegex = "^[0-9]{3}-[a-zA-Z]{3}-[0-9]{3}$";
        String branchNo = branchNoField.getText();
        return !Pattern.matches(branchNoRegex, branchNo);
    }
}
