package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.Bulletin;
import modele.Classe;
import modele.Enseignant;

public class ClasseDAO extends DAO<Classe> {

	
public ClasseDAO (Connexion con)
{
	super(con);
}


public  void insert(Classe obj)
{
	int fk_niveau;
	int fk_annescolaire; 
	ResultSet resultSet;
	try {
		
	
	resultSet=this.conect.executeQuerry("SELECT niveau.id  FROM niveau  WHERE niveau.nom="+"'"+obj.getniveau()+"'");
	
		if(resultSet.first())
		{	
			fk_niveau=resultSet.getInt("id");
			resultSet=this.conect.executeQuerry("SELECT anneescolaire.id  FROM anneescolaire  WHERE anneescolaire.annee="+"'"+obj.getanneescolaire()+"'");
			if(resultSet.next())
			{
				fk_annescolaire=resultSet.getInt("id");
				
		 PreparedStatement prepare =this.conect.preparestatement("INSERT INTO classe (nom,fk_niveau,fk_anneescolaire) VALUES (?,?,?)");	 
	 prepare.setString(1,obj.get_nom());
	 prepare.setInt(2,fk_niveau);
	 prepare.setInt(3,fk_annescolaire);
	 prepare.executeUpdate();
						
			}
		
			
		
		}
	
	}
		catch (Exception e) {
	      e.printStackTrace();
	}		
	
}
public  void delete(Classe obj)
{
	try {
		 PreparedStatement prepare =this.conect.preparestatement("DELETE FROM classe WHERE id=?");	 
	 prepare.setString(1, Integer.toString(obj.get_id()));
	 prepare.executeUpdate();
	}
	catch (Exception e) {
	      e.printStackTrace();
	}
}


public  void update(Classe obj)
{
	
}



public  Classe find(int id)
{
	
	Classe classe=null;
	ResultSet res;

	try {
		res=this.conect.executeQuerry("SELECT * FROM classe WHERE id=" + id);
	
		if(res.first())
		{
			classe=new Classe(res.getInt("id"), res.getString("nom"));
			
			EleveDAO elevedao = new EleveDAO(this.conect);
			EnseignantDAO enseignantdao = new EnseignantDAO(this.conect);
			
			res=this.conect.executeQuerry("SELECT personne.id FROM personne "
					+ "INNER JOIN inscription ON inscription.fk_personne=personne.id "
					+ "INNER JOIN classe ON classe.id=inscription.fk_classe "
					+ "WHERE personne.type=0 AND classe.id="+ id);
			while(res.next())
			{
				classe.addEleve(elevedao.find(res.getInt("id")));

			
			}
		
			res=this.conect.executeQuerry("SELECT personne.id, personne.prenom, personne.nom, personne.photo FROM personne "
					+ "INNER JOIN enseignement ON enseignement.fk_personne=personne.id "
					+ "INNER JOIN classe ON classe.id=enseignement.fk_classe"
					+ " WHERE personne.type=1 AND classe.id="+id);
			while(res.next())
			{
				classe.addEnseignant(new Enseignant(res.getInt("id"), res.getString("nom"),res.getString("prenom"),res.getString("photo"))); //on fait ainsi car sinon on appel la méthode classe.find dans elle meme
				
			}
			
			res=this.conect.executeQuerry("SELECT niveau.nom FROM niveau INNER JOIN classe ON classe.fk_niveau=niveau.id WHERE classe.id="+id);
			
			while(res.next())
			{	
				classe.setniveau(res.getString("nom"));
			}
			res=this.conect.executeQuerry("SELECT anneescolaire.annee FROM anneescolaire "
					+ "INNER JOIN classe ON classe.fk_anneescolaire=anneescolaire.id "
					+ "WHERE classe.id="+id);
			while(res.next())
			{
				classe.setannescolaire(res.getString("annee"));
			}
			
		}
					
		
	}
	catch(SQLException e)
	{
		e.getMessage();
	}
	
	return classe;
	
	
	
}
public  ArrayList<Classe> findAll()
{

	int nbRow=0; 
	ResultSet res; 
	ArrayList<Classe> arrayclasse = new ArrayList<Classe>(); 

	try {

		res= this.conect.executeQuerry("SELECT classe.id FROM classe");
		while(res.next())
		{
			arrayclasse.add(find(res.getInt("id")));	
		}
		

	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	
	return arrayclasse;
	
	
}

public ArrayList<Classe> rechercher() 
{
	ArrayList<Classe> arraybulletin = new ArrayList<Classe>(); 
	
	return arraybulletin; 
}



}
