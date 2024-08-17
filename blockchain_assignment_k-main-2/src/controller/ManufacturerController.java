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
import java.util.ArrayList;
import java.util.stream.Stream;
import model.Manufacturer;

/**
 *
 * @author Yuan
 */
public class ManufacturerController extends FileController {

    public ArrayList<Manufacturer> allManufacturers = new ArrayList<>();

    public ManufacturerController(String filename) {
        super(filename);
    }

    @Override
    public boolean writeToFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)))) {
            for (Manufacturer m : allManufacturers) {
                writer.write(m.toString());
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

    @Override
    public boolean readFromFile() {
        try (Stream<String> lineStream = Files.lines(path)) {
            lineStream.forEach(line -> {
                String[] manufacturerDetails = line.split("\\,");
                System.out.println(manufacturerDetails[0]);
                Manufacturer m = new Manufacturer();
                m.setID(manufacturerDetails[0]);
                m.setName(manufacturerDetails[1]);
                m.setContact(manufacturerDetails[2]);
                m.setCode(manufacturerDetails[3]);
                m.setPassword(manufacturerDetails[4]);
                m.setPublicKeyPath(manufacturerDetails[5]);
                m.setPrivateKeyPath(manufacturerDetails[6]);

                allManufacturers.add(m);
                System.out.println(allManufacturers.size());
            });
            lineStream.close();
            return true;
        } catch (IOException e) {
            return false;
//            JOptionPane.showMessageDialog(show, "Something Went Wrong");

        } catch (Exception e) {
            return false;
//            JOptionPane.showMessageDialog(show, "Something Went Wrong");
        }
    }

    public Manufacturer getManufacturer(String ID) {
        Manufacturer exist = null;

        for (Manufacturer m : allManufacturers) {
            if (m.getID().equals(ID)) {
                exist = m;
                break;
            }
        }

        return exist;
    }

    public Manufacturer createNewUser(String name, String code, String password, String contact) {
        Manufacturer m = new Manufacturer();

        int newID = this.allManufacturers.size() + 1;
        m.setID("M" + newID);
        m.setName(name);
        m.setCode(code);
        m.setPassword(password);
        m.setContact(contact);

        String publicKeyPath = "src/KeyStore/" + "Manufacturer" + "/PublicKey_" + m.getID();
        String privateKeyPath = "src/KeyStore/" + "Manufacturer" + "/PrivateKey_" + m.getID();

        m.setPublicKeyPath(publicKeyPath);
        m.setPrivateKeyPath(privateKeyPath);

        this.allManufacturers.add(m);
        this.writeToFile();

        return m;
    }
    
    public ArrayList<Manufacturer> getAllManufacturer(){
        return this.allManufacturers;
    }
}
