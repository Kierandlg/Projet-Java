package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.print.attribute.standard.RequestingUserName;

import modele.Discipline;
import modele.Eleve;

public class DisciplineDAO extends DAO<Discipline> {

	public DisciplineDAO(Connexion  con)
	{
		super(con); 
	}
	
	
	public  void insert(Discipline obj) {
		
	}
	public  void delete(Discipline obj)
	{
		
	}
	public  void update(Discipline obj)
	{
		
	}
	public  Discipline find(int id)
	{
		Discipline dis=null;
		ResultSet res;
		
		try {
			res=this.conect.executeQuerry("SELECT * FROM discipline WHERE id="+id);
			if(res.first())
			{
				dis = new Discipline(id,res.getString("nom")); 
				System.out.println(res.getString("nom"));
			}
		}
		catch(SQLException e)
		{
			e.getMessage();
		}
		return dis;
	}
	public ArrayList<Discipline> findAll()
	{
		int nbRow=0; 
		ResultSet res; 
		ArrayList<Discipline> arraydiscipline = new ArrayList<Discipline>(); 
		ArrayList<Integer> id= new ArrayList<Integer>();
		
	
		
		try {
			res= this.conect.executeQuerry("SELECT discipline.id FROM discipline");
			while(res.next())
			{
			id.add(res.getInt("id"));
			}
			
			for(int i=0; i<id.size(); i++)
			{
				arraydiscipline.add(find(id.get(i)));
			}
			

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return arraydiscipline;
	}
	public  ArrayList<Discipline> rechercher(){
		
		 ArrayList<Discipline> arraydisc=null; 
		 return arraydisc; 
	}
	
	
	
	
}
