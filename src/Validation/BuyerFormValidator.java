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
        this.editingBuyerForm = editingBuyerForm;
    }

    @Override
    public boolean validateForm(){
        return super.validateForm();
    }

    public boolean validateAlterForm(){
        return super.validateAlterForm() && super.validateForm();
    }
}
