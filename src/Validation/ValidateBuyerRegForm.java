package Validation;

import Controller.Register.RegBuyer;

public class ValidateBuyerRegForm extends ValidateRegForm {
    RegBuyer buyerForm;
    
    public ValidateBuyerRegForm(RegBuyer buyerForm){
        super(buyerForm.getInputName(), buyerForm.getInputEmail(), buyerForm.getInputMobileNo());
        this.buyerForm = buyerForm;
    }

    @Override
    public boolean validateForm(){
        return super.validateForm();
    }
}
