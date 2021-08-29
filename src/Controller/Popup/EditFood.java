package Controller.Popup;
import Cache.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private String newImgFileExtension;    

    @Override
    public void initialize(URL location, ResourceBundle resources) {            
        SpinnerValueFactory<Double> priceValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.00, 10000.00, 6.50);
        spinnerPrice.setValueFactory(priceValueFactory);
        spinnerPrice.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            spinnerPrice.getEditor().setText(newValue.replaceAll("[^0-9.]+",""));
            if(spinnerPrice.getEditor().getText().isEmpty()){
                spinnerPrice.getEditor().setText("0");
            }
        });  
        
        inputName.setText(data.getFood().getName());
        spinnerPrice.getValueFactory().setValue(data.getFood().getPrice());
        inputCategory.setText(data.getFood().getCategory());
        inputDescription.setText(data.getFood().getDesc());
        
        // https://stackoverflow.com/questions/22710053/how-can-i-show-an-image-using-the-imageview-component-in-javafx-and-fxml
        // Image.setImage(new Image(getClass().getResourceAsStream("/Images/temp.jpg"))); 
        Image.setImage(new Image(getClass().getResourceAsStream(data.getFood().getImgPath())));
    }    

    @FXML
    void chooseImage(ActionEvent event) throws IOException {
        shopImageFile = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your shop image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif", "*.jpeg")
        );
        shopImageFile = fileChooser.showOpenDialog(gui.getStage());
        if(shopImageFile!=null){                                    
            // https://stackoverflow.com/questions/36991165/how-to-set-the-save-path-for-a-file-chosen-in-filechooser-javafx/36991844#36991844
            // String currentPath = Paths.get("").toAbsolutePath().toString().replaceAll("\\\\", "/");            
            newImgFileExtension = shopImageFile.getName().substring(shopImageFile.getName().indexOf("."));            
            // Path path = Paths.get(currentPath+"/src/Images/temp"+newImgFileExtension);
            Image.setImage(new Image(shopImageFile.toURI().toString()));
            // try{
            //     Files.delete(path);
            // }catch (DirectoryNotEmptyException e) {
            //     // happens sometimes if Windows is too slow to remove children of a directory                
            //     System.out.println("Error on remove the file!!!!!");
            // }finally{
                // Files.copy(shopImageFile.toPath(), path);
            // }           
        }                               
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

    public Spinner<Double> getSpinnerPrice() {
        return spinnerPrice;
    }

    public void setSpinnerPrice(Spinner<Double> spinnerPrice) {
        this.spinnerPrice = spinnerPrice;
    }

    public TextField getInputName() {
        return inputName;
    }

    public void setInputName(TextField inputName) {
        this.inputName = inputName;
    }

    public TextField getInputCategory() {
        return inputCategory;
    }

    public void setInputCategory(TextField inputCategory) {
        this.inputCategory = inputCategory;
    }

    public TextArea getInputDescription() {
        return inputDescription;
    }

    public void setInputDescription(TextArea inputDescription) {
        this.inputDescription = inputDescription;
    }

    public File getShopImageFile() {
        return shopImageFile;
    }

    public void setShopImageFile(File shopImageFile) {
        this.shopImageFile = shopImageFile;
    }

    public String getNewImgFileExtension() {
        return newImgFileExtension;
    }

    public void setNewImgFileExtension(String newImgFileExtension) {
        this.newImgFileExtension = newImgFileExtension;
    }

       
    
}