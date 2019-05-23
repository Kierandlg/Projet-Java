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
public class Bulletin {
    int id;
    String id_trimestre;
    String id_inscription;
    String appreciation;
    
    public Bulletin(int id, String id_trimestre, String id_inscription, String appreciation){
        this.id=id;
        this.id_trimestre=id_trimestre;
        this.appreciation=appreciation;
        this.id_inscription=id_inscription;
    }
    
}
