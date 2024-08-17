/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;

/**
 *
 * @author Yuan
 */
public class Asymmetric {

    private static final String ALGORITHM = "RSA";

    private Cipher cipher;

    public Asymmetric(String string) throws Exception {
        cipher = Cipher.getInstance(ALGORITHM);
    }

    public Asymmetric() throws Exception {
        cipher = Cipher.getInstance(ALGORITHM);
    }
    
    public String encrypt(String data, PublicKey key) throws Exception{
         String cipherText = null;
        
        //init
        
        cipher.init(Cipher.ENCRYPT_MODE, key);
   
        // encrypt
        
        byte[] cipherBytes = cipher.doFinal(data.getBytes());
        
        // convert to String
       cipherText = Base64.getEncoder().encodeToString(cipherBytes);
       return cipherText;
    }
    
    public String decrypt(String cipherText, PrivateKey key) throws Exception{
        cipher.init(Cipher.DECRYPT_MODE, key);
        
        // convert to byte[]   
        byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
        
        byte[] dataBytes = cipher.doFinal(cipherBytes);
        return new String(dataBytes);
    }
    
    public String encrypt(String data, PrivateKey key) throws Exception{
         String cipherText = null;
        
        //init
        
        cipher.init(Cipher.ENCRYPT_MODE, key);
   
        // encrypt
        
        byte[] cipherBytes = cipher.doFinal(data.getBytes());
        
        // convert to String
       cipherText = Base64.getEncoder().encodeToString(cipherBytes);
       return cipherText;
    }
    
    public String decrypt(String cipherText, PublicKey key) throws Exception{
        cipher.init(Cipher.DECRYPT_MODE, key);
        
        // convert to byte[]   
        byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
        
        byte[] dataBytes = cipher.doFinal(cipherBytes);
        return new String(dataBytes);
    }
}
