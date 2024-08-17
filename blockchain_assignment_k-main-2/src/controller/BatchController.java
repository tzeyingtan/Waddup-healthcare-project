///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package controller;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.HashMap;
//import java.util.stream.Stream;
//import model.Batch;
//import model.Manufacturer;
//
///**
// *
// * @author Yuan
// */
//public class BatchController extends FileController {
//
//    private HashMap<String, Batch> allBatch = new HashMap<String, Batch>();
//
//    public BatchController(String filename) {
//        super(filename);
//    }
//
//    @Override
//    public boolean writeToFile() {
////
////        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)))) {
////            for (Manufacturer Manufacturer : allManufacturers) {
////                writer.write(Manufacturer.toString());
////                writer.newLine();
//////             
////            }
////            writer.close();
////            return true;
////        } catch (IOException e) {
////            return false;
////
////        } catch (Exception e) {
////            return false;
////        }
//
////        JOptionPane.showMessageDialog(show, message);
//    }
//
//    @Override
//    public boolean readFromFile() {
//        try (Stream<String> lineStream = Files.lines(path)) {
//            lineStream.forEach(line -> {
//                String[] batchDetails = line.split("\\,");
//                Batch b = new Batch();
//                b.setBatchID(batchDetails[0]);
//                b.setBatchCode(Integer.parseInt(batchDetails[1]));
//                b.setBatchQuantity(Integer.parseInt(batchDetails[2]));
//                allBatch.put(b.getBatchID(), b);
//            });
//            lineStream.close();
//            return true;
//        } catch (IOException e) {
//            return false;
////            JOptionPane.showMessageDialog(show, "Something Went Wrong");
//
//        } catch (Exception e) {
//            return false;
////            JOptionPane.showMessageDialog(show, "Something Went Wrong");
//        }
//    }
//}
