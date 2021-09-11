package Validation;

import java.util.regex.Pattern;

import Controller.Popup.EditShopProfile;
import Controller.Register.RegShop;
import javafx.scene.control.TextField;

public class ShopFormValidator extends FormValidator{
    RegShop shopForm;
    EditShopProfile alterForm;

    public ShopFormValidator(RegShop shopForm) {
        super();
        this.shopForm = shopForm;
    }

    public ShopFormValidator(EditShopProfile alterForm) {
        super();
        this.alterForm = alterForm;
    }
    
    @Override
    public boolean validateForm(){
        TextField tel = shopForm.getInputShopTelNo();
        if(telIsInvalid(tel)){
            setTextFieldBorderToRed(tel);
            isInvalid = true;
        }else{
            setTextFieldBorderToGreen(tel);
        }
        return isInvalid;
    }

    public boolean validateAlterForm(){
        TextField tel = alterForm.getInputTelNo();
        if(telIsInvalid(tel)){
            setTextFieldBorderToRed(tel);
            isInvalid = true;
        }else{
            setTextFieldBorderToGreen(tel);
        }
        return isInvalid;
    }

    public boolean telIsInvalid(TextField telField){
        String telRegex = "^(0\\d-\\d{7})$|^(0\\d{2}-\\d{6})$";
        String tel = telField.getText();
        return !Pattern.matches(telRegex, tel);
    }
}
