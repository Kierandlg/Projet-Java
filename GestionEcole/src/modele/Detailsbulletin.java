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
   private int id;
    private String appreciation;
    private Evaluation eval;
   
    
    public Detailsbulletin(int id, String appreciation){
        this.id=id;
        this.appreciation=appreciation;
    }
    
    public int get_id()
    {
    	return this.id;
    }
    public void setEval(Evaluation eval)
    {
    	this.eval=eval;
    }
    
    public String toString()
    {
    	String str=""; 
    	if(eval!=null)
    	{
    		str+=eval.toString()+"\n";
    	}
    	
    	str+="appreciation: "+ this.appreciation;
    	return str; 
    }
    
}
