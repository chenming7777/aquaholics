/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartinventorysystem;

/**
 *
 * @author YAO FENG PC
 */
public class CRUD {
    public void create(Seed s){
        System.out.println("Seed has been added to the Inventory");
    }
    public Seed read(){
        Seed s = new Seed();
        System.out.println("Seed has been read from the Inventory");
        return s;
    }
    public void update(Seed s){
        System.out.println("Seed has been updated to the Inventory");
    }
    public void delete(){
        System.out.println("Seed has been deleted from the Inventory");
    }
}
