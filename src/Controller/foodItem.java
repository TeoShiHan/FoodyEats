package Controller;
import Classes.Food;
import Interfaces.FoodItemListeners;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class FoodItem {

    @FXML private HBox foodItemHbox;
    @FXML private ImageView foodPic;
    @FXML private Label foodName;
    @FXML private Text foodDesc;
    @FXML private Label malaysiaCurrency;
    @FXML private Label foodPrice;
    @FXML private Label qtyLabel;
    @FXML private Spinner<Integer> inputQtySpin;
    @FXML private HBox addToCartBtn;
    @FXML private ImageView cartIcon;

    @FXML public void clickAddToCartBtn(MouseEvent mouseEvent){
        int quantity = inputQtySpin.getValue();
            /**DEBUG MSG*/System.out.println("QUANTITY IS : " + quantity);
        
        foodItemListeners.addToCart(food, quantity);
    }

    private FoodItemListeners foodItemListeners;
    private Food food;

    public void setDataToFoodItem(Food food, FoodItemListeners foodItemListeners) {
        this.foodItemListeners = foodItemListeners;
        this.food = food;
        Image foodImage = new Image(getClass().getResourceAsStream(food.getImgPath()));
            /**DEBUG MSG*/System.out.println("img path  IS >>>: " + food.getImgPath());
            /**DEBUG MSG*/System.out.println("foodImage IS >>>: " + foodImage);
        Image cartImage = new Image(getClass().getResourceAsStream("../Images/cartIcon.png"));
        cartIcon.setImage(cartImage);
        foodPic.setImage(foodImage);
        foodName.setText(food.getName());
        foodDesc.setText(food.getDesc());
        foodPrice.setText(food.getPrice().toString());
    }

    public void configureTheSpinner(){
        SpinnerValueFactory<Integer> qtyValueFactory  = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50,0);
        this.inputQtySpin.setValueFactory(qtyValueFactory);
        inputQtySpin.setEditable(true);
    }
}
