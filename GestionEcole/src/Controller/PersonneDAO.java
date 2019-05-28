package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.Personne;
/*
public class PersonneDAO extends DAO<Personne>{
	
	private ArrayList<String> attributelist;
	public PersonneDAO(Connexion con)
	{
		super(con); 
	}
	
	
	public ArrayList<Personne> create()
	{	ResultSet res;
		ArrayList<Personne> arraypers;
		
		try {
			res= this.conect.executeQuerry("SELECT * FROM Personne");
			while(res.next())
			{
				arraypers.add(new Personne(res.getInt("id"),res.getString("nom"),res.getString("prenom"),res.getString("type")));
			}
		
			for(int i=0; i<arraypers.size();i++)
			{
				if(arraypers.get(i).get_type()=="1")
				{
				EnseignementDAO enseignement = new EnseignementDAO(this.conect);
				arraypers.get(i).setenseignement(enseignement.find(arraypers.get(i).get_id()));
				}
				else {
				InscriptionDAO inscri = new Inscription(this.conect);
				arraypers.get(i).setinscription(inscri.find(arraypers.get(i).get_id()));
				
				}
			}
			
		}
		catch(SQLException e)
		{
			e.getMessage();
		}
			return arraypers;
		}

		
		 
	
	public void delete(Personne obj)
	{	
		try {
			 PreparedStatement prepare =this.conect.preparestatement("DELETE FROM Personne WHERE id=?");	 
		 prepare.setString(1, Integer.toString(obj.get_id()));
		 prepare.executeUpdate();
		}
		catch (Exception e) {
		      e.printStackTrace();
	}
	}
	public void update(Personne obj)
	{
		try {
			 PreparedStatement prepare =this.conect.preparestatement("UPDATE Personne SET nom=?, prenom=? ,type=? WHERE id=?");	
		 prepare.setString(1,obj.get_nom());
		 prepare.setString(1,obj.get_prenom());
		 prepare.setString(1,obj.get_type());
		 prepare.executeUpdate();
		}
		catch (Exception e) {
		      e.printStackTrace();
		}
		
		 
	}
	
	public Personne find(int id) {
		
		Personne Pers=null;
		ResultSet res; 
		try {
			res=this.conect.executeQuerry("SELECT FROM Presonne WHERE id= "+ id);
			if(res.first()) {
				Pers= new Personne (res.getInt("id"), res.getString("nom"), res.getString("prenom"), res.getString("type"));
				res.beforeFirst();
				if(Pers.get_type()=="1")
				{
				EnseignementDAO enseignement = new EnseignementDAO(this.conect);
				Pers.setenseignement(enseignement.find(Pers.get_id()));
				}
			else {
				InscriptionDAO inscri = new Inscription(this.conect);
				Pers.setinscription(inscri.find(Pers.get_id()));
			}
		}
		}
		catch(SQLException e)
		{
			e.getMessage();
		}
		
		return Pers;
		
	}
	
	public void insert(Personne obj)
	{	ResultSet res; 
		try {
			res=this.conect.executeQuerry("INSERT INTO Personne (nom,prenom,type) VALUES (" + obj.get_nom()+ "," + obj.get_prenom()+ "," + obj.get_type());
		}
		catch(SQLException e)
		{
			e.getMessage();
		}
	}
	

}*/
