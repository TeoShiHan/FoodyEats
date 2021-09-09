package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CategoryLabel {

    @FXML private Label categoryLabel;

    public void setLabelText(String categoryStr){
        categoryLabel.setText(categoryStr);
    }
}
