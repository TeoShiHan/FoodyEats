package Controller;
import Cache.*;
import Classes.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AdminSellerAccountDetails implements Initializable {    
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();    
    private DataHolder data = DataHolder.getInstance();    
    
    @FXML private ImageView shopImg;
    @FXML private Label linkLogout,lblSellerAccountDetails,lblDateReg,lblName,lblEmail,lblMobileNo,lblNRIC,lblLicenseNo,lblBankAccNo,lblAddress,lblShopName,lblShopTel,lblShopAddress,lblShopDeliveryFee,lblShopDateCraeted;        
    @FXML private ImageView iconProfile,iconHome,iconBack;            
    @FXML private Button btnApprove;          

    @Override
    public void initialize(URL location, ResourceBundle resources) {                
        
        lblSellerAccountDetails.setText("Seller Account #"+data.getSeller().getAccountID());
        lblDateReg.setText(data.getSeller().getRegDate().toString());        
        lblName.setText(data.getSeller().getName());
        lblEmail.setText(data.getSeller().getEmail());
        lblMobileNo.setText(data.getSeller().getMobileNo());
        lblNRIC.setText(data.getSeller().getNRIC());
        lblLicenseNo.setText(data.getSeller().getLicenseNumber());
        lblBankAccNo.setText(data.getSeller().getBankAcc());
        lblAddress.setText(data.getSeller().getAddress());
        lblShopName.setText(data.getSeller().getShop().getName());
        lblShopTel.setText(data.getSeller().getShop().getTel());
        lblShopAddress.setText(data.getSeller().getShop().getAddress());        
        lblShopDeliveryFee.setText(String.format("RM %.2f",data.getSeller().getShop().getDeliveryFee()));
        lblShopDateCraeted.setText(data.getSeller().getShop().getDateCreated().toString());
        try {
            shopImg.setImage(new Image(new FileInputStream(Paths.get("").toAbsolutePath().toString().replaceAll("\\\\", "/")+"/src"+data.getSeller().getShop().getImgPath())));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }    

    @FXML
    void actionApprove(ActionEvent event) {
        data.getAdmin().verifySeller(data.getSeller().getSellerID());
        btnApprove.setDisable(true);
    }

    @FXML
    void toHome(MouseEvent event) throws IOException {
        gui.toNextScene("View/AdminHome.fxml");
    }        

    @FXML
    void toBack(MouseEvent event) throws IOException {
        gui.toPrevScene();
    }  
}