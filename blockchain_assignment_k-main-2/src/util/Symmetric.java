/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
//import static org.apache.commons.codec.digest.HmacAlgorithmsTest.data;

/**
 *
 * @author Yuan
 */
public class Symmetric {
    
    private static final String ALGORITHM = "AES";
    private Cipher cipher;
    
    
    public Symmetric(String string) throws Exception{
        cipher = Cipher.getInstance(ALGORITHM);
    }
    
    public Symmetric() throws Exception {
        cipher = Cipher.getInstance(ALGORITHM);
    }
    
    public String encrypt(String data, Key key) throws Exception{
        String cipherText = null;
        
        //init
        
        cipher.init(Cipher.ENCRYPT_MODE, key);
        
        // encrypt
        
        byte[] cipherBytes = cipher.doFinal(data.getBytes());
        
        // convert to String
       cipherText = Base64.getEncoder().encodeToString(cipherBytes);
       return cipherText;
    }
    
    public String decrypt(String cipherText, Key key) throws Exception{
        cipher.init(Cipher.DECRYPT_MODE, key);
        
        // convert to byte[]   
        byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
        
        byte[] dataBytes = cipher.doFinal(cipherBytes);
        return new String(dataBytes);
    }
}
