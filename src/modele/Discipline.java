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
public class Discipline {
    int id;
    String nom;
    
    public Discipline(int id, String nom){
    this.id=id;
    this.nom=nom;
    
}
    
    public int get_id()
    {
    	return this.id;
    }
    public void set_id(int id)
    {
    	this.id=id;
    }
    
    public String get_nom()
    {
    	return nom;
    }
    public void set_nom(String nom)
    {
    	this.nom=nom;
    }
}
