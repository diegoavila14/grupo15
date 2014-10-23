package TestingDiagramClass;

import static org.junit.Assert.*;

import org.junit.Test;

import UseCase.Connection;
import DiagramCase.Atributos;
import DiagramCase.Clase;
import DiagramCase.ClassDiagram;

public class ClassDiagramText {

	@Test
	public void addClassTest(){
		
		ClassDiagram cd = new ClassDiagram();
		Clase c = new Clase("cl", "MOV");
		cd.addClass(c);
		assertEquals("Test de agregar Clase", c, cd.getClasses().get(0));
		
	}
	
	@Test
	public void addConnectionTest(){
		
		ClassDiagram cd = new ClassDiagram();
		Connection c = new Connection("basic", "c1", "c2");
		cd.addConnections(c);
		assertEquals("Test de agregar Conexion", c, cd.getConnections().get(0));
		
	}

}
