package Controller.Popup;
import Cache.*;
import Classes.*;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class EditVehicleDetails implements Initializable{    
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();


    @FXML private TextField inputBrand,inputModel,inputPlateNo,inputColor;
    @FXML private ComboBox<String> dropdownVehicleType = new ComboBox<>();
    @FXML private Button btnYes,btnNo;
    @FXML private VBox vboxBankAcc,vboxAddress,vboxNRIC,vboxLicenseNo,vboxCompanyBranch;    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> options = FXCollections.observableArrayList("Van","Car","Motorcycle","Bicycle","Pedestrian");
        dropdownVehicleType.getItems().addAll(options);
        dropdownVehicleType.getSelectionModel().select(data.getVehicle().getType());
        
        inputBrand.setText(data.getVehicle().getBrand());
        inputModel.setText(data.getVehicle().getModel());
        inputPlateNo.setText(data.getVehicle().getPlateNo());
        inputColor.setText(data.getVehicle().getColor());
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

    public Button getBtnYes() {
        return btnYes;
    }

    public void setBtnYes(Button btnYes) {
        this.btnYes = btnYes;
    }

    public Button getBtnNo() {
        return btnNo;
    }

    public void setBtnNo(Button btnNo) {
        this.btnNo = btnNo;
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

    public TextField getInputPlateNo() {
        return inputPlateNo;
    }

    public void setInputPlateNo(TextField inputPlateNo) {
        this.inputPlateNo = inputPlateNo;
    }

    public TextField getInputColor() {
        return inputColor;
    }

    public void setInputColor(TextField inputColor) {
        this.inputColor = inputColor;
    }

    public ComboBox<String> getDropdownVehicleType() {
        return dropdownVehicleType;
    }

    public void setDropdownVehicleType(ComboBox<String> dropdownVehicleType) {
        this.dropdownVehicleType = dropdownVehicleType;
    }

       
}