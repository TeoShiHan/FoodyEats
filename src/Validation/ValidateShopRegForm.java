package Validation;

import java.util.regex.Pattern;

import Controller.Register.RegShop;
import javafx.scene.control.TextField;

public class ValidateShopRegForm extends ValidateRegForm{
    RegShop shopForm;

    public ValidateShopRegForm(RegShop shopForm) {
        super();
        this.shopForm = shopForm;
    }
    
    @Override
    public boolean validateForm(){
        TextField tel = shopForm.getInputShopTelNo();
        if(telIsInvalid()){
            setTextFieldBorderToRed(tel);
            isInvalid = true;
        }else{
            setTextFieldBorderToGreen(tel);
        }
        return isInvalid;
    }

    public boolean telIsInvalid(){
        String telRegex = "^(0\\d-\\d{7})$|^(0\\d{2}-\\d{6})$";
        String tel = shopForm.getInputShopTelNo().getText();
        return !Pattern.matches(telRegex, tel);
    }
}
