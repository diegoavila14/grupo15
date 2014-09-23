import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class Editor {

	public static void main(String[] args) {
		
		boolean correcto = false;
		File archivo = null;
		Scanner teclado = null;
		while(correcto == false){ // Para checkear que sea un xml
		
		teclado = new Scanner(System.in);
		JFileChooser fc = new JFileChooser();
		int respuesta = fc.showOpenDialog(null);
		archivo = fc.getSelectedFile();
		if(getExtension(archivo).equals("xml")){
			correcto = true;
		}
			}
		
		//leer xml con dom parser
		
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(archivo);
			
			System.out.println(doc.getDocumentElement().getNodeName());
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						DiagClase window = new DiagClase();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			
						
			
		} 
		
		catch (IOException | ParserConfigurationException | SAXException e) {

			e.printStackTrace();
		}
		
		teclado.close();
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
}