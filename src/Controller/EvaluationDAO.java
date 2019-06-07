package Controller;

import java.sql.PreparedStatement;
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
		try {
			PreparedStatement preparedStatement=this.conect.preparestatement("INSERT INTO evaluation (fk_detailbulletin,note,appreciation,nom) "
					+ "VALUES (?,?,?,?)");
			preparedStatement.setInt(1, obj.get_fkdb());
			preparedStatement.setString(2,obj.get_note());
			preparedStatement.setString(3,obj.get_appreciation());
			preparedStatement.setString(4,obj.get_nom());
			preparedStatement.executeUpdate();
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public  void delete(Evaluation obj)
	{
		try {
			PreparedStatement preparedStatement=this.conect.preparestatement("DELETE FROM evaluation WHERE id=?");
			preparedStatement.setInt(1, obj.get_id());
			preparedStatement.executeUpdate();
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public  void update(Evaluation obj)
	{
		try {
			PreparedStatement preparedStatement=this.conect.preparestatement("UPDATE evaluation SET note=?,appreciation=?,nom=? WHERE id=?");
			preparedStatement.setString(1, obj.get_note());
			preparedStatement.setString(2, obj.get_appreciation());
			preparedStatement.setString(3, obj.get_nom());
			preparedStatement.setInt(4, obj.get_id());
			preparedStatement.executeUpdate();
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public  Evaluation find(int id)
	{
		
		Evaluation eval =null;
		ResultSet res; 
		
		try {
			res=this.conect.executeQuerry("SELECT * FROM evaluation WHERE id="+id);
			if(res.first())
			{
				eval=new Evaluation(res.getInt("id"),res.getString("note"),res.getString("nom"),res.getString("appreciation"),res.getInt("fk_detailbulletin"));
				
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
