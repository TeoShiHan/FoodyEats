package Controller.Popup;
import Cache.*;
import Validation.ShopFormValidator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class EditShopProfile implements Initializable{    
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();    

    @FXML private TextField inputName,inputAddress,inputTelNo;    
    @FXML private Spinner<Double> spinnerDeliveryFee = new Spinner<>();
    @FXML private Spinner<Integer> spinnerStartHour,spinnerEndHour = new Spinner<Integer>();    
    @FXML private Button btnChangeImage,btnYes,btnNo;
    @FXML private ImageView image;
    private File shopImageFile;
    private String newImgFileExtension;        

    @Override
    public void initialize(URL location, ResourceBundle resources) {                    
        
        SpinnerValueFactory<Double> deliveryFeeValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.00, 100.00, 3.00);
        SpinnerValueFactory<Integer> startHourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 8);
        SpinnerValueFactory<Integer> endHourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 17);     
        
        spinnerDeliveryFee.setValueFactory(deliveryFeeValueFactory);
        spinnerStartHour.setValueFactory(startHourValueFactory);
        spinnerEndHour.setValueFactory(endHourValueFactory);

        configureSpinner(spinnerDeliveryFee);
        configureSpinner(spinnerStartHour);
        configureSpinner(spinnerEndHour);
                        
        inputName.setText(data.getSeller().getShop().getName());
        inputAddress.setText(data.getSeller().getShop().getAddress());
        inputTelNo.setText(data.getSeller().getShop().getTel());
        spinnerDeliveryFee.getValueFactory().setValue(data.getSeller().getShop().getDeliveryFee());
        spinnerStartHour.getValueFactory().setValue(data.getSeller().getShop().getStartHour().getHour());
        spinnerEndHour.getValueFactory().setValue(data.getSeller().getShop().getEndHour().getHour());
                                
        // https://stackoverflow.com/questions/22710053/how-can-i-show-an-image-using-the-imageview-component-in-javafx-and-fxml        
        image.setImage(new Image(getClass().getResourceAsStream(data.getSeller().getShop().getImgPath())));
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
            newImgFileExtension = shopImageFile.getName().substring(shopImageFile.getName().indexOf("."));            
            image.setImage(new Image(shopImageFile.toURI().toString()));
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

    public Spinner<Double> getSpinnerDeliveryFee() {
        return spinnerDeliveryFee;
    }

    public void setSpinnerDeliveryFee(Spinner<Double> spinnerDeliveryFee) {
        this.spinnerDeliveryFee = spinnerDeliveryFee;
    }

    public TextField getInputName() {
        return inputName;
    }

    public void setInputName(TextField inputName) {
        this.inputName = inputName;
    }
        
    public TextField getInputAddress() {
        return inputAddress;
    }

    public void setInputAddress(TextField inputAddress) {
        this.inputAddress = inputAddress;
    }

    public TextField getInputTelNo() {
        return inputTelNo;
    }

    public void setInputTelNo(TextField inputTelNo) {
        this.inputTelNo = inputTelNo;
    }

    public Spinner<Integer> getSpinnerStartHour() {
        return spinnerStartHour;
    }

    public void setSpinnerStartHour(Spinner<Integer> spinnerStartHour) {
        this.spinnerStartHour = spinnerStartHour;
    }

    public Spinner<Integer> getSpinnerEndHour() {
        return spinnerEndHour;
    }

    public void setSpinnerEndHour(Spinner<Integer> spinnerEndHour) {
        this.spinnerEndHour = spinnerEndHour;
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

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
       
    public boolean detectedInvalidFields(){
        ShopFormValidator formValidator = new ShopFormValidator(this);
        return formValidator.validateAlterForm();
    }

    public boolean isFilled(){
        
        boolean nameIsNotNull =  !(this.inputName.getText().isEmpty());
            System.out.println("NAME IS NOT NULL : " + nameIsNotNull);
        boolean telIsNotNull  =  !(this.inputTelNo.getText().isEmpty());
            System.out.println("TEL IS NOT NULL : " + telIsNotNull);
        boolean addressIsNotNull = !(this.inputAddress.getText().isEmpty());
            System.out.println("ADDRESS IS NOT NULL : " + addressIsNotNull);

        return(nameIsNotNull && telIsNotNull && addressIsNotNull);
    }
    
    public void configureSpinner(Spinner<?> spinner) {
        spinner.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            spinner.getEditor().setText(newValue.replaceAll("[^0-9.]+",""));
            if(spinner.getEditor().getText().isEmpty()){
                spinner.getEditor().setText("0");
            }
        });  
    }
}