package Controller;
import java.util.ArrayList;

import Classes.Shop;
import ListenerInterfaces.BuyerHomeListeners;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ShopItemController{
    
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
    @FXML private Button viewShopBtn;

    @FXML
    private void clickShopBtn(MouseEvent mouseEvent){
        listener.showFoodMenuOfChoosenShop(shop);
    };

    private BuyerHomeListeners listener;
    private Shop shop;
    private ArrayList<String> availableCategory;
    private String categoryStr;

    public void setData(Shop shop, BuyerHomeListeners listener){
        this.listener = listener;
        this.shop = shop;

        Image shopImage = new Image(getClass().getResourceAsStream(shop.getImgPath()));
        Image deliveryTruckIcon = new Image(getClass().getResourceAsStream("../Images/deliveryIcon.png"));
        deliveryIcon.setImage(deliveryTruckIcon);
        shopImg.setImage(shopImage);
        shadowPane.setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0,0,0,0.8), 7, 0, 0, 3));
        shopName.setText(shop.getName());

        availableCategory = shop.getAvailableFoodCategoryInShop();

        for(int i = 0 ; i < availableCategory.size() ; i++){
            if(i > 0){
                categoryStr = categoryStr + ", " + availableCategory.get(i);
            }
            else{
                categoryStr = availableCategory.get(0);
            }
            
            if(i > 5){
                break;
            }
        }
        
        categoryLabel.setText(categoryStr);
        deliveryFee.setText("RM " + shop.getDeliveryFee());
    }

    public Button getViewShopBtn() {
        return viewShopBtn;
    }

    public void setViewShopBtn(Button viewShopBtn) {
        this.viewShopBtn = viewShopBtn;
    }
}
