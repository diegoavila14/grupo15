package UseCase;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.EventObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import pEventsUtil.*;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModoTextoManager extends JFrame
{
	Diagram d;
	JTextArea jtaPr;
	JTextArea jtaSec;
	JTextArea jtaCon;
	JTextArea jtaUC;
	int idA;
	int idUC;
	
	// Se crea el evento
	//public static pEvent ClickEvent;
	
	public ModoTextoManager(Diagram diagram)
	{
		//super("Diagrama De Casos de Uso");
		setSize(1200, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.d = diagram;
		placeComponents();
		setVisible(true);
		this.setResizable(false);
		
		idA = 1;
		idUC = 1;
	}
	
	private void placeComponents() 
	{
		
		getContentPane().setLayout(null);
		
		
		placeActors();
		placeUserCases();
		placeConnections();
		placeMenu();
		
	}
	
	private void placeMenu() //Sin funcionalidad, En prueba.
	{
		JMenuBar jmb = new JMenuBar();
		JMenu jmFile = new JMenu("File");
	    JMenuItem jmiImport = new JMenuItem("Import");
	    JMenu jmiSave = new JMenu("Export");
	    JMenuItem jmiPNG = new JMenuItem("as PNG");
	    JMenuItem jmiXML = new JMenuItem("as XML");
	    jmiXML.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) 
	    	{
	    		//Manager.ClickEvent.fireEvent(5);
	    		try {
					exportXML();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    jmiSave.add(jmiPNG);
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
		    		//Lanzar evento de eliminacion
			    }
			    else if (e.getActionCommand().equals("as XML"))
			    {
			    	try {
						exportXML();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			    else if (e.getActionCommand().equals("as PNG"))
			    {
			    	exportPNG();
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
	    jmiPNG.addActionListener(MenuListener);
	    
		
	    
	    
	}
	
	private void placeActors()
	{
		JLabel l = new JLabel("Actores primarios");
		l.setFont(new Font("Courier New", Font.ITALIC, 15));
		l.setBounds(45,34,250,35);
		getContentPane().add(l);
		
		jtaPr = new JTextArea();
		jtaPr.setFont(new Font("Monospaced", Font.PLAIN, 14));
		jtaPr.setColumns(1);
		jtaPr.setEditable(false);
		jtaPr.setBounds(45,80,250,381);
		
		JLabel l2 = new JLabel("Actores secundarios");
		l2.setFont(new Font("Courier New", Font.ITALIC, 15));
		l2.setBounds(840,34,250,35);
		getContentPane().add(l2);
		
		jtaSec = new JTextArea();
		jtaSec.setEditable(false);
		jtaSec.setBounds(840,80,250,381);
		
		placeActors2();
		
//		//Itero para recoger todos los actores
//		java.util.List<Actor> list = d.getActors();
//		for (int i = 0; i < list.size(); i++)
//		{
//			Actor a = list.get(i);
//			String temp = a.id+" "+a.name+"\n";
//			if (a.type.equals("primary"))
//			{
//				jtaPr.append(temp);				
//			}
//			else
//			{
//				jtaSec.append(temp);				
//			}
//			
//		}
		getContentPane().add(jtaPr);
		getContentPane().add(jtaSec);
	}
	
	public void placeActors2()
	{
		jtaPr.setText("");
		jtaSec.setText("");
		//Itero para recoger todos los actores
		java.util.List<Actor> list = d.getActors();
		for (int i = 0; i < list.size(); i++)
		{
			Actor a = list.get(i);
			String temp = a.id+" "+a.name+"\n";
			if (a.type.equals("primary"))
			{
				jtaPr.append(temp);				
			}
			else
			{
				jtaSec.append(temp);				
			}
			
		}
	}
	
	private void placeUserCases()
	{
		JLabel l = new JLabel("Casos de Uso");
		l.setFont(new Font("Courier New", Font.ITALIC, 15));
		l.setBounds(310,34,250,35);
		getContentPane().add(l);
		
		jtaUC = new JTextArea();
		jtaUC.setEditable(false);
		jtaUC.setBounds(310,80,250,381);
		
		placeUserCases2();
		
		getContentPane().add(jtaUC);
	}
	
	public void placeUserCases2()
	{
		jtaUC.setText("");
		//Itero para recoger todos los Casos de Uso
		java.util.List<UserCase> list = d.getUserCases();
		//int lastAprox = 0; //Para hacer la separación de los bloques de acuerdo a su largo
		for (int i = 0; i < list.size(); i++)
		{
			UserCase uc = list.get(i);
			String temp = uc.id+" "+uc.name+"\n";
			jtaUC.append(temp);
			
		}
	}
	
	private void placeConnections()
	{
		JLabel l = new JLabel("Conexiones");
		l.setFont(new Font("Courier New", Font.ITALIC, 15));
		l.setBounds(575,34,250,35);
		getContentPane().add(l);
		
		jtaCon = new JTextArea();
		jtaCon.setEditable(false);
		jtaCon.setBounds(575,80,250,381);	
		
		placeConnections2();
		
		getContentPane().add(jtaCon);
		
		
		//Constructor del DesignMode
		
		//Agragar Actor primario
		JButton bAdd1 = new JButton("Agregar");
		bAdd1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EntityBuilderWindow ab = new EntityBuilderWindow(d.ids);
				ab.id = 0;
				ab.setVisible(true);
				ab.setTitle("Crear Actor Primario");
			}
		});
		bAdd1.setBounds(105, 472, 115, 23);
		getContentPane().add(bAdd1);
		
		//Agregar User Case
		JButton bAdd2 = new JButton("Agregar");
		bAdd2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EntityBuilderWindow ab = new EntityBuilderWindow(d.ids);
				ab.id = 2;
				ab.setVisible(true);
				ab.setTitle("Crear Caso de Uso");
			}
		});
		bAdd2.setBounds(374, 472, 115, 23);
		getContentPane().add(bAdd2);
		
		JButton bAdd3 = new JButton("Agregar");
		bAdd3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ConnectionBuilderWindow cbw = new ConnectionBuilderWindow(d.ids);
				cbw.setVisible(true);
			}
		});
		bAdd3.setBounds(626, 472, 115, 23);
		getContentPane().add(bAdd3);
		
		JButton bAdd4 = new JButton("Agregar");
		bAdd4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EntityBuilderWindow ab = new EntityBuilderWindow(d.ids);
				ab.setTitle("Crear Actor Secundario");
				ab.id = 1;
				ab.setVisible(true);
			}
		});
		bAdd4.setBounds(897, 472, 115, 23);
		getContentPane().add(bAdd4);
		
		JButton btnModoGrfico = new JButton("Modo gr\u00E1fico");
		btnModoGrfico.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) 
			{
				ModoGraficoManager mgm = new ModoGraficoManager(d);
				mgm.setVisible(true);
				setVisible(false);
			}
		});
		btnModoGrfico.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModoGrfico.setBounds(1069, 11, 115, 35);
		getContentPane().add(btnModoGrfico);
		
		JLabel lblIdName = new JLabel("id   nombre");
		lblIdName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIdName.setBounds(45, 66, 216, 14);
		getContentPane().add(lblIdName);
		
		JLabel lblIdName_2 = new JLabel(" id   nombre");
		lblIdName_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIdName_2.setBounds(305, 66, 216, 14);
		getContentPane().add(lblIdName_2);
		
		JLabel lbldesdehaciaTypeName = new JLabel("(desde,hacia)   tipo");
		lbldesdehaciaTypeName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbldesdehaciaTypeName.setBounds(570, 66, 216, 14);
		getContentPane().add(lbldesdehaciaTypeName);
		
		JLabel lblIdName_1 = new JLabel("id   nombre");
		lblIdName_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIdName_1.setBounds(840, 66, 216, 14);
		getContentPane().add(lblIdName_1);
		
		JButton btnEliminar = new JButton("Eliminar entidad");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EliminarEntidadWindow eew = new EliminarEntidadWindow(d.ids);
				eew.setVisible(true);
				
			}
		});
		btnEliminar.setBounds(493, 550, 172, 35);
		getContentPane().add(btnEliminar);
	}
	
	public void placeConnections2()
	{
		jtaCon.setText("");
		//Itero para recoger todas las conexiones
		java.util.List<Connection> list = d.getConnections();
		for (int i = 0; i < list.size(); i++)
		{
			Connection c = list.get(i);
			String temp = "("+c.idFrom+","+c.idTo+") "+c.type+"\n";
			jtaCon.append(temp);
			
		}
	}
	
	public void addActor(Actor a, boolean primary)
	{
	    d.addActor(a);
	    if (primary)
	    {
	    	jtaPr.append(a.id+" "+a.name+"\n");
	    }
	    else
	    {
	    	jtaSec.append(a.id+" "+a.name+"\n");
	    }
	}
	
	public void addUserCase(UserCase uc)
	{
		d.addUserCase(uc);
		jtaUC.append(uc.id+" "+uc.name+"\n");
	}
	
	public void addConnection(Connection c)
	{
		d.addConnection(c);
		jtaCon.append("("+c.idFrom+","+c.idTo+") "+c.type+"\n");
	}
	
	public void exportXML(/*String nFile*/) throws Exception
	{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document;
		DocumentBuilder builder = factory.newDocumentBuilder();
		document = builder.newDocument();
		
		//Main Node
		Element root = document.createElement("UseCaseDiagram");
		document.appendChild(root);
		Attr genderAttribute = document.createAttribute("name");
	    genderAttribute.setValue(d.name);
	    root.setAttributeNode(genderAttribute);
	    
	    Element ac = document.createElement("actors");
	    Element uc = document.createElement("usecases");
	    Element con = document.createElement("connections");
	    root.appendChild(ac);
	    root.appendChild(uc);
	    root.appendChild(con);
	    
	    for (int i = 0; i< d.actors.size(); i++)
	    {
	    	Actor a = d.actors.get(i);
	    	Element e = document.createElement("actor");
	    	//att type
	    	Attr att = document.createAttribute("type");
	    	att.setValue(a.type);
	    	e.setAttributeNode(att);
	    	//att id
	    	Attr att2 = document.createAttribute("id");
	    	att2.setValue(a.id);
	    	e.setAttributeNode(att2);
	    	//name
	    	Attr att3 = document.createAttribute("name");
	    	att3.setValue(a.name);
	    	e.setAttributeNode(att3);
	    	ac.appendChild(e);
	    }
	    
	    for (int i = 0; i< d.userCases.size(); i++)
	    {
	    	UserCase u = d.userCases.get(i);
	    	Element e = document.createElement("usecase");
	    	//att id
	    	Attr att = document.createAttribute("id");
	    	att.setValue(u.id);
	    	e.setAttributeNode(att);
	    	//att name
	    	Attr att2 = document.createAttribute("name");
	    	att2.setValue(u.name);
	    	e.setAttributeNode(att2);
	    	uc.appendChild(e);
	    }
	    for (int i = 0; i< d.connections.size(); i++)
	    {
	    	Connection c = d.connections.get(i);
	    	Element e = document.createElement("connection");
	    	//att id
	    	Attr att = document.createAttribute("type");
	    	att.setValue(c.type);
	    	e.setAttributeNode(att);
	    	//att name
	    	Attr att2 = document.createAttribute("from");
	    	att2.setValue(c.idFrom);
	    	e.setAttributeNode(att2);
	    	
	    	Attr att3 = document.createAttribute("to");
	    	att3.setValue(c.idTo);
	    	e.setAttributeNode(att3);
	    	    	
	    	con.appendChild(e);
	    }
	    
	    
	    // write the XML document to disk
	    try {

            // create DOMSource for source XML document
	        Source xmlSource = new DOMSource(document);

	        // create StreamResult for transformation result
	        JFileChooser c = new JFileChooser();
			int rVal = c.showSaveDialog(null);
			String name = c.getSelectedFile().getAbsolutePath() + ".xml";
			
			Result result = new StreamResult(new FileOutputStream(name));

	        // create TransformerFactory
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();

	        // create Transformer for transformation
	        Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty("indent", "yes");

	        // transform and deliver content to client
	        transformer.transform(xmlSource, result);
	        
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
	
	public void exportPNG(/*String nFile*/)
	{
		ModoGraficoManager mg = new ModoGraficoManager(d);
		mg.setVisible(true);
		setVisible(false);
		mg.getPNG();
	}
}
