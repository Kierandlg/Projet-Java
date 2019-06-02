package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.Bulletin;
import modele.Evaluation;
import modele.Trimestre;

public class TrimestreDAO extends DAO<Trimestre>{

	
	
	public TrimestreDAO(Connexion  con)
	{
		super(con); 
	}
	
	
	public void insert(Trimestre obj)
	{
		
	}
	public void delete(Trimestre obj)
	{
		
	}
	public  void update(Trimestre obj)
	{
		
	}
	public  Trimestre find(int id)
	{
		
		Trimestre trim =null;
		ResultSet res; 
		
		try {
			res=this.conect.executeQuerry("SELECT * FROM trimestre WHERE id="+id);
			if(res.first())
			{
				trim=new Trimestre(res.getInt("id"),res.getString("numero"),res.getString("debut"),res.getString("fin"));
			
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return trim;
		
		
	}
	public  ArrayList<Trimestre> findAll()
	{
		int nbRow=0; 
		ResultSet res; 
		ArrayList<Trimestre> arraytrim = new ArrayList<Trimestre>(); 
		try {
			res= this.conect.executeQuerry("SELECT * FROM Trimestre");
			if(res!=null)
			{
			res.last(); 
		    nbRow = res.getRow(); 
		    res.beforeFirst(); 
			}
			for(int i=1; i<=nbRow; i++)
			{
				arraytrim.add(find(i));
			}
			

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return arraytrim;
	}
	
	
	public ArrayList<Trimestre> rechercher() 
	{
		ArrayList<Trimestre> arraytrimestre = new ArrayList<Trimestre>(); 
		
		return arraytrimestre; 
	}
	
}
