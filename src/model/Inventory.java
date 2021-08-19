package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part part){
        allParts.add(part);
    }

    public static void addProduct(Product product){
        allProducts.add(product);
    }

    public static Part lookupPart(int partId) {
        Part partSearch = null;

        for (Part part : allParts) {
            if (part.getId() == partId) {
                partSearch = part;
            }
        }

        return partSearch;
    }

    public static Product lookupProduct(int productId) {
        Product productSearch = null;

        for (Product product : allProducts) {
            if (product.getId() == productId) {
                productSearch = product;
            }
        }

        return productSearch;
    }

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partSearch = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                partSearch.add(part);
            }
        }
        return partSearch;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> productSearch = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for (Product product : allProducts) {
            if (product.getName().equals(productName)) {
                productSearch.add(product);
            }
        }
        return productSearch;
    }

    public static void updatePart (int index, Part selectedPart) {

        allParts.set(index, selectedPart);
    }


    public static void updateProduct (int index, Product selectedProduct) {

        allProducts.set(index, selectedProduct);
    }


    public static ObservableList<Part> getAllParts(){
      return allParts;
    }

    public static ObservableList<Product> getAllProducts(){ return allProducts; }

}
