/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package blockchain_assignment;

import controller.DistributorController;
import controller.HealthcareProviderController;
import controller.InventoryController;
import controller.ManufacturerController;
import controller.OrderController;
import controller.TransactionController;
import java.awt.Component;
import java.io.File;
import java.security.Key;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import util.RandomSecretKey;
import view.LoginMenu;
import view.TestFrame;
import view.ManufacturerFrame;

/**
 *
 * @author Yuan
 */
public class Blockchain_Assignment {

    /**
     * @param args the command line arguments
     */
    public static LoginMenu loginMenu;
    public static TestFrame testFrame;
    public static ManufacturerFrame manufacturerFrame;
    public static JFrame activeFrame;
    public static ArrayList<JFrame> previousFrame;

    public static ManufacturerController manufacturerController;
    public static HealthcareProviderController healthcareProviderController;
    public static DistributorController distributorController;
    public static InventoryController inventoryController;
    public static OrderController orderController;
    public static TransactionController transactionController;
//    public static Key secretKey;

    public static void main(String[] args) throws Exception {

        File f = new File("src/RandomKey/secretkey");

        if (!f.exists()) {
            Key randKey = RandomSecretKey.create();
            RandomSecretKey.put(randKey.getEncoded(), "src/RandomKey/secretkey");
            System.out.println("Secret Key created");
        }

        manufacturerController = new ManufacturerController("src/Off_Chain/Manufacturer");
        manufacturerController.readFromFile();

        healthcareProviderController = new HealthcareProviderController("src/Off_Chain/HealthcareProvider");
        healthcareProviderController.readFromFile();

        distributorController = new DistributorController("src/Off_Chain/Distributor");
        distributorController.readFromFile();

        inventoryController = new InventoryController();
        orderController = new OrderController();
        transactionController = new TransactionController();

        loginMenu = new LoginMenu(manufacturerController, distributorController, healthcareProviderController);
        loginMenu.setVisible(true);

    }

    public static void swapVisibleFrame(JFrame frame) {

        previousFrame.add(frame);
        activeFrame.setVisible(false);
        frame.setVisible(true);
        activeFrame = frame;
    }

    public static void swapPreviousFrame() {
        activeFrame.setVisible(false);
        activeFrame = previousFrame.remove(previousFrame.size() - 1);
        activeFrame.setVisible(true);
    }

    public static void clearPage(JPanel panel) {
        for (Component control : panel.getComponents()) {
            if (control instanceof JTextField) {
                JTextField ctrl = (JTextField) control;
                ctrl.setText("");
            }
        }
    }

}
