import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class Editor {

	public static void main(String[] args) 
	{
		Import();		
	}

	
	public static String getExtension(File f) // obtener extension del archivo
	{
	    String ext = null;
	    String s = f.getName();
	    int i = s.lastIndexOf('.');

	    if (i > 0 &&  i < s.length() - 1) {
	        ext = s.substring(i+1).toLowerCase();
	    }
	    return ext;
	}
	
	public static void Import()
	{
		boolean correcto = false;
		File archivo = null;
		Scanner teclado = null;
		while(correcto == false){ // Para checkear que sea un xml
		
		teclado = new Scanner(System.in);
		JFileChooser fc = new JFileChooser();
		int respuesta = fc.showOpenDialog(null);
		archivo = fc.getSelectedFile();
		//File archivo = new File("C:/Users/Diego Avila/Desktop/REPOteMP/prueba.xml");
		if(getExtension(archivo).equals("xml")){
			correcto = true;
		}
		
		
		}
		
		//leer xml con dom parser
		
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(archivo);
			
			//Lo siguiente es para detectar si el xml es de UserCase o de Clases
			String rootN = doc.getFirstChild().getNodeName();
			int indicador; //0=UserCaseDiagram 1 =ClassDiagram
			if (rootN.equals("UseCaseDiagram"))
			{
				System.out.println("User Case Diagram");
				indicador = 0;
			}
			else
			{
				System.out.println("Class Diagram");
				indicador = 1;
			}
			
			if (indicador == 0) //UserCaseDiagram
			{
				UseCase.Manager manager = new UseCase.Manager(archivo);				
			}
			else //ClassDiagram
			{
				Compilador comp = new Compilador(archivo);
			}
			
			
		
		
			
			
		
			
		} 
		
		catch (IOException | ParserConfigurationException | SAXException e) 
		{
			String mensajeError = "Error en el formato del archivo XML" + "\nInténtelo denuevo con otro archivo";
			JOptionPane.showMessageDialog(null,mensajeError,"Parsing Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			Import();
		}
		
		teclado.close();		
	}
}
