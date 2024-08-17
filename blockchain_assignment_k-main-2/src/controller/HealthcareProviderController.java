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
import model.HealthcareProvider;

/**
 *
 * @author Yuan
 */
public class HealthcareProviderController extends FileController {

    public ArrayList<HealthcareProvider> allHealthcareProviders = new ArrayList<>();

    public HealthcareProviderController(String filename) {
        super(filename);
    }

    @Override
    public boolean writeToFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)))) {
            for (HealthcareProvider HealthcareProvider : allHealthcareProviders) {
                writer.write(HealthcareProvider.toString());
                writer.newLine();
//             
            }
            writer.close();
            return true;
        } catch (IOException e) {
            return false;

        } catch (Exception e) {
            return false;
        }

//        JOptionPane.showMessageDialog(show, message);
    }

    public ArrayList<HealthcareProvider> getAllHealthcareProviders() {
        return allHealthcareProviders;
    }

    @Override
    public boolean readFromFile() {
        try (Stream<String> lineStream = Files.lines(path)) {
            lineStream.forEach(line -> {
                String[] HealthcareProviderDetails = line.split("\\,");
                System.out.println(HealthcareProviderDetails[0]);
                HealthcareProvider hp = new HealthcareProvider();
                hp.setID(HealthcareProviderDetails[0]);
                hp.setName(HealthcareProviderDetails[1]);
                hp.setContact(HealthcareProviderDetails[2]);
                hp.setCode(HealthcareProviderDetails[3]);
                hp.setPassword(HealthcareProviderDetails[4]);
                hp.setPublicKeyPath(HealthcareProviderDetails[5]);
                hp.setPrivateKeyPath(HealthcareProviderDetails[6]);

                allHealthcareProviders.add(hp);
                System.out.println(allHealthcareProviders.size());
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

    public HealthcareProvider getHealthcareProvider(String ID) {
        HealthcareProvider exist = null;

        for (HealthcareProvider hp : allHealthcareProviders) {
            if (hp.getID().equals(ID)) {
                exist = hp;
                break;
            }
        }

        return exist;
    }

    public HealthcareProvider createNewUser(String name, String code, String password, String contact) {
        HealthcareProvider hp = new HealthcareProvider();

        int newID = this.allHealthcareProviders.size() + 1;
        hp.setID("HP" + newID);
        hp.setName(name);
        hp.setCode(code);
        hp.setPassword(password);
        hp.setContact(contact);

        String publicKeyPath = "src/KeyStore/" + "HealthcareProvider" + "/PublicKey_" + hp.getID();
        String privateKeyPath = "src/KeyStore/" + "HealthcareProvider" + "/PrivateKey_" + hp.getID();

        hp.setPublicKeyPath(publicKeyPath);
        hp.setPrivateKeyPath(privateKeyPath);

        this.allHealthcareProviders.add(hp);
        this.writeToFile();
        return hp;
    }
}
