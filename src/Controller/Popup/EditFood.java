package Controller.Popup;
import Cache.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class EditFood implements Initializable{    
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();

    @FXML private Spinner<Double> spinnerPrice = new Spinner<>();    
    @FXML private ImageView Image;
    @FXML private TextField inputName,inputCategory;
    @FXML private TextArea inputDescription;
    @FXML private Button btnChangeImage,btnYes,btnNo;    
    private File shopImageFile;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        SpinnerValueFactory<Double> priceValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.00, 10000.00, 6.50);
        spinnerPrice.setValueFactory(priceValueFactory);
        spinnerPrice.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            spinnerPrice.getEditor().setText(newValue.replaceAll("[^0-9.]+",""));
            if(spinnerPrice.getEditor().getText().isEmpty()){
                spinnerPrice.getEditor().setText("0");
            }
        });        
        // https://stackoverflow.com/questions/22710053/how-can-i-show-an-image-using-the-imageview-component-in-javafx-and-fxml
        Image.setImage(new Image(getClass().getResourceAsStream("/Images/temp.jpg"))); 
    }    

    @FXML
    void chooseImage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your shop image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif", "*.jpeg")
        );
        shopImageFile = fileChooser.showOpenDialog(gui.getStage());
        if(shopImageFile!=null){
            // Image image = new Image(shopImageFile);            
            // String imageName = shopImageFile.getName();
            // String extension = shopImageFile.getPath().substring(shopImageFile.getPath().lastIndexOf(".")+1);            
            // ImageIO.write(shopImageFile, extension, new File(shopImageFile.getPath()));
            // copy(shopImageFile.getAbsolutePath(), "C:\\Users\\Asus\\Downloads\\SEM 3 - OOP\\FoodyEats\\src\\Images\\shopImageFile.png");
            
            // https://stackoverflow.com/questions/36991165/how-to-set-the-save-path-for-a-file-chosen-in-filechooser-javafx/36991844#36991844
            String currentPath = Paths.get("").toAbsolutePath().toString().replaceAll("\\\\", "/");
            System.out.println(currentPath+"/src/Images");
            Path path = Paths.get(currentPath+"/src/Images", shopImageFile.getName());
            Files.copy(shopImageFile.toPath(), path);
            
        }                               
    }   
    
}