package DiagramCase;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Component;

import javax.swing.Box;

import java.awt.Color;

import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import net.miginfocom.swing.MigLayout;


public class Cuadro extends JPanel {
	
	JLabel Nombre;
	JSeparator SeparadorAtributos;
	JSeparator SeparadorMetodos;
	JTextArea Atributos;
	JTextArea Metodos;
	
	public Cuadro() {
		
		setForeground(Color.LIGHT_GRAY);
		setBorder(new LineBorder(new Color(0, 0, 0), 3));
		setBackground(Color.LIGHT_GRAY);
		setLayout(new MigLayout("", "[grow]", "[][][][][]"));
		
		Nombre = new JLabel("");
		Nombre.setHorizontalAlignment(SwingConstants.CENTER);
		Nombre.setForeground(new Color(0, 0, 0));
		Nombre.setBackground(Color.LIGHT_GRAY);
		add(Nombre, "cell 0 0,alignx center");
		
		SeparadorAtributos = new JSeparator();
		SeparadorAtributos.setForeground(Color.BLACK);
		SeparadorAtributos.setBackground(Color.BLACK);
		
		add(SeparadorAtributos, "cell 0 1,growx");
		
		Atributos = new JTextArea();
		Atributos.setBackground(Color.LIGHT_GRAY);
		Atributos.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		add(Atributos, "cell 0 2,growx,aligny top");
		
		SeparadorMetodos = new JSeparator();
		SeparadorMetodos.setForeground(Color.BLACK);
		SeparadorMetodos.setBackground(Color.BLACK);
		add(SeparadorMetodos, "cell 0 3,growx");
		
		Metodos = new JTextArea();
		Metodos.setBackground(Color.LIGHT_GRAY);
		Metodos.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		add(Metodos, "cell 0 4,grow");

	}
}
