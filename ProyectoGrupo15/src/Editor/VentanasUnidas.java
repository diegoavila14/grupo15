package Editor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.util.regex.*;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import DiagramCase.ClassDiagram;
import DiagramCase.Compilador;
import DiagramCase.InterfazDiagClase;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class VentanasUnidas extends JFrame {

	private static JPanel contentPane;
	public JSeparator separator;
	public static File archivo;
	public InterfazEditorText iet;
	public JButton Compilar;
	public static InterfazDiagClase ddc;
	public JButton PNGButton;
	public JButton btnVerErrores;
	
	public VentanasUnidas(File f) {
		archivo = f;
		initialize();
	}
	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1260, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[400px,grow][][400px,grow]", "[grow][]"));
		
		separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(separator, "cell 1 0 1 2,grow");
		
		Compilar = new JButton("Compilar");
		Compilar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				ObtenerXML(iet.editorPane.getText());
				PNGButton.setVisible(true);
				validate();
			}
		});
		contentPane.add(Compilar, "flowx,cell 0 1,alignx center");
		
		iet = new InterfazEditorText();
		contentPane.add(iet, "cell 0 0,grow");
		PNGButton = new JButton("PNG");
		
		ActionListener PNGButtonListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (e.getSource() == PNGButton){
				
				 {
				BufferedImage image = new BufferedImage(ddc.getJp().getWidth(), ddc.getJp().getHeight(), BufferedImage.TYPE_INT_RGB);
					
				ddc.getJp().paint(image.getGraphics());
				File file = new File("diagclase.png");
				try {
					ImageIO.write(image, "png", file);
				} catch (IOException e1) {
				
					e1.printStackTrace();
				}
				 }
				 
			} 
			}
		};
		
		PNGButton.addActionListener(PNGButtonListener);
		PNGButton.setVisible(false);
		contentPane.add(PNGButton,"cell 2 1,alignx center");
		
		btnVerErrores = new JButton("Ver Errores");
		btnVerErrores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				VerErrores(iet.editorPane.getText());
				
				
			}
		});
		contentPane.add(btnVerErrores, "cell 0 1");
		
		setVisible(true);
	}
	
	 public static Document convertStringToDocument(String xmlStr) { // metodo que pasa un string a un Document
	        
		 	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	        DocumentBuilder builder;  
	   
	        try 
	        {  
	            builder = factory.newDocumentBuilder();  
	            InputSource is = new InputSource();
	            is.setCharacterStream(new StringReader(xmlStr));
	            Document doc = builder.parse(is);
	            return doc;
	            
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } 
	        return null;
	    }
	 
	 public static void ObtenerXML(String s){
		 Document doc;
		 try {
		
				doc = convertStringToDocument(s);
				
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
		
					Compilador comp = new Compilador(doc);
					ClassDiagram classdiagram = comp.getClassdiagram();
					if(ddc != null)
					contentPane.remove(ddc);
					
					ddc = new InterfazDiagClase(classdiagram);
						
					contentPane.add(ddc, "cell 2 0,grow");
					
				}
				
			} 
			
			catch (Exception e) 
			{
				String mensajeError = "Error en el formato del archivo XML" + "\nInténtelo denuevo con otro archivo";
				JOptionPane.showMessageDialog(null,mensajeError,"Parsing Error",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				
			}
		 
	 }
	 
	 public static void VerErrores(String s){
		 
		 Boolean inicio = VerInicio(s);
		 if(inicio == true){ // ver si el nodo raiz esta correcto o su terminacion
			 Boolean Clases = VerClases(s);
			 
			 if(Clases==true){
				 
			 }
			 
			 else{
				 JOptionPane.showMessageDialog(null,"Existe un error en la creacion de clases"); 
			 }
		 }
		 else{
			 JOptionPane.showMessageDialog(null,"Existe un error en el nodo raiz o en su terminacion"); 
		 }
	 }
	 
	 public static Boolean VerInicio(String s){
		 
		 s = s.replace("\t", "");
		 s = s.replace("\n", "");

		 Pattern p = Pattern.compile("^<ClassDiagram name\\s*=\\s*\"[a-zA-Z0-9 ]+\">.*</ClassDiagram>$");
		 Matcher mat = p.matcher(s);
		 if (mat.matches()) {
	         return true;
	     } else {
	         return false;
	     }
	 }
	 
	 public static Boolean VerClases(String s){
		 
		 s = s.replace("\t", "");
		 s = s.replace("\n", "");
		 System.out.print(s);
		 Pattern p = Pattern.compile("^<ClassDiagram name\\s*=\\s*\"[a-zA-Z0-9 ]+\">(<class id\\s*=\\s*\"[a-zA-Z0-9]+\"\\s*name\\s*=\\s*\"[a-zA-Z0-9]+\">(.*)</class>)+.*</ClassDiagram>$");
		 Matcher mat = p.matcher(s);
		 if (mat.matches()) {
	         return true;
	     } else {
	         return false;
	     }
	 }
}
