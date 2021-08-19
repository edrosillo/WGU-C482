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


public class ModifyPartController implements Initializable {

    private Part selectedPart;


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

    public void initData(Part part) {

        selectedPart = part;

        partIdTxt.setText(Integer.toString(selectedPart.getId()));
        partNameTxt.setText(selectedPart.getName());
        partPriceText.setText(Double.toString(selectedPart.getPrice()));
        partStockTxt.setText(String.valueOf(selectedPart.getStock()));
        partMinTxt.setText(Integer.toString(selectedPart.getMin()));
        partMaxTxt.setText(Integer.toString(selectedPart.getMax()));


    }

    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {

        int id = 0;
        String name = partNameTxt.getText();
        double price = Double.parseDouble(partPriceText.getText()) ;
        int stock = Integer.parseInt(partStockTxt.getText());
        int min = Integer.parseInt(partMinTxt.getText());
        int max = Integer.parseInt(partMaxTxt.getText());
        int machineId;
        String companyName;

        if (inHouseRBtn.isSelected()) {
            machineId = Integer.parseInt(partMachineIdTxt.getText());
            Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));

        } else if (outsourcedRBtn.isSelected()) {
            companyName = partMachineIdTxt.getText();
            Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
        }

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

        selectedPart = MainController.getPartModify();

        if (selectedPart instanceof InHouse) {
            inHouseRBtn.setSelected(true);
            changeLabel.setText("Machine ID");
            partMachineIdTxt.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        } else {
            outsourcedRBtn.setSelected(true);
            changeLabel.setText("Company Name");
            partMachineIdTxt.setText(((Outsourced) selectedPart).getCompanyName());
        }

        partIdTxt.setText(String.valueOf(selectedPart.getId()));
        partNameTxt.setText(selectedPart.getName());
        partStockTxt.setText(String.valueOf(selectedPart.getStock()));
        partPriceText.setText(String.valueOf(selectedPart.getPrice()));
        partMaxTxt.setText(String.valueOf(selectedPart.getMax()));
        partMinTxt.setText(String.valueOf(selectedPart.getMin()));


    }


}
