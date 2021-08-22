package Controller;
import Cache.*;
import Classes.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SellerEditFood implements Initializable {
    GUI gui = GUI.getInstance();
    DataHolder data = DataHolder.getInstance();
    @FXML private AnchorPane paneSellerOrders;
    @FXML private Label lblWelcome;
    @FXML private ImageView iconCart,iconHome;    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // gui.confirmationPopup("title", "message", "yesBtnText", "noBtnText", passback->{
        //     if(passback){
        //         System.out.println("yess");
        //     }else{
        //         System.out.println("no");
        //     }
        // });
    }    

    @FXML
    void toBack(MouseEvent event) throws IOException {
        gui.toPrevScene();
    }  

    @FXML
    void toHome(MouseEvent event) throws IOException {
        gui.toNextScene("View/SellerHome.fxml");
    }
}
