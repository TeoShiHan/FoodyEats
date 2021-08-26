package Controller.Popup;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.event.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProceedPayment implements Initializable{
    @FXML
    private WebView webView;

    private WebEngine webEngine;    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        webEngine = webView.getEngine();
        try {
            loadPage();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            webEngine.load("https://en.wikipedia.org/wiki/HTTP_404");
            // e.printStackTrace();
        }
    }
    
    @FXML
    void loadPage() throws IOException{                  
        // if(!(textField.getText().startsWith("https://")||textField.getText().startsWith("http://")||textField.getText().startsWith("https:")||textField.getText().startsWith("http:"))){
        //     textField.setText("https://"+textField.getText());                
        // }        
        // if(detectURL()==200){
        //     webEngine.load(textField.getText());
        // }else{
            webEngine.load("https://en.wikipedia.org/wiki/HTTP_404");
        // }
        
        // webEngine.load(textField.getText());       
        // if(textField.getText().endsWith(".com/")||textField.getText().endsWith(".com")){
        // }else{
        //     webEngine.load("https://en.wikipedia.org/wiki/HTTP_404");
        // }               
    }

    // public int detectURL() throws IOException{
    //     URL u = new URL (textField.getText());
    //     HttpURLConnection huc =  ( HttpURLConnection )  u.openConnection (); 
    //     huc.setRequestMethod ("GET");  //OR  huc.setRequestMethod ("HEAD"); 
    //     huc.connect () ; 
    //     int code = huc.getResponseCode() ;        
    //     return code;
    // }
}
