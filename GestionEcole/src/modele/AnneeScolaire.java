/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;
import java.util.ArrayList;

/**
 *
 * @author BEILLEVAIRE Léo
 */
public class AnneeScolaire {
    int id;
    ArrayList<String> id_trimestre;
    
    
    public AnneeScolaire(int id, ArrayList id_trimestre){
        this.id=id;
        this.id_trimestre=id_trimestre;
    }
    
    
    public int get_id(){
        return this.id;
    }
    
    public ArrayList<String> get_id_trimestre(){
        return this.id_trimestre;
    }
    
    public void set_id(int id){
        this.id=id;
    }
    
    public void set_id_trimestre(ArrayList id_trim){
        this.id_trimestre=id_trim;
    }
    
}
