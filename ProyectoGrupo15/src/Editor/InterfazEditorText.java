package Editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.DefaultEditorKit;
import javax.swing.undo.UndoManager;

import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.swing.undo.UndoableEdit;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import DiagramCase.Compilador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DropMode;

public class InterfazEditorText extends JPanel {

	private JPanel contentPane;
	public static JTextPane editorPane;
	public JScrollPane scrollPane;
	public JButton Compilar;
	
	public JMenuItem mntmCopiar;
	public JMenuItem mntmCortar;
	public JMenuItem mntmPegar;
	public static JMenuItem mbItemUndo;
	
		
		public InterfazEditorText() {
		
			initialize();
		}
		
		
		private void initialize() {
			
			setBounds(100, 100, 828, 360);
			
			setLayout(new MigLayout("", "[grow]", "[grow]"));
			
			
			editorPane = new JTextPane(){
			    public boolean getScrollableTracksViewportWidth()
			    {
			        return getUI().getPreferredSize(this).width 
			            <= getParent().getSize().width;
			    }
			};
	
		
		
			scrollPane = new JScrollPane(editorPane);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
			add(scrollPane, "cell 0 0,grow");
			
			
			setMenu();
			
			setVisible(true);
		}
		
	     private static void setMenu(){
			
			ActionMap acciones = editorPane.getActionMap();
			
			Action accionCopiar = acciones.get(DefaultEditorKit.copyAction);
			Action accionPegar = acciones.get(DefaultEditorKit.pasteAction);
			Action accionCortar = acciones.get(DefaultEditorKit.cutAction);
	
			accionCopiar.putValue(Action.NAME, "Copiar");
			accionCopiar.putValue(
			   Action.ACCELERATOR_KEY,
			   KeyStroke.getAWTKeyStroke('C', Event.CTRL_MASK)); 

			accionCortar.putValue(Action.NAME, "Cortar");
			accionCortar.putValue(
			   Action.ACCELERATOR_KEY,
			   KeyStroke.getAWTKeyStroke('X', Event.CTRL_MASK)); 

			accionPegar.putValue(Action.NAME, "Pegar");
			accionPegar.putValue(
			   Action.ACCELERATOR_KEY,
			   KeyStroke.getAWTKeyStroke('V', Event.CTRL_MASK));
			
		}
		
	    

	

		 
	}


