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
    String id_detailbulletin;
    
    public Bulletin(int id,String id_detailbulletin, String id_trimestre, String id_inscription, String appreciation){
        this.id=id;
        this.id_trimestre=id_trimestre;
        this.appreciation=appreciation;
        this.id_inscription=id_inscription;
        this.id_detailbulletin=id_detailbulletin;
    }
    
    public int get_id(){
        return this.id;
    }
    
    public String get_id_trimestre(){
        return this.id_trimestre;
    }
    
    public String get_id_inscription(){
        return this.id_inscription;
    }
    
    public String get_appreciation(){
        return this.appreciation;
    }
    public String get_id_detailbulletin(){
        return this.id_detailbulletin;
    }
    
    
   public void set_id_detalbulletin(String id_detailbulletin){
       this.id_detailbulletin=id_detailbulletin;
   }
   
   public void set_appreciation(String appre){
       this.appreciation=appre;
   }
   
   public void set_id_inscription(String id_inscr){
       this.id_inscription=id_inscr;
   }
   
   public void set_id_trimestre(String trim){
       this.id_trimestre=trim;
   }
   
   public void set_id(int id){
       this.id=id;
   }
}

