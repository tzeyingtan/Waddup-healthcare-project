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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Stream;
import model.Order;
import util.Helper;

/**
 *
 * @author Yuan
 */
public class OrderController {

    private ArrayList<Order> allOrders = new ArrayList<>();
    private String filename;
    private Path path;

    public OrderController() {

    }

    public boolean writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)))) {
            for (Order o : allOrders) {
                writer.write(o.toString());
                writer.newLine();
//             
            }
            writer.close();
            return true;
        } catch (IOException e) {
            return false;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean readFromFile() {
        this.allOrders.clear();

        try (Stream<String> lineStream = Files.lines(path)) {
            lineStream.forEach(line -> {
                String[] orderDetails = line.split("\\,");

                Order o = new Order();
                o.setID(orderDetails[0]);
                o.setUserID(orderDetails[1]);
                o.setSellerID(orderDetails[2]);
                o.setOrderDate(LocalDate.parse(orderDetails[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                o.setOrderPrice(Integer.parseInt(orderDetails[4]));
                o.setOrderStatus(orderDetails[5]);

                this.allOrders.add(o);

            });
            lineStream.close();
            return true;
        } catch (IOException e) {
            return false;

        } catch (Exception e) {
            return false;
        }
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
        this.path = Path.of(filename);

    }

    public ArrayList<Order> getUserOrder(String userID) {
        ArrayList<Order> userOrder = new ArrayList<>();
        for (Order o : this.allOrders) {
            if (o.getUserID().equals(userID)) {
                userOrder.add(o);
            }
        }
        return userOrder;
    }
    
    public void addNewOrder(String userID, String sellerID, LocalDate date,  int price, String status){
        Order o = new Order();
        
        String newID = Helper.generateID("OD");
        
        o.setID(newID);
        o.setUserID(userID);
        o.setSellerID(sellerID);
        o.setOrderDate(date);
        o.setOrderPrice(price);
        o.setOrderStatus(status);
        
        this.allOrders.add(o);
        this.writeToFile();
    }
}
