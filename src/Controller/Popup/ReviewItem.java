package Controller.Popup;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ReviewItem implements Initializable {
    @FXML private Label lblUsername,lblDateCreated,lblRating,lblComment;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public Label getLblUsername() {
        return lblUsername;
    }

    public void setLblUsername(Label lblUsername) {
        this.lblUsername = lblUsername;
    }

    public Label getLblDateCreated() {
        return lblDateCreated;
    }

    public void setLblDateCreated(Label lblDateCreated) {
        this.lblDateCreated = lblDateCreated;
    }

    public Label getLblRating() {
        return lblRating;
    }

    public void setLblRating(Label lblRating) {
        this.lblRating = lblRating;
    }

    public Label getLblComment() {
        return lblComment;
    }

    public void setLblComment(Label lblComment) {
        this.lblComment = lblComment;
    }   
    
    
}