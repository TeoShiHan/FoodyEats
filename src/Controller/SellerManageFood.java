package Controller;
import Cache.*;
import Classes.*;
import Controller.Popup.EditFood;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SellerManageFood implements Initializable {
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();
    @FXML private AnchorPane paneSellerOrders;
    @FXML private Label lblWelcome;    
    @FXML private TableView<Food> tableView;    
    @FXML private TableColumn<Food,String> colImage;
    @FXML private TableColumn<Food,Object> colName,colPrice,colDescription,colCategory;
    @FXML private TableColumn<Food,Food> colAction;
    private String currentFXMLPath = "/View/SellerManageFood.fxml";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data.setFoods(new ArrayList<>());
        if(data.getShop().getFoods()==null){ 
            data.getShop().loadFoods();            
        }
        data.setFoods(data.getShop().getFoods());

        ObservableList<Food> observableList = FXCollections.observableArrayList(data.getFoods());
        
        tableView.setItems(observableList);
        
        colImage.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(150);
            imageview.setFitWidth(150);
     
            //Set up the Table
            TableCell<Food, String> cell = new TableCell<Food, String>() {
                public void updateItem(String imgPath, boolean empty) {                    
                  if (imgPath != null) {
                       imageview.setImage(new Image(getClass().getResourceAsStream(imgPath)));
                  }
                }
            };
            // Attach the imageview to the cell
            cell.setGraphic(imageview);
            return cell;
        });        
        colImage.setCellValueFactory(new PropertyValueFactory<Food,String>("imgPath"));
        colName.setCellFactory(param -> {
            return new TableCell<Food, Object>() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);
    
                    if (item == null || empty) {
                        this.setText(null);
                        this.setStyle("");
                    } else {
                        Text text = new Text(item.toString());                        
                        text.setStyle("-fx-padding: 5px 30px 5px 5px;"
                                + "-fx-text-alignment:center;");
                        text.setWrappingWidth(param.getPrefWidth() - 35);
                        //the only change that i make
                        this.setPrefHeight(text.getLayoutBounds().getHeight()+10);
                        this.setGraphic(text);
                    }
                }
            };
        });
        colName.setCellValueFactory(new PropertyValueFactory<Food,Object>("name")); 
        colDescription.setCellFactory(param -> {
            return new TableCell<Food, Object>() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);
    
                    if (item == null || empty) {
                        this.setText(null);
                        this.setStyle("");
                    } else {
                        Text text = new Text(item.toString());                        
                        text.setStyle("-fx-padding: 5px 30px 5px 5px;"
                                + "-fx-text-alignment:justify;");
                        text.setWrappingWidth(param.getPrefWidth() - 35);
                        //the only change that i make
                        this.setPrefHeight(text.getLayoutBounds().getHeight()+10);
                        this.setGraphic(text);
                    }
                }
            };
        });
        colDescription.setCellValueFactory(new PropertyValueFactory<Food,Object>("desc"));  
        colPrice.setCellValueFactory(new PropertyValueFactory<Food,Object>("price"));  
        colCategory.setCellValueFactory(new PropertyValueFactory<Food,Object>("category"));           
        colAction.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        // to set what to display in each table cell, instead string only, because by default it display string (https://stackoverflow.com/questions/32282230/fxml-javafx-8-tableview-make-a-delete-button-in-each-row-and-delete-the-row-a)        
        colAction.setCellFactory(param -> new TableCell<Food,Food>(){
            ImageView deleteImage = new ImageView(new Image(getClass().getResourceAsStream("/Images/deleteIcon.png")));
            ImageView editImage = new ImageView(new Image(getClass().getResourceAsStream("/Images/editIcon.png")));                  
            Button btnDelete = new Button();
            Button btnEdit = new Button();            
            @Override
            public void updateItem(Food food, boolean empty){                
                super.updateItem(food, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                deleteImage.setFitWidth(50);
                deleteImage.setFitHeight(50);                
                editImage.setFitWidth(50);
                editImage.setFitHeight(50);                
                btnDelete.setGraphic(deleteImage);
                btnEdit.setGraphic(editImage);
                HBox pane = new HBox(btnEdit,btnDelete);
                pane.setSpacing(20);                
                pane.setAlignment(Pos.CENTER);
                setGraphic(pane);
                btnEdit.setOnAction(event->{                    
                    data.setFood(food);

                    Stage myDialog = new Stage();
                    gui.alertInProgress(myDialog);
                    myDialog.initModality(Modality.APPLICATION_MODAL);  //make user unable to press the original stage/window unless close the current stage/window
                    myDialog.initOwner(gui.getStage());
                    
                    EditFood controller = new EditFood();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Popup/EditFood.fxml"));
                    loader.setController(controller);                        
                    Scene dialogScene = null;
                    try {
                        dialogScene = new Scene((Parent)loader.load());                        
                    } catch (IOException e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                    }
                    
                    controller.getBtnYes().setOnAction(ev->{                                                      
                        myDialog.getScene().getRoot().setDisable(true);
                        myDialog.getScene().setCursor(Cursor.WAIT);
                        Task<Void> task = new Task<Void>() {
                            @Override
                            public Void call() throws IOException, SQLException {                                
                                String currentPath = Paths.get("").toAbsolutePath().toString().replaceAll("\\\\", "/");
                                Path oldImgPath = Paths.get(currentPath+"/src"+food.getImgPath());
                                String newImgName = food.getFoodID()+controller.getNewImgFileExtension();
                                Path newImgPath = Paths.get(currentPath+"/src/Images/"+newImgName);
                                data.getFood().edit(controller.getInputName().getText(), controller.getInputDescription().getText(), controller.getSpinnerPrice().getValue(), controller.getInputCategory().getText(), "/Images/"+newImgName);
                                try {                        
                                    // File fileName = new File(oldImgPath.toString());            
                                    // fileName.delete();
                                    Files.delete(oldImgPath);                                    
                                } catch (Exception e) {
                                    //TODO: handle exception
                                    System.out.println("Unable to delete the old img");
                                }finally{
                                    Files.copy(controller.getShopImageFile().toPath(), newImgPath);
                                    // https://stackoverflow.com/questions/1158777/rename-a-file-using-java/20260300#20260300
                                    // Files.move(tempSource, tempSource.resolveSibling(food.getFoodID()+controller.getNewImgFileExtension()));
                                }
                                return null ;
                            }
                        };
                        task.setOnSucceeded(e -> {
                            myDialog.close();
                            try {
                                gui.refreshScene(currentFXMLPath);
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                                gui.notAlertInProgress(myDialog);
                            }
                            // tableView.refresh();
                        });
                        new Thread(task).start();                        
                    });
                    
                    controller.getBtnNo().setOnAction(e->{
                        myDialog.close();
                        gui.notAlertInProgress(myDialog);
                    });  
                            
                    myDialog.setScene(dialogScene);
                    myDialog.setMaximized(false);
                    myDialog.show();
                });                

                btnDelete.setOnAction(event->{
                    Task<Void> task = new Task<Void>() {
                        @Override
                        public Void call() throws IOException {                            
                            food.delete();
                            data.getFoods().remove(food);
                            tableView.getItems().remove(food);
                            return null;
                        }
                    };
                    task.setOnSucceeded(e -> {                                                    
                        tableView.refresh();                         
                    });
                    new Thread(task).start();                                                            
                });                                       
            }                     
        });

        tableView.setItems(observableList);        
        // tableView.getColumns().addAll(colOrderId,colDate,colStatus,colAction); //not needed
    }    

    @FXML
    void toBack(MouseEvent event) throws IOException {
        gui.toPrevScene();
    }

    @FXML
    void toHome(MouseEvent event) throws IOException {
        gui.toNextScene("View/SellerHome.fxml");
    }
}
