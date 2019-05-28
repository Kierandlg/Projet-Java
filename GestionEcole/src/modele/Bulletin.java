/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author BEILLEVAIRE LÃ©o
 */
public class Bulletin {
	
    private int id;
    private String appreciation;
    private int fk_eleve; 
    private ArrayList<Trimestre> arrayttrimestre = new ArrayList<Trimestre>();
    private ArrayList<Detailsbulletin> arrayDBulletin = new ArrayList<Detailsbulletin>();
    
    public Bulletin(int id, String appreciation){
        this.id=id;
        this.appreciation=appreciation;
 
    }
    
    public int get_id(){
        return this.id;
    }
    
   public void set_id(int id){
       this.id=id;
   }
   
   public void addTrimestre(Trimestre t)
   {
	   this.arrayttrimestre.add(t); 
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
   
   public String getappreciation()
   {
	   return this.appreciation;
   }
   public void setapprecaition(String str)
   {
	   this.appreciation=str; 
   }
   public String toString()
   {
	   String str="";
	   str+=this.arrayttrimestre.get(0).toString();
	   for(int i=0 ; i< this.arrayDBulletin.size(); i++)
	   {	
		   str+="\nModule n° "+(i+1);
		   str+="\n"+this.arrayDBulletin.get(i).toString(); 
		   
	   }
	   
	   str+="\nappreciation: " + this.appreciation+"\n";
	 return str;
   }
   
   
   
}

