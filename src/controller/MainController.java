package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    Stage stage;
    Parent scene;

    private static Part partModify;

    public static Part getPartModify () {
        return partModify;
    }

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
        ObservableList<Part> selectedRows, allParts;
        allParts = partTableView.getItems();
        selectedRows = partTableView.getSelectionModel().getSelectedItems();

        for (Part part: selectedRows) {
            allParts.remove(part);
        }
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        ObservableList<Product> selectedRows, allProducts;
        allProducts = productTableView.getItems();
        selectedRows = productTableView.getSelectionModel().getSelectedItems();

        for (Product product: selectedRows) {
            allProducts.remove(product);
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

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyPart.fxml"));
        scene.setStyle("-fx-font-family: 'SansSerif';");
        stage.setScene(new Scene(scene));
        stage.show();

        }


    @FXML
    void onActionDisplayModifyProduct(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyProduct.fxml"));
        scene.setStyle("-fx-font-family: 'SansSerif';");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void getPartsHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String partName = queryPartsTF.getText();
            ObservableList<Part> parts = Inventory.lookupPart(partName);
            partTableView.setItems(parts);
            queryPartsTF.setText("");
        }
    }

    @FXML
    void getProductsHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String product = queryProductsTF.getText();
            ObservableList<Product> products = Inventory.lookupProduct(product);
            productTableView.setItems(products);
            queryProductsTF.setText("");
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
