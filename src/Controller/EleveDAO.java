package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.Detailsbulletin;
import modele.Eleve;

public class EleveDAO extends DAO<Eleve> {
	
	public EleveDAO(Connexion con)
	{
		super(con); 
	}
	
	
	
	public void insert(Eleve obj)
	{	ResultSet res;
		int fk_classe;
		int fk_niveau;
		int fk_inscription;
		int fk_bulletin;
		ArrayList<Integer> arrayidens = new ArrayList<Integer>();
		ArrayList<Detailsbulletin> arraydb = new ArrayList<Detailsbulletin>();
		DAO<Detailsbulletin> dbDao=DAOFactory.getDetailsbulletinDAO();
		int id;
		
		try {
			res=this.conect.executeQuerry("SELECT niveau.id FROM niveau WHERE niveau.nom="+"'"+obj.getniveau()+"'");
			if(res.first())
			{
				fk_niveau=res.getInt("id");
				 System.out.println();
				res=this.conect.executeQuerry("SELECT classe.id FROM classe WHERE classe.nom="+"'"+obj.getclasse()+"'"+" AND classe.fk_niveau="+fk_niveau);
				if(res.first())
				{	fk_classe=res.getInt("id");
				
					 PreparedStatement prepare =this.conect.preparestatement("INSERT INTO Personne (nom,prenom,type,photo) VALUES (?,?,?,?)");	 
					 prepare.setString(1,obj.get_nom());
					 prepare.setString(2,obj.get_prenom());
					 prepare.setString(3,"0");
					 prepare.setString(4,obj.get_photo());
					 prepare.executeUpdate();
					
					res=this.conect.executeQuerry("SELECT MAX(id) FROM personne");
					if(res.first())
					{
						id=res.getInt(1);
					prepare=this.conect.preparestatement("INSERT INTO inscription (fk_classe,fk_personne) VALUES (?,?)");
					 prepare.setInt(1,fk_classe);
					 prepare.setInt(2,id);
					 prepare.executeUpdate();
					 
					 res=this.conect.executeQuerry("SELECT id FROM inscription WHERE fk_personne="+id);
					 if(res.first())
					 {	
						 fk_inscription=res.getInt("id");
						 
						 for(int i=1;i<=3;i++)
						 {
							  prepare=this.conect.preparestatement("INSERT INTO bulletin (fk_trimestre,fk_inscription,fk_eleve,appreciation) VALUES(?,?,?,?)");
						 prepare.setInt(1,i);
						 prepare.setInt(2,fk_inscription);
						 prepare.setInt(3,id);
						 prepare.setString(4, " ");
						 prepare.executeUpdate();
						 res=this.conect.executeQuerry("SELECT MAX(id) FROM bulletin");
							if(res.first())
							{	
							
								fk_bulletin=res.getInt(1);
								 res=this.conect.executeQuerry("SELECT enseignement.id FROM enseignement WHERE enseignement.fk_classe="+fk_classe);
								 while(res.next())
								 {	
									 arrayidens.add(res.getInt("id"));
								 }
								 for(int j=0; j<arrayidens.size();j++)
								 {
									 Detailsbulletin detailsbulletin=new Detailsbulletin(0," ");
								 detailsbulletin.set_fkenseignement(arrayidens.get(j));
								 detailsbulletin.set_idbulletin(fk_bulletin);
								dbDao.insert(detailsbulletin);
								 
								 }
								 
								 
								 arrayidens.clear();
								 
							}
						
						 
						 
						 }
						
						 
						 /*for(int i=1;i<=3;i++)
						 {
						prepare= this.conect.preparestatement("UPDATE trimestre SET fk_anneescolaire=(SELECT anneescolaire.id FROM anneescolaire "
						 		+ "WHERE anneescolaire.annee=(SELECT MAX(anneescolaire.annee) FROM anneescolaire)) WHERE trimestre.id=?");
						prepare.setInt(1, i);
						prepare.executeUpdate();
						 }*/
						 
					} 
					 }
					
					
					  
					 
				}
			
			}
			}
			
		catch (Exception e) {
		      e.printStackTrace();
	}
	}
	public void delete(Eleve obj) {
		
		
		try {
			for(int i=0;i<obj.getarraybulletin().size();i++)
			{
				PreparedStatement prepare =this.conect.preparestatement("DELETE FROM detailbulletin WHERE id=?");
				for(int j=0;j<obj.getarraybulletin().get(i).get_arrayDBulletin().size();j++)
				{
					prepare.setInt(1,(obj.getarraybulletin().get(i).get_arrayDBulletin().get(j).get_id()));
					prepare.executeUpdate();
				}
				
				
			}
			 
		
		}
		catch (Exception e) {
		      e.printStackTrace();
		}
		
		try {
			 PreparedStatement prepare =this.conect.preparestatement("DELETE FROM bulletin WHERE fk_eleve=?");	 
		 prepare.setString(1, Integer.toString(obj.get_id()));
		 prepare.executeUpdate();
		
		}
		catch (Exception e) {
		      e.printStackTrace();
		}
		      
		      try {
					 PreparedStatement prepare =this.conect.preparestatement("DELETE FROM inscription WHERE fk_personne=?");	 
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
	

	public void update(Eleve obj)
	{
		int fk_classe; 
		int fk_niveau; 
		int fk_anneescolaire;
		ResultSet res;
		ResultSet res2;
		try {
			res=conect.executeQuerry("SELECT niveau.id FROM niveau WHERE niveau.nom="+"'"+obj.getniveau()+"'");
			if(res.first())
			{
				fk_niveau=res.getInt("id");
				System.out.println("fk_niveau:"+fk_niveau);
				res2=conect.executeQuerry("SELECT classe.id FROM classe WHERE classe.nom="+"'"+obj.getclasse()+"'"+" AND classe.fk_anneescolaire="+1);
				if(res2.first())
					
				
				{
					fk_classe=res2.getInt("id"); 
					 PreparedStatement prepare =this.conect.preparestatement("UPDATE inscription SET fk_classe=? WHERE fk_personne=?");	
					 prepare.setInt(1,fk_classe);
					 prepare.setInt(2,obj.get_id());
					 prepare.executeUpdate();
				}
				else {
					PreparedStatement prepare =this.conect.preparestatement("INSERT INTO classe (nom, fk_niveau, fk_anneescolaire) VALUES (?,?,?)");	
					 prepare.setString(1,obj.getclasse());
					 prepare.setInt(2,fk_niveau);
					 prepare.setInt(3,1);
					 prepare.executeUpdate();
				}
				
				
			}
			
			PreparedStatement prepare =this.conect.preparestatement("UPDATE personne SET nom=?, prenom=? WHERE id=?");	
				 prepare.setString(1,obj.get_nom());
				 prepare.setString(2,obj.get_prenom());
				 prepare.setInt(3,obj.get_id());
				 prepare.executeUpdate();
		 
				
		}
		catch (Exception e) {
		      e.printStackTrace();
		}
		
		
	}
	
	
	public Eleve find(int id) {
		
		Eleve elv=null;
		ResultSet res;
		
		try {
			res=this.conect.executeQuerry("SELECT * FROM personne WHERE type=0 AND id=" + id);
			if(res.first())
			{
				elv=new Eleve(res.getInt("id"), res.getString("nom"),res.getString("prenom"),res.getString("photo"));
				BulletinDAO bulletin = new BulletinDAO(this.conect);
				res=this.conect.executeQuerry("SELECT * FROM bulletin WHERE fk_eleve="+ id);
				while(res.next())
				{
					elv.addBulletin(bulletin.find(res.getInt("id")));
				
					
				}
				
				res=this.conect.executeQuerry("SELECT classe.nom, niveau.nom FROM classe "
						+ "INNER JOIN inscription ON inscription.fk_classe=classe.id "
						+ "INNER JOIN personne ON inscription.fk_personne=personne.id "
						+ "INNER JOIN niveau ON classe.fk_niveau=niveau.id "
						+ "WHERE niveau.id=classe.fk_niveau AND personne.id="+id);
				while(res.next())
				{
					
					elv.setCalsse(res.getString(1));
					elv.setniveau(res.getString(2));
				}
				
			
			}
						
			
		}
		catch(SQLException e)
		{
			e.getMessage();
		}
		return elv;
	}
	
	
	
	
	public ArrayList<Eleve> findAll() {
		
		int nbRow=0; 
		ResultSet res; 
		ArrayList<Eleve> arrayeleve = new ArrayList<Eleve>(); 
		ArrayList<Integer> eleveid= new ArrayList<Integer>();
		
	
		
		try {
			res= this.conect.executeQuerry("SELECT personne.id FROM personne WHERE type=0");
			while(res.next())
			{
			eleveid.add(res.getInt("id"));
			}
			
			for(int i=0; i<eleveid.size(); i++)
			{
				arrayeleve.add(find(eleveid.get(i)));
			}
			

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return arrayeleve; 
		

		
	}
	
	public ArrayList<Eleve> rechercher() 
	{
		ArrayList<Eleve> arrayeleve = new ArrayList<Eleve>(); 
	
		
		return arrayeleve; 
	}

}
