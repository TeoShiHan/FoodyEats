package Validation;
import java.lang.ModuleLayer.Controller;
import java.util.regex.Pattern;

import Controller.Register.RegBuyer;
import javafx.scene.control.TextField;

interface ValidateForm {

    public void validateForm(TextField name, TextField email);
    public void validateForm(TextField icNo, TextField licenseNo, TextField bankAccNo);
}

abstract class Validation {
    
    public void validateForm(){
        
    }

    public boolean nameIsInvalid(String name){
        String fullNameRegex = "^[a-zA-Z]{4,}(?: [a-zA-Z]+)?(?: [a-zA-Z]+)?$";
        return !Pattern.matches(fullNameRegex, name);
    }   

    public boolean emailIsInvalid(String email){
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return !Pattern.matches(emailRegex, email);
    }
}

class ValidateBuyerRegFrom extends Validation{
    
}

class ValidateSellerRegFrom extends Validation{

    RegBuyer buyerFormController;
    
    //#region : CONSTRUCTORS
    public ValidateSellerRegFrom(RegBuyer buyerFormController) {
        this.buyerFormController = buyerFormController;
    }
    //#endregion

    public RegBuyer getBuyerFormController() {
        return buyerFormController;
    }

    //#region : GETTER && SETTER
    public void setBuyerFormController(RegBuyer buyerFormController) {
        this.buyerFormController = buyerFormController;
    }

    @Override
    public void validateForm(){
        buyerFormController.getInputName().getText();
    }

    //#region : METHODS
    public boolean icIsInvalid(String icNo){
        String icRegex = "^\\d{6}\\-\\d{2}\\-\\d{4}$";
        return !Pattern.matches(icRegex, icNo);
    }

    public boolean licenseIsInvalid(String licenseNo){
        String licenseRegex = "^[0-9]{3}-[a-zA-Z]{3}-[0-9]{3}$";
        return !Pattern.matches(licenseRegex, licenseNo);

    }

    public boolean bankAccIsInvalid(String backAccNo){
        //BANK ACCOUNT REFERENCE : https://www.affinonline.com/ibg_listing
        String bankAccRegex = "^\\d{10} || \\d{14} || \\d{15} || \\d{13} || \\d{12}$";
        return !Pattern.matches(bankAccRegex, backAccNo);
    }
    //#endregion
}

class ValidateRiderRegFrom extends Validation{
    
    
    @Override
    public void validateForm(){
        
    }

    public boolean brandIsInvalid(String carBrand){
        String brandRegex = "^([A-Z][a-z]*)((\s||-)[A-Z][a-z]*){0,2}$";
        return !Pattern.matches(brandRegex, carBrand);
    }

    public boolean plateNumIsInvalid(String plateNum){
        String carPlateRegex = "^[A-Z]{1,3}(\s?)[0-9]{1,4}(\s?)[A-Z]{0,3}$";
        return !Pattern.matches(carPlateRegex, plateNum);
    }

    public boolean modelIsInvalid(String model){
        String carPlateRegex = "^([A-Z]||-||[a-z]||[0-9])*(\s([A-Z]||-||[a-z]||[0-9])*)*?$";
        return !Pattern.matches(carPlateRegex, model);
    }

    public boolean colorIsInvalid(String color){
        String colorRegex = "^\\S[A-Za-z\s]*$";
        return !Pattern.matches(colorRegex, color);
    }
}