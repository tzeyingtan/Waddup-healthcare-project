/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author vinie
 */
public class Batch {

    private String batchID;
    private LocalDate batchExpiry;
    private LocalDate batchProduction;
    private Product product;
    private String batchStatus;
    private int batchQuantity;
    private int batchCode;

    public int getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(int batchCode) {
        this.batchCode = batchCode;
    }

    // GETTERS
    public String getBatchID() {
        return batchID;
    }

    public LocalDate getBatchExpiry() {
        return batchExpiry;
    }

    public LocalDate getBatchProduction() {
        return batchProduction;
    }

    public Product getProduct() {
        return product;
    }

    public String getBatchStatus() {
        return batchStatus;
    }

    public int getBatchQuantity() {
        return batchQuantity;
    }

    // SETTERS
    public void setBatchID(String batchID) {
        this.batchID = batchID;
    }

    public void setBatchExpiry(LocalDate batchExpiry) {
        this.batchExpiry = batchExpiry;
    }

    public void setBatchProduction(LocalDate batchProduction) {
        this.batchProduction = batchProduction;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus;
    }

    public void setBatchQuantity(int batchQuantity) {
        this.batchQuantity = batchQuantity;
    }
}
