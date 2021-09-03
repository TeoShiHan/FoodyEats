package Controller.Popup;

import java.net.URL;
import java.util.ResourceBundle;

import Cache.*;
import Classes.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;

public class WriteReview implements Initializable {
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();

    @FXML private TextArea inputComment;
    @FXML private Spinner<Integer> spinnerRating = new Spinner<>();
    @FXML private Button btnYes,btnNo;    

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {        
        SpinnerValueFactory<Integer> ratingValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 3);
        spinnerRating.setValueFactory(ratingValueFactory);
        spinnerRating.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {            
            String stringRatingValue = newValue.replaceAll("[^0-9.]+","");
            
            if(stringRatingValue.isEmpty()){
                spinnerRating.getEditor().setText("1");
            }else{
                int intRatingValue = Integer.parseInt(stringRatingValue);
                if(intRatingValue>5){
                    spinnerRating.getEditor().setText("5");
                }else if(intRatingValue<1){
                    spinnerRating.getEditor().setText("1");
                }else{
                    spinnerRating.getEditor().setText(String.valueOf(intRatingValue));
                }
            }            
        });                
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

    public TextArea getInputComment() {
        return inputComment;
    }

    public void setInputComment(TextArea inputComment) {
        this.inputComment = inputComment;
    }

    public Spinner<Integer> getSpinnerRating() {
        return spinnerRating;
    }

    public void setSpinnerRating(Spinner<Integer> spinnerRating) {
        this.spinnerRating = spinnerRating;
    }

    
}
