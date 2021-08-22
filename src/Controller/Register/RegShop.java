package Controller.Register;
import Classes.*;
import Cache.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class RegShop implements Initializable{
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();

    @FXML private TextField inputShopName,inputShopAddr,inputShopTelNo;
    @FXML private Spinner<Double> spinnerDeliveryFee = new Spinner<>();
    @FXML private Spinner<Integer> spinnerStartHour,spinnerEndHour = new Spinner<Integer>();    
    @FXML private DatePicker datePicker;
    @FXML private Button imageChooser;
    @FXML private Label lblImage;
    private File shopImageFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub            
        SpinnerValueFactory<Double> deliveryValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.00, 100.00, 1.00);        
        SpinnerValueFactory<Integer> startHourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 8);
        SpinnerValueFactory<Integer> endHourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 17);        
        spinnerDeliveryFee.setValueFactory(deliveryValueFactory);
        spinnerStartHour.setValueFactory(startHourValueFactory);
        spinnerEndHour.setValueFactory(endHourValueFactory);

        spinnerDeliveryFee.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            spinnerDeliveryFee.getEditor().setText(newValue.replaceAll("[^0-9.]+",""));
            if(spinnerDeliveryFee.getEditor().getText().isEmpty()){
                spinnerDeliveryFee.getEditor().setText("0");
            }
        });
        spinnerStartHour.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            spinnerStartHour.getEditor().setText(newValue.replaceAll("[^0-9]+",""));
            if(spinnerStartHour.getEditor().getText().isEmpty()){
                spinnerStartHour.getEditor().setText("0");
            }
        });
        spinnerEndHour.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            spinnerEndHour.getEditor().setText(newValue.replaceAll("[^0-9]+",""));
            if(spinnerEndHour.getEditor().getText().isEmpty()){
                spinnerEndHour.getEditor().setText("0");
            }
        });
        
        datePicker.setValue(LocalDate.now());
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
            
            lblImage.setText(shopImageFile.getName());

            // https://stackoverflow.com/questions/36991165/how-to-set-the-save-path-for-a-file-chosen-in-filechooser-javafx/36991844#36991844
            String currentPath = Paths.get("").toAbsolutePath().toString().replaceAll("\\\\", "/");
            System.out.println(currentPath+"/src/Images");
            Path path = Paths.get(currentPath+"/src/Images", shopImageFile.getName());
            Files.copy(shopImageFile.toPath(), path);            
        }
    }   
              
    // @FXML private TextField inputShopName,inputShopAddr,inputShopTelNo;
    // @FXML private Spinner<Double> spinnerDeliveryFee = new Spinner<>();
    // @FXML private Spinner<Integer> spinnerStartHour,spinnerEndHour = new Spinner<Integer>();
    // @FXML private Button imageChooser;
    public boolean isFilled(){                
        System.out.println(shopImageFile);
        return !(inputShopName.getText().isEmpty() || inputShopAddr.getText().isEmpty() || 
                inputShopTelNo.getText().isEmpty() || shopImageFile==null || 
                datePicker.getValue().isAfter(LocalDate.now()));                
    }

    public void getInfo(){
        // Shop shop = new Shop();        
        // shop.setName(inputShopName.getText());
        // shop.setAddress(inputShopAddr.getText());
        // shop.setTel(inputShopTelNo.getText());                      
        // shop.setStartHour(spinnerStartHour.getValue().toString());
        // shop.setEndHour(spinnerEndHour.getValue().toString());
        // shop.setDateCreated(datePicker.getValue());
        // shop.setDeliveryFee(spinnerDeliveryFee.getValue());        
        // data.addObjectHolder("shopImgPath", "/Images/"+shopImageFile.getName());
    }       
}