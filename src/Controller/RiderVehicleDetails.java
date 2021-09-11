package Controller;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

import Cache.*;
import Classes.*;
import Controller.Popup.*;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RiderVehicleDetails implements Initializable{
    
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();
    private JDBC db = new JDBC();

    @FXML private AnchorPane paneProfile;
    @FXML private Label lblWelcome;    
    @FXML private ImageView iconHome,imgShop;
    @FXML private ListView<String> listView;    
    private String currentFXMLPath = "View/RiderVehicleDetails.fxml";    

    @Override
    public void initialize(URL location, ResourceBundle resources) {           
        if(data.getRider().getVehicle()==null){
            HashMap<String,Object> v = db.readOne(String.format("SELECT * FROM `Vehicle` WHERE vehicleID='%s'",data.getRider().getVehicleID()));
            Vehicle vehicle = new Vehicle(v.get("vehicleID"), v.get("type"), v.get("plateNo"), v.get("brand"), v.get("model"), v.get("color"));
            data.getRider().setVehicle(vehicle);
            data.setVehicle(vehicle);
        }     
        listView.getItems().add("Type: "+data.getVehicle().getType());        
        if(!(data.getVehicle().getType().equals("Bicycle") || data.getVehicle().getType().equals("Pedestrian"))){
            listView.getItems().add("Brand: "+data.getVehicle().getBrand());
            listView.getItems().add("Model: "+data.getVehicle().getModel());
            listView.getItems().add("Plate No. : "+data.getVehicle().getPlateNo());     
            listView.getItems().add("Color: "+data.getVehicle().getColor());        
        }
    }

    @FXML
    void actionEditProfile(MouseEvent event) throws IOException {                    
        Stage myDialog = new Stage();
        gui.alertInProgress(myDialog);
        myDialog.initModality(Modality.APPLICATION_MODAL);  //make user unable to press the original stage/window unless close the current stage/window
        myDialog.initOwner(gui.getStage());
        
        EditVehicleDetails controller = new EditVehicleDetails();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Popup/EditVehicleDetails.fxml"));
        loader.setController(controller);                        
        Scene dialogScene = new Scene((Parent)loader.load());
        
        controller.getBtnYes().setOnAction(e->{
            if(controller.isFilled()){

                if (!controller.detectedInvalidVehicleDetails()) {
                    myDialog.getScene().getRoot().setDisable(true);
                    myDialog.getScene().setCursor(Cursor.WAIT);
                    updateVehicleInDataHolder(controller);
                    updateVehicleDataToDatabase(controller);
                    myDialog.close();
                    refreshScene(myDialog);
                }else{
                    System.out.println("NOT DETECTED ERROR : " + !controller.detectedInvalidVehicleDetails());
                    popUpInvalidFieldDetectedMsg();
                }
            }else{
                popUpEmptyFieldDetectedMsg();
            }
        });
        
        controller.getBtnNo().setOnAction(e->{      
            // passback.accept(false);
            myDialog.close();
            gui.notAlertInProgress(myDialog);
        });  
                
        myDialog.setScene(dialogScene);
        myDialog.setMaximized(false);
        myDialog.show();
    }

    @FXML
    void toHome(MouseEvent event) throws IOException {        
        gui.toNextScene(String.format("View/%sHome.fxml",data.getAccount().getAccType()));
    }
    
    public void popUpEmptyFieldDetectedMsg(){
        try {
            gui.informationPopup("Attention", "Please fill in all the blank.");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void popUpInvalidFieldDetectedMsg(){
        try {
            gui.informationPopup("Attention", "Invalid fields detected, please try again!");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void updateVehicleDataToDatabase(EditVehicleDetails controller){
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws IOException, SQLException {                 
                String selectedItem = controller.getDropdownVehicleType().getSelectionModel().getSelectedItem();
                String newBrand = controller.getInputBrand().getText();
                String newModel = controller.getInputModel().getText();
                String newPlateNo = controller.getInputPlateNo().getText();
                String newColor = controller.getInputColor().getText();

                data.getVehicle().edit(selectedItem, newBrand, newModel, newPlateNo, newColor);                                            
                return null;
            }
        };
        new Thread(task).start();
    }

    public void updateVehicleInDataHolder(EditVehicleDetails controller) {
        data.getVehicle().setType(controller.getDropdownVehicleType().getSelectionModel().getSelectedItem());
        data.getVehicle().setBrand(controller.getInputBrand().getText());
        data.getVehicle().setModel(controller.getInputModel().getText());
        data.getVehicle().setPlateNo(controller.getInputPlateNo().getText());
        data.getVehicle().setColor(controller.getInputColor().getText());
    }
    
    public void refreshScene(Stage myDialog){
        try {
            gui.notAlertInProgress(myDialog);
            gui.refreshScene(currentFXMLPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

