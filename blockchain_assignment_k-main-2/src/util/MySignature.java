/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 *
 * @author Yuan
 */
public class MySignature {
    private static final String ALGORITHM = "SHA256WithRSA";
    
    private Signature sig;
    
    public MySignature() throws Exception {
        this.sig = Signature.getInstance(ALGORITHM);
    }
    
    public byte[] getSignature( String message, PrivateKey privateKey)throws Exception {
        sig.initSign(privateKey);
        sig.update(message.getBytes());
        return sig.sign();
    }
    
    public boolean isTextAndSignatureValid(String data, byte[] signature, PublicKey publicKey) throws Exception{
        sig.initVerify(publicKey);
        sig.update(data.getBytes());
        return sig.verify(signature);
    }
}
