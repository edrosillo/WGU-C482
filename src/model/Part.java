package model;

/**
 * This class models products.
 * Two other classes, InHouse and Outsourced, will extend this abstract class.
 */

public abstract class Part {

    /**
     * The ID of the part.
     */
    private int id;

    /**
     * The name of the part.
     */
    private String name;

    /**
     * The price of the part.
     */
    private double price;

    /**
     * The stock of the part.
     */
    private int stock;

    /**
     * The minimum stock of the part.
     */
    private int min;

    /**
     * The maximum stock of the part.
     */
    private int max;

    /**
     * Constructor of a new instance of a part
     *
     * @param id the ID of the part
     * @param name the name of the part
     * @param price the price of the part
     * @param stock the stock level of the part
     * @param min the minimum stock of the part
     * @param max the maximum stock of the part
     */
    public Part(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * The getter for the id of the part.
     * @return id of the part.
     */
    public int getId() {
        return id;
    }

    /**
     * The getter for the name of the part.
     * @return name of the part.
     */
    public String getName() {
        return name;
    }

    /**
     * The getter for the price of the part.
     * @return price of the part.
     */
    public double getPrice() {
        return price;
    }

    /**
     * The getter for the stock of the part.
     * @return stock of the part.
     */
    public int getStock() {
        return stock;
    }

    /**
     * The getter for the minimum stock of the part.
     * @return minimum stock of the part.
     */
    public int getMin() {
        return min;
    }

    /**
     * The getter for the maximum stock of the part.
     * @return maximum stock of the part.
     */
    public int getMax() {
        return max;
    }

    /**
     * The setter for the part id.
     * @param id The id of the part.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * The setter for the name of the part.
     * @param name The name of the part.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The setter for the price of the part.
     * @param price The price of the part.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * The setter for the stock of the part.
     * @param stock The stock of the part.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * The setter for the minimum stock of part.
     * @param min The minimum stock of the part.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * The setter for the maximum stock of the part.
     * @param max The maximum stock of the part.
     */
    public void setMax(int max) {
        this.max = max;
    }


}
