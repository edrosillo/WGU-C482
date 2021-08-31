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

public class MainController implements Initializable {

    Stage stage;
    Parent scene;

    private static Part partModify;

    public static Part getPartModify () {
        return partModify;
    }

    private static Product productModify;

    public static Product getProductModify () {return productModify;}



    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partStockCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TextField queryPartsTF;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableColumn<Product, Integer> productIdCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productStockCol;

    @FXML
    private TableColumn<Product, Double> productPriceCol;

    @FXML
    private TextField queryProductsTF;

    @FXML
    void onActionDeletePart(ActionEvent event) {
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(3);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Are you sure you want to delete the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

         Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

         if (selectedProduct == null) {
             displayAlert(4);
         } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Are you sure you want to delete the selected product?");
            Optional<ButtonType> result = alert.showAndWait();

         if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deleteProduct(selectedProduct);
            }
         }

    }

    @FXML
    void onActionDisplayAddPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        scene.setStyle("-fx-font-family: 'SansSerif';");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDisplayAddProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        scene.setStyle("-fx-font-family: 'SansSerif';");
        stage.setScene(new Scene(scene));
        stage.show();
    }

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

    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void searchPartsHandler(ActionEvent event) {
            try {
                Part foundPart = Inventory.lookupPart(Integer.parseInt(queryPartsTF.getText()));
                partTableView.getSelectionModel().select(foundPart);
            } catch (Exception e) {
                String partName = queryPartsTF.getText();
                ObservableList<Part> parts = Inventory.lookupPart(partName);
                partTableView.setItems(parts);
                queryPartsTF.setText("");
            }
    }

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

    private void displayAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Alert alertError = new Alert(Alert.AlertType.ERROR);

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
