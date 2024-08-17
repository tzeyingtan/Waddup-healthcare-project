/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;
import model.Inventory;
import util.Helper;

/**
 *
 * @author Yuan
 */
public class InventoryController {

    private ArrayList<Inventory> allInventory = new ArrayList<>();
//    private ArrayList<Inventory> userInventory = new ArrayList<>();
    private String filename;
    private Path path;

    public InventoryController() {

    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
        this.path = Path.of(filename);
    }

    public boolean writeToFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)))) {
            for (Inventory i : allInventory) {
                writer.write(i.toString());
                writer.newLine();
            }
            writer.close();
            return true;
        } catch (IOException e) {
            return false;

        } catch (Exception e) {
            return false;
        }
    }

    public void updateInventoryQuantity(Inventory i, int quantity){
        int newQuantity = i.getQuantity() - quantity;
        
        i.setQuantity(newQuantity);
        this.writeToFile();
    }
   
    public Inventory getSpecificInventory(String ID){
        Inventory exist = null;
        
         for (Inventory i : this.allInventory) {
            if (i.getID().equals(ID)) {
                exist = i;
                break;
            }
        }
        return exist;
    }
    
    public boolean readFromFile() {
        this.allInventory.clear();
        try (Stream<String> lineStream = Files.lines(path)) {
            lineStream.forEach(line -> {
                String[] inventoryDetails = line.split("\\,");
                Inventory i = new Inventory();
                i.setID(inventoryDetails[0]);
                i.setUserID(inventoryDetails[1]);
                i.setUPC(inventoryDetails[2]);
                i.setProductName(inventoryDetails[3]);
                i.setBatchNumber(inventoryDetails[4]);
                i.setQuantity(Integer.parseInt(inventoryDetails[5]));
                this.allInventory.add(i);
            });
            lineStream.close();
            return true;
        } catch (IOException e) {
            return false;
//            JOptionPane.showMessageDialog(show, "Something Went Wrong");

        } catch (Exception e) {
            return false;
//            JOptionPane.showMessageDialog(show, "Something Went Wrong");
        }
    }

//    public int getUserInventorySize() {
//        return this.userInventory.size();
//    }
    public ArrayList<Inventory> getUserInventory(String userID) {
        ArrayList<Inventory> userInventory = new ArrayList<>();
        for (Inventory i : this.allInventory) {
            if (i.getUserID().equals(userID)) {
                userInventory.add(i);
            }
        }
        return userInventory;
    }

    public void addNewInventory(String userID, String UPC, String batchNumber, int quantity, String productName) {
        Inventory i = new Inventory();

//        int newID = this.allInventory.size() + 1;
        String newID = Helper.generateID("INV");

        i.setID(newID);
        i.setUserID(userID);
        i.setUPC(UPC);
        i.setBatchNumber(batchNumber);
        i.setQuantity(quantity);
        i.setProductName(productName);

        this.allInventory.add(i);
        this.writeToFile();
    }

}
