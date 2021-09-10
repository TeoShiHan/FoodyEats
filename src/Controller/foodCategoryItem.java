package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FoodCategoryItem {
    @FXML private Button catTag;

    public void setStringToLabel(String categoryName){
        catTag.setText(categoryName);
    }
}
