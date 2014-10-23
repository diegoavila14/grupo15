package TestingDiagramClass;

import static org.junit.Assert.*;


import org.junit.Test;

import DiagramCase.Atributos;
import DiagramCase.Metodos;
import DiagramCase.Parametros;

public class MetodosTest {

	@Test
	public void CreateMethodTest() {
		
		Metodos m = new Metodos("MOV", "String", "+");
		boolean testVisibility = m.getVisibilidad().equals("+");
		boolean testReturn = m.getRetorno().equals("String");
		boolean testName = m.getNombre().equals("MOV");
		
		assertTrue("Testing creation...", testName && testReturn && testVisibility);
		
	}

	@Test
	public void addParamTest(){
		
		Parametros p = new Parametros("ADD", "int");
		Metodos m = new Metodos("MOV", "String", "+");
		m.addParam(p);
		assertEquals("Test de agregar Parametro", p, m.getParam().get(0));
		
	}
}
