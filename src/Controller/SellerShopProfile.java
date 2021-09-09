package Controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ResourceBundle;

import Cache.*;
import Controller.Popup.EditShopProfile;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SellerShopProfile implements Initializable{
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();

    @FXML private AnchorPane paneProfile;
    @FXML private Label lblWelcome;    
    @FXML private ImageView iconHome,imgShop;
    @FXML private ListView<String> listView;    
    private String currentFXMLPath = "View/SellerShopProfile.fxml";
    private File imgFile;
    private InputStream isImage;
    private Image img;

    @Override
    public void initialize(URL location, ResourceBundle resources) {                
        listView.getItems().add("Name: "+data.getSeller().getShop().getName());
        listView.getItems().add("Tel No. : "+data.getSeller().getShop().getTel());
        listView.getItems().add("Address: "+data.getSeller().getShop().getAddress());
        listView.getItems().add("Business Hour: "+(data.getSeller().getShop().getStartHour().getHour()==data.getSeller().getShop().getEndHour().getHour()?"24 Hours":data.getSeller().getShop().getStartHour()+" ~ "+data.getSeller().getShop().getEndHour()));
        listView.getItems().add("Delivery Fee: "+data.getSeller().getShop().getDeliveryFee());        
        imgFile = new File(Paths.get("").toAbsolutePath().toString().replaceAll("\\\\", "/")+"/src"+data.getSeller().getShop().getImgPath());                
        try {
            isImage = (InputStream) new FileInputStream(imgFile);            
            img = new Image(isImage);
            imgShop.setImage(img);
            // System.out.println("img url - "+img.getUrl());
            // System.out.println("img url - "+img.getHeight());
            // System.out.println("img url - "+img.getWidth());
            // System.out.println("class name  - "+img.getClass());            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }                
    }

    @FXML
    void actionEditProfile(MouseEvent event) throws IOException {        
        Stage myDialog = new Stage();
        gui.alertInProgress(myDialog);
        myDialog.initModality(Modality.APPLICATION_MODAL);  //make user unable to press the original stage/window unless close the current stage/window
        myDialog.initOwner(gui.getStage());
        
        EditShopProfile controller = new EditShopProfile();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Popup/EditShopProfile.fxml"));
        loader.setController(controller);                        
        Scene dialogScene = new Scene((Parent)loader.load());
        
        controller.getBtnYes().setOnAction(e->{
            myDialog.getScene().getRoot().setDisable(true);
            myDialog.getScene().setCursor(Cursor.WAIT);
            if(controller.getShopImageFile()!=null){
                imgFile = null;
                isImage = null;
                img = null;
                imgShop.setImage(null);
                System.gc();
                Task<Void> task = new Task<Void>() {
                    @Override
                    public Void call() throws IOException, SQLException {                                
                        String currentPath = Paths.get("").toAbsolutePath().toString().replaceAll("\\\\", "/");                              
                        Path oldImgPath = Paths.get(currentPath+"/src"+data.getSeller().getShop().getImgPath());
                        String newImgName = data.getSeller().getShop().getShopID()+controller.getNewImgFileExtension();
                        Path newImgPath = Paths.get(currentPath+"/src/Images/"+newImgName);
                        data.getSeller().getShop().edit(controller.getInputName().getText(), controller.getInputAddress().getText(), controller.getInputTelNo().getText(), LocalTime.of(controller.getSpinnerStartHour().getValue(), 0, 0), LocalTime.of(controller.getSpinnerEndHour().getValue(), 0, 0), controller.getSpinnerDeliveryFee().getValue(), "/Images/"+newImgName);                                            
                        try {                                            
                            Files.delete(oldImgPath);                                    
                        } catch (Exception e) {
                            System.out.println("Unable to delete the old img, error: "+e);
                        }finally{
                            Files.copy(controller.getShopImageFile().toPath(), newImgPath);
                        }
                        return null ;
                    }
                };
                task.setOnSucceeded(ev -> {
                    myDialog.close();
                    try {
                        gui.refreshScene(currentFXMLPath);
                        gui.notAlertInProgress(myDialog);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();                    
                    }
                    // tableView.refresh();
                });
                new Thread(task).start();                                                   
            }else{
                data.getSeller().getShop().edit(controller.getInputName().getText(), controller.getInputAddress().getText(), controller.getInputTelNo().getText(), LocalTime.of(controller.getSpinnerStartHour().getValue(), 0, 0), LocalTime.of(controller.getSpinnerEndHour().getValue(), 0, 0), controller.getSpinnerDeliveryFee().getValue(), data.getSeller().getShop().getImgPath());                    
                myDialog.close();
                gui.notAlertInProgress(myDialog);
                try {
                    gui.refreshScene(currentFXMLPath);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        
        controller.getBtnNo().setOnAction(e->{      
            // passback.accept(false);
            myDialog.close();
            gui.notAlertInProgress(myDialog);
        });  
                
        myDialog.setScene(dialogScene);
        myDialog.setMaximized(false);
        myDialog.show();
    }

    @FXML
    void toHome(MouseEvent event) throws IOException {        
        gui.toNextScene(String.format("View/%sHome.fxml",data.getAccount().getAccType()));
    } 
}
