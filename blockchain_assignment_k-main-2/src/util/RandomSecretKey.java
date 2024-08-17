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
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class RandomSecretKey {

    private static final String ALGORITHM = "AES";

    public static Key create() {
        short keySize = 256;

        try {
            KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
            kg.init(keySize, new SecureRandom());
            return kg.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void put(byte[] keyBytes, String path) {
        File f = new File(path);
        f.getParentFile().mkdirs();

        try {
            Files.write(Paths.get(path), keyBytes, StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Key getSecretKey() throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get("src/RandomKey/secretkey"));

        Key key = new SecretKeySpec(keyBytes, ALGORITHM);
        return key;
    }
}
