/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartinventorysystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author YAO FENG PC
 */
public class CRUD {
    // Constructor
    private String fileName;
    public CRUD(String fileName) {
        this.fileName = fileName;
    }
    public ArrayList<String[]> saveTo2DArrayList(){
        ArrayList<String[]> ArrayList2D = new ArrayList();
        ArrayList<String> datalines = this.readFile();
        for (String line:datalines){
            String[] dataArray = line.trim().split("\\|");
            ArrayList2D.add(dataArray);
        }
        return ArrayList2D;
    }
    
    public void addToFile(String[] newObject){
        try{
            FileWriter file = new FileWriter(fileName,true);
            BufferedWriter writer = new BufferedWriter(file);
            String line = String.join("|", newObject);
            writer.append(line);
            writer.append("\n");
            writer.close();     
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
   
    public void removeLineFromFile(String primaryKey){
        ArrayList<String[]> dataLines = this.saveTo2DArrayList();
        try{
            FileWriter file = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(file);
            for(String[] data:dataLines){
                if(data[0].equals(primaryKey)){
                    continue;
                }
                else{
                    String line = String.join("|", data);
                    writer.write(line+"\n");
                    writer.flush();
                }
            }
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void editFile(String[] targetLine, String[] newLine) {
        ArrayList<String[]> dataLines = this.saveTo2DArrayList();
        for(int i =0 ; i <dataLines.size();i++){

            if(Arrays.equals(dataLines.get(i) ,targetLine)){
                dataLines.set(i, newLine);
            }
        }
        
        try{
            FileWriter file = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(file);
            for(String[] data:dataLines){
                String line = String.join("|", data);
                writer.write(line+"\n");
                writer.flush();         
            }
            
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public ArrayList<String> readFile() {
        ArrayList<String> object = new ArrayList<String>();
        try{
            FileReader file = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null){
                object.add(line);
            }
            reader.close();
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return object;
    }
}
