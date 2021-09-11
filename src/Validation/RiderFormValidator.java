package Validation;

import java.util.regex.Pattern;

import Controller.Popup.EditProfile;
import Controller.Popup.EditVehicleDetails;
import Controller.Register.RegRider;
import javafx.scene.control.TextField;

public class RiderFormValidator extends FormValidator {
    RegRider riderForms;
    EditProfile alterRiderForms;
    EditVehicleDetails alterCarForms;

    public RiderFormValidator(RegRider riderForms) {
        super(riderForms.getInputName(), riderForms.getInputEmail(), riderForms.getInputMobileNo());
        this.riderForms = riderForms;
    }

    public RiderFormValidator(EditProfile alterRiderForms) {
        super(alterRiderForms.getInputUsername(), alterRiderForms.getInputName(), alterRiderForms.getInputEmail(),
                alterRiderForms.getInputMobileNo());
        this.alterRiderForms = alterRiderForms;
    }

    public RiderFormValidator(EditVehicleDetails alterCarForms) {
        super();
        this.alterCarForms = alterCarForms;
    }

    @Override
    public boolean validateForm() {

        TextField carModel = riderForms.getInputModel();
        TextField carBrand = riderForms.getInputBrand();
        TextField carPlateNo = riderForms.getInputPlateNo();
        TextField carColor = riderForms.getInputColor();

        isInvalid = super.validateForm();

        if (brandIsInvalid(carBrand)) {
            setTextFieldBorderToRed(carBrand);
            isInvalid = true;
        } else {
            setTextFieldBorderToGreen(carBrand);
        }

        if (modelIsInvalid(carModel)) {
            setTextFieldBorderToRed(carModel);
            isInvalid = true;
        } else {
            setTextFieldBorderToGreen(carModel);
            isInvalid = true;
        }

        if (plateNumIsInvalid(carPlateNo)) {
            setTextFieldBorderToRed(carPlateNo);
            isInvalid = true;
        } else {
            setTextFieldBorderToGreen(carPlateNo);
        }

        if (colorIsInvalid(carColor)) {
            setTextFieldBorderToRed(carColor);
            isInvalid = true;
        } else {
            setTextFieldBorderToGreen(carColor);
        }
        return isInvalid;
    }

    public boolean validateRiderCar() {
        TextField carModel = alterCarForms.getInputModel();
        TextField carBrand = alterCarForms.getInputBrand();
        TextField carPlateNo = alterCarForms.getInputPlateNo();
        TextField carColor = alterCarForms.getInputColor();
        
        isInvalid = false;

        System.out.println("IS IN VALID >>>--->      : " + isInvalid);

        if (brandIsInvalid(carBrand)) {
            setTextFieldBorderToRed(carBrand);
            isInvalid = true;
            System.out.println("RED >>>--->      : " + isInvalid);
        } else {
            setTextFieldBorderToGreen(carBrand);
            System.out.println("GREEN >>>--->      : " + isInvalid);
        }
        
        System.out.println("IS IN VALID >>>--->      : " + isInvalid);


        if (modelIsInvalid(carModel)) {
            setTextFieldBorderToRed(carModel);
            isInvalid = true;
            System.out.println("RED >>>--->      : " + isInvalid);
        } else {
            setTextFieldBorderToGreen(carModel);
            System.out.println("GREEN >>>--->      : " + isInvalid);
        }

        System.out.println("IS IN VALID >>>--->      : " + isInvalid);

        if (plateNumIsInvalid(carPlateNo)) {
            setTextFieldBorderToRed(carPlateNo);
            isInvalid = true;
            System.out.println("RED >>>--->      : " + isInvalid);

        } else {
            setTextFieldBorderToGreen(carPlateNo);
            System.out.println("GREEN >>>--->      : " + isInvalid);
        }

        System.out.println("IS IN VALID >>>--->      : " + isInvalid);

        if (colorIsInvalid(carColor)) {
            setTextFieldBorderToRed(carColor);
            isInvalid = true;
            System.out.println("RED >>>--->      : " + isInvalid);

        } else {
            setTextFieldBorderToGreen(carColor);
            System.out.println("GREEN >>>--->      : " + isInvalid);
        }

        System.out.println("IS IN VALID >>>--->      : " + isInvalid);
        return isInvalid;
    }

    public boolean validateRiderProfile() {
        isInvalid = super.validateForm();
        return isInvalid || super.validateAlterForm();
    }

    public boolean brandIsInvalid(TextField brandField) {
        String brandRegex = "^([A-Z][a-z]*)((\s||-)[A-Z][a-z]*){0,2}$";
        String carBrand = brandField.getText();
        System.out.println("CAR BRAND : " + carBrand);
        System.out.println("RSLT      : " + !Pattern.matches(brandRegex, carBrand));
        return !Pattern.matches(brandRegex, carBrand);
    }

    public boolean plateNumIsInvalid(TextField plateField) {
        String carPlateRegex = "^[A-Z]{1,3}(\s?)[0-9]{1,4}(\s?)[A-Z]{0,3}$";
        String plateNum = plateField.getText();
        System.out.println("plateNum : " + plateNum);
        System.out.println("RSLT      : " + !Pattern.matches(carPlateRegex, plateNum));
        return !Pattern.matches(carPlateRegex, plateNum);
    }

    public boolean modelIsInvalid(TextField modelField) {
        String carPlateRegex = "^([A-Z]||-||[a-z]||[0-9])*(\s([A-Z]||-||[a-z]||[0-9])*)*?$";
        String model = modelField.getText();
        System.out.println("model : " + model);
        System.out.println("RSLT      : " + !Pattern.matches(carPlateRegex, model));
        return !Pattern.matches(carPlateRegex, model);
    }

    public boolean colorIsInvalid(TextField colorField) {
        String colorRegex = "^\\S[A-Za-z\s]*$";
        String color = colorField.getText();
        System.out.println("color : " + color);
        System.out.println("RSLT      : " + !Pattern.matches(colorRegex, color));
        return !Pattern.matches(colorRegex, color);
    }
}
