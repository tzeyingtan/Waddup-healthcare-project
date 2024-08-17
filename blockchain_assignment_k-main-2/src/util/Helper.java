/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedList;
import java.util.UUID;

/**
 *
 * @author Yuan
 */
public class Helper {

    public static String generateID(String prefix) {
        return prefix + String.format("%05d", UUID.randomUUID().hashCode());
    }

    public static boolean systemVerifyTransaction(String transaction, String publicKeyPath) throws Exception {

        Symmetric symm = new Symmetric();
        Key secretKey = RandomSecretKey.getSecretKey();
        String decrypted = symm.decrypt(transaction, secretKey);
        String[] transactionDataSplit = decrypted.split("\\|");
        String signatureString = transactionDataSplit[transactionDataSplit.length - 1];
        String[] dataArray = Arrays.copyOfRange(transactionDataSplit, 0, transactionDataSplit.length - 1);
        String data = String.join("|", dataArray);

        MySignature mySig = new MySignature();
        PublicKey publicKey = KeyAccess.getPublicKey(publicKeyPath);

        byte[] sig = Base64.getDecoder().decode(signatureString);
        boolean success = mySig.isTextAndSignatureValid(data, sig, publicKey);

        return success;
    }

    public static boolean userVerifyTransaction(String transaction, String publicKeyPath) throws Exception {

        String[] transactionDataSplit = transaction.split("\\|");
        String signatureString = transactionDataSplit[transactionDataSplit.length - 1];
        String[] dataArray = Arrays.copyOfRange(transactionDataSplit, 0, transactionDataSplit.length - 1);
        String data = String.join("|", dataArray);

        MySignature mySig = new MySignature();
        PublicKey publicKey = KeyAccess.getPublicKey(publicKeyPath);

        byte[] sig = Base64.getDecoder().decode(signatureString);
        boolean success = mySig.isTextAndSignatureValid(data, sig, publicKey);

        return success;
    }

    public static TransactionCollection signTransaction(String data, String PrivateKeyPath) {

        try {
            PrivateKey privateKey = KeyAccess.getPrivateKey(PrivateKeyPath);
            TransactionCollection transactionCollection = new TransactionCollection();
            MySignature sig = new MySignature();

            byte[] signature = sig.getSignature(data, privateKey);
            Symmetric symm = new Symmetric();
            Key secretKey = RandomSecretKey.getSecretKey();
            String newData = data + "|" + Base64.getEncoder().encodeToString(signature);
            String encryptedData = symm.encrypt(newData, secretKey);
            transactionCollection.add(encryptedData);

            return transactionCollection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean addBlock(TransactionCollection transactionCollection) {

        try {
            MerkleTree mt = MerkleTree.getInstance(transactionCollection.getTranxList());
            mt.build();
            transactionCollection.setMerkleRoot(mt.getRoot());

            // Create a new Block with the data, signature, and Merkle Tree root hash
            Blockchain blockchain = Blockchain.getInstance("src/On_Chain/Blockchain.bin");
            LinkedList<Block> blockchainList = blockchain.get();
            int currentSize = blockchainList.size();

            String previousHash = "0"; // Default value for the first block
            if (blockchainList != null && !blockchainList.isEmpty()) {
                previousHash = blockchainList.getLast().getHeader().getCurrentHash();
            }

            Block newBlock = new Block(previousHash, currentSize);

            // Set the TransactionCollection in the new Block
            newBlock.setTransactionCollection(transactionCollection);

            // Add the new Block to the blockchain
            blockchain.nextBlock(newBlock);
            blockchain.distribute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
