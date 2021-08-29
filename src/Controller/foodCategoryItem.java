package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class foodCategoryItem {
    @FXML private Button catTag;

    public void setStringToLabel(String categoryName){
        catTag.setText(categoryName);
    }
}
