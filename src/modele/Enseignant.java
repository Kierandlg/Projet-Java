package modele;

import java.util.ArrayList;

public class Enseignant extends Personne{
	
	private ArrayList<Classe> arrayclasse = new ArrayList<Classe>();
	private ArrayList<Detailsbulletin> arrayDBulletin=new ArrayList<Detailsbulletin>();
	private String Discipline; 

	
	public Enseignant(int id, String nom, String prenom,String photo)
	{
		super(id,nom,prenom,photo);
	}
	
	public String getDiscipline()
	{
		return this.Discipline;
	}
	public void setDiscipline(String str)
	{
		this.Discipline=str;
	}
	
	public void addClasse(Classe cl)
	{
		this.arrayclasse.add(cl);
	}
	
	public ArrayList<Classe> getClasse()
	{
		return this.arrayclasse;
	}
	
	public void addDetBulletin(Detailsbulletin bul)
	{
		this.arrayDBulletin.add(bul);
	}
	
	public String toString()
	{
		String str="*****Enseignant*****\n";
		str= "nom: "+this.nom +"  |  "+"prenom:"+ this.prenom;
		str+="\n*****Discipline*****\n";
		str+="-"+this.Discipline;
		str+= "\n*******Classes*****\n";
		for(int i=0 ; i<this.arrayclasse.size(); i++)
		{
			str+="- "+ this.arrayclasse.get(i).get_nom() + "\n";
		}
		return str;
	}

}
