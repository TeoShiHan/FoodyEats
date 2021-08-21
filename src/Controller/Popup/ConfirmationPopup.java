package Controller.Popup;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;

public class ConfirmationPopup implements Initializable{
    @FXML Label lblHeading,lblMessage;    
    @FXML Button btnYes,btnNo;

    //#region    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        // btnYes.setEffect(new DropShadow());
        // btnNo.setEffect(new DropShadow());        
    }    

    public Label getLblHeading() {
        return lblHeading;
    }

    public void setLblHeading(Label lblHeading) {
        this.lblHeading = lblHeading;
    }

    public Label getLblMessage() {
        return lblMessage;
    }

    public void setLblMessage(Label lblMessage) {
        this.lblMessage = lblMessage;
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
    //#endregion
}
