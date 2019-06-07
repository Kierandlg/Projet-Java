package Controller;

import java.sql.SQLException;

public class SingletonConnexion {
	
	private static String dbname="ecole";
	private static String login="root";
	private static String password="";
	private Connexion connect;
	private static SingletonConnexion single = null;
	
	private SingletonConnexion()
	{	
		try {
			connect = new Connexion(this.dbname,this.login,this.password);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static Connexion getInstance()
	{
		if(single==null)
		{
			single= new SingletonConnexion();
		}
		
		return single.connect;
	}
	

}
