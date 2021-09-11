package Validation;

import java.util.regex.Pattern;

import javafx.scene.control.TextField;

public abstract class ValidateRegForm {
    
    protected boolean isInvalid = false;
    private TextField nameField;
    private TextField emailField;
    private TextField mobileField;

    ValidateRegForm(TextField nameField, TextField emailField, TextField mobileField){
        this.nameField = nameField;
        this.emailField = emailField;
        this.mobileField = mobileField;
            /*DEBUG MSG*/System.out.println("Parent class name : " + this.nameField.getText());
    }

    ValidateRegForm(){

    }
    
    public boolean validateForm(){
        if(nameIsInvalid()){
            setTextFieldBorderToRed(nameField);
            isInvalid = true;
        }else{
            setTextFieldBorderToGreen(nameField);
        }

        if(emailIsInvalid()){
            setTextFieldBorderToRed(emailField);
            isInvalid = true;
        }else{
            setTextFieldBorderToGreen(emailField);
        }

        if(mobileNoIsInvalid()){
            setTextFieldBorderToRed(mobileField);
            isInvalid = true;
        }else{
            setTextFieldBorderToGreen(mobileField);
        }
        return isInvalid;
    }
    
    public boolean nameIsInvalid() {
        String fullNameRegex = "^[a-zA-Z]{4,}(?: [a-zA-Z]+)?(?: [a-zA-Z]+)?$";
        String name = nameField.getText();
        String validateName;

        validateName = name.replace("@", "");
        validateName = validateName.replace("A/L ", "");
        validateName = validateName.replace("A/P ", "");

        System.out.println(validateName);
        
        return !Pattern.matches(fullNameRegex, validateName);
    }

    public boolean emailIsInvalid() {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        String email = emailField.getText();
        return !Pattern.matches(emailRegex, email);
    }

    public boolean mobileNoIsInvalid() {
        String mobileRegex = "^(01[(2-9|0)]-\\d{7})$|^(011-\\d{8})$";
        String mobileNo = mobileField.getText();
        return !Pattern.matches(mobileRegex, mobileNo);
    }

    public void setTextFieldBorderToRed(TextField textField) {
        textField.setStyle("-fx-border-color : red");
    }

    public void setTextFieldBorderToGreen(TextField textField) {
        textField.setStyle("-fx-border-color : green");
    }
}
