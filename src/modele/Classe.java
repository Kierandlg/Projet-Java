/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;
import java.util.ArrayList;

import com.mysql.jdbc.StreamingNotifiable;

/**
 *
 * @author Eienbarth Florian
 */
public class Classe {
    int id;
    String nom; 
    String niveau;
    String anneescolaire;
    ArrayList<Eleve> arrayEleve = new ArrayList<Eleve>();
    ArrayList<Enseignant > arrayEnseignant = new ArrayList<Enseignant>();
    
    
    public Classe(int id, String name){
        this.id=id;
        this.nom=name;
      
    }
    
    public int get_id(){
        return this.id;
    }
    
    public String get_nom(){
        return this.nom;
    }
    
    public void setniveau(String niv)
    {
    	this.niveau=niv; 
    }
    public String getniveau()
    {
    	return this.niveau; 
    }
    
    public void set_id(int id){
        this.id=id;
    }
    
    public void set_nom(String nom){
        this.nom=nom;
    }
    
    public void addEleve(Eleve el)
    {
    	this.arrayEleve.add(el); 
    }
    public void addEnseignant(Enseignant en)
    {
    	this.arrayEnseignant.add(en); 
    }
    public  ArrayList<Enseignant > get_arrayens()
    {
    	return this.arrayEnseignant;
    }
    
    public void setannescolaire(String str)
    {
    	this.anneescolaire=str; 
    }
    public String getanneescolaire()
    {
    	return this.anneescolaire; 
    }
    public  ArrayList<Eleve> getarrayeleve()
    {
    	return this.arrayEleve; 
    }
    
    public String toString()
    {
    	String str="";
    	str+="******Classe******\n";
    	str+="-"+this.nom + "  Niveau:"+this.niveau +  "|  "+ "Annee scolaire:"+this.anneescolaire; 
    	str+="******\nListe des enseignant dans cette classe******\n";
    	for(int i=0 ;i<this.arrayEnseignant.size(); i++)
    	{
    		str+="- "+ this.arrayEnseignant.get(i).get_nom()+ "\n";
    		
    	}
    	str+="\n******Liste des eleves dans cette classe******\n";
    	for(int i=0 ;i<this.arrayEleve.size(); i++)
    	{
    		str+="- "+ this.arrayEleve.get(i).get_nom() + "\n";
    		
    	}
    	
    	return str; 
    }
    
   
}
