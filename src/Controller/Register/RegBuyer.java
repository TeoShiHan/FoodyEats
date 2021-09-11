package Controller.Register;
import Cache.*;
import Classes.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegBuyer {
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();
    
    @FXML private TextField inputName,inputAddress,inputMobileNo,inputEmail;

    public TextField getInputName() {
        return inputName;
    }
    public void setInputName(TextField inputName) {
        this.inputName = inputName;
    }

    public TextField getInputEmail() {
        return inputEmail;
    }
    public void setInputEmail(TextField inputEmail) {
        this.inputEmail = inputEmail;
    }

    public TextField getInputAddress() {
        return inputAddress;
    }
    public void setInputAddress(TextField inputAddress) {
        this.inputAddress = inputAddress;
    }

    public TextField getInputMobileNo() {
        return inputMobileNo;
    }
    public void setInputMobileNo(TextField inputMobileNo) {
        this.inputMobileNo = inputMobileNo;
    }    

    public boolean isFilled(){                
        return !(inputName.getText().strip().isEmpty() || inputAddress.getText().strip().isEmpty() || 
        inputEmail.getText().strip().isEmpty() || inputMobileNo.getText().strip().isEmpty());
    }

    public void getInfo(){                
        Buyer buyer = new Buyer();        
        buyer.setAccType("Buyer");
        buyer.setName(inputName.getText());
        buyer.setEmail(inputEmail.getText());
        buyer.setMobileNo(inputMobileNo.getText());
        buyer.setAddress(inputAddress.getText());
        data.setAccount(buyer);
    }

    public void setTextFieldBorderToRed(TextField textField){
        textField.setStyle("-fx-border-color : red");
    }

    public void setTextFieldBorderToGreen(TextField textField){
        textField.setStyle("-fx-border-color : red");
    }
}
