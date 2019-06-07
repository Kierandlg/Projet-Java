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
public class Personne {
	
    protected int id;
    protected String nom;
    protected  String prenom;
    protected  String photo;
      
    public Personne(int id, String nom, String prenom,String photo){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.photo=photo;
    }
    
    public int get_id(){
        return this.id;
    }
    
    public String get_nom(){
        return this.nom;
    }
    
    public String get_prenom(){
        return this.prenom;
    }
      
    
    public void set_id(int id){
        this.id=id;
    }
    
    public void set_nom(String name){
        this.nom=name;
    }
    
    public void set_prenom(String prenom){
        this.prenom=prenom;
    }
    
    public String get_photo()
    {
    	return this.photo;
    }
    
    public void Set_photo(String photo)
    {
    	this.photo=photo;
    }
    
}
