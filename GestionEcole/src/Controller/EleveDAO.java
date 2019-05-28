package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.Bulletin;
import modele.Detailsbulletin;
import modele.Eleve;

public class EleveDAO extends DAO<Eleve> {
	
	public EleveDAO(Connexion con)
	{
		super(con); 
	}
	
	
	
	public void insert(Eleve obj)
	{
		try {
			 PreparedStatement prepare =this.conect.preparestatement("INSERT INTO Personne (nom,prenom,type) VALUES (?,?)");	 
		 prepare.setString(1,obj.get_nom());
		 prepare.setString(1,obj.get_prenom());
		 prepare.executeUpdate();
		}
		catch (Exception e) {
		      e.printStackTrace();
	}
	}
	public void delete(Eleve obj) {
		
		
		try {
			 PreparedStatement prepare =this.conect.preparestatement("DELETE FROM eleve WHERE id=?");	 
		 prepare.setString(1, Integer.toString(obj.get_id()));
		 prepare.executeUpdate();
		}
		catch (Exception e) {
		      e.printStackTrace();
		
	}
	}
	public void update(Eleve obj)
	{
		
		try {
			 PreparedStatement prepare =this.conect.preparestatement("UPDATE eleve SET nom=?, prenom=? WHERE id=?");	
		 prepare.setString(1,obj.get_nom());
		 prepare.setString(1,obj.get_prenom());
		 prepare.executeUpdate();
		}
		catch (Exception e) {
		      e.printStackTrace();
		}
		
	}
	
	
	public Eleve find(int id) {
		
		Eleve elv=null;
		ResultSet res;
		
		try {
			res=this.conect.executeQuerry("SELECT * FROM personne WHERE type=0 AND id=" + id);
			if(res.first())
			{
				elv=new Eleve(res.getInt("id"), res.getString("nom"),res.getString("prenom"));
				
				BulletinDAO bulletin = new BulletinDAO(this.conect);
				res=this.conect.executeQuerry("SELECT * FROM bulletin WHERE fk_eleve="+ id);
				while(res.next())
				{
					elv.addBulletin(bulletin.find(res.getInt("id")));
					
				}
				
				res=this.conect.executeQuerry("SELECT classe.nom, niveau.nom FROM classe "
						+ "INNER JOIN inscription ON inscription.fk_classe=classe.id "
						+ "INNER JOIN personne ON inscription.fk_personne=personne.id "
						+ "INNER JOIN niveau ON classe.fk_niveau=niveau.id "
						+ "WHERE niveau.id=classe.fk_niveau AND personne.id="+id);
				while(res.next())
				{
					
					elv.setCalsse(res.getString(1));
					elv.setniveau(res.getString(2));
				}
				
			
			}
						
			
		}
		catch(SQLException e)
		{
			e.getMessage();
		}
		return elv;
	}
	
	
	
	
	public ArrayList<Eleve> findAll() {
		
		int nbRow=0; 
		ResultSet res; 
		ArrayList<Eleve> arrayeleve = new ArrayList<Eleve>(); 
		ArrayList<Integer> eleveid= new ArrayList<Integer>();
		
	
		
		try {
			res= this.conect.executeQuerry("SELECT personne.id FROM personne WHERE type=0");
			while(res.next())
			{
			eleveid.add(res.getInt("id"));
			}
			
			for(int i=0; i<eleveid.size(); i++)
			{
				arrayeleve.add(find(eleveid.get(i)));
			}
			

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return arrayeleve; 
		

		
	}
	
	public ArrayList<Eleve> rechercher() 
	{
		ArrayList<Eleve> arrayeleve = new ArrayList<Eleve>(); 
	
		
		return arrayeleve; 
	}

}
