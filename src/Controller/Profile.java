package Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Cache.*;
import Classes.*;
import Controller.Popup.EditFood;
import Controller.Popup.EditProfile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Profile implements Initializable{
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();

    @FXML private AnchorPane paneProfile;
    @FXML private Label lblWelcome;    
    @FXML private ImageView iconHome,iconCart;
    @FXML private ListView<String> listView;
    private String currentFXMLPath = "View/Profile.fxml";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub        
        listView.getItems().add("Username: "+data.getAccount().getUsername());
        listView.getItems().add("Passsowrd: "+data.getAccount().getPassword());
        listView.getItems().add("Name: "+data.getAccount().getName());
        listView.getItems().add("Email: "+data.getAccount().getEmail());
        listView.getItems().add("MobileNo: "+data.getAccount().getMobileNo());        
        if(data.getAccount() instanceof Buyer){
            listView.getItems().add("Address: "+((Buyer)data.getAccount()).getAddress());
        }else if(data.getAccount() instanceof Rider){
            // listView.getItems().add("Vehicle: "+((Rider)data.getAccount()).getVehicle().toString()+"\nvehiclevehicle");
        }else if(data.getAccount() instanceof Seller){
            listView.getItems().add("Bank Acc No: "+((Seller)data.getAccount()).getBankAcc());
            listView.getItems().add("NRIC: "+((Seller)data.getAccount()).getNRIC());
            listView.getItems().add("License No: "+((Seller)data.getAccount()).getLicenseNumber());            
        }        
    }

    @FXML
    void actionEditProfile(MouseEvent event) throws IOException {
        gui.alertInProgress();
        Stage myDialog = new Stage();
        myDialog.initModality(Modality.APPLICATION_MODAL);  //make user unable to press the original stage/window unless close the current stage/window
        myDialog.initOwner(gui.getStage());
        
        EditProfile controller = new EditProfile();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Popup/EditProfile.fxml"));
        loader.setController(controller);                        
        Scene dialogScene = new Scene((Parent)loader.load());
        
        controller.getBtnYes().setOnAction(e->{      
            // passback.accept(true);
            myDialog.close();            
            try {
                gui.refreshScene(currentFXMLPath);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            gui.notAlertInProgress();
        });
        
        controller.getBtnNo().setOnAction(e->{      
            // passback.accept(false);
            myDialog.close();
            gui.notAlertInProgress();
        });  
        
        myDialog.setScene(dialogScene);
        myDialog.setMaximized(false);
        myDialog.show();
    }    

    @FXML
    void toHome(MouseEvent event) throws IOException {
        gui.toNextScene(String.format("View/%sHome.fxml",data.getAccount().getAccType()));
    }    
}
