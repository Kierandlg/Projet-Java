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
    int id;
    String nom;
    String prenom;
    String id_enseignement; 
    String id_inscription;
    
    public Personne(int id, String nom, String prenom, String id_enseignement, String id_inscription){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.id_enseignement=id_enseignement;
        this.id_inscription=id_inscription;
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
    
    public String get_id_enseignement(){
        return this.id_enseignement;
    }
    
    public String get_id_inscription(){
        return this.id_inscription;
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
    
    public void set_id_enseignement(String id_ensei){
        this.id_enseignement=id_ensei;
    }
    
    public void set_id_inscription(String id_inscri){
        this.id_inscription=id_inscri;
    }
}
