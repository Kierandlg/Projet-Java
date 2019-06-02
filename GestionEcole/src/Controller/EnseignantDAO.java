package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.Bulletin;
import modele.Eleve;
import modele.Enseignant;

public class EnseignantDAO extends DAO<Enseignant>{
	
	public EnseignantDAO(Connexion  con)
	{
		super(con);
	}
	
	public void insert(Enseignant obj)
	{
		
	}
	public  void delete(Enseignant obj)
	{	int fk_enseignement;
		ResultSet res;
		try {
			res= this.conect.executeQuerry("SELECT enseignement.id FROM enseignement WHERE enseignement.fk_personne="+obj.get_id());
			if(res.first())
			{
				fk_enseignement=res.getInt("id");
			 PreparedStatement prepare =this.conect.preparestatement("DELETE FROM detailbulletin WHERE fk_enseignement=?");	 
			 prepare.setInt(1,fk_enseignement);
			 prepare.executeUpdate();
			}
		}
		catch (Exception z) {
			  z.printStackTrace();
			}	     
		
			
			
	try {
		
		PreparedStatement prepare =this.conect.preparestatement("DELETE FROM enseignement WHERE enseignement.fk_personne=?");	 
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
	public  void update(Enseignant obj)
	{		
		int fk_discipline; 
		int fk_enseignement;
		ResultSet res;
		
		try {
			res=this.conect.executeQuerry("SELECT id FROM discipline WHERE nom="+"'"+obj.getDiscipline()+"'");
			if(res.first())
			{
				fk_discipline=res.getInt("id"); 
				PreparedStatement prepare=this.conect.preparestatement("UPDATE enseignement SET fk_discipline=? WHERE fk_personne=?");
				 prepare.setInt(1,fk_discipline );
				 prepare.setInt(2, obj.get_id());
				 prepare.executeUpdate();
				
				 prepare =this.conect.preparestatement("UPDATE personne SET nom=?, prenom=? WHERE id=?");	
				 prepare.setString(1,obj.get_nom());
				 prepare.setString(2,obj.get_prenom());
				 prepare.setInt(3,obj.get_id());
				 prepare.executeUpdate();
				
				 for(int i=0;i<obj.getClasse().size();i++)
			{	
					PreparedStatement preparedStatement=this.conect.preparestatement("INSERT INTO enseignement (fk_classe,fk_discipline,fk_personne)"
							+ " SELECT ?,?,?  WHERE NOT EXISTS (SELECT * FROM enseignement "
							+ "WHERE enseignement.fk_classe=? AND enseignement.fk_discipline=?)");

					
				 preparedStatement.setInt(1,obj.getClasse().get(i).get_id() );
				 preparedStatement.setInt(3, obj.get_id());
				 preparedStatement.setInt(2, fk_discipline);
				 preparedStatement.setInt(4, obj.getClasse().get(i).get_id());
				 preparedStatement.setInt(5, fk_discipline);
				 preparedStatement.executeUpdate();
				
				
			}
			
				
				
			}
			
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
	public  Enseignant find(int id)
	{
		Enseignant ens=null;
		ResultSet res;
	
		try {
			res=this.conect.executeQuerry("SELECT * FROM personne WHERE type=1 AND id="+id);
		
			if(res.first())
			{
				ens=new Enseignant(res.getInt("id"), res.getString("nom"),res.getString("prenom"));
				
				ClasseDAO classe = new ClasseDAO(this.conect);
				res=this.conect.executeQuerry("SELECT classe.id FROM classe "
						+ "INNER JOIN enseignement ON enseignement.fk_classe=classe.id "
						+ "INNER JOIN personne ON enseignement.fk_personne=personne.id "
						+ "WHERE personne.id="+id);
				
				while(res.next())
				{
					ens.addClasse(classe.find(res.getInt("id")));
					
				}
				
				res=this.conect.executeQuerry("SELECT discipline.nom FROM discipline "
						+ "INNER JOIN enseignement ON enseignement.fk_discipline=discipline.id "
						+ "INNER JOIN personne ON personne.id=enseignement.fk_personne "
						+ "WHERE personne.id=" +id);
				while(res.next())
				{
					ens.setDiscipline(res.getString("nom"));
				}
				
				
				DetailsbulletinDAO db = new DetailsbulletinDAO(this.conect);
				res=this.conect.executeQuerry("SELECT detailbulletin.id FROM detailbulletin"
						+ " INNER JOIN enseignement ON enseignement.id=detailbulletin.fk_bulletin" 
						+ " INNER JOIN personne on personne.id="+ id);
				while(res.next())
				{
					ens.addDetBulletin(db.find(res.getInt("id")));
				}
				
			
			}
						
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return ens;
	}
	public ArrayList<Enseignant> findAll()
	{
		
		int nbRow=0; 
		ResultSet res; 
		ArrayList<Enseignant> arrayens = new ArrayList<Enseignant>(); 
		ArrayList<Integer> ensid = new ArrayList<Integer>(); 
		try {
			res= this.conect.executeQuerry("SELECT personne.id FROM personne WHERE type=1");
			while(res.next())
			{
			ensid.add(res.getInt("id"));
			System.out.println(ensid.get(0));
			}
			for(int i=0; i<ensid.size(); i++)
			{
				arrayens.add(find(ensid.get(i)));
				
			}
			

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return arrayens;
		
	}
	
	public ArrayList<Enseignant> rechercher() 
	{
		ArrayList<Enseignant> arrayenseignant = new ArrayList<Enseignant>(); 
		
		return arrayenseignant; 
	}

}
