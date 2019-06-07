/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author Eisenbarth Florian
 */
public class Bulletin {
	
    private int id;
    private String appreciation;
    private int fk_eleve; 
    private String trimestre;
    private ArrayList<Detailsbulletin> arrayDBulletin = new ArrayList<Detailsbulletin>();
    
    public Bulletin(int id, String appreciation,int fk_eleve){
        this.id=id;
        this.appreciation=appreciation;
        this.fk_eleve=fk_eleve;
     
 
    }
    
    public int get_id(){
        return this.id;
    }
    
   public void set_id(int id){
       this.id=id;
   }
   
   public String get_trimestre(){
       return this.trimestre;
   }
   
  public void set_trimestre(String str){
      this.trimestre=str;
  }
   
   public void addDBulletin(Detailsbulletin db)
   {
	   this.arrayDBulletin.add(db); 
   }
   public void setfk_eleve(int x)
   {
	   this.fk_eleve=x;
   }
   public int getfk_eleve()
   {
	   return this.fk_eleve; 
   }
   
   public ArrayList<Detailsbulletin> get_arrayDBulletin() {
	return this.arrayDBulletin;
   }
   
   public String getappreciation()
   {
	   return this.appreciation;
   }
   public void setapprecaition(String str)
   {
	   this.appreciation=str; 
   }
  
   
   
   
}

