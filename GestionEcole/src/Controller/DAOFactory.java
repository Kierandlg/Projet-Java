package Controller;

public class DAOFactory {

	
	protected static final Connexion Connexion = SingletonConnexion.getInstance();
	
	public static DAO getEleveDAO()
	{
		return new EleveDAO(Connexion); 
	}
	
	public static DAO getEnseignantDAO()
	{
		return new EnseignantDAO(Connexion); 
	}
	
	
	public static DAO getClasseEleveDAO()
	{
		return new ClasseDAO(Connexion); 
	}
	
	
	public static DAO getBulletinDAO()
	{
		return new BulletinDAO(Connexion); 
	}
	
	public static DAO getDetailsbulletinDAO()
	{
		return new DetailsbulletinDAO(Connexion); 
	}
	
	public static DAO getEvaluationDAO()
	{
		return new EvaluationDAO(Connexion); 
	}
	
	public static DAO getTrimestreDAO()
	{
		return new TrimestreDAO(Connexion); 
	}
	
	
}
