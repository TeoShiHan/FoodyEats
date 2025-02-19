package Controller;
import Classes.Food;
import ListenerInterfaces.FoodItemListeners;
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
        foodItemListeners.addToCart(food, quantity);
    }

    private FoodItemListeners foodItemListeners;
    private Food food;

    public void setDataToFoodItem(Food food, FoodItemListeners foodItemListeners) {
        this.foodItemListeners = foodItemListeners;
        this.food = food;
        Image foodImage = new Image(getClass().getResourceAsStream(food.getImgPath()));
        Image cartImage = new Image(getClass().getResourceAsStream("../Images/cartIcon.png"));
        cartIcon.setImage(cartImage);
        foodPic.setImage(foodImage);
        foodName.setText(food.getName());
        foodDesc.setText(food.getDesc());
        foodPrice.setText(food.getPrice().toString());
    }

    public void configureTheSpinner(){
        SpinnerValueFactory<Integer> qtyValueFactory  = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50,1);
        this.inputQtySpin.setValueFactory(qtyValueFactory);
        inputQtySpin.setEditable(true);
        inputQtySpin.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            inputQtySpin.getEditor().setText(newValue.replaceAll("[^0-9]+",""));
            if(inputQtySpin.getEditor().getText().isEmpty()){
                inputQtySpin.getEditor().setText("0");
            }
        });
    }
}
