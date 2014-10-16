package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import DiagramCase.Atributos;
import DiagramCase.Clase;
import DiagramCase.Metodos;
import DiagramCase.Parametros;

public class ClaseTest {

	@Test
	public void CrearClaseTest() {
		
		Clase a = new Clase("cl", "MOV");
		boolean testID = a.getId().equals("cl");
		boolean testName = a.getNombre().equals("MOV");
		
		assertTrue("Testing creation...", testID && testName);
		
		
		
	}
	@Test
	public void addAttTest(){
		
		Atributos a = new Atributos("+", "String", "MOV");
		Clase c = new Clase("cl", "MOV");
		c.addAtt(a);
		assertEquals("Test de agregar Parametro", a, c.getAtt().get(0));
		
	}
	
	@Test
	public void addMethodTest(){
		
		Metodos m = new Metodos("MOV", "String", "+");
		Clase c = new Clase("cl", "MOV");
		c.addMethod(m);
		assertEquals("Test de agregar Parametro", m, c.getMethods().get(0));
		
	}
	
	
}
