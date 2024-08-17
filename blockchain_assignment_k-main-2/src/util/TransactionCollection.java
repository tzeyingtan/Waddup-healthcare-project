/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;

/**
 *
 * @author Yuan
 */
public class TransactionCollection implements Serializable {

    final int SIZE = 10;
    private static final long serialVersionUID = 1L;

    private String merkleRoot;
    private ArrayList<String> tranxList = new ArrayList<>();

    public TransactionCollection() {

    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public ArrayList<String> getTranxList() {
        return tranxList;
    }

    public void setTranxList(ArrayList<String> tranxList) {
        this.tranxList = tranxList;
    }

    public void add(String tranx) {
        this.tranxList.add(tranx);
    }
    
   
    public void add (String[] dataList){
        for (String data: dataList){
            this.tranxList.add(data);
        }
    }

    @Override
    public String toString() {
        return "TransactionCollection= " + "|" + String.valueOf(this.tranxList);
    }
    
//    public void addProductTransaction(String productId, String productName, String productDesc, byte[] signature) {
//        String transaction = productId + "|" + productName + "|" + productDesc + "|" + Base64.getEncoder().encodeToString(signature);
//        this.tranxList.add(transaction);
//    }
}
