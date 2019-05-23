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
public class Detailsbulletin {
    int id;
    String id_bulletin;
    String id_enseignement;
    String appreciation;
    
    public Detailsbulletin(int id, String id_bulletin, String id_enseignement, String appreciation){
        this.id=id;
        this.id_bulletin=id_bulletin;
        this.id_enseignement=id_enseignement;
        this.appreciation=appreciation;
    }
    
    
}
