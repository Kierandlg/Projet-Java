/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

import javax.print.attribute.standard.RequestingUserName;

/**
 *
 * @author Eisenbarth Florian
 */
public class Detailsbulletin {
   private int id;
   private int fk_enseignement ; 
   private String Discipline; 
    private String appreciation;
    private String moyenne; 
    private String moyenneClasse; 
    private ArrayList<Evaluation> eval = new ArrayList<Evaluation>();
   
    
    public Detailsbulletin(int id, String appreciation){
        this.id=id;
        this.appreciation=appreciation;
    }
    
    public int get_id()
    {
    	return this.id;
    }
    public void addEval(Evaluation eval)
    {
    	this.eval.add(eval);
    }
    public ArrayList<Evaluation> get_eval()
    {
    	return this.eval;
    }
    public String get_Discipline()
    {
    	return this.Discipline;
    }
    public void set_Discipline(String str)
    {
     this.Discipline=str;
    }
    
    public String get_moyenne()
    {
    	return this.moyenne;
    }
    public void set_moyenne(String str)
    {
     this.moyenne=str;
    }
    public String get_moyenneClasse()
    {
    	return this.moyenneClasse;
    }
    public void set_moyenneClasse(String str)
    {
     this.moyenneClasse=str;
    }
    
    public int get_fkenseignement()
    {
    	return this.fk_enseignement;
    }
    public void set_fkenseignement(int fk)
    {
     this.fk_enseignement=fk;
    }
    
    public String get_appreciation()
    {
    	return this.appreciation;
    }
    public void set_appreciation(String str) {
		this.appreciation=str; 
	}
    
    public String toString()
    {
    	String str=""; 
    	if(eval!=null)
    	{
    		str+=eval.toString()+"\n";
    	}
    	
    	str+="appreciation: "+ this.appreciation;
    	return str; 
    }
    
}
