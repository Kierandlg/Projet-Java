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
		try {
			 PreparedStatement prepare =this.conect.preparestatement("UPDATE detailbulletin SET appreciation=? WHERE id=?");	 
		 prepare.setString(1,obj.get_appreciation());
		 prepare.executeUpdate();
		}
		catch (Exception e) {
		      e.printStackTrace();
		}
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
				while(res.next())
				{
					dbulletin.addEval(evaldao.find(res.getInt("id")));
					System.out.println(res.getInt("id"));
				}
					
				res=this.conect.executeQuerry("SELECT discipline.nom FROM discipline "
						+ "INNER JOIN enseignement ON enseignement.fk_discipline=discipline.id "
						+ "INNER JOIN detailbulletin ON detailbulletin.fk_enseignement=enseignement.id "
						+ "WHERE detailbulletin.id=+"+id);
				if(res.first()) {
					
					dbulletin.set_Discipline(res.getString("nom"));
				}
				
				res=this.conect.executeQuerry("SELECT detailbulletin.fk_enseignement FROM detailbulletin WHERE detailbulletin.id="+id);
				if(res.first()) {
					
					dbulletin.set_fkenseignement(res.getInt("fk_enseignement"));
				}
				
				res=this.conect.executeQuerry("SELECT SUBSTRING(AVG(evaluation.note),1,5) AS moyenneClasse FROM evaluation "
						+ "INNER JOIN detailbulletin ON detailbulletin.id=evaluation.fk_detailbulletin "
						+ "INNER JOIN bulletin ON detailbulletin.fk_bulletin=bulletin.id "
						+ "INNER JOIN personne ON personne.id=bulletin.fk_eleve "
						+ "INNER JOIN enseignement ON enseignement.id=detailbulletin.fk_enseignement "
						+ "WHERE personne.type=0 AND enseignement.id="+dbulletin.get_fkenseignement() );
				if(res.first()) {
					
					dbulletin.set_moyenneClasse(res.getString("moyenneClasse"));
				}
				
				
				res=this.conect.executeQuerry("SELECT SUBSTRING(AVG(evaluation.note),1,5) AS moyenne FROM evaluation " + 
						" INNER JOIN detailbulletin ON detailbulletin.id=evaluation.fk_detailbulletin " + 
						"WHERE detailbulletin.id="+id );
				if(res.first()) {
					
					dbulletin.set_moyenne(res.getString("moyenne"));
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
