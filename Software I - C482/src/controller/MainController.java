package controller;

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
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 *  RUNTIME ERROR
 *
 *  A runtime error occurs if no part is selected before the user selects the modify button.
 *  The error is triggered because a NULL value being passed to the initialize method of the
 *  ModifyPartController or ModifyProductController. A way to prevent this from happening was to implement an Alert pop up message
 *  informing the user that no PART or PRODUCT was selected.
 *
 */


/**
 * The MainController class provides control of the logic of the main screen of this Inventory application.
 *
 */


public class MainController implements Initializable {

    /**
     * The stage object that will be used to hold the GUI and data of the new screens.
     */

    Stage stage;

    /**
     * The scene object that will be used to generate the new screens.
     */

    Parent scene;

    /**
     * The part object that is selected by the user in the table view.
     */
    private static Part partModify;

    /**
     * The method used to get the part to modify.
     * @return The part that will be modified in the ModifyPart section of the application.
     */

    public static Part getPartModify () {
        return partModify;
    }

    /**
     * The product object that is selected by the user in the table view.
     */

    private static Product productModify;

    /**
     * The method used to get the part to modify.
     * @return The part that will be modified in the ModifyPart section of the application.
     */

    public static Product getProductModify () {return productModify;}


    /**
     * Table view of the parts.
     */
    @FXML
    private TableView<Part> partTableView;

    /**
     * The ID column of the parts table.
     */

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    /**
     * The Name column of the parts table.
     */

    @FXML
    private TableColumn<Part, String> partNameCol;

    /**
     * The Stock column of the parts table.
     */

    @FXML
    private TableColumn<Part, Integer> partStockCol;

    /**
     * The Price column of the parts table.
     */

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    /**
     * The text field that will be used to search for parts.
     */

    @FXML
    private TextField queryPartsTF;

    /**
     * Table view of the products.
     */

    @FXML
    private TableView<Product> productTableView;

    /**
     * The ID column of the products table.
     */

    @FXML
    private TableColumn<Product, Integer> productIdCol;

    /**
     * The Name column of the products table.
     */

    @FXML
    private TableColumn<Product, String> productNameCol;

    /**
     * The Stock column of the products table.
     */

    @FXML
    private TableColumn<Product, Integer> productStockCol;

    /**
     * The Price column of the products table.
     */

    @FXML
    private TableColumn<Product, Double> productPriceCol;

    /**
     * The text field that will be used to search for products.
     */

    @FXML
    private TextField queryProductsTF;

    /**
     * This method deletes the part selected by the user in the part table.
     *
     * The method shows an error message if no part is selected and a confirmation
     * message before deleting the selected part.
     *
     * @param event Part delete button action.
     */

    @FXML
    void onActionDeletePart(ActionEvent event) {
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(3);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Are you sure you want to delete the selected part?");
            DialogPane dp = alert.getDialogPane();
            dp.setStyle("-fx-font-family:sans-serif");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
    }

    /**
     * This method deletes the product selected by the user in the product table.
     *
     * The method displays an error message if no product is selected, an alert if the product has associated parts
     * and a confirmation dialog before deleting the selected product.
     *
     * @param event Product delete button action.
     */

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

         Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

         if (selectedProduct == null) {
             displayAlert(4);
         } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Are you sure you want to delete the selected product?");
             DialogPane dp = alert.getDialogPane();
             dp.setStyle("-fx-font-family:sans-serif");
            Optional<ButtonType> result = alert.showAndWait();

         if (result.isPresent() && result.get() == ButtonType.OK) {

             ObservableList<Part> assocParts = selectedProduct.getAllAssociatedParts();

             if (assocParts.size() >= 1) {
                 displayAlert(5);
             } else {
                 Inventory.deleteProduct(selectedProduct);
             }
            }
         }

    }

    /**
     * This method Loads the AddPart section of the application.
     * @param event Clicking the Add Button beneath the Parts table.
     */

    @FXML
    void onActionDisplayAddPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        scene.setStyle("-fx-font-family: 'SansSerif';");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method Loads the AddProduct section of the application.
     * @param event Clicking the Add Button beneath the Products table.
     */

    @FXML
    void onActionDisplayAddProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        scene.setStyle("-fx-font-family: 'SansSerif';");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method loads the ModifyPart section of the application.
     * @param event Clicking the Modify Button beneath the Parts table.
     */

    @FXML
    void onActionDisplayModifyPart(ActionEvent event) throws IOException {

        partModify = partTableView.getSelectionModel().getSelectedItem();

        if (partModify == null) {
            displayAlert(3);
        } else {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/ModifyPart.fxml"));
            scene.setStyle("-fx-font-family: 'SansSerif';");
            stage.setScene(new Scene(scene));
            stage.show();
        }

        }

    /**
     * This method Loads the ModifyProduct section of the application.
     * @param event Clicking the Modify Button beneath the Products table.
     */

    @FXML
    void onActionDisplayModifyProduct(ActionEvent event) throws IOException {

        productModify = productTableView.getSelectionModel().getSelectedItem();

        if (productModify == null) {
            displayAlert(4);
        } else {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/ModifyProduct.fxml"));
            scene.setStyle("-fx-font-family: 'SansSerif';");
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * This method Exits the program.
     * @param event Exit button action.
     */

    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
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
     * This method starts a search using values typed into the search text field and refreshes the products
     * table view with search results.
     *
     * Products can be searched for by ID or name.
     *
     * @param event Product search keyboard input.
     */

    @FXML
    void searchProductsHandler(ActionEvent event) {
            try {
                Product foundProduct = Inventory.lookupProduct(Integer.parseInt(queryProductsTF.getText()));
                productTableView.getSelectionModel().select(foundProduct);
            } catch (Exception e) {
                String productName = queryProductsTF.getText();
                ObservableList<Product> products = Inventory.lookupProduct(productName);
                productTableView.setItems(products);
                queryProductsTF.setText("");
            }
    }

    /**
     * This switch statement is used to displays a variety of alert and error messages.
     * @param alertType Alert selects a message based on case number.
     */

    private void displayAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane dp = alert.getDialogPane();
        dp.setStyle("-fx-font-family:sans-serif");
        DialogPane dp2 = alertError.getDialogPane();
        dp2.setStyle("-fx-font-family:sans-serif");

        switch (alertType) {
            case 1:
                alert.setTitle("Information");
                alert.setHeaderText("Part not found");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Information");
                alert.setHeaderText("Product not found");
                alert.showAndWait();
                break;
            case 3:
                alertError.setTitle("Error");
                alertError.setHeaderText("No part selected");
                alertError.showAndWait();
                break;
            case 4:
                alertError.setTitle("Error");
                alertError.setHeaderText("No product selected");
                alertError.showAndWait();
                break;
            case 5:
                alertError.setTitle("Error");
                alertError.setHeaderText("Parts Associated");
                alertError.setContentText("All associated parts must be removed from product before proceeding with deletion.");
                alertError.showAndWait();
                break;
        }
    }


    /**
     * This method initializes the MainController and populates the Part and Product table views.
     *
     * @param url The url is used to find the relative paths for the root object.
     * @param resourceBundle The resource bundle is used to localize the root object.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partTableView.setItems(Inventory.getAllParts());

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        productTableView.setItems(Inventory.getAllProducts());

        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

    }


}
