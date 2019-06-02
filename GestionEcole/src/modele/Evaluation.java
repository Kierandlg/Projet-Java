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
    String nom;
    int fk_detailbulletin;
    
    public Evaluation ()
    {
    	
    }
    public Evaluation(int id, String note,String nom, String appreciation,int fk_db){
        this.id=id;
        this.note=note;
        this.appreciation=appreciation;
        this.nom=nom;
        this.fk_detailbulletin=fk_db;
    }
    
    public int get_id(){
        return this.id;
    }
    public String get_nom()
    {
    	return this.nom;
    }
    public void set_nom(String str)
    {
    	this.nom=str;
    }
    public int get_fkdb()
    {
    	return this.fk_detailbulletin;
    }
    public void set_fkdb(int fk)
    {
    	this.fk_detailbulletin=fk;
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
       
    public void set_note(String note){
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
