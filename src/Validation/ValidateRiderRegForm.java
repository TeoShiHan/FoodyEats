package Validation;

import java.util.regex.Pattern;

import Controller.Register.RegRider;
import javafx.scene.control.TextField;

public class ValidateRiderRegForm extends ValidateRegForm {
    RegRider riderForms;

    public ValidateRiderRegForm(RegRider riderForms) {
        super(riderForms.getInputName(), riderForms.getInputEmail(), riderForms.getInputMobileNo());
        this.riderForms = riderForms;
    }

    @Override
    public boolean validateForm() {

        TextField carModel = riderForms.getInputModel();
        TextField carBrand = riderForms.getInputBrand();
        TextField carPlateNo = riderForms.getInputPlateNo();
        TextField carColor = riderForms.getInputColor();

        isInvalid = super.validateForm();

        if (brandIsInvalid()) {
            setTextFieldBorderToRed(carBrand);
            isInvalid = true;
        } else {
            setTextFieldBorderToGreen(carBrand);
        }

        if (brandIsInvalid()) {
            setTextFieldBorderToRed(carBrand);
            isInvalid = true;
        } else {
            setTextFieldBorderToGreen(carBrand);
            isInvalid = true;
        }

        if (plateNumIsInvalid()) {
            setTextFieldBorderToRed(carPlateNo);
            isInvalid = true;
        } else {
            setTextFieldBorderToGreen(carPlateNo);
        }

        if (colorIsInvalid()) {
            setTextFieldBorderToRed(carColor);
            isInvalid = true;
        } else {
            setTextFieldBorderToGreen(carColor);
        }
        return isInvalid;
    }

    public boolean brandIsInvalid() {
        String brandRegex = "^([A-Z][a-z]*)((\s||-)[A-Z][a-z]*){0,2}$";
        String carBrand = riderForms.getInputBrand().getText();
        return !Pattern.matches(brandRegex, carBrand);
    }

    public boolean plateNumIsInvalid() {
        String carPlateRegex = "^[A-Z]{1,3}(\s?)[0-9]{1,4}(\s?)[A-Z]{0,3}$";
        String plateNum = riderForms.getInputPlateNo().getText();
        return !Pattern.matches(carPlateRegex, plateNum);
    }

    public boolean modelIsInvalid() {
        String carPlateRegex = "^([A-Z]||-||[a-z]||[0-9])*(\s([A-Z]||-||[a-z]||[0-9])*)*?$";
        String model = riderForms.getInputModel().getText();
        return !Pattern.matches(carPlateRegex, model);
    }

    public boolean colorIsInvalid() {
        String colorRegex = "^\\S[A-Za-z\s]*$";
        String color = riderForms.getInputColor().getText();
        return !Pattern.matches(colorRegex, color);
    }
}
