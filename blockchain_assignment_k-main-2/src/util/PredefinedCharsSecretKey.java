/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Yuan
 */

import java.security.Key;
import java.util.Arrays;


import javax.crypto.spec.SecretKeySpec;


//import javax.crpyto.spec.SecretKeySpec;

public class PredefinedCharsSecretKey {
    
    private static final String ALGORITHM = "AES";
    
    private static final String SECRET_CHARS = "12345";
    
    public static Key create() {
        int keySize = 16;
        return new SecretKeySpec(Arrays.copyOf(SECRET_CHARS.getBytes(),keySize),ALGORITHM );
    }
}
