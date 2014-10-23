package TestingDiagramClass;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import DiagramCase.Compilador;

public class CompiladorTest {

	@Test
	public void CreateCompiladorText() throws ParserConfigurationException, SAXException, IOException { // probar si compila bien el compilador al crearlo
		
	
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse("Diagclases.xml");
		
		Compilador c = new Compilador(doc);
		
		assertEquals("Test leer xml", c.getXcd().getD(), doc);
		
	}

}
