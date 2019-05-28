package modele;

import java.util.ArrayList;

public class Eleve extends Personne{
	
	private String classe;
	private String niveau;
	private ArrayList<Bulletin> arraybulletin = new ArrayList<Bulletin>();
	
	
	
	public Eleve(int id, String nom, String prenom)
	{
		super(id,nom,prenom);
	}
	
	public String getclasse()
	{
		return this.classe;
	}
	public String getniveau()
	{
		return this.niveau;
	}
	public void setniveau(String str)
	{
		this.niveau=str;
	}
	public void setCalsse(String str)
	{
		this.classe=str;
	}
	
	public ArrayList<Bulletin> getarraybulletin()
	{
		return this.arraybulletin;
	}
	
	public void addBulletin(Bulletin bul)
	{
		this.arraybulletin.add(bul);
	}
	
	public String toString()
	{
		String str="";
		str+="nom:" + this.nom + "\n" + "prenom:" + this.prenom +"\n"+"classe:" +this.classe;
		return str; 
	}

}
