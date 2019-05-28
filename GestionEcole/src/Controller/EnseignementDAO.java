package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.Enseignement;
import modele.Personne;
/*
public class EnseignementDAO extends DAO<Enseignement> {
	
	private ArrayList<String> attributelist;
	
	public EnseignementDAO(Connexion con)
	{
		super(con); 
	}
	
	
	public Enseignement findAll()
	{	ResultSet res;
	ArrayList<Enseignement> arrayEnseignement;
	
	try {
		res= this.conect.executeQuerry("SELECT * FROM enseignement");
		while(res.next())
		{
			arrayEnseignement.add(new Enseignement(res.getInt("id"),res.getInt("classe.id"),res.getInt("discipline.id"),res.getInt("personne.id")));
		}
		DisciplineDAO disc = new DisciplineDAO(this.conect);
		PersonneDAO persdao = new PersonneDAO(this.conect);
		CalsseDAO classedao = new CalsseDAO(this.conect);
		DetailsbulletinDAO dbulletindao = new DetailsbulletinDAO(this.conect);
		
		for(int i=0; i<arrayEnseignement.size();i++)
		{	
			arrayEnseignement.get(i).setpersonne(persdao.find(arrayEnseignement.get(i).get_idpersonne()));
			
			for(int j=0; j<arrayEnseignement.get(i).get_idclasses().size(); j++) {
				arrayEnseignement.get(i).addclasse(classeado.find(arrayEnseignement.get(i).get_idclasses().get(j)));
			}
			
			arrayEnseignement.get(i).addDetailbulletin(dbulletindao.find());
			
		}
	
	}
	catch(SQLException e)
	{
		e.getMessage();
	}
		
	return arraypers; 
	}
	public void update(Personne obj)
	{
		try {
			 PreparedStatement prepare =this.conect.preparestatement("UPDATE Personne SET nom=?, prenom=? ,type=? WHERE id=?");	
		 prepare.setString(1,obj.getnom());
		 prepare.setString(1,obj.getprenom());
		 prepare.setString(1,obj.gettype());
		 prepare.executeUpdate();
		}
		catch (Exception e) {
		      e.printStackTrace();
		}
		
		 
	}
	
	public Personne find(int id) {
		
		Personne Pers=null;
		ResultSet res; 
		try {
			res=this.conect.executeQuerry("SELECT FROM Presonne WHERE id= "+ id);
			if(res.first()) {
				Pers= new Personne (res.getInt("id"), res.getString("nom"), res.getString("prenom"), res.getString("type"));
			}
		}
		catch(SQLException e)
		{
			e.getMessage();
		}
		
		return Pers;
		
	}
	
	public void insert(Personne obj)
	{	ResultSet res; 
		try {
			res=this.conect.executeQuerry("INSERT INTO Personne (nom,prenom,type) VALUES (" + obj.getnom()+ "," + obj.getprenom()+ "," + obj.gettype());
		}
		catch(SQLException e)
		{
			e.getMessage();
		}
	}

}*/
