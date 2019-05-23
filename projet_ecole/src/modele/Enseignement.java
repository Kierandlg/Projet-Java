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
    ArrayList<String > id_personne;
    
    public Enseignement(int id, String id_classe,ArrayList<String> id_personne){
        this.id=id;
        this.id_classe=id_classe;
        this.id_personne=id_personne;
    }
    
}
