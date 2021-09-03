package Controller;
import Cache.*;
import Classes.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SellerHome implements Initializable{
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();    
    private DataHolder data = DataHolder.getInstance();
    
    @FXML private AnchorPane paneSellerHome;
    @FXML private ImageView iconProfile;
    @FXML private Label lblWelcome,linkLogout;
    @FXML private HBox toggleSwitch;
    @FXML private Pane btnIncomingOrder,btnManageProduct,btnOrderHistory,btnMyShop;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        if(data.getSeller().getShop()==null){
            data.getSeller().loadShop();
            data.setShop(data.getSeller().getShop());
        }        
        toggleSwitch.setAlignment(Pos.CENTER_RIGHT);
        toggleSwitch.getChildren().setAll(new SwitchButton());
        lblWelcome.setText("Welcome "+data.getAccount().getUsername());
    }

    @FXML
    void toProfile(MouseEvent event) throws IOException {
        gui.toNextScene("View/Profile.fxml");
    }

    @FXML
    void logout(MouseEvent event) throws IOException {
        gui.toNextScene("View/Login.fxml");
        data.clear();
    }

    @FXML
    void toSellerIncomingOrder(MouseEvent event) throws IOException {
        gui.toNextScene("View/SellerIncomingOrder.fxml");
    }    

    @FXML
    void toSellerOrderHistory(MouseEvent event) throws IOException {
        gui.toNextScene("View/SellerOrderHistory.fxml");
    }

    @FXML
    void toSellerManageFood(MouseEvent event) throws IOException {
        gui.toNextScene("View/SellerManageFood.fxml");
    }
    
    @FXML
    void toSellerShopProfile(MouseEvent event) throws IOException {
        gui.toNextScene("View/SellerShopProfile.fxml");
    }
}

// https://stackoverflow.com/questions/30593193/creating-sliding-on-off-switch-button-in-javafx
class SwitchButton extends StackPane {
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();    
    private DataHolder data = DataHolder.getInstance();
    private final Rectangle back = new Rectangle(100, 40, Color.RED);
    private final Button button = new Button();
    private final Label label = new Label();
    private String buttonStyleOff = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: WHITE;";
    private String buttonStyleOn = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: #00893d;";
    private BooleanProperty state = new SimpleBooleanProperty(false);
    private int limitCount = 4;

