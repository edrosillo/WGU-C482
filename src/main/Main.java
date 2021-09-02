package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;


/**
 *
 * RUNTIME ERROR
 * A runtime error occurs if no part is selected before the user clicks the modify button.
 * This error is caused by a NULL value being passed to the initialize method of the
 * ModifyPartController or ModifyProductController. A way to prevent this from happening was to implement an Alert pop up message
 * informing the user that no PART or PRODUCT was selected.
 *
 *
 * FUTURE ENHANCEMENT
 * A new feature suitable for a future version of this application would be the ability to automatically change the stock number of parts as they
 * are associated and removed from various products.
 *
 */

/**
 * Main Class for an Inventory Management application that is designed to manage
 * an inventory of parts and products containing of parts.
 */

public class Main extends Application {

    /**
     * The start method creates the first screen in the application.
     * It generates FXML stage and loads the GUI and information of the initial scene.
     *
     * @param primaryStage This will be the GUI and information the user will see.
     * @throws Exception From FXMLLoader.
     */

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/main.fxml"));
        root.setStyle("-fx-font-family: 'SansSerif';");
        primaryStage.setTitle("Droid Depot Inventory");
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.show();
    }

    /**
     * The main method is the entry point to the application.
     * The main method creates sample data and launches the application.
     *
     * @param args
     */

    public static void main(String[] args) {

        Part part1 = new Outsourced(1, "Utilty Arm", 250.99, 20, 1, 20,"Hondo's Droid Parts");
        Part part2 = new InHouse(2, "Holographic Projector", 625.00, 20, 4, 25,616);
        Part part3 = new Outsourced(3, "Dome", 55.60, 4, 1, 25,"Astromech");
        Part part4 = new InHouse(4, "Power Coupling", 330.00, 7, 4, 35,52);
        Part part5 = new Outsourced(5, "Vocabulator", 700.50, 10, 1, 10,"Jawa Scavengers");

        Inventory.partId = 5;
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        Inventory.addPart(part5);

        Product product1 = new Product(100, "R2 Unit", 20000.00,6,1,12);
        product1.addAssociatedPart(part1);
        product1.addAssociatedPart(part3);
        Product product2 = new Product(101, "R4 Unit", 25000.00,4,4,10);
        product2.addAssociatedPart(part3);
        product2.addAssociatedPart(part4);
        Product product3 = new Product(102, "BB Unit", 40000.00,10,6,20);
        product3.addAssociatedPart(part1);
        product3.addAssociatedPart(part4);
        Product product4 = new Product(103, "K2 Unit", 60000.00,12,6,25);
        product4.addAssociatedPart(part2);
        product4.addAssociatedPart(part5);

        Inventory.productId = 104;
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);

        launch(args);
    }
}
