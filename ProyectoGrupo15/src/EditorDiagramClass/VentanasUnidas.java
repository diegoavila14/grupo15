package EditorDiagramClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

public class VentanasUnidas extends JFrame {

	private static JPanel contentPane;
	public JSeparator separator;
	public static File archivo;
	public InterfazEditorText iet;
	public JButton Compilar;
	public static InterfazDiagClase ddc;
	public static JButton PNGButton;
	
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
		contentPane.add(Compilar, "cell 0 1,alignx center");
		
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
		placeMenu();
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
	        	String mensajeError = "Error en el formato del archivo XML" + "\nInténtelo denuevo con otro archivo";
				JOptionPane.showMessageDialog(null,mensajeError,"Parsing Error",JOptionPane.ERROR_MESSAGE);  
				e.printStackTrace();
	        } 
	        return null;
	    }
	 
	 public static void ObtenerXML(String s){
		 Document doc;
		 try {
		
				doc = convertStringToDocument(s);
			   
				
				
		
				Compilador comp = new Compilador(doc);
				ClassDiagram classdiagram = comp.getClassdiagram();
				if(ddc != null)
				contentPane.remove(ddc);
				
				ddc = new InterfazDiagClase(classdiagram);
					
				contentPane.add(ddc, "cell 2 0,grow");
				
				
			} 
			
			catch (Exception e) 
			{
				String mensajeError = "Error en el formato del archivo XML" + "\nInténtelo denuevo con otro archivo";
				JOptionPane.showMessageDialog(null,mensajeError,"Parsing Error",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				
			}
		 
	 }
	 private void placeMenu() //Sin funcionalidad, En prueba.
		{
			JMenuBar jmb = new JMenuBar();
			JMenu jmFile = new JMenu("File");
		    JMenuItem jmiImport = new JMenuItem("Import");
		    JMenu jmiSave = new JMenu("Export");
		    JMenuItem jmiXML = new JMenuItem("as XML");
		
		    jmiSave.add(jmiXML);
		    JMenuItem jmiExit = new JMenuItem("Exit");
		    jmFile.add(jmiImport);
		
		    jmFile.add(jmiSave);
		    jmFile.addSeparator();
		    jmFile.add(jmiExit);
		    jmb.add(jmFile);
		    
		    setJMenuBar(jmb);
		    setVisible(true);
		    
		    ActionListener MenuListener = new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					String comStr = e.getActionCommand();
				    if (e.getActionCommand().equals("Import"))
				    {
			    		Inicial.Editor ed = new Inicial.Editor();
			    		ed.Import();
			    		dispose();
			    	
				    }
				    else if (e.getActionCommand().equals("as XML"))
				    {
				    	try {
							exportXML();
						} catch (Exception e1) {
							
							String mensajeError = "Error en el formato del archivo XML" + "\nInténtelo denuevo con otro archivo";
							JOptionPane.showMessageDialog(null,mensajeError,"Parsing Error",JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}		    	
				    }
				    
				    else if (e.getActionCommand().equals("Exit"))
				    {
				    	System.exit(0);
				    }
				}
			};
			
			jmiImport.addActionListener(MenuListener);
		  
		    jmiSave.addActionListener(MenuListener);
		    jmiExit.addActionListener(MenuListener);
		    jmiXML.addActionListener(MenuListener);
		    jmiExit.addActionListener(MenuListener);
		    
		}
	
	 public void exportXML() throws Exception{ //Crea un xml de lo escrito en el jtextpane siempre y cuando este correcto y lo crea con el nombre DiagramClass.xml
		 
		  try {

	            // create DOMSource for source XML document
		        Source xmlSource = new DOMSource(convertStringToDocument(iet.editorPane.getText()));

		        // create StreamResult for transformation result
		        Result result = new StreamResult(new FileOutputStream("DiagramClass.xml"));

		        // create TransformerFactory
		        TransformerFactory transformerFactory = TransformerFactory.newInstance();

		        // create Transformer for transformation
		        Transformer transformer = transformerFactory.newTransformer();
		        transformer.setOutputProperty("indent", "yes");

		        // transform and deliver content to client
		        transformer.transform(xmlSource, result);
		        
		        JOptionPane.showMessageDialog(null,"Se ha creado exitosamente el XML con el nombre DiagramClass.xml");
		      }

		      // handle exception creating TransformerFactory
		      catch (TransformerFactoryConfigurationError factoryError) {
		        System.err.println("Error creating " + "TransformerFactory");
		        factoryError.printStackTrace();
		      }catch (TransformerException transformerError) {
		        System.err.println("Error transforming document");
		        transformerError.printStackTrace();
		      }    catch (IOException ioException) {
		        ioException.printStackTrace();
		      }
		 
	 }
	 
}
