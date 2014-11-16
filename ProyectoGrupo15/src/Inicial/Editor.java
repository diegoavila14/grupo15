package Inicial;

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
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import UseCase.Manager;
import DiagramCase.Compilador;
import EditorDiagramClass.*;


public class Editor {

	public static final String UTF8_BOM = "\uFEFF";
	static String ruta = "";
	public Editor()
	{	
	
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
		FileReader fr = null;
	    BufferedReader br = null;
		int contador = 0;
		while(correcto == false)
		{ // Para chequear que sea un xml
			
			if (contador > 0)
			{
				String mensajeError = "Extensión de archivo incorrecta" + "\nInténtelo denuevo con un archivo XML";
				JOptionPane.showMessageDialog(null,mensajeError,"Parsing Error",JOptionPane.ERROR_MESSAGE);
				Import();
			}	
			teclado = new Scanner(System.in);
			JFileChooser fc = new JFileChooser(ruta);
			int respuesta = fc.showOpenDialog(null);
			if (respuesta == JFileChooser.APPROVE_OPTION) {
				
			
			archivo = fc.getSelectedFile();
			ruta = archivo.getPath();
			//File archivo = new File("C:/Users/Diego Avila/Desktop/REPOteMP/prueba.xml");
			if(getExtension(archivo).equals("xml"))
			{
				correcto = true;
			}
			contador++;
			}
			else if (respuesta == JFileChooser.CANCEL_OPTION || respuesta == JFileChooser.ERROR_OPTION) {
				break;
		    }
			
		}	
			try 
			{
				
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(archivo);
				
				//Lo siguiente es para detectar si el xml es de UserCase o de Clases
				String rootN = doc.getFirstChild().getNodeName();
				int indicador; //0=UserCaseDiagram 1 =ClassDiagram
				if (rootN.equals("UseCaseDiagram"))
				{
					System.out.println("User Case Diagram");
					Manager m = new Manager(false,archivo);
				}
				else //ClassDiagram
				{					
					try{
						fr = new FileReader(archivo);
						br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo),"UTF-8"));
						VentanasUnidas ie = new VentanasUnidas(archivo);
						
						String Dato = "";
						String XML = "";
						while((Dato = br.readLine())!=null){  // habia un simbolo extraño cuando leia un "-" en la visibilidad, asi que lo elimine con estos if
						
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
						if (XML.startsWith(UTF8_BOM))  // Arregla problemas con la compilacion del xml
						XML = XML.substring(1);
						
						ie.iet.editorPane.setText(XML);
						
						fr.close();
						br.close();
					}
					
					catch(IOException e )
					{
						String mensajeError = "Error en el formato del archivo XML" + "\nInténtelo denuevo con otro archivo";
						JOptionPane.showMessageDialog(null,mensajeError,"Parsing Error",JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
						Import();
					}
				}
			}
			catch (IOException | ParserConfigurationException | SAXException e) 
			{
				String mensajeError = "Error en el formato del archivo XML" + "\nInténtelo denuevo con otro archivo";
				JOptionPane.showMessageDialog(null,mensajeError,"Parsing Error",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return;
			}
			teclado.close();
		}

		
			
	
	
	
}
