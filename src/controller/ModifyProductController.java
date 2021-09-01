package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {

    private Product selectedProduct;

    Stage stage;
    Parent scene;

    private ObservableList<Part> assocParts = FXCollections.observableArrayList();

    @FXML
    private TextField queryPartsTF;

    @FXML
    private TextField productMaxTxt;

    @FXML
    private TextField productIdTxt;

    @FXML
    private TextField productNameTxt;

    @FXML
    private TextField productStockTxt;

    @FXML
    private TextField productPriceTxt;

    @FXML
    private TextField productMinTxt;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, Integer> partStockCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableView<Part> assocPartTableView;

    @FXML
    private TableColumn<Part, Integer> assocPartIdCol;

    @FXML
    private TableColumn<Part, String> assocPartNameCol;

    @FXML
    private TableColumn<Part, Integer> assocPartStockCol;

    @FXML
    private TableColumn<Part, Double> assocPartPriceCol;

    @FXML
    void onActionAddPart(ActionEvent event) {
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(5);
        } else {
            assocParts.add(selectedPart);
            assocPartTableView.setItems(assocParts);
        }
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
    void onActionRemovePart(ActionEvent event) {

        Part selectedPart = assocPartTableView.getSelectionModel().getSelectedItem();
        assocParts.remove(selectedPart);
        assocPartTableView.setItems(assocParts);

    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {

        try {

            int index = Inventory.getAllProducts().indexOf(selectedProduct);

            int id = Integer.parseInt(productIdTxt.getText());
            String name = productNameTxt.getText();
            double price = Double.parseDouble(productPriceTxt.getText());
            int stock = Integer.parseInt(productStockTxt.getText());
            int min = Integer.parseInt(productMinTxt.getText());
            int max = Integer.parseInt(productMaxTxt.getText());

            if (name.isEmpty()) {
                displayAlert(7);
            } else {
                if (minMaxValid(min, max) && stockValid(min, max, stock)) {

                    selectedProduct.setId(id);
                    selectedProduct.setName(name);
                    selectedProduct.setPrice(price);
                    selectedProduct.setStock(stock);
                    selectedProduct.setPrice(price);
                    selectedProduct.setMin(min);
                    selectedProduct.setMax(max);

                    Inventory.updateProduct(index, selectedProduct);

                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
                    scene.setStyle("-fx-font-family: 'SansSerif';");
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }

        } catch (Exception e) {
            displayAlert(1);
        }

    }

    @FXML
    void searchPartsHandler(ActionEvent event) {
        try {
            Part foundPart = Inventory.lookupPart(Integer.parseInt(queryPartsTF.getText()));
            partTableView.getSelectionModel().select(foundPart);
            if (foundPart == null) {
                displayAlert(1);
            }
        } catch (Exception e) {
            String partName = queryPartsTF.getText();
            ObservableList<Part> parts = Inventory.lookupPart(partName);
            partTableView.setItems(parts);
            queryPartsTF.setText("");
            if (!(Inventory.partsFound)){
                displayAlert(1);
            }
        }
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
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        DialogPane dp = alert.getDialogPane();
        dp.setStyle("-fx-font-family:sans-serif");
        DialogPane dp2 = alertInfo.getDialogPane();
        dp2.setStyle("-fx-font-family:sans-serif");

        switch (alertType) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Product");
                alert.setContentText("Form contains blank fields or invalid values.");
                alert.showAndWait();
                break;
            case 2:
                alertInfo.setTitle("Information");
                alertInfo.setHeaderText("Part not found");
                alertInfo.showAndWait();
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
                alert.setContentText("Inventory must be a number equal to or between Min and Max");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("Part not selected");
                alert.showAndWait();
                break;
            case 7:
                alert.setTitle("Error");
                alert.setHeaderText("Name Empty");
                alert.setContentText("Name cannot be empty.");
                alert.showAndWait();
                break;
        }
    }

    /** Initializes the controller class. */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        selectedProduct = MainController.getProductModify();
        assocParts = selectedProduct.getAllAssociatedParts();


        partTableView.setItems(Inventory.getAllParts());

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        assocPartTableView.setItems(assocParts);

        assocPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productIdTxt.setText(String.valueOf(selectedProduct.getId()));
        productNameTxt.setText(selectedProduct.getName());
        productStockTxt.setText(String.valueOf(selectedProduct.getStock()));
        productPriceTxt.setText(String.valueOf(selectedProduct.getPrice()));
        productMinTxt.setText(String.valueOf(selectedProduct.getMin()));
        productMaxTxt.setText(String.valueOf(selectedProduct.getMax()));


    }

}
