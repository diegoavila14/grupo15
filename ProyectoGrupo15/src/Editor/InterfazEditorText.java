package Editor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import DiagramCase.Compilador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InterfazEditorText extends JFrame {

	private JPanel contentPane;

	public JMenuBar menuBar;
	public JMenu mnArchivo;
	public JEditorPane editorPane;
	public JScrollPane scrollPane;
	public JButton Compilar;
	public static File archivo;
		
		public InterfazEditorText(File f) {
			archivo = f;
			initialize();
		}
		
		
		private void initialize() {
			
			setBounds(100, 100, 800, 360);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setLayout(new MigLayout("", "[grow]", "[grow][]"));
			
			editorPane = new JEditorPane();
				
			scrollPane = new JScrollPane(editorPane);
			
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			getContentPane().add(scrollPane, "cell 0 0,grow");
			
			Compilar = new JButton("Compilar");
			Compilar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					ObtenerXML(editorPane.getText());
				}
			});
			getContentPane().add(Compilar, "cell 0 1,alignx center");
			
			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
			mnArchivo = new JMenu("Archivo");
			menuBar.add(mnArchivo);
			
			setVisible(true);
		}
		
		 public static Document convertStringToDocument(String xmlStr) { // metodo que pasa un string a un Document
		        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
		        DocumentBuilder builder;  
		        try 
		        {  
		            builder = factory.newDocumentBuilder();  
		            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
		            return doc;
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        } 
		        return null;
		    }
		 
		 public static void ObtenerXML(String s){
			 Document doc;
			 try {
					System.out.print(s);
					if(s.startsWith("<")){ //Esto es por un error extraño que hace aparecer un ? cuando leo el xml, curiosamente solo pasa en el diagrama de clase.
						doc = convertStringToDocument(s);
				   
					}
					else{
						doc = convertStringToDocument(s.substring(1, s.length()));
					}
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
				
				catch (Exception e) 
				{
					String mensajeError = "Error en el formato del archivo XML" + "\nInténtelo denuevo con otro archivo";
					JOptionPane.showMessageDialog(null,mensajeError,"Parsing Error",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					
				}
			 
		 }

	}


