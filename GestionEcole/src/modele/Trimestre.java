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
public class Trimestre {
    int id;
    String numero;
    String debut;
    String fin;
    
    
    public Trimestre(int id, String numero, String debut, String fin){
        this.id=id;
        this.numero=numero;
        this.debut=debut;
        this.fin=fin;
      
    }
    
    public int get_id(){
        return this.id;
    }
    
    public String get_numero(){
        return this.numero;
    }
    
    public String get_debut(){
        return this.debut;
    }
    
    public String get_fin(){
        return this.fin;
    }
    
    
    public void set_id(int id){
        this.id=id;
    }
    
    public void set_debut(String deb){
        this.debut=deb;
    }
    
    public void set_fin(String fin){
        this.fin=fin;
    }
    
    public void set_numero(String num){
        this.numero=num;
    }
    
    public String toString()
    {
    	String str=""; 
    	str+=this.numero + "trimestre\n";
    	str+="debut: "+this.debut+" | " +"fin:" + this.fin;
    	return str; 
    }
    
}

