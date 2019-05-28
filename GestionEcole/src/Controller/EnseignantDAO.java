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
	{
		try {
			 PreparedStatement prepare =this.conect.preparestatement("DELETE FROM personne WHERE id=?");	 
		 prepare.setString(1, Integer.toString(obj.get_id()));
		 prepare.executeUpdate();
		}
		catch (Exception e) {
		      e.printStackTrace();
		
		}
	}
	public  void update(Enseignant obj)
	{
		
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
