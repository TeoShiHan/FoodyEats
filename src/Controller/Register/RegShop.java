package Controller.Register;
import Classes.*;
import Validation.ValidateShopRegForm;
import Cache.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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
    private List<String> imgExtensions = new ArrayList<>(){
        {
            add(".png");
            add(".jpg");
            add(".gif");
            add(".jpeg");
        }
    };

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
            //#region old method of storing image
            // Image image = new Image(shopImageFile);            
            // String imageName = shopImageFile.getName();
            // String extension = shopImageFile.getPath().substring(shopImageFile.getPath().lastIndexOf(".")+1);            
            // ImageIO.write(shopImageFile, extension, new File(shopImageFile.getPath()));
            // copy(shopImageFile.getAbsolutePath(), "C:\\Users\\Asus\\Downloads\\SEM 3 - OOP\\FoodyEats\\src\\Images\\shopImageFile.png");
            //#endregion
            
            lblImage.setText(shopImageFile.getName());
            
            data.addObjectHolder("choosenImagePath", shopImageFile.toPath());
            data.addObjectHolder("choosenImageExtension", shopImageFile.getName().substring(shopImageFile.getName().lastIndexOf(".")));
            // // https://stackoverflow.com/questions/36991165/how-to-set-the-save-path-for-a-file-chosen-in-filechooser-javafx/36991844#36991844
            // String currentPath = System.getProperty("user.dir");
            // for(String ext: imgExtensions){
            //     Path path = Paths.get(currentPath, "temp"+ext);
            //     if(Files.exists(path)){
            //         Files.delete(path);
            //     }                            
            // }                        
            // // shopImageFileName = currentPath+"/src/Images/temp"+shopImageFile.getName().substring(shopImageFile.getName().lastIndexOf("."));
            // Files.copy(shopImageFile.toPath(), Paths.get(currentPath,"temp"+shopImageFile.getName().substring(shopImageFile.getName().indexOf("."))));
        }
    }   
              
    public boolean isFilled(){                        
        return !(inputShopName.getText().isEmpty() || inputShopAddr.getText().isEmpty() || 
                inputShopTelNo.getText().isEmpty() || shopImageFile==null || 
                datePicker.getValue().isAfter(LocalDate.now()));
    }

    public void getInfo(){                
        Shop shop = new Shop();
        shop.setName(inputShopName.getText());
        shop.setAddress(inputShopAddr.getText());
        shop.setTel(inputShopTelNo.getText());                      
        shop.setStartHour(LocalTime.of(spinnerStartHour.getValue(), 0, 0));
        shop.setEndHour(LocalTime.of(spinnerEndHour.getValue(), 0, 0));
        shop.setDateCreated(datePicker.getValue());
        shop.setDeliveryFee(spinnerDeliveryFee.getValue());        
        shop.setImgPath(shopImageFile.toPath().toString());
        shop.setStatus(0);
        Seller seller = (Seller)data.getAccount();
        seller.setShop(shop);
    }

    public TextField getInputShopName() {
        return inputShopName;
    }

    public void setInputShopName(TextField inputShopName) {
        this.inputShopName = inputShopName;
    }

    public TextField getInputShopAddr() {
        return inputShopAddr;
    }

    public void setInputShopAddr(TextField inputShopAddr) {
        this.inputShopAddr = inputShopAddr;
    }

    public TextField getInputShopTelNo() {
        return inputShopTelNo;
    }

    public void setInputShopTelNo(TextField inputShopTelNo) {
        this.inputShopTelNo = inputShopTelNo;
    }

    public boolean detectedInvalidFields(){
        ValidateShopRegForm formValidator = new ValidateShopRegForm(this);
        return formValidator.validateForm();
    }
}