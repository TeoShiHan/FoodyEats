package Controller;
import Classes.Shop;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class shopItemController {
    @FXML private HBox shopItem;
    @FXML private VBox shopImgContainer;
    @FXML private ImageView shopImg;
    @FXML private VBox shopDesc;
    @FXML private Label shopName;
    @FXML private Label categoryLabel;
    @FXML private HBox iconCentralizer;
    @FXML private VBox deliveryVBox;
    @FXML private ImageView deliveryIcon;
    @FXML private VBox priceVerticalAlign;
    @FXML private Label deliveryFee;
    @FXML private StackPane shadowPane;


    public void setData(Shop shop){
        Image shopImage = new Image(getClass().getResourceAsStream(shop.getImgPath()));
        Image deliveryTruckIcon = new Image(getClass().getResourceAsStream("../Images/deliveryIcon.png"));
        deliveryIcon.setImage(deliveryTruckIcon);
        shopImg.setImage(shopImage);
        shadowPane.setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0,0,0,0.8), 7, 0, 0, 3));
        shopName.setText(shop.getName());
        categoryLabel.setText("Not yet include this feature");
        deliveryFee.setText(shop.getDeliveryFee().toString());
    }
}
