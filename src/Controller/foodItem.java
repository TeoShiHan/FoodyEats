package Controller;
import Classes.Food;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class foodItem {

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

    public void setDataToFoodItem(Food food) {
        Image foodImage = new Image(getClass().getResourceAsStream(food.getImgPath()));
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
    }
}
