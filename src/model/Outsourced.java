package model;

/**
 * This class models Outsourced parts that extend the Part class.
 */

public class Outsourced extends Part {

    /**
     * The company name ID of the Outsourced part
     */

    private String companyName;

    /**
     * Constructor for a new instance of an InHouse part.
     *
     * @param id the ID of the part
     * @param name the name of the part
     * @param price the price of the part
     * @param stock the stock level of the part
     * @param min the minimum stock of the part
     * @param max the maximum stock of the part
     * @param companyName the company name of the Outsourced instance of a part
     */

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * The setter of the company name
     * @param companyName the company name of the part
     */

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * The getter for the machineId
     * @return company name of the part
     */

    public String getCompanyName() {
        return companyName;
    }
}
