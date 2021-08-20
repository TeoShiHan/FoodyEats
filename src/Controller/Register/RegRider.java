package Controller.Register;
import Cache.*;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

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
        return !(inputName.getText().isEmpty() || inputMobileNo.getText().isEmpty() || 
                inputEmail.getText().isEmpty() || (!inputBrand.isDisabled() && 
                inputBrand.getText().isEmpty() || inputModel.getText().isEmpty() ||
                inputColor.getText().isEmpty() || inputPlateNo.getText().isEmpty()));
    }

    public void getInfo(){        
        data.addObjectHolder("accountName", inputName.getText());
        data.addObjectHolder("accountEmail", inputEmail.getText());
        data.addObjectHolder("accountMobileNo", inputMobileNo.getText());
        data.addObjectHolder("vehicleType", dropdownVehicleType.getSelectionModel().getSelectedItem());
        data.addObjectHolder("vehicleBrand", inputBrand.getText());
        data.addObjectHolder("vehicleModel", inputModel.getText());
        data.addObjectHolder("vehiclePlateNo", inputPlateNo.getText());
        data.addObjectHolder("vehicleColor", inputColor.getText());
    }
    
}
