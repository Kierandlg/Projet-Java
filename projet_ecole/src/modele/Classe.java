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
public class Classe {
    int id;
    String nom; 
    String id_niveau;
    String id_anneescolaire;
    
    public Classe(int id, String name, String id_niveau, String id_anneescolaire){
        this.id=id;
        this.nom=name;
        this.id_anneescolaire=id_anneescolaire;
        this.id_niveau=id_niveau;
    }
    
}
