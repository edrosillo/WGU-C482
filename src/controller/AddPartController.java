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

            int id = 0; //Integer.parseInt(partIdTxt.getText());
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

            //Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));
            //Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
