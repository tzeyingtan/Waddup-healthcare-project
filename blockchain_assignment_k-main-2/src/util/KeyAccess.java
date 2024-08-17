/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Yuan
 */


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyAccess {

    public static PublicKey getPublicKey(String path) throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(path));
        
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }
    
    public static PrivateKey getPrivateKey(String path) throws Exception
    {
        byte[] keyBytes = Files.readAllBytes(Paths.get(path));
        
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }
    
//    public static PublicKey getPublicKey(String userId, String role) throws Exception {
//        String path = "src/KeyStore/" + role + "/PublicKey_" + userId;
//        byte[] keyBytes = Files.readAllBytes(Paths.get(path));
//        
//        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
//        return KeyFactory.getInstance("RSA").generatePublic(spec);
//    }
//    
//    public static PrivateKey getPrivateKey(String userId, String role) throws Exception {
//        String path = "src/KeyStore/" + role + "/PrivateKey_" + userId;
//        byte[] keyBytes = Files.readAllBytes(Paths.get(path));
//        
//        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
//        return KeyFactory.getInstance("RSA").generatePrivate(spec);
//    }
}
