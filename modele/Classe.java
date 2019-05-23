/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;
import java.util.ArrayList;

/**
 *
 * @author BEILLEVAIRE Léo
 */
public class Classe {
    int id;
    String nom; 
    String id_niveau;
    String id_anneescolaire;
    ArrayList<String> id_enseignement;
    ArrayList<String> id_inscription;
    
    
    public Classe(int id, String name, String id_niveau, String id_anneescolaire, ArrayList<String> id_enseignement,  ArrayList<String> id_inscription){
        this.id=id;
        this.nom=name;
        this.id_anneescolaire=id_anneescolaire;
        this.id_niveau=id_niveau;
        this.id_enseignement=id_enseignement;
        this.id_inscription=id_inscription;
    }
    
    public int get_id(int id){
        return this.id;
    }
    
    public String get_nom(){
        return this.nom;
    }
    
    public String get_id_anneescolaire(){
        return this.id_anneescolaire;
    }
    public String get_id_niveau(){
        return this.id_niveau;
    }
    public ArrayList<String> get_id_inscription(){
        return this.id_inscription;
    }
    
    public ArrayList<String> get_id_enseignement(){
        return this.id_enseignement;
    }
    
    public void set_id(int id){
        this.id=id;
    }
    
    public void set_nom(String nom){
        this.nom=nom;
    }
    
    public void set_id_anneescolaire( String id_annee){
        this.id_anneescolaire=id_annee;
    }
    
    public void set_id_niveau(String id_niveau){
        this.id_niveau=id_niveau;
    }
    
    public void set_id_inscription(ArrayList<String> id_inscr){
        this.id_inscription=id_inscr;
    }
    
    public void set_id_enseignement(ArrayList<String> id_enseignement){
        this.id_enseignement=id_enseignement;
    }
}
