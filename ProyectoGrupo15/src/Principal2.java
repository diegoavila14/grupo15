import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Editor.VentanasUnidas;
import UseCase.Manager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;


public class Principal2 {

	private JFrame frmEditorDeDiagramas;
	private Editor editor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal2 window = new Principal2();
					window.frmEditorDeDiagramas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditorDeDiagramas = new JFrame();
		frmEditorDeDiagramas.setTitle("Editor de Diagramas UML");
		frmEditorDeDiagramas.setBounds(100, 100, 450, 300);
		frmEditorDeDiagramas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		placeMenu();
		editor = new Editor();
	}
	
	private void placeMenu() //Sin funcionalidad, En prueba.
	{
		final File f = new File("zzzzzzzz.txt"); //archivo vacio
		JMenuBar jmb = new JMenuBar();
		JMenu jmFile = new JMenu("File");
	    JMenuItem jmiImport = new JMenuItem("Import");
	    JMenu jmiNew = new JMenu("Create new");
	    JMenuItem jmiUC = new JMenuItem("Use Case Diagram");
	    JMenuItem jmiD = new JMenuItem("Class Diagram");
	    jmiNew.add(jmiUC);
	    jmiNew.add(jmiD);
	    
	    JMenuItem jmiPNG = new JMenuItem("as PNG");
	    JMenuItem jmiXML = new JMenuItem("as XML");
	    JMenuItem jmiExit = new JMenuItem("Exit");
	    jmFile.add(jmiNew);
	    jmFile.add(jmiImport);
	    jmFile.addSeparator();
	    jmFile.add(jmiExit);
	    jmb.add(jmFile);
	    
	    JMenu jmOptions = new JMenu("Options");
	    JMenu a = new JMenu("A");
	    JMenuItem b = new JMenuItem("B");
	    JMenuItem c = new JMenuItem("C");
	    JMenuItem d = new JMenuItem("D");
	    a.add(b);
	    a.add(c);
	    a.add(d);
	    jmOptions.add(a);
	    jmb.add(jmOptions);
	    
	    frmEditorDeDiagramas.setJMenuBar(jmb);
	    frmEditorDeDiagramas.setVisible(true);
	    
	    ActionListener MenuListener = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String comStr = e.getActionCommand();
			    if (e.getActionCommand().equals("Import"))
			    {
			    	System.out.println("Import funciona");
			    	editor.Import();
			    }
			    else if (e.getActionCommand().equals("Use Case Diagram"))
			    {
			    	Manager m = new Manager(true,f);
			    }
			    else if (e.getActionCommand().equals("Class Diagram"))
			    {
			    	VentanasUnidas ie = new VentanasUnidas(f);
			    }
			    else if (e.getActionCommand().equals("Exit"))
			    {
			    	System.exit(0);
			    }
			}
		};
		
		jmiImport.addActionListener(MenuListener);
	    jmiNew.addActionListener(MenuListener);
	    jmiExit.addActionListener(MenuListener);
	    jmiUC.addActionListener(MenuListener);
	    jmiD.addActionListener(MenuListener);
	    b.addActionListener(MenuListener);
	    c.addActionListener(MenuListener);
	    d.addActionListener(MenuListener);  
	    
	}

}
