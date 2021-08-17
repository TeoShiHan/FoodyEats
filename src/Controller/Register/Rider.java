package Controller.Register;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Rider implements Initializable{
    @FXML private TextField inputName,inputMobileNo,inputEmail,inputBrand,inputModel,inputColor,inputPlateNo;
    @FXML private ComboBox<String> dropdownVehicleType = new ComboBox<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        // dropdownVehicleType.getItems().add("a");
        // dropdownVehicleType.getItems().add("Choice 2");
        // dropdownVehicleType.getItems().add("Choice 3");
        ObservableList<String> options = FXCollections.observableArrayList("Car","Motorcycle","Bicycle","Pedestrian");
        dropdownVehicleType.getItems().addAll(options);        
    }    

    @FXML
    void selectVehicleType(ActionEvent event) {
        if(dropdownVehicleType.getValue().equals("Car")||dropdownVehicleType.getValue().equals("Motorcycle")){
            setVehicleInputAble();
        }else{
            setVehicleInputDisable();
        }
        // Node node = ((Node)event.getTarget());
        // VBox vbox = (VBox) node.getParent();
        // vbox.getChildren().addAll()    
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
}
