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
public class Evaluation {
    int id;
    String id_detailbulletin;
    String note;
    String appreciation;
    
    public Evaluation(int id, String id_detailbulletin, String note, String appreciation){
        this.id=id;
        this.id_detailbulletin=id_detailbulletin;
        this.note=note;
        this.appreciation=appreciation;
    }
    
}
