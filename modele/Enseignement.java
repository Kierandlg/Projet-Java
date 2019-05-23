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
public class Enseignement {
    int id;
    String id_classe;
    String id_discipline;
    String id_personne;
    ArrayList<String> id_detailbulletin;
    
    public Enseignement(int id, String id_classe,String id_personne, ArrayList<String> id_detaibulletin){
        this.id=id;
        this.id_classe=id_classe;
        this.id_personne=id_personne;
        this.id_detailbulletin=id_detaibulletin;
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
    
    public ArrayList get_id_detailbulletin(){
        return this.id_detailbulletin;
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
    
    public void set_id_detailbulletin(ArrayList<String> id_detail){
        this.id_detailbulletin=id_detail;
    }
    
    
    
}
