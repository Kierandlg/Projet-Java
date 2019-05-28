package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.Bulletin;
import modele.Evaluation;

public class EvaluationDAO extends DAO<Evaluation>{
	
	public EvaluationDAO(Connexion  con)
	{
		super(con); 
	}
	
	
	public  void insert(Evaluation obj)
	{
		
	}
	public  void delete(Evaluation obj)
	{
		
	}
	public  void update(Evaluation obj)
	{
		
	}
	public  Evaluation find(int id)
	{
		
		Evaluation eval =null;
		ResultSet res; 
		
		try {
			res=this.conect.executeQuerry("SELECT * FROM evaluation WHERE id="+id);
			if(res.first())
			{
				eval=new Evaluation(res.getInt("id"),res.getString("note"),res.getString("appreciation"));
				
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return eval;
		
	}
	public  ArrayList<Evaluation> findAll()
	{
		int nbRow=0; 
		ResultSet res; 
		ArrayList<Evaluation> arrayeval = new ArrayList<Evaluation>(); 
		try {
			res= this.conect.executeQuerry("SELECT * FROM Evaluation");
			if(res!=null)
			{
			res.last(); 
		    nbRow = res.getRow(); 
		    res.beforeFirst(); 
			}
			for(int i=1; i<=nbRow; i++)
			{
				arrayeval.add(find(i));
			}
			

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return arrayeval;
		
		
	}
	
	
	public ArrayList<Evaluation> rechercher() 
	{
		ArrayList<Evaluation> arrayeval = new ArrayList<Evaluation>(); 
		
		return arrayeval; 
	}
}
