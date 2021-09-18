package Validation;

import Controller.Popup.EditProfile;
import Controller.Register.RegBuyer;

public class BuyerFormValidator extends FormValidator {
    
    RegBuyer buyerForm;
    EditProfile editingBuyerForm;

    public BuyerFormValidator(RegBuyer buyerForm){
        super(buyerForm.getInputName(), buyerForm.getInputEmail(), buyerForm.getInputMobileNo());
        this.buyerForm = buyerForm;
    }

    public BuyerFormValidator(EditProfile editingBuyerForm){
        super(editingBuyerForm.getInputUsername(),editingBuyerForm.getInputName(), editingBuyerForm.getInputEmail(), editingBuyerForm.getInputMobileNo());
        
        System.out.println("USERNAME IS : " + editingBuyerForm.getInputUsername().getText());
        System.out.println("NAME IS : " + editingBuyerForm.getInputName().getText());
        System.out.println("EMAIL IS : " + editingBuyerForm.getInputEmail().getText());
        System.out.println("MOBILE IS : " + editingBuyerForm.getInputMobileNo().getText());

        this.editingBuyerForm = editingBuyerForm;
    }

    @Override
    public boolean validateForm(){
        return super.validateForm();
    }

    @Override
    public boolean validateAlterForm(){
        boolean accountInfoInvalid = super.validateForm();
        boolean buyerInfoIsInvalid = super.validateAlterForm();
        return accountInfoInvalid && buyerInfoIsInvalid;
    }
}
