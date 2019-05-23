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
    
    public Trimestre(int id, String numero, String debut, String fin, String id_anneescolaire){
        this.id=id;
        this.numero=numero;
        this.debut=debut;
        this.fin=fin;
        this.id_anneescolaire=id_anneescolaire;
        
    }
    
}

