package TestingDiagramClass;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import EditorDiagramClass.VentanasUnidas;

public class VentanasUnidasTest {

	@Test
	public void convertStringToDocumenttest() throws ParserConfigurationException, SAXException, IOException { //Como no podemos comparar dos documentos probamos con su contenido
		final String UTF8_BOM = "\uFEFF";
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse("Diagclases.xml");
		File f = new File("Diagclases.xml");
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f),"UTF-8"));
		String Dato = "";
		String XML = "";
		while((Dato = br.readLine())!=null){ 
		
			if(Dato.contains("visibility")){
			
				String[] Error = Dato.split("visibility");
			
				if(Error[1].contains("Â")){
					String[] Error2 = Error[1].split("Â");
			
					XML = XML + Error[0] + "visibility" + Error2[0] + Error2[1] + "\n";
					
				}
				
				
				else{
				
				XML = XML  + Error[0] + "visibility" + Error[1] + "\n";
				}
			}
			else{
			XML = XML + Dato + "\n";
			}
		}
		if (XML.startsWith(UTF8_BOM)) 
		XML = XML.substring(1);
		
		
		Document prueba = VentanasUnidas.convertStringToDocument(XML);
		
		assertEquals("Test ver si pasa un string correctamente a document", doc.getAttributes() , prueba.getAttributes());
		assertEquals("Test ver si pasa un string correctamente a document", doc.getXmlEncoding() , prueba.getXmlEncoding());
		
		fr.close();
		br.close();
	}

	
	// El metodo ObtenerXML que posee no lo vamos a testear porque ocupa parte de la intefaz y el metodo que testeamos antes.
	// Lo mismo para el metodo exportXML, pues debe leer lo que contiene el editor y para testearlo tendriamos que correr todo el programa.
	
}
