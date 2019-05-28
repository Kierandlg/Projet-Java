package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.Bulletin;
import modele.Detailsbulletin;

public class DetailsbulletinDAO extends DAO<Detailsbulletin>{
	
	public DetailsbulletinDAO(Connexion  con)
	{
		super(con);
	}
	
	
	public void insert(Detailsbulletin obj)
	{
		
	}
	public void delete(Detailsbulletin obj)
	{
		try {
			 PreparedStatement prepare =this.conect.preparestatement("DELETE FROM detailbulletin WHERE id=?");	 
		 prepare.setString(1, Integer.toString(obj.get_id()));
		 prepare.executeUpdate();
		}
		catch (Exception e) {
		      e.printStackTrace();
		}
	}
	public  void update(Detailsbulletin obj)
	{
		
	}
	public  Detailsbulletin find(int id)
	{
		
		
		Detailsbulletin dbulletin =null;
		ResultSet res; 
		
		try {
			res=this.conect.executeQuerry("SELECT * FROM detailbulletin WHERE id="+id);
			if(res.first())
			{
				dbulletin=new Detailsbulletin(res.getInt("id"),res.getString("appreciation"));
			
				EvaluationDAO evaldao = new EvaluationDAO(this.conect);
				
				res=this.conect.executeQuerry("SELECT evaluation.id FROM evaluation "
						+ "INNER JOIN detailbulletin ON detailbulletin.id=evaluation.fk_detailbulletin "
						+ "WHERE detailbulletin.id="+id);
				if(res.first()) {
					
					dbulletin.setEval(evaldao.find(res.getInt("id")));
				}
				
				
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return dbulletin;
		
		
	}
	public  ArrayList<Detailsbulletin> findAll()
	{
		int nbRow=0; 
		ResultSet res; 
		ArrayList<Detailsbulletin> arraydbulletin = new ArrayList<Detailsbulletin>(); 
		try {
			res= this.conect.executeQuerry("SELECT * FROM detailbulletin");
			if(res!=null)
			{
			res.last(); 
		    nbRow = res.getRow(); 
		    res.beforeFirst(); 
			}
			for(int i=1; i<=nbRow; i++)
			{
				arraydbulletin.add(find(i));
			}
			

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return arraydbulletin; 
	}
	
	public ArrayList<Detailsbulletin> rechercher() 
	{
		ArrayList<Detailsbulletin> arraybulletin = new ArrayList<Detailsbulletin>(); 
		
		return arraybulletin; 
	}
	
}
