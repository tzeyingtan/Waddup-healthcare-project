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
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import model.Transaction;
import util.RandomSecretKey;
import util.Symmetric;

/**
 *
 * @author Yuan
 */
public class TransactionController {

    private ArrayList<Transaction> allTransaction = new ArrayList<>();
    private String filename;
    private Path path;

    public TransactionController() {

    }

    public boolean writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)))) {
            for (Transaction t : allTransaction) {
                writer.write(t.getTransactionID() + "|" + t.toString());
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

    public boolean readFromFile() {
        this.allTransaction.clear();

        try (Stream<String> lineStream = Files.lines(path)) {
            lineStream.forEach(line -> {
                String[] transactionDataSplit = line.split("\\|");
                String signatureString = transactionDataSplit[transactionDataSplit.length - 1];
                String id = transactionDataSplit[0];
                String[] dataArray = Arrays.copyOfRange(transactionDataSplit, 1, transactionDataSplit.length - 1);
                String data = String.join("|", dataArray);
                
                Transaction t = new Transaction();
                
                t.setTransactionID(id);
                t.setTransactionData(data);
                t.setSignatureString(signatureString);

                this.allTransaction.add(t);

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

    public ArrayList<Transaction> getUserTransaction(String userID, String code) {
        ArrayList<Transaction> userTransaction = new ArrayList<>();
        for (Transaction t : this.allTransaction) {
            String[] data = t.getTransactionData().split("\\|");
            String transactionCode = data[0];
            String receiver = data[2];

            if (transactionCode.equals(code) && receiver.equals(userID)) {
                userTransaction.add(t);
            }
        }
        return userTransaction;
    }

    
    public Transaction getTransaction(String ID){
        
        Transaction exist = null;
        
        for (Transaction t: this.allTransaction) {      
            if(t.getTransactionID().equals(ID)){
                exist = t;
                break;
            }
        }
        
        return exist;
    }
    
    public void removeTransaction(String ID){
         for (Transaction t: this.allTransaction) {      
            if(t.getTransactionID().equals(ID)){
                this.allTransaction.remove(t);
                break;
            }
        }
         
         this.writeToFile();
    }
}
