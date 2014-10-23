package TestUserCase;

import static org.junit.Assert.*;

import org.junit.Test;

import UseCase.Connection;

public class ConnectionTest {

	@Test
	public void CrearConnectionTest() 
	{
		Connection c = new Connection("type","idFrom","idTo");
		boolean b1 = c.getType().equals("type");
		boolean b2 = c.getidFrom().equals("idFrom");
		boolean b3 = c.getidTo().equals("idTo");
		
		assertTrue("Test de creación conexion", b1 && b2 && b3);
	}

}
