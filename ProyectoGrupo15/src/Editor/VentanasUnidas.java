package Editor;

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
	            e.printStackTrace();  
	        } 
	        return null;
	    }
	 
	 public static void ObtenerXML(String s){
		 Document doc;
		 try {
		
				if(s.startsWith("<")){ //Esto es por un error extraño que hace aparecer un ? cuando leo el xml, curiosamente solo pasa en el diagrama de clase.
					doc = convertStringToDocument(s);
			   
				}
				else{
					doc = convertStringToDocument(s.substring(1, s.length()));
				}
				
		
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
		    JMenuItem jmiClose = new JMenuItem("Close");
		    JMenu jmiSave = new JMenu("Export");
		    JMenuItem jmiPNG = new JMenuItem("as PNG");
		    JMenuItem jmiXML = new JMenuItem("as XML");
		    jmiSave.add(jmiPNG);
		    jmiSave.add(jmiXML);
		    JMenuItem jmiExit = new JMenuItem("Exit");
		    jmFile.add(jmiImport);
		    jmFile.add(jmiClose);
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
			    		//Lanzar evento de eliminacion
				    }
				    else if (e.getActionCommand().equals("as XML"))
				    {
				    	//Exportar a xml		    	
				    }
				}
			};
			
			jmiImport.addActionListener(MenuListener);
		    jmiClose.addActionListener(MenuListener);
		    jmiSave.addActionListener(MenuListener);
		    jmiExit.addActionListener(MenuListener);
			
		    
		    
		}
	 
	 
}
