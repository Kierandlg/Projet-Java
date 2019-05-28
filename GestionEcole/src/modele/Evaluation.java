/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author BEILLEVAIRE Léo
 */
public class Evaluation {
    int id;
    String note;
    String appreciation;
    
    public Evaluation(int id, String note, String appreciation){
        this.id=id;
        this.note=note;
        this.appreciation=appreciation;
    }
    
    public int get_id(){
        return this.id;
    }
    
    
    public String get_note(){
        return this.note;
    }
    
    public String get_appreciation(){
        return this.appreciation;
    
    }
    public void set_id(int id){
        this.id=id;
    }
       
    public void set_id_note(String note){
        this.note=note;
    }
    
    public void set_appreciation(String appreciation){
        this.appreciation=appreciation;
    }
    
    public String toString()
    {
    	String str=""; 
    	str+="Note : "+this.note +" | " +"appreciation: "+ this.appreciation;
    	return str; 
    }
    
}
