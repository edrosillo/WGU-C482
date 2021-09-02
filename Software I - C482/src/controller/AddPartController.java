package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;
import model.Inventory;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * The AddPartController class provides control logic for the add part screen of the application.
 */

public class AddPartController implements Initializable {


    /**
     * The machine ID/Comapny name label for the part.
     */
    public Label changeLabel;

    /**
     * The in-house radio button.
     */

    public RadioButton inHouseRBtn;

    /**
     * The outsourced radio button.
     */
    public RadioButton outsourcedRBtn;

    /**
     * The stage object that will be used to hold the GUI and data of the new screens.
     */

    Stage stage;

    /**
     * The scene object that will be used to generate the new screens.
     */
    Parent scene;

    /**
     * The toggle group for the radio buttons.
     */

        @FXML
        private ToggleGroup partTG;

    /**
     * The part max stock text field.
     */

        @FXML
        private TextField partMaxTxt;

    /**
     * The part ID text field.
     */

        @FXML
        private TextField partIdTxt;

    /**
     * The part name text field.
     */

        @FXML
        private TextField partNameTxt;

    /**
     * The part stock text field.
     */

        @FXML
        private TextField partStockTxt;

    /**
     * The part price text field.
     */

        @FXML
        private TextField partPriceText;

    /**
     * The part min stock text field.
     */

        @FXML
        private TextField partMinTxt;

    /**
     * The part Machine ID/Company Name text field.
     */

        @FXML
        private TextField partMachineIdTxt;

    /**
     * Sets machine ID/Company Name label to "Machine ID".
     * @param event In-house Radio button action.
     */

        @FXML
        void onActionInHouse(ActionEvent event) {
            changeLabel.setText("Machine ID");
        }

    /**
     * Loads MainController.
     * @param event Cancel button action.
     */

        @FXML
        void onActionDisplayMain(ActionEvent event) throws IOException {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
            scene.setStyle("-fx-font-family: 'SansSerif';");
            stage.setScene(new Scene(scene));
            stage.show();
        }

    /**
     * Adds new part to inventory and loads MainController.
     * Text fields are validated with error messages displayed preventing empty and/or
     * invalid values.
     * @param event Save button action.
     */

        @FXML
        void onActionSavePart(ActionEvent event) throws IOException {

            try {
                int id = Integer.parseInt(partIdTxt.getText());
                String name = partNameTxt.getText();
                double price = Double.parseDouble(partPriceText.getText());
                int stock = Integer.parseInt(partStockTxt.getText());
                int min = Integer.parseInt(partMinTxt.getText());
                int max = Integer.parseInt(partMaxTxt.getText());
                int machineId;
                String companyName;

                if (minMaxValid(min, max) && stockValid(min, max, stock)) {
                    if (inHouseRBtn.isSelected()) {
                        machineId = Integer.parseInt(partMachineIdTxt.getText());
                        Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));

                    } else if (outsourcedRBtn.isSelected()) {
                        companyName = partMachineIdTxt.getText();
                        Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
                    }
                    Inventory.partId = id;
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
                    scene.setStyle("-fx-font-family: 'SansSerif';");
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            } catch (Exception e) {
                displayAlert(1);
            }

        }

    /**
     * Sets machine ID/Company Name label to "Machine ID".
     * @param event Outsourced Radio button action.
     */

    @FXML
    void onActionOutsourced(ActionEvent event) {
        changeLabel.setText("Company Name");
    }

    /**
     * Validates that min is greater than 0 and less than max.
     * @param min The minimum value for stock of the part.
     * @param max The maximum value for stock of the part.
     * @return Boolean indicating if min is valid.
     */

    private boolean minMaxValid(int min, int max) {

        boolean isValid = true;

        if (min <= 0 || min >= max) {
            isValid = false;
            displayAlert(3);
        }
        return isValid;
    }

    /**
     * Validates that stock level is between min and max.
     * @param min The minimum value of stock for the part.
     * @param max The maximum value of stock for the part.
     * @param stock The stock level for the part.
     * @return Boolean indicating if stock is valid.
     */

    private boolean stockValid(int min, int max, int stock) {

        boolean isValid = true;

        if (stock < min || stock > max) {
            isValid = false;
            displayAlert(4);
        }
        return isValid;
    }

    /**
     * This switch statement is used to displays a variety of alert and error messages.
     * @param alertType Alert selects a message based on case number.
     */

    private void displayAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        DialogPane dp = alert.getDialogPane();
        dp.setStyle("-fx-font-family:sans-serif");

        switch (alertType) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Part");
                alert.setContentText("Form contains blank fields or invalid values.");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Machine ID");
                alert.setContentText("Machine ID may only contain numbers.");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Min");
                alert.setContentText("Min must be a number greater than 0 and less than Max.");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Inventory");
                alert.setContentText("Inventory must be a number equal to or between Min and Max.");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("Name Empty");
                alert.setContentText("Name field can't be empty!");
                alert.showAndWait();
                break;
        }
    }


    /**
     * Initializes the AddPartController and sets up the variable to be used for the Unique ID generation.
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resourceBundle is used to localize the root object, or null if the root object was not localized.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            int tempId = Inventory.partId+=1;
        partIdTxt.setText(String.valueOf(tempId));

    }


}
