package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.AnneeScolaire;
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
			 PreparedStatement prepare =this.conect.preparestatement("DELETE FROM bulletin WHERE fk_eleve=?");	 
		 prepare.setString(1, Integer.toString(obj.get_id()));
		 prepare.executeUpdate();
		
		}
		catch (Exception e) {
		      e.printStackTrace();
		}
		      
		      try {
					 PreparedStatement prepare =this.conect.preparestatement("DELETE FROM inscription WHERE fk_personne=?");	 
				 prepare.setString(1, Integer.toString(obj.get_id()));
				 prepare.executeUpdate();
				}
				catch (Exception z) {
				      z.printStackTrace();
				}	
		      
		      try {
					 PreparedStatement prepare =this.conect.preparestatement("DELETE FROM personne WHERE id=?");	 
				 prepare.setString(1, Integer.toString(obj.get_id()));
				 prepare.executeUpdate();
				}
				catch (Exception z) {
				      z.printStackTrace();
				}	      
		
	
	}
	

	public void update(Eleve obj)
	{
		int fk_classe; 
		int fk_niveau; 
		int fk_anneescolaire;
		ResultSet res;
		ResultSet res2;
		try {
			res=conect.executeQuerry("SELECT niveau.id FROM niveau WHERE niveau.nom="+"'"+obj.getniveau()+"'");
			if(res.first())
			{
				fk_niveau=res.getInt("id");
				System.out.println("fk_niveau:"+fk_niveau);
				res2=conect.executeQuerry("SELECT classe.id FROM classe WHERE classe.nom="+"'"+obj.getclasse()+"'"+" AND classe.fk_anneescolaire="+1);
				if(res2.first())
				{
					fk_classe=res2.getInt("id"); 
					 PreparedStatement prepare =this.conect.preparestatement("UPDATE inscription SET fk_classe=? WHERE fk_personne=?");	
					 prepare.setInt(1,fk_classe);
					 prepare.setInt(2,obj.get_id());
					 prepare.executeUpdate();
				}
				else {
					PreparedStatement prepare =this.conect.preparestatement("INSERT INTO classe (nom, fk_niveau, fk_anneescolaire) VALUES (?,?,?)");	
					 prepare.setString(1,obj.getclasse());
					 prepare.setInt(2,fk_niveau);
					 prepare.setInt(3,1);
					 prepare.executeUpdate();
				}
				
				
			}
			
			PreparedStatement prepare =this.conect.preparestatement("UPDATE personne SET nom=?, prenom=? WHERE id=?");	
				 prepare.setString(1,obj.get_nom());
				 prepare.setString(2,obj.get_prenom());
				 prepare.setInt(3,obj.get_id());
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
