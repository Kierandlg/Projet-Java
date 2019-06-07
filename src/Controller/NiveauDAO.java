package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.Evaluation;
import modele.Niveau;

public class NiveauDAO extends DAO<Niveau> {
	
	public NiveauDAO(Connexion  con)
	{
		super(con);
	}
	
	
	public  void insert(Niveau obj) {
		
	}
	public  void delete(Niveau obj)
	{
		
	}
	public  void update(Niveau obj)
	{
		
	}
	public  Niveau find(int id)
	{
		Niveau niv =null;
		ResultSet res; 
		
		try {
			res=this.conect.executeQuerry("SELECT * FROM niveau WHERE id="+id);
			if(res.first())
			{
				niv=new Niveau(res.getInt("id"),res.getString("nom"));
				
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return niv;
	}
	public  ArrayList<Niveau> findAll()
	{
		int nbRow=0; 
		ResultSet res; 
		ArrayList<Niveau> arrayniv = new ArrayList<Niveau>(); 
		try {
			res= this.conect.executeQuerry("SELECT id FROM niveau");
			while(res.next())
			{
				arrayniv.add(find(res.getInt("id")));
				
			}
			

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	
		return arrayniv;
	}
	public  ArrayList<Niveau> rechercher()
	{
		return null;
	}

}
