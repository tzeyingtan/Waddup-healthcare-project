/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import model.HealthcareProvider;


/**
 *
 * @author user
 */
public abstract class FileController {
    
    protected String filename;
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
    protected Path path;
    
    FileController(String filename){
        this.filename = filename;
        this.path = Path.of(filename);
    }
    
    FileController(){
        
    }
    
    public abstract boolean writeToFile();
    
    public abstract boolean readFromFile();
    
    public void setFile(String filename){
        this.filename = filename;
        this.path = Path.of(filename);
    }
    
}
