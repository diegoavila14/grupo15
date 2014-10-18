package Editor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class InterfazEditorText extends JFrame {

	private JPanel contentPane;

	public JMenuBar menuBar;
	public JMenu mnArchivo;
	public JEditorPane editorPane;
		
		public InterfazEditorText() {
			initialize();
		}
		
		
		private void initialize() {
			
			setBounds(100, 100, 461, 306);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setLayout(new MigLayout("", "[grow][]", "[grow][]"));
			
			editorPane = new JEditorPane();
			
			getContentPane().add(editorPane, "cell 0 0,grow");
			
			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
			mnArchivo = new JMenu("Archivo");
			menuBar.add(mnArchivo);
			
			setVisible(true);
		}

	}


