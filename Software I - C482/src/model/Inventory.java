package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * The class models an inventory of Parts and Products.
 * This class provides persistent data for the application.
 */

public class Inventory {

    /**
     * An ID for a part. The variable is used to generate unique part IDs.
     */

    public static int partId;

    /**
     * An ID for a product. The variable is used to generate unique product IDs.
     */

    public static int productId;

    /**
     * A list of found parts. The variable is used to populate a list with found parts.
     */

    public static boolean partsFound;

    /**
     * A list of all parts in the inventory.
     */

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * A list of all products in the inventory.
     */

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Adds a part to the inventory.
     * @param part The part is the object to add to the inventory.
     */

    public static void addPart(Part part){
        allParts.add(part);
    }

    /**
     * Adds a product to the inventory.
     * @param product The product is the object to add to the inventory.
     */

    public static void addProduct(Product product){
        allProducts.add(product);
    }

    /**
     * Searches the list of parts by ID.
     * @param partId The part ID.
     * @return The part object if found, null if it is not found.
     */

    public static Part lookupPart(int partId) {
        Part partSearch = null;

        for (Part part : allParts) {
            if (part.getId() == partId) {
                partSearch = part;
                return partSearch;
            }
        }

        return null;
    }

    /**
     * Searches the list of products by ID.
     * @param productId The product ID.
     * @return The product object if found, null if it is not found.
     */

    public static Product lookupProduct(int productId) {
        Product productSearch = null;

        for (Product product : allProducts) {
            if (product.getId() == productId) {
                productSearch = product;
            }
        }

        return productSearch;
    }

    /**
     * Searches the list of parts by name.
     * @param partName The part name.
     * @return A list of parts.
     * This additional method is to help overload the lookupPart method in order to search for parts by name or ID.
     */

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partSearch = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                partSearch.add(part);
            }
        }
        if (partSearch.isEmpty()){
            partsFound = false;
            return allParts;
        }
        partsFound = true;
        return partSearch;
    }

    /**
     * Searches the list of products by name.
     * @param productName The product name.
     * @return A list of products.
     * This additional method is to help overload the lookupProduct method in order to search for products by name or ID.
     */

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> productSearch = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                productSearch.add(product);
            }
        }
        return productSearch;
    }

    /**
     * Replaces a part in the list of parts.
     * @param index Index of the part to be replaced.
     * @param selectedPart The part used for replacement.
     */

    public static void updatePart (int index, Part selectedPart) {

        allParts.set(index, selectedPart);

    }

    /**
     * Replaces a product in the list of products.
     * @param index Index of the product to be replaced.
     * @param selectedProduct The product used for replacement.
     */

    public static void updateProduct (int index, Product selectedProduct) {

        allProducts.set(index, selectedProduct);

    }

    /**
     * Removes a part from the list of parts.
     * @param selectedPart The part to be removed.
     * @return A boolean indicating status of part removal.
     */

    public static boolean deletePart (Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }

    }

    /**
     * Removes a product from the list of parts.
     * @param selectedProduct The product to be removed.
     * @return A boolean indicating status of product removal.
     */

    public static boolean deleteProduct (Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }

    }


    /**
     * Gets a list of all parts in the inventory.
     * @return A list of part objects in the inventory.
     */

    public static ObservableList<Part> getAllParts(){
      return allParts;
    }

    /**
     * Gets a list of all products in the inventory.
     * @return A list of product objects in the inventory.
     */

    public static ObservableList<Product> getAllProducts(){ return allProducts; }

}
