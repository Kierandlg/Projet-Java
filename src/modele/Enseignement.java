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
public class Enseignement {
    private int id;
    private int id_discipline;
    private int id_personne;
    
    private ArrayList<String> id_classes;
    
    private ArrayList<Classe> classe;
    private Discipline discipline;
    private Personne enseignant;
    private ArrayList<Detailsbulletin> detailbulletin;
    
    public Enseignement(int id, String idclasses, int did, int p){
    	String str="";
    	this.id=id;
    	this.id_discipline=did;
    	this.id_personne=p;
    	//Désérialisation
    	for(int i=0 ; i<idclasses.length();i++)
    	{
    		if(idclasses.charAt(i)=='/') 
    		{
    			id_classes.add(str);
    			str="";
    		}
    		else {
    			str+=idclasses.charAt(i);
    		}
    		
    	}
        
    }
    
    public int get_id(){
        return this.id;
    }
    
    public ArrayList<Classe> get_classe(){
        return this.classe;
    }
    
    public Personne get_Enseignant(){
        return this.enseignant;
    }
    
    public ArrayList<Detailsbulletin> get_detailbulletin(){
        return this.detailbulletin;
    }
    public void addDetailbulletin(Detailsbulletin bul){
        this.detailbulletin.add(bul);
    }
    
    public void set_id(int id){
        this.id=id;
    }
    
    public void setclasse(ArrayList<Classe> classe){
        this.classe=classe;
    }
    public void addclasse(Classe classe){
        this.classe.add(classe);
    }
    
    public void setpersonne(Personne personne){
        this.enseignant=personne;
    }
    
    public void setdetailbulletin(ArrayList<Detailsbulletin> id_detail){
        this.detailbulletin=id_detail;
    }
    
    public int get_idpersonne()
    {
    	return id_personne;
    }
    public int get_iddiscipline()
    {
    	return id_discipline;
    }
    public ArrayList<String> get_idclasses()
    {
    	return this.id_classes;
    }
    
    
    
    
}
