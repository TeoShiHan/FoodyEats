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

    @FXML private TextField inputShopName,inputEmail,inputContactNo,inputShopAddr,inputBankAccNo,inputLisenceNo;
    @FXML private Button imageChooser;

    @FXML
    void chooseImage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your shop image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif", "*.jpeg")
        );
        File shopImage = fileChooser.showOpenDialog(gui.getStage());
        if(shopImage!=null){
            // Image image = new Image(shopImage);            
            // String imageName = shopImage.getName();
            // String extension = shopImage.getPath().substring(shopImage.getPath().lastIndexOf(".")+1);            
            // ImageIO.write(shopImage, extension, new File(shopImage.getPath()));

            // try {
            //     BufferedReader br = Files.newBufferedReader(shopImage.toPath(), StandardCharsets.UTF_8);                
            // } catch (IOException e) {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            // }

            // Files.createFile(shopImage.toPath(), attrs)

            // copy(shopImage.getAbsolutePath(), "C:\\Users\\Asus\\Downloads\\SEM 3 - OOP\\FoodyEats\\src\\Images\\shopImage.png");
            
            // https://stackoverflow.com/questions/36991165/how-to-set-the-save-path-for-a-file-chosen-in-filechooser-javafx/36991844#36991844
            Path path = Paths.get("C:/Users/Asus/Downloads/SEM 3 - OOP/FoodyEats/src/Images", "shopImage.png");
            Files.copy(shopImage.toPath(), path);
        }                        

        // try {
        //     // retrieve image
        //     BufferedImage bi = getMyImage();
        //     File outputfile = new File("saved.png");
        //     ImageIO.write(bi, "png", outputfile);
        // } catch (IOException e) {
        //     ...
        // }        
    }

    // public void copy(String from, String to) {
    //     FileReader fr = null;
    //     FileWriter fw = null;
    //     try {
    //         fr = new FileReader(from);
    //         fw = new FileWriter(to);
    //         int c = fr.read();
    //         while(c!=-1) {
    //             fw.write(c);
    //             c = fr.read();
    //         }
    //     } catch(IOException e) {
    //         e.printStackTrace();
    //     } finally {
    //         close(fr);
    //         close(fw);
    //     }
    // }

    // public void close(Closeable stream){
    //     try {
    //         if (stream != null) {
    //             stream.close();
    //         }
    //     } catch(IOException e) {
    //         //...
    //     }
    // }
}
