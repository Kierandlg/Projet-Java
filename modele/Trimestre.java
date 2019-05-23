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
    String id_anneescolaire;
    String id_bulletin;
    
    public Trimestre(int id, String numero, String debut, String fin, String id_anneescolaire, String id_bulletin){
        this.id=id;
        this.numero=numero;
        this.debut=debut;
        this.fin=fin;
        this.id_anneescolaire=id_anneescolaire;
        this.id_bulletin=id_bulletin;
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
    
    public String get_id_anneescolaire(){
        return this.id_anneescolaire;
    }
    
    public String get_id_bulletin(){
        return this.id_bulletin;
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
    
    public void set_id_anneescolaire(String id_annee){
        this.id_anneescolaire=id_annee;
    }
    
    public void set_id_bulletin(String id_bulletin){
        this.id_bulletin=id_bulletin;
    }
    
}

