/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.Serializable;
import java.sql.Timestamp;
import static java.util.Objects.hash;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author Yuan
 */
public class Block implements Serializable {

    private static final long serialVersionUID = 1L;
    private String previousHash;
    private TransactionCollection transactionCollection;
    private Header header;

    public Block(String previousHash, int currentSize) {
        this.header = new Header();
        this.header.setIndex(currentSize + 1);
        this.header.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        this.header.setPreviousHash(previousHash);
        String info = String.join("+", 
                Integer.toString(this.header.getIndex()),
                Long.toString(header.getTimestamp()), 
                header.getPreviousHash());
//                transactionCollection.getMerkleRoot());
//        String blockHash = String.valueOf(hash(info, "SHA-256"));
        String blockHash = Hasher.sha256(info);
//        Hex.encodeHex(blockHash);
        header.setCurrentHash(blockHash);
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public TransactionCollection getTransactionCollection() {
        return transactionCollection;
    }

    public void setTransactionCollection(TransactionCollection transactionCollection) {
        this.transactionCollection = transactionCollection;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    @Override
    public String toString() {

        if (this.transactionCollection != null) {
            return this.header.toString() + "|" + this.transactionCollection.toString();

        }
        return null;
    }

    public class Header implements Serializable {

        private static final long serialVersionUID = 1L;
        private int index;
        private String currentHash;
        private String previousHash;
        private long timestamp;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getCurrentHash() {
            return currentHash;
        }

        public void setCurrentHash(String currentHash) {
            this.currentHash = currentHash;
        }

        public String getPreviousHash() {
            return previousHash;
        }

        public void setPreviousHash(String previousHash) {
            this.previousHash = previousHash;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return this.index + " | currentHash= " + this.currentHash + "| previousHash= " + this.previousHash + "| timestamp= " + this.timestamp;
        }
    }

}
