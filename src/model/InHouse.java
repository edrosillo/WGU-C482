package model;

/**
 * This class models InHouse Parts that extend the Part class.
 */

public class InHouse extends Part {

    /**
     * The machine ID of the InHouse part
     */

    private int machineId;

    /**
     * Constructor for a new instance of an InHouse part.
     *
     * @param id the ID of the part
     * @param name the name of the part
     * @param price the price of the part
     * @param stock the stock level of the part
     * @param min the minimum stock of the part
     * @param max the maximum stock of the part
     * @param machineId the machine ID for the InHouse instance of a part
     */

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * The setter of the machineId
     * @param machineId the machineId of the part
     */

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     * The getter for the machineId
     * @return machineId of the part
     */

    public int getMachineId() {
        return machineId;
    }
}
