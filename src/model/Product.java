package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class models products that can have associated parts.
 */

public class Product {

    /**
     * The ID of the product.
     */
    private int id;
    /**
     * The Name of the product.
     */
    private String name;
    /**
     * The price of the product.
     */
    private double price;
    /**
     * The stock of the product.
     */
    private int stock;
    /**
     * The min stock of the product.
     */
    private int min;
    /**
     * The max stock of the product.
     */
    private int max;
    /**
     * The list of Associated Parts of the product.
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Constructor of a new instance of a product
     *
     * @param id the ID of the product
     * @param name the name of the product
     * @param price the price of the product
     * @param stock the stock level of the product
     * @param min the minimum stock of the product
     * @param max the maximum stock of the product
     */

    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * The setter for the product id.
     * @param id The id of the product.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * The setter for the name of the product.
     * @param name The name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The setter for the price of the product.
     * @param price The price of the product.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * The setter for the stock of the product.
     * @param stock The stock of the product.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * The setter for the minimum stock of product.
     * @param min The minimum stock of the product.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * The setter for the maximum stock of the product.
     * @param max The maximum stock of the product.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * The getter for the id of the product.
     * @return id of the product.
     */
    public int getId() {
        return id;
    }

    /**
     * The getter for the name of the product.
     * @return name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * The getter for the price of the product.
     * @return price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * The getter for the stock of the product.
     * @return stock of the product.
     */
    public int getStock() {
        return stock;
    }

    /**
     * The getter for the minimum stock of the product.
     * @return minimum stock of the product.
     */
    public int getMin() {
        return min;
    }

    /**
     * The getter for the maximum stock of the product.
     * @return maximum stock of the product.
     */
    public int getMax() {
        return max;
    }

    /**
     * This method adds a part to the associated parts list of the product.
     * @param part The part to add.
     */
    public void  addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * This method deletes a part from the associated parts list for the product.
     * @param selectedAssociatedPart The associated part to delete
     * @return a boolean indicating status of part deletion
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        if (associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        else
            return false;
    }

    /**
     * This method gets list of associated of the product.
     * @return a list of parts
     */
    public ObservableList<Part> getAllAssociatedParts() {return associatedParts;}

}
