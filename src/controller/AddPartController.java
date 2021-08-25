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


public class AddPartController implements Initializable {


    public Label changeLabel;
    public RadioButton inHouseRBtn;
    public RadioButton outsourcedRBtn;

    Stage stage;
    Parent scene;


        @FXML
        private ToggleGroup partTG;

        @FXML
        private TextField partMaxTxt;

        @FXML
        private TextField partIdTxt;

        @FXML
        private TextField partNameTxt;

        @FXML
        private TextField partStockTxt;

        @FXML
        private TextField partPriceText;

        @FXML
        private TextField partMinTxt;

        @FXML
        private TextField partMachineIdTxt;


        @FXML
        void onActionInHouse(ActionEvent event) {
            changeLabel.setText("Machine ID");
        }

        @FXML
        void onActionDisplayMain(ActionEvent event) throws IOException {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
            scene.setStyle("-fx-font-family: 'SansSerif';");
            stage.setScene(new Scene(scene));
            stage.show();
        }

        @FXML
        void onActionSavePart(ActionEvent event) throws IOException {

            int id = Integer.parseInt(partIdTxt.getText());
            String name = partNameTxt.getText();
            double price = Double.parseDouble(partPriceText.getText()) ;
            int stock = Integer.parseInt(partStockTxt.getText());
            int min = Integer.parseInt(partMinTxt.getText());
            int max = Integer.parseInt(partMaxTxt.getText());
            //int machineId = Integer.parseInt(partMachineIdTxt.getText());
            int machineId;
            //String companyName = partMachineIdTxt.getText();
            String companyName;

            if (inHouseRBtn.isSelected()) {
                machineId = Integer.parseInt(partMachineIdTxt.getText());
                Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));

            } else if (outsourcedRBtn.isSelected()) {
                companyName = partMachineIdTxt.getText();
                Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
            }
            Inventory.partId = id;
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
            scene.setStyle("-fx-font-family: 'SansSerif';");
            stage.setScene(new Scene(scene));
            stage.show();

        }


    @FXML
    void onActionOutsourced(ActionEvent event) {
        changeLabel.setText("Company Name");
    }



    private boolean minMaxValid(int min, int max) {

        boolean isValid = true;

        if (min <= 0 || min >= max) {
            isValid = false;
            displayAlert(3);
        }
        return isValid;
    }


    private boolean stockValid(int min, int max, int stock) {

        boolean isValid = true;

        if (stock < min || stock > max) {
            isValid = false;
            displayAlert(4);
        }
        return isValid;
    }


    private void displayAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

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




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            int tempId = Inventory.partId+=1;
        partIdTxt.setText(String.valueOf(tempId));

    }


}
