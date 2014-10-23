package TestUserCase;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import UseCase.XmlManager;

public class XmlManagerTest {

	@Test
	public void NotXmlTest() 
	{
		File f = new File("prueba.xml");
		//Este archivo de prueba tiene datos conocidos
		//Se puede revisar el archivo prueba.xml que esta en la carpeta principal del proyecto
		//name = Content Management System
		//Actor[0].name = "Administrator"
		//Connection[3].type = "extend"
		//UserCase[5].name = "Create a New Editorial Blog Account"
		
		XmlManager xm = new XmlManager(f);
		boolean b1 = xm.diagram.name.equals("Content Management System");
		boolean b2 = xm.diagram.getActors().get(0).getName().equals("Administrator");
		boolean b3 = xm.diagram.getConnections().get(3).getType().equals("extend");
		boolean b4 = xm.diagram.getUserCases().get(5).getName().equals("Create a New Editorial Blog Account");
		
		assertTrue("Test de verificación de lectura correcta de xml", b1 && b2 && b3 && b4);
	}

}
