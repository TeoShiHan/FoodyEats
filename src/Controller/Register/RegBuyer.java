package Controller.Register;
import Cache.*;

import java.time.LocalDate;
import java.util.HashMap;

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
        return !(inputName.getText().isEmpty() || inputAddress.getText().isEmpty() || 
        inputEmail.getText().isEmpty() || inputMobileNo.getText().isEmpty());
    }

    public void getInfo(){        
        data.addObjectHolder("accountName", inputName.getText());
        data.addObjectHolder("accountEmail", inputEmail.getText());
        data.addObjectHolder("accountMobileNo", inputMobileNo.getText());
        // data.addObjectHolder("accountDateCreated", LocalDate.now());
        data.addObjectHolder("buyerAddress", inputAddress.getText());
    }
}
