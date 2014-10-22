package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import DiagramCase.Atributos;

public class AtributoTest {

	@Test
	public void CrearAtributoTest(){
	
		Atributos a = new Atributos("+", "String", "MOV");
		boolean testVisibility = a.getVisibilidad().equals("+");
		boolean testType = a.getTipo().equals("String");
		boolean testName = a.getNombre().equals("MOV");
		
		assertTrue("Testing creation...", testVisibility && testType && testName);
		
		
	}

}
