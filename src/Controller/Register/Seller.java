package Controller.Register;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import Cache.GUI;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

public class Seller {
    GUI gui = GUI.getInstance();

    @FXML private TextField inputName,inputAddress,inputState,inputNRIC,inputBankAccNo,inputLisenceNo;

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

    public TextField getInputNRIC() {
        return inputNRIC;
    }
    public void setInputNRIC(TextField inputNRIC) {
        this.inputNRIC = inputNRIC;
    }

    public TextField getInputBankAccNo() {
        return inputBankAccNo;
    }
    public void setInputBankAccNo(TextField inputBankAccNo) {
        this.inputBankAccNo = inputBankAccNo;
    }

    public TextField getInputLisenceNo() {
        return inputLisenceNo;
    }
    public void setInputLisenceNo(TextField inputLisenceNo) {
        this.inputLisenceNo = inputLisenceNo;
    }
}
