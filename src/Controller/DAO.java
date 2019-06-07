package Controller;

import java.util.ArrayList;

public abstract class DAO<T> {
	
	protected Connexion conect= null; 
	
	public DAO(Connexion  con)
	{
		this.conect=con; 
	}
	
	
	public abstract void insert(T obj); 
	public abstract void delete(T obj); 
	public abstract void update(T obj); 
	public abstract T find(int id);
	public abstract ArrayList<T> findAll();
	public abstract ArrayList<T> rechercher(); 
	
	
	
	
}
