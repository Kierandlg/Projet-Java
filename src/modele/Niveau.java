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
public class Niveau {
    int id;
    String nom;
    
    public Niveau(int id, String name){
    this.id=id;
    this.nom=name;
}
    
    public String get_nom() {
    	return this.nom;
    }
    
}
