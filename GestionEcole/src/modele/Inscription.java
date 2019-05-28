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
public class Inscription {
    int id;
    String id_classe;
    String id_personne;
    String id_bulletin;
    
    public Inscription(int id, String id_classe,String id_personne, String id_bulletin){
        this.id=id;
        this.id_classe=id_classe;
        this.id_personne=id_personne;
        this.id_bulletin=id_bulletin;
    }
    
    public int get_id(){
        return this.id;
    }
    
    public String get_id_classe(){
        return this.id_classe;
    }
    
    public String get_id_personne(){
        return this.id_personne;
    }
    
    public String get_id_bulletin(){
        return this.id_bulletin;
    }
    
    public void set_id(int id){
        this.id=id;
    }
    
    public void set_id_classe(String id_classe){
        this.id_classe=id_classe;
    }
    public void set_id_personne(String id_personne){
        this.id_personne=id_personne;
    }
    
    public void set_id_bulletin(String id_bulletin){
        this.id_bulletin=id_bulletin;
    }
    
}
