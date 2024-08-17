/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author vinie
 */
public class Product {

    private String productID;
    private String productName;
    private String productDescription;
    private Manufacturer manufacturer;
    private String usCode;
    private int productQuantity;

    // GETTERS
    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getUsCode() {
        return usCode;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    // SETTERS
    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setUsCode(String usCode) {
        this.usCode = usCode;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