    public BooleanProperty getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state.setValue(state);
    }

    public boolean getStatePropValue(){
        return this.state.getValue();
    }

    private void init() {
        getChildren().addAll(back,button,label);
        setMinSize(30, 15);
        back.maxWidth(30);
        back.minWidth(30);
        back.maxHeight(10);
        back.minHeight(10);
        back.setArcHeight(back.getHeight());
        back.setArcWidth(back.getHeight());        
        Double r = 25.0;
        button.setShape(new Circle(r));        
        button.setMaxSize(50, 50);
        button.setMinSize(50, 50);        
        
        //  https://stackoverflow.com/questions/43910652/change-label-depending-on-boolean-javafx
        this.alignmentProperty().bind(Bindings.createObjectBinding(()->{
            return getState().get()?Pos.CENTER_RIGHT:Pos.CENTER_LEFT;
        }, getState()));        
        label.textProperty().bind(Bindings.createStringBinding(()->{
            return getState().get()?"ON":"OFF";
        },getState()));
        button.styleProperty().bind(Bindings.createStringBinding(()->{
            return getState().get()?buttonStyleOn:buttonStyleOff;
        }, getState()));
        back.styleProperty().bind(Bindings.createStringBinding(()->{
            return getState().get()?"-fx-fill: #80C49E":"-fx-fill: #ced5da";
        },getState()));        
        label.styleProperty().bind(Bindings.createStringBinding(()->{
            return getState().get() 
                    ? "-fx-font-weight:bold; -fx-font-size:20px; -fx-text-fill:white; -fx-padding:0 10px 0 10px;"
                    : "-fx-font-weight:bold; -fx-font-size:20px; -fx-text-fill:black; -fx-padding: 0 10px 0 5px;";

        }, getState()));        
    }

    public SwitchButton() {
        init();
        this.setCursor(Cursor.HAND);
        EventHandler<Event> click = new EventHandler<Event>() {
            @Override
            public void handle(Event event) {                
                if(limitCount-->0){
                    if(state.get()){
                        gui.getStage().getScene().getRoot().setDisable(true);
                        gui.getStage().getScene().setCursor(Cursor.WAIT);
                        Task<Void> task = new Task<Void>() {
                            @Override
                            public Void call() {                                
                                data.getSeller().getShop().closeShop();                                                    
                                return null ;
                            }
                        };
                        task.setOnSucceeded(e -> {
                            setState(false);
                            gui.getStage().getScene().setCursor(Cursor.DEFAULT);
                            gui.getStage().getScene().getRoot().setDisable(false);
                        });
                        new Thread(task).start();
                    }else{
                        gui.getStage().getScene().getRoot().setDisable(true);
                        gui.getStage().getScene().setCursor(Cursor.WAIT);
                        Task<Void> task = new Task<Void>() {
                            @Override
                            public Void call() {                                
                                data.getSeller().getShop().openShop();                                                 
                                return null ;
                            }
                        };
                        task.setOnSucceeded(e -> {
                            setState(true);
                            gui.getStage().getScene().setCursor(Cursor.DEFAULT);
                            gui.getStage().getScene().getRoot().setDisable(false);
                        });
                        new Thread(task).start();                                                
                    }
                }else{
                    try {
                        gui.informationPopup("Attention", "You cannot frequently open and close the shop");
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        };

        button.setFocusTraversable(false);
        setOnMouseClicked(click);
        button.setOnMouseClicked(click);
    }
}

//#region another method to make a toggle switch
// https://stackoverflow.com/questions/20556945/changing-cursor-in-javafx-application-for-long-operations
// class ToggleSwitch extends HBox {
	
// 	private final Label label = new Label();
// 	private final Button button = new Button();
	
// 	private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(false);
// 	public SimpleBooleanProperty switchOnProperty() { return switchedOn; }
	
// 	private void init() {
		
// 		label.setText("OFF");
		
// 		getChildren().addAll(label, button);	
// 		button.setOnAction((e) -> {
// 			switchedOn.set(!switchedOn.get());
// 		});
// 		label.setOnMouseClicked((e) -> {
// 			switchedOn.set(!switchedOn.get());
// 		});
// 		setStyle();
// 		bindProperties();
// 	}
	
// 	private void setStyle() {
// 		//Default Width
// 		setWidth(80);
// 		label.setAlignment(Pos.CENTER);
// 		setStyle("-fx-background-color: grey; -fx-text-fill:black; -fx-background-radius: 4;");
// 		setAlignment(Pos.CENTER_LEFT);
// 	}
	
// 	private void bindProperties() {
// 		label.prefWidthProperty().bind(widthProperty());
// 		label.prefHeightProperty().bind(heightProperty());
// 		button.prefWidthProperty().bind(widthProperty());
// 		button.prefHeightProperty().bind(heightProperty());
// 	}
	
// 	public ToggleSwitch() {
//         this.setCursor(Cursor.HAND);
//         label.setStyle("-fx-text-fill:white;");
//         button.setStyle("-fx-focus-color:transparent; -fx-faint-focus-color:transparent;");
// 		init();
// 		switchedOn.addListener((a,b,c) -> {
// 			if (c) {
//                 label.setText("ON");
//                 setStyle("-fx-background-color: green;");
//                 label.toFront();
//             }
//             else {
//                 label.setText("OFF");
//                 setStyle("-fx-background-color: grey;");
//                 button.toFront();
//             }
// 		});
// 	}    
// }
//#endregion