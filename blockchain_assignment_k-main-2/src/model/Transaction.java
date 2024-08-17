/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Yuan
 */
public class Transaction {
    
    private String transactionID;
    private String transactionData;
    private String signatureString;

    public String getTransactionData() {
        return transactionData;
    }

    public void setTransactionData(String transactionData) {
        this.transactionData = transactionData;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getSignatureString() {
        return signatureString;
    }

    public void setSignatureString(String signatureString) {
        this.signatureString = signatureString;
    }
    
    
    public String toString(){
        return this.transactionData + "|" + this.signatureString;
    }
    
}
