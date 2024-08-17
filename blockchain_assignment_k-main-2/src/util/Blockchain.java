/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
//import 

/**
 *
 * @author Yuan
 */
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
public class Blockchain {
    
    private static LinkedList<Block> db = new LinkedList<>();
    private static Blockchain _instance = new Blockchain();
    private String chainFile;
 
    public static Blockchain getInstance(String chainFile){
         _instance.chainFile = chainFile;
         return _instance;
    }
    
    public void genesis(){
        Block genesis = new Block("0", 0);
        db.add(genesis);
        persist();
    }
    
    public void nextBlock(Block newBlock){
        db = get();
        db.add(newBlock);
        persist();
    }
    
    public LinkedList<Block> get() {
        LinkedList<Block> blockchain = new LinkedList<>();
        try (FileInputStream fin = new FileInputStream(this.chainFile);
             ObjectInputStream in = new ObjectInputStream(fin)) {
            Object obj = in.readObject();
            if (obj instanceof LinkedList<?>) {
                blockchain = (LinkedList<Block>) obj;
            }
        } catch (FileNotFoundException e) {
            // Handle file not found error
            System.err.println("Blockchain file not found: " + e.getMessage());
        } catch (IOException e) {
            // Handle other IO errors
            System.err.println("Error reading blockchain file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // Handle class not found error
            System.err.println("Error reading blockchain data: " + e.getMessage());
        }
        return blockchain;
    }
    
    //previous code
//    public LinkedList<Block> get(){
//        try (FileInputStream fin = new FileInputStream(this.chainFile);
//             ObjectInputStream in = new ObjectInputStream(fin);
//             ) {
//              return(LinkedList<Block>)in.readObject();
//              
//         }
//        catch(Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    
    public void persist() {
        try (FileOutputStream fout = new FileOutputStream(this.chainFile);
             ObjectOutputStream out = new ObjectOutputStream(fout)) {
            out.writeObject(db);
            System.out.println("Blockchain file updated successfully.");
        } catch (IOException e) {
            // Handle IO errors
            System.err.println("Error writing blockchain file: " + e.getMessage());
        }
    }
    
    // previous code
//    public void persist(){
//        try (FileOutputStream fout = new FileOutputStream(this.chainFile);
//             ObjectOutputStream out = new ObjectOutputStream(fout);
//           ){
//            out.writeObject(db);
//            System.out.println(">> Master file is updated!");
//        }catch(Exception e) {
//            e.printStackTrace();
//        }               
//    }
    
    public void distribute(){
        String chain = new GsonBuilder().setPrettyPrinting().create().toJson(db);
        System.out.println(chain);
    }
    
}
