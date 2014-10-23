package TestingDiagramClass;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import DiagramCase.XMLClassDiagram;

public class XMLClassDiagramTest {

	@Test
	public void CreateXMLClassDiagramTest() throws SAXException, IOException, ParserConfigurationException { // Como la clase usa el metodo leer vamos a chequear que el documento que lee es el mismo que uno le entrega.
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse("Diagclases.xml");
		
		XMLClassDiagram xcd = new XMLClassDiagram(doc);
		
		assertEquals("Test leer xml", xcd.getD(), doc);
		
	}
	@Test
	public void getChildIndexTest() throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse("Diagclases.xml");
		Element rootElement = doc.getDocumentElement();	
		
		NodeList classes = rootElement.getElementsByTagName("class");
		
		Node clase = classes.item(0);
		
		NodeList att = clase.getChildNodes();
		
		Node atri = XMLClassDiagram.getChildIndex(att,0);
		
		assertEquals("Test obtener el index del hijo", "attributes" , atri.getNodeName());
		
	}
	@Test
	public void getListLengthTest() throws ParserConfigurationException, SAXException, IOException // Test de que calcula bien al cantidad de un NodeList
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse("Diagclases.xml");
		Element rootElement = doc.getDocumentElement();	
		int valor = 0;
		NodeList classes = rootElement.getElementsByTagName("class");
		
		Node clase = classes.item(0);
		
		NodeList att = clase.getChildNodes();
		
		Node atri = XMLClassDiagram.getChildIndex(att,0);
		NodeList listatt = atri.getChildNodes();
		
		valor = XMLClassDiagram.getListLength(listatt);
		assertEquals("Test obtener el index del hijo", 3 , valor);
		
	}

}
