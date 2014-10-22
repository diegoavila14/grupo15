package UseCase;

import java.util.*;

public class Diagram 
{
	List<Actor> actors;
	List<UserCase> userCases;
	List<Connection> connections;
	List<String> ids;
	String name;
	
	public Diagram(String n)
	{
		name = n;
		actors = new ArrayList<Actor>();
		userCases = new ArrayList<UserCase>();
		connections = new ArrayList<Connection>();
		ids = new ArrayList<String>();
		
	}
	
	public void addActor(Actor a)
	{
		actors.add(a);
		ids.add(a.id);
		
	}
	
	public void addUserCase(UserCase uc)
	{
		userCases.add(uc);
		ids.add(uc.id);
	}
	
	public void addConnection(Connection c)
	{
		connections.add(c);
	}
	
	public List<Actor> getActors()
	{
		return actors;
	}
	
	public List<UserCase> getUserCases()
	{
		return userCases;
	}
	
	public List<Connection> getConnections()
	{
		return connections;
	}
	

}
