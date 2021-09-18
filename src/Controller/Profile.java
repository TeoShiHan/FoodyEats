package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Cache.*;
import Classes.*;
import Controller.Popup.EditProfile;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Profile implements Initializable {
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();
    private String currentFXMLPath = "View/Profile.fxml";

    @FXML
    private AnchorPane paneProfile;
    @FXML
    private Label lblWelcome;
    @FXML
    private ImageView iconHome, iconCart;
    @FXML
    private ListView<String> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println(data.getAccount().getAccType());

        listView.getItems().add("Username: " + data.getAccount().getUsername());
        listView.getItems().add("Passsowrd: " + data.getAccount().getPassword());
        listView.getItems().add("Name: " + data.getAccount().getName());
        listView.getItems().add("Email: " + data.getAccount().getEmail());
        listView.getItems().add("Mobile No.: " + data.getAccount().getMobileNo());

        if (data.getAccount() instanceof Buyer) {
            listView.getItems().add("Address: " + ((Buyer) data.getAccount()).getAddress());

        } else if (data.getAccount() instanceof Rider) {
            // do nothing

        } else if (data.getAccount() instanceof Seller) {
            listView.getItems().add("Bank Acc No.: " + ((Seller) data.getAccount()).getBankAcc());
            listView.getItems().add("NRIC: " + ((Seller) data.getAccount()).getNRIC());
            listView.getItems().add("License No.: " + ((Seller) data.getAccount()).getLicenseNumber());

        } else if (data.getAccount() instanceof Admin) {
            listView.getItems().add("NRIC: " + ((Admin) data.getAccount()).getNRIC());
            listView.getItems().add("Company Branch: " + ((Admin) data.getAccount()).getCompanyBranch());
        }
    }

    @FXML
    void actionEditProfile(MouseEvent event) throws IOException {
        Stage myDialog = new Stage();
        gui.alertInProgress(myDialog);
        myDialog.initModality(Modality.APPLICATION_MODAL); // make user unable to press the original stage/window unless
                                                           // close the current stage/window
        myDialog.initOwner(gui.getStage());

        EditProfile controller = new EditProfile();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Popup/EditProfile.fxml"));
        loader.setController(controller);
        Scene dialogScene = new Scene((Parent) loader.load());

        controller.getBtnYes().setOnAction(e -> {

            if (data.getAccount() instanceof Seller) {

                if (sellerProfileIsFilled(controller)) {
                    if (!controller.detectedInvalidSellerInfo()) {
                        updateChangeInSellerProfileToDatabase(controller);
                        myDialog.close();
                    } else {
                        popUpInvalidFieldMsg();
                    }
                } else {
                    popUpEmptyFieldMsg();
                }

            } else if (data.getAccount() instanceof Buyer) {

                if (buyerProfileIsFilled(controller)) {
                    if (!controller.detectedInvalidBuyerInfo()) {
                        System.out.println("IN PROFILE THE DETECTED INVALID BUYER INFO IS: " + !controller.detectedInvalidBuyerInfo());
                        updateChangeInBuyerProfileToDatabase(controller);
                        myDialog.close();
                    } else {
                        popUpInvalidFieldMsg();
                    }
                } else {
                    popUpEmptyFieldMsg();
                }

            } else if (data.getAccount() instanceof Rider) {

                if (riderProfileIsFilled(controller)) {
                    if (!controller.detectedInvalidRiderInfo()) {
                        updateChangeInRiderProfileToDatabase(controller);
                        myDialog.close();
                    } else {
                        popUpInvalidFieldMsg();
                    }
                } else {
                    popUpEmptyFieldMsg();
                }

            } else if (data.getAccount() instanceof Admin) {
                if (adminProfileIsFilled(controller)) {
                    if (!controller.detectedInvalidAdminInfo()) {
                        updateChangeInAdminProfileToDatabase(controller);
                        myDialog.close();
                    } else {
                        popUpInvalidFieldMsg();
                    }
                } else {
                    popUpEmptyFieldMsg();
                }
            }

            try {
                gui.refreshScene(currentFXMLPath);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            gui.notAlertInProgress(myDialog);
        });

        controller.getBtnNo().setOnAction(e -> {
            myDialog.close();
            gui.notAlertInProgress(myDialog);
        });

        myDialog.setScene(dialogScene);
        myDialog.setMaximized(false);
        myDialog.show();
    }

    @FXML
    void toHome(MouseEvent event) throws IOException {
        gui.toNextScene(String.format("View/%sHome.fxml", data.getAccount().getAccType()));
    }

    public void popUpInvalidFieldMsg() {
        try {
            gui.informationPopup("Attention", "Invalid field detected");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void popUpEmptyFieldMsg() {
        try {
            gui.informationPopup("Attention", "Empty field detected, please fill out the form!");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void updateChangeInBuyerProfileToDatabase(EditProfile controller) {
        String newAddress = controller.getInputAddress().getText();
        Account tempAcc = createTemptAccountInstance(controller);
        ((Buyer) data.getAccount()).edit(tempAcc, newAddress);
    }

    public void updateChangeInAdminProfileToDatabase(EditProfile controller) {
        String newNRIC = controller.getInputNRIC().getText();
        String newBranchNo = controller.getInputCompanyBranch().getText();
        Account tempAcc = createTemptAccountInstance(controller);
        ((Admin) data.getAccount()).edit(tempAcc, newNRIC, newBranchNo);
    }

    public void updateChangeInSellerProfileToDatabase(EditProfile controller) {
        String newAddress = controller.getInputAddress().getText();
        String newBankAcc = controller.getInputBankAccNo().getText();
        String newNRIC = controller.getInputNRIC().getText();
        String newLicenseNo = controller.getInputLicenseNo().getText();
        Account tempAcc = createTemptAccountInstance(controller);
        ((Seller) data.getAccount()).edit(tempAcc, newAddress, newBankAcc, newNRIC, newLicenseNo);
    }

    public void updateChangeInRiderProfileToDatabase(EditProfile controller) {
        Account tempAcc = createTemptAccountInstance(controller);
        ((Rider) data.getAccount()).edit(tempAcc);
    }

    public boolean buyerProfileIsFilled(EditProfile controller) {
        boolean addressIsEmpty = controller.getInputAddress().getText().isEmpty();
        boolean accContainEmpty = accInstanceContainEmptyField(controller);
        return (!(accContainEmpty || addressIsEmpty));
    }

    public boolean sellerProfileIsFilled(EditProfile controller) {
        boolean addressIsEmpty = controller.getInputAddress().getText().isEmpty();
        boolean bankAccIsEmpty = controller.getInputBankAccNo().getText().isEmpty();
        boolean icNoIsEmpty = controller.getInputNRIC().getText().isEmpty();
        boolean licenseNoIsEmpty = controller.getInputLicenseNo().getText().isEmpty();
        boolean accContainEmpty = accInstanceContainEmptyField(controller);
        return (!(addressIsEmpty || bankAccIsEmpty || icNoIsEmpty || licenseNoIsEmpty || accContainEmpty));
    }

    public boolean adminProfileIsFilled(EditProfile controller) {
        boolean icNoIsEmpty = controller.getInputNRIC().getText().isEmpty();
        boolean branchNoIsEmpty = controller.getInputCompanyBranch().getText().isEmpty();
        boolean accContainEmpty = accInstanceContainEmptyField(controller);
        return (!(icNoIsEmpty || branchNoIsEmpty || accContainEmpty));
    }

    public boolean riderProfileIsFilled(EditProfile controller) {
        return !accInstanceContainEmptyField(controller);
    }

    public Account createTemptAccountInstance(EditProfile controller) {
        Account tempAcc = new Account();
        tempAcc.setUsername(controller.getInputUsername().getText());
        tempAcc.setPassword(controller.getInputPassword().getText());
        tempAcc.setName(controller.getInputName().getText());
        tempAcc.setEmail(controller.getInputEmail().getText());
        tempAcc.setMobileNo(controller.getInputMobileNo().getText());
        return tempAcc;
    }

    public boolean accInstanceContainEmptyField(EditProfile controller) {
        Account tempAccInstance = createTemptAccountInstance(controller);

        boolean usernameIsEmpty = tempAccInstance.getUsername().isEmpty();
        boolean passwordIsEmpty = tempAccInstance.getPassword().isEmpty();
        boolean nameIsEmpty = tempAccInstance.getName().isEmpty();
        boolean emailIsEmpty = tempAccInstance.getEmail().isEmpty();
        boolean mobileNoIsEmpty = tempAccInstance.getMobileNo().isEmpty();

        return (usernameIsEmpty || passwordIsEmpty || nameIsEmpty || emailIsEmpty || mobileNoIsEmpty);
    }
}
