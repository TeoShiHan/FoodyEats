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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

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

        for(Review r : data.getReviews()){            
            ReviewItem controller = new ReviewItem();
            FXMLLoader loader = new FXMLLoader();            
            loader.setLocation(getClass().getClassLoader().getResource("/View/Popup/ReviewItem.fxml"));        
            loader.setController(controller);
            VBox root = null;
            try {
                controller.getLblUsername().setText(r.getBuyerUserName());
                controller.getLblDateCreated().setText(r.getDateCreated().toString());
                controller.getLblRating().setText(String.format("%d/5",r.getRating()));
                controller.getLblComment().setText(r.getComment());
                root = loader.load();
                System.out.println("get controller comment --> "+controller.getLblComment().getText());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }                                    
            reviewsContainer.getChildren().add(root);
        }
    }       
}