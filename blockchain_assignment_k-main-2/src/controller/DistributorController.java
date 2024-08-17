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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Stream;
import model.Distributor;
import model.Manufacturer;

/**
 *
 * @author Yuan
 */
public class DistributorController {

    public ArrayList<Distributor> allDistributor = new ArrayList<>();

    private String filename;
    private Path path;

    public DistributorController(String filename) {
        this.filename = filename;
        this.path = Path.of(filename);
    }

    public boolean writeToFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)))) {
            for (Distributor d : allDistributor) {
                writer.write(d.toString());
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
        try (Stream<String> lineStream = Files.lines(path)) {
            lineStream.forEach(line -> {
                String[] distributorDetails = line.split("\\,");
                System.out.println(distributorDetails[0]);
                Distributor d = new Distributor();
                d.setID(distributorDetails[0]);
                d.setName(distributorDetails[1]);
                d.setContact(distributorDetails[2]);
                d.setCode(distributorDetails[3]);
                d.setPassword(distributorDetails[4]);
                d.setPublicKeyPath(distributorDetails[5]);
                d.setPrivateKeyPath(distributorDetails[6]);

                allDistributor.add(d);
                System.out.println(allDistributor.size());
            });
            lineStream.close();
            return true;
        } catch (IOException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Distributor getDistributor(String ID) {
        Distributor exist = null;

        for (Distributor d : allDistributor) {
            if (d.getID().equals(ID)) {
                exist = d;
                break;
            }
        }

        return exist;
    }

    public Distributor createNewUser(String name, String code, String password, String contact) {
        Distributor d = new Distributor();

        int newID = this.allDistributor.size() + 1;

        d.setID("D" + newID);
        d.setName(name);
        d.setCode(code);
        d.setPassword(password);
        d.setContact(contact);

        String publicKeyPath = "src/KeyStore/" + "Distributor" + "/PublicKey_" + d.getID();
        String privateKeyPath = "src/KeyStore/" + "Distributor" + "/PrivateKey_" + d.getID();

        d.setPublicKeyPath(publicKeyPath);
        d.setPrivateKeyPath(privateKeyPath);

        this.allDistributor.add(d);
        this.writeToFile();

        return d;
    }

    public ArrayList<Distributor> getAllDistributor() {
        return allDistributor;
    }
    
    

}
