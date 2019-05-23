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
    String promo;
    String id_enseignement; 
    
    public Personne(int id, String nom, String promo, String id_enseignement){
        this.id=id;
        this.nom=nom;
        this.promo=promo;
        this.id_enseignement=id_enseignement;
    }
    
}
