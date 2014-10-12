package DiagramCase;
import java.util.ArrayList;
import java.util.List;

import UseCase.Actor;
import UseCase.Connection;
import UseCase.UserCase;


public class ClassDiagram {

	
	List<Clase> Clases;
	List<Connection> Conexiones;
	
	public ClassDiagram()
	{
		Clases = new ArrayList<Clase>();
	    Conexiones = new ArrayList<Connection>();
		
	}
	
	public void addClass(Clase c)
	{
		Clases.add(c);		
	}
	
	public void addConnections(Connection c)
	{
		Conexiones.add(c);		
	}
	
	public List<Connection> getConnections()
	{
		return Conexiones;	
	}
	
	public List<Clase> getClasses()
	{
		return Clases;	
	}
}
