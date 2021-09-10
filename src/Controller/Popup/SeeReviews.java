package Controller.Popup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Cache.*;
import Classes.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class SeeReviews implements Initializable {
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();

    @FXML VBox reviewsContainer;        
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(data.getShop().getReviews()==null){
            data.getShop().loadReviews();
            data.setReviews(data.getShop().getReviews());
        }

        if(data.getReviews().size()==0){
            Label lblEmptyLabel = new Label("There is no reviews yet.");
            lblEmptyLabel.setFont(Font.font("Arial", 20));
            reviewsContainer.getChildren().setAll(lblEmptyLabel);            
        }else{
            for(Review r : data.getReviews()){                        
                ReviewItem controller = new ReviewItem();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Popup/ReviewItem.fxml"));            
                loader.setController(controller);
                VBox root = null;
                try {
                    root = loader.load();                
                    controller.getLblUsername().setText(r.getBuyerUserName());
                    controller.getLblDateCreated().setText(r.getDateCreated().toString());
                    controller.getLblRating().setText(String.format("%d/5",r.getRating()));
                    controller.getLblComment().setText(r.getComment());                
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }          
                System.out.println(root);                          
                reviewsContainer.getChildren().add(root);
            }
        }        
    }       
}