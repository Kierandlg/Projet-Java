package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.Bulletin;

public class BulletinDAO extends DAO<Bulletin> {
	
	public BulletinDAO(Connexion con)
	{
		super(con); 
	}
	
	
	public void insert(Bulletin obj) {
		
		/*try {
			 PreparedStatement prepare =this.conect.preparestatement("INSERT INTO Bulletin (fk_trimestre,fk_inscription,fk_eleve,appreciation)"
			 		+ " VALUES (?,?,?,?)");	 
		 prepare.setString(1,obj.get_nom());
		 prepare.setString(1,obj.get_prenom());
		 prepare.executeUpdate();
		}
		catch (Exception e) {
		      e.printStackTrace();
		}*/
		
	}
	public  void delete(Bulletin obj)
	{
		
		try {
			 PreparedStatement prepare =this.conect.preparestatement("DELETE FROM bulletin WHERE id=?");	 
		 prepare.setString(1, Integer.toString(obj.get_id()));
		 prepare.executeUpdate();
		}
		catch (Exception e) {
		      e.printStackTrace();
		
		}
		
	}
	public  void update(Bulletin obj)
	{
		try {
			 PreparedStatement prepare =this.conect.preparestatement("UPDATE bulletin SET appreciation=? WHERE id=?");	
		 prepare.setString(1,obj.getappreciation());
		 prepare.executeUpdate();
		}
		catch (Exception e) {
		      e.printStackTrace();
		}
		
	}
	public  Bulletin find(int id)
	{
		Bulletin bulletin =null;
		ResultSet res; 

		
		try {
			res=this.conect.executeQuerry("SELECT * FROM bulletin WHERE id="+id);
			if(res.first())
			{
				bulletin=new Bulletin(res.getInt("id"),res.getString("appreciation"));
				
				TrimestreDAO trimestre = new TrimestreDAO(this.conect);
				
				res=this.conect.executeQuerry("SELECT trimestre.id FROM trimestre INNER JOIN bulletin ON trimestre.id=bulletin.fk_trimestre "
						+ "WHERE bulletin.id="+id);
				if(res.first()) {
					
					bulletin.addTrimestre(trimestre.find(res.getInt("id")));
					
				}
				
				DetailsbulletinDAO dbulletin = new DetailsbulletinDAO(this.conect);
				
				res=this.conect.executeQuerry("SELECT db.id FROM detailbulletin AS db "
						+ "INNER JOIN bulletin ON db.fk_bulletin=bulletin.id "
						+ "WHERE bulletin.id="+id);
			
				while(res.next())
				{	
					bulletin.addDBulletin(dbulletin.find(res.getInt("id")));
					
				}
				
				
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	return bulletin; 
	
	}
	public  ArrayList<Bulletin> findAll(){
		
		int nbRow=0; 
		ResultSet res; 
		ArrayList<Bulletin> arraybulletin = new ArrayList<Bulletin>(); 
		try {
			res= this.conect.executeQuerry("SELECT * FROM bulletin");
			if(res!=null)
			{
			res.last(); 
		    nbRow = res.getRow(); 
		    res.beforeFirst(); 
			}
			for(int i=1; i<=nbRow; i++)
			{
				arraybulletin.add(find(i));
			}
			

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return arraybulletin; 
		

		
	}
	public ArrayList<Bulletin> rechercher() 
	{
		ArrayList<Bulletin> arraybulletin = new ArrayList<Bulletin>(); 
		
		return arraybulletin; 
	}
}
