package TestingDiagramClass;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import Inicial.Editor;

public class EditorTest {

	@Test
	public void getExtensiontest() {
	
		
		
		File f = new File("Diagclases.xml");
		String s = Editor.getExtension(f);
		String correcto = "xml";
		
		assertEquals("Probar el metodo obtenedor de extensiones", s , correcto);
		
	}

	// el metodo import es un poco dificil de testear unitariamente, ya que ocupa muchas cosas, ademas de ser el inicio para la creacion de la interfaz, es como un sub main
}
