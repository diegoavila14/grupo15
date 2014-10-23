package TestUserCase;

import static org.junit.Assert.*;

import org.junit.Test;

import UseCase.Actor;
import UseCase.Connection;
import UseCase.Diagram;
import UseCase.UserCase;

public class DiagramTest {

	@Test
	public void nametest() 
	{
		Diagram d = new Diagram("name");
		boolean b = d.name.equals("name");
		assertTrue("Test de creación de diagrama", b);
	}
	
	@Test
	public void addActortest()
	{
		Diagram d = new Diagram("name");
		Actor a1 = new Actor("type","id","name");
		d.addActor(a1);
		Actor a2 = d.getActors().get(0);
		boolean b = a2.equals(a1);
		assertTrue("Test de agregar actor al diagrama", b);
	}
	
	@Test
	public void addConnectionTest()
	{
		Diagram d = new Diagram("name");
		Connection c1 = new Connection("type","idFrom","idTo");
		d.addConnection(c1);
		Connection c2 = d.getConnections().get(0);
		boolean b = c2.equals(c1);
		assertTrue("Test de agregar conexión al diagrama", b);
	}
	
	@Test
	public void addUseCaseTest()
	{
		Diagram d = new Diagram("name");
		UserCase uc1 = new UserCase("id","name");
		d.addUserCase(uc1);
		UserCase uc2 = d.getUserCases().get(0);
		boolean b = uc1.equals(uc2);
		assertTrue("Test de agregar user case al diagrama", b);
	}

}
