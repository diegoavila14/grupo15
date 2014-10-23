package TestingDiagramClass;

import static org.junit.Assert.*;

import org.junit.Test;

import DiagramCase.Atributos;
import DiagramCase.Parametros;

public class ParametroTest {

	@Test
	public void test() {

		Parametros p = new Parametros( "MOV","String");
		boolean testType = p.getTipo().equals("String");
		boolean testName = p.getNombre().equals("MOV");
		
		assertTrue("Testing creation...", testName && testType);
		
	}

}
