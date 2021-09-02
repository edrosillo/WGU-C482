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

/**
 * The ModifyProductController class provides control logic for the modify product screen of the application.
 */
public class ModifyProductController implements Initializable {

    /**
     * The product object selected in the MainController.
     */
    private Product selectedProduct;

    /**
     * The stage object that will be used to hold the GUI and data of the new screens.
     */
    Stage stage;

    /**
     * The scene object that will be used to generate the new screens.
     */
    Parent scene;

    /**
     * A list that contains all the parts associated with the product.
     */
    private ObservableList<Part> assocParts = FXCollections.observableArrayList();

    /**
     * The text field that will be used to search for parts.
     */
    @FXML
    private TextField queryPartsTF;

    /**
     * The Max Stock product text field.
     */
    @FXML
    private TextField productMaxTxt;

    /**
     * The ID product text field.
     */
    @FXML
    private TextField productIdTxt;

    /**
     * The name product text field.
     */
    @FXML
    private TextField productNameTxt;

    /**
     * The stock product text field.
     */
    @FXML
    private TextField productStockTxt;

    /**
     * The price product text field.
     */
    @FXML
    private TextField productPriceTxt;

    /**
     * The Min Stock product text field.
     */
    @FXML
    private TextField productMinTxt;

    /**
     * The table view of parts.
     */
    @FXML
    private TableView<Part> partTableView;

    /**
     * The ID column of the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partIdCol;

    /**
     * The stock column of the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partStockCol;

    /**
     * The price column of the parts table.
     */
    @FXML
    private TableColumn<Part, Double> partPriceCol;

    /**
     * The name column of the parts table.
     */
    @FXML
    private TableColumn<Part, String> partNameCol;

    /**
     * The associated parts table view.
     */
    @FXML
    private TableView<Part> assocPartTableView;

    /**
     * The part ID column of the associated parts table.
     */
    @FXML
    private TableColumn<Part, Integer> assocPartIdCol;

    /**
     * The part name column of the associated parts table.
     */
    @FXML
    private TableColumn<Part, String> assocPartNameCol;

    /**
     * The part stock column of the associated parts table.
     */
    @FXML
    private TableColumn<Part, Integer> assocPartStockCol;

    /**
     * The part price column of the associated parts table.
     */
    @FXML
    private TableColumn<Part, Double> assocPartPriceCol;

    /**
     * Adds new part from the part table to the associated part table.
     * Validates that a part is selected.
     * @param event Add button action.
     */
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
     * Removes part from the associated parts table.
     * Validates that a part is selected.
     * @param event Add button action.
     */
    @FXML
    void onActionRemovePart(ActionEvent event) {

        Part selectedPart = assocPartTableView.getSelectionModel().getSelectedItem();
        assocParts.remove(selectedPart);
        assocPartTableView.setItems(assocParts);

    }


    /**
     * Adds new part to inventory and loads MainController.
     * Text fields are validated with error messages displayed preventing empty and/or
     * invalid values.
     * @param event Save button action.
     */
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

    /**
     * This method starts a search using values typed into the search text field and refreshes the parts
     * table view with search results.
     * Parts can be searched for by ID or name.
     * @param event Part search keyboard input.
     */
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

    /**
     * Initializes the ModifyProductController carries over the information of the Product to be modified
     * and populates table views for the Parts table and the Associated Parts.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resourceBundle is used to localize the root object, or null if the root object was not localized.
     */
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
