package Validation;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

import Cache.DataHolder;
import Cache.GUI;
import Classes.JDBC;
import javafx.scene.control.TextField;

public abstract class FormValidator {
    
    protected boolean isInvalid = false;
    private TextField nameField;
    private TextField emailField;
    private TextField mobileField;
    private TextField usernameField;

    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();

    FormValidator(TextField usernameField,TextField nameField, TextField emailField, TextField mobileField){
        this.nameField = nameField;
        this.emailField = emailField;
        this.mobileField = mobileField;
        this.usernameField = usernameField;
    }

    FormValidator(TextField nameField, TextField emailField, TextField mobileField){
        this.nameField = nameField;
        this.emailField = emailField;
        this.mobileField = mobileField;
    }

    FormValidator(){

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

    public boolean validateAlterForm(){
        
        if(usernameExistInDb(usernameField.getText())){
            setTextFieldBorderToRed(usernameField);
            popUpUsernameExistMsg();
            isInvalid = true;
        }

        if(emailExistInDb(emailField.getText())){
            setTextFieldBorderToRed(emailField);
            popUpEmailExistMsg();
            isInvalid = true;
        }

        return isInvalid;
    }
    
    public boolean nameIsInvalid() {
        String fullNameRegex = "^[a-zA-Z]{1,}(?: [a-zA-Z]+)?(?: [a-zA-Z]+)?$";
        String name = nameField.getText();
        String validateName;

        validateName = name.replace("@", "");
        validateName = validateName.replace("A/L ", "");
        validateName = validateName.replace("A/P ", "");
        
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

    public boolean usernameExistInDb(String username) {
        String sqlStmt = String.format("SELECT accountID, username " + "FROM Account " + "WHERE username = '%s';", username);

        if (db.readOne(sqlStmt) == null) {
            return false;
        }

        HashMap<String, Object> dbRecord = db.readOne(sqlStmt);

        String dbAccId = dbRecord.get("accountID").toString();
        String thisAccID = data.getAccount().getAccountID().toString();

        return !dbAccId.equals(thisAccID);
    }

    public boolean emailExistInDb(String email) {

        String sqlStmt = String.format("SELECT accountID, email " + "FROM Account " + "WHERE email = '%s';", email);
        if (db.readOne(sqlStmt) == null) {
            return false;
        }
        HashMap<String, Object> dbRecord = db.readOne(sqlStmt);
        String dbAccId = dbRecord.get("accountID").toString();
        String thisAccID = data.getAccount().getAccountID().toString();
        return !dbAccId.equals(thisAccID);
    }

    public void popUpUsernameExistMsg(){
        try {
            gui.informationPopup("Attention", "This username has been registered, please try again!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void popUpEmailExistMsg(){
        try {
            gui.informationPopup("Attention", "This email has been registered, please try again!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
