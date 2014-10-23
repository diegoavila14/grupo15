package TestUserCase;

import static org.junit.Assert.*;

import org.junit.Test;

import UseCase.Actor;

public class ActorTest {

	@Test
	public void CrearActorTest() 
	{
		Actor a = new Actor("type","id","name");
		boolean b1 = a.getType().equals("type");
		boolean b2 = a.getID().equals("id");
		boolean b3 = a.getName().equals("name");
		
		assertTrue("Test de creación actor", b1 && b2 && b3);
	}

}
