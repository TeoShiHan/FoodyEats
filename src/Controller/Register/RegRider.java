package Controller.Register;
import Cache.*;
import Classes.Rider;
import Classes.Vehicle;
import Validation.RiderFormValidator;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RegRider implements Initializable{
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();

    @FXML private TextField inputName,inputMobileNo,inputEmail,inputBrand,inputModel,inputColor,inputPlateNo;
    @FXML private ComboBox<String> dropdownVehicleType = new ComboBox<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub        
        ObservableList<String> options = FXCollections.observableArrayList("Van","Car","Motorcycle","Bicycle","Pedestrian");
        dropdownVehicleType.getItems().addAll(options);        
    }    

    @FXML
    void selectVehicleType(ActionEvent event) {
        if(dropdownVehicleType.getValue().equals("Van")||dropdownVehicleType.getValue().equals("Car")||dropdownVehicleType.getValue().equals("Motorcycle")){
            setVehicleInputAble();
        }else{
            setVehicleInputDisable();
        }        
    }

    void setVehicleInputAble(){
        inputBrand.setDisable(false);
        inputModel.setDisable(false);
        inputColor.setDisable(false);
        inputPlateNo.setDisable(false);        
    }

    void setVehicleInputDisable(){
        inputBrand.setDisable(true);
        inputModel.setDisable(true);
        inputColor.setDisable(true);
        inputPlateNo.setDisable(true);        
    }

    public TextField getInputName() {
        return inputName;
    }

    public void setInputName(TextField inputName) {
        this.inputName = inputName;
    }

    public TextField getInputMobileNo() {
        return inputMobileNo;
    }

    public void setInputMobileNo(TextField inputMobileNo) {
        this.inputMobileNo = inputMobileNo;
    }

    public TextField getInputEmail() {
        return inputEmail;
    }

    public void setInputEmail(TextField inputEmail) {
        this.inputEmail = inputEmail;
    }

    public TextField getInputBrand() {
        return inputBrand;
    }

    public void setInputBrand(TextField inputBrand) {
        this.inputBrand = inputBrand;
    }

    public TextField getInputModel() {
        return inputModel;
    }

    public void setInputModel(TextField inputModel) {
        this.inputModel = inputModel;
    }

    public TextField getInputColor() {
        return inputColor;
    }

    public void setInputColor(TextField inputColor) {
        this.inputColor = inputColor;
    }

    public TextField getInputPlateNo() {
        return inputPlateNo;
    }

    public void setInputPlateNo(TextField inputPlateNo) {
        this.inputPlateNo = inputPlateNo;
    }

    public ComboBox<String> getDropdownVehicleType() {
        return dropdownVehicleType;
    }

    public void setDropdownVehicleType(ComboBox<String> dropdownVehicleType) {
        this.dropdownVehicleType = dropdownVehicleType;
    }

    public boolean isFilled(){                                                 
        return !(inputName.getText().strip().isEmpty() || inputMobileNo.getText().strip().isEmpty() || 
                inputEmail.getText().strip().isEmpty() || dropdownVehicleType.getSelectionModel().getSelectedItem()==null ||
                (!inputBrand.isDisabled() && (inputBrand.getText().strip().isEmpty() || inputModel.getText().strip().isEmpty() ||
                inputColor.getText().strip().isEmpty() || inputPlateNo.getText().strip().isEmpty())));
    }

    public void getInfo(){    
        Vehicle vehicle = new Vehicle();          
        vehicle.setType(dropdownVehicleType.getSelectionModel().getSelectedItem());
        vehicle.setBrand(inputBrand.getText());
        vehicle.setModel(inputModel.getText());
        vehicle.setPlateNo(inputPlateNo.getText());
        vehicle.setColor(inputColor.getText());
        Rider rider = new Rider();
        rider.setAccType("Rider");
        rider.setName(inputName.getText());
        rider.setEmail(inputEmail.getText());
        rider.setMobileNo(inputMobileNo.getText());
        rider.setVehicle(vehicle);
        data.setAccount(rider);
    }

    public boolean detectedInvalidFields(){
        RiderFormValidator formValidator = new RiderFormValidator(this);
        return formValidator.validateForm();
    }
}
