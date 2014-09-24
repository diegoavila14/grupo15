package UseCase;

import java.util.*;

public class Diagram 
{
	List<Actor> actors;
	List<UserCase> userCases;
	List<Connection> connections;
	
	public Diagram()
	{
		actors = new ArrayList<Actor>();
		userCases = new ArrayList<UserCase>();
		connections = new ArrayList<Connection>();
	}
	
	public void addActor(Actor a)
	{
		actors.add(a);		
	}
	
	public void addUserCase(UserCase uc)
	{
		userCases.add(uc);		
	}
	
	public void addConnection(Connection c)
	{
		connections.add(c);
	}

}
