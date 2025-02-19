package Controller;
import Cache.*;
import Classes.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AdminRiderAccountDetails implements Initializable {    
    
    private GUI gui = GUI.getInstance();    
    private DataHolder data = DataHolder.getInstance();    

    @FXML private Label linkLogout,lblRiderAccountDetails,lblName,lblEmail,lblMobileNo,lblVehicleType,lblVehicleBrand,lblVehicleModel,lblVehiclePlateNo,lblVehicleColor,lblDateReg;        
    @FXML private ImageView iconProfile,iconHome,iconBack;            
    @FXML private Button btnApprove;          

    @Override
    public void initialize(URL location, ResourceBundle resources) {                
        
        lblRiderAccountDetails.setText("Rider Account #"+data.getRider().getAccountID());
        lblName.setText(data.getRider().getName());
        lblEmail.setText(data.getRider().getEmail());
        lblMobileNo.setText(data.getRider().getMobileNo());
        lblVehicleType.setText(data.getRider().getVehicle().getType());
        lblVehicleBrand.setText(data.getRider().getVehicle().getBrand());
        lblVehicleColor.setText(data.getRider().getVehicle().getColor());
        lblVehicleModel.setText(data.getRider().getVehicle().getModel());
        lblVehiclePlateNo.setText(data.getRider().getVehicle().getPlateNo());        
        lblDateReg.setText(data.getRider().getRegDate().toString());

    }    

    @FXML
    void actionApprove(ActionEvent event) {
        data.getAdmin().verifyRider(data.getRider().getRiderID());
        btnApprove.setDisable(true);
    }

    @FXML
    void toHome(MouseEvent event) throws IOException {
        gui.toNextScene("View/AdminHome.fxml");
    }        

    @FXML
    void toBack(MouseEvent event) throws IOException {
        gui.toPrevScene();
    }  
}