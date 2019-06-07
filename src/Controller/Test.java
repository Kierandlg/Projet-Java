package Controller;

import java.util.ArrayList;

import modele.Bulletin;
import modele.Classe;
import modele.Detailsbulletin;
import modele.Eleve;
import modele.Enseignant;

public class Test {

	public static void main(String arg[])
	{
		
		ArrayList<Eleve> arrayeleve = new ArrayList<Eleve>();
		ArrayList<Enseignant> arrayens = new ArrayList<Enseignant>();
		ArrayList<Classe> arrayclasse = new ArrayList<Classe>();
		ArrayList<Bulletin> arraybulletin = new ArrayList<Bulletin>();
		ArrayList<Detailsbulletin> arraydbulletin = new ArrayList<Detailsbulletin>();
		
		EleveDAO elevedao= new EleveDAO(SingletonConnexion.getInstance());
		EnseignantDAO ensdao= new EnseignantDAO(SingletonConnexion.getInstance());
		ClasseDAO classedao= new ClasseDAO(SingletonConnexion.getInstance());
		DAO<Bulletin> bulletindao = DAOFactory.getBulletinDAO();
		DetailsbulletinDAO dbulletindao= new DetailsbulletinDAO(SingletonConnexion.getInstance());
		
		//arrayens=ensdao.findAll();
		arrayeleve=elevedao.findAll();
		//arrayclasse=classedao.findAll();
		arraybulletin=bulletindao.findAll();
		
		
		
		System.out.println(arrayeleve.get(1).toString());
		//System.out.println(arrayeleve.get(0).toString());
		//System.out.println(arrayens.get(0).toString());
		//System.out.println(arrayclasse.get(0).toString());
		System.out.println(arraybulletin.get(0).toString());
		
		
		
		
		
	}
	
	
	
	
}
