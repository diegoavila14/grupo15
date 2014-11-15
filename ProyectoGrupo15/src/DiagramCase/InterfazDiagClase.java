package DiagramCase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Conector.ConnectLine;
import Conector.ConnectorContainer;
import Conector.ConnectorPropertiesPanel;
import Conector.JConnector;
import EditorDiagramClass.Contenedor;
import EditorDiagramClass.Flecha;
import EditorDiagramClass.Union;
import UseCase.Connection;
import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;
import javax.swing.DebugGraphics;

	public class InterfazDiagClase extends JScrollPane {
	
	ClassDiagram cd;

	List<Cuadro> Bloques;
	List<Union> Uniones;
	List<Integer> CantConexiones;
	Cuadro cu;
	Contenedor con;
	int lineArrow;
	
	public InterfazDiagClase(ClassDiagram cdd) {
		

		cd = cdd;
		lineArrow = 10;
		placeComponents();


	}
	
	private void placeComponents() {
		
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		Bloques = new ArrayList<Cuadro>();
		Uniones = new ArrayList<Union>();
		CantConexiones = new ArrayList<Integer>();
	
		placeClasses();
		
		placeConnections();
		
		con = new Contenedor(Uniones);
		
		for(int i = 0 ; i< Bloques.size(); i++){
			con.add(Bloques.get(i));
		}
		setViewportView(con);
		con.setLayout(null);
		
	}
	
	private void placeClasses(){
						
		java.util.List<Clase> list = cd.getClasses();
		
		for (int i = 0; i < list.size(); i++)
		{
			
			cu = new Cuadro();
			
			Clase c = list.get(i);
			
		
			cu.Nombre.setText(c.nombre);
			cu.ID = c.id;
			cu.setBounds(300,50,250,500);
			add(cu);
			java.util.List<Atributos> list2= c.getAtt();
			
			
			for (int j = 0; j < list2.size(); j++)
			{
				Atributos a = list2.get(j);
			
				
				cu.Atributos.setText(cu.Atributos.getText()+ a.visibilidad + a.nombre + ":" + a.tipo );				
				if(j+1 != list2.size()){
				cu.Atributos.setText(cu.Atributos.getText() + "\n");	
				}
			}
			
			java.util.List<Metodos> list3= c.getMethods();
		
			
			for (int j = 0; j < list3.size(); j++)
			{
				Metodos m = list3.get(j);
				
				cu.Metodos.setText(cu.Metodos.getText()+ m.visibilidad + m.nombre + "(");
				
				java.util.List<Parametros> list4= m.getParam();
			
	
				
				for (int k = 0; k < list4.size(); k++)
				{
					Parametros p = list4.get(j);
					
					
					cu.Metodos.setText(cu.Metodos.getText()+ p.nombre + ":" +  p.tipo );
					if(k+1 != list4.size()){
						cu.Atributos.setText(cu.Atributos.getText() + ", ");	
						}
					
				
			
				}
				
				cu.Metodos.setText(cu.Metodos.getText()+ ")" + ":" + m.retorno);
				if(j+1 != list3.size()){
					cu.Atributos.setText(cu.Atributos.getText() + "\n");	
					}
			}
			
						
			ActionListener MenuListener = new ActionListener() {
				 public void actionPerformed(ActionEvent e) 
					{
					 if (e.getActionCommand().equals("Crear Nota"))
					    {
						   Note n = new Note();
						   String nota =  "";
						    Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
					        nota = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
						   
						 //  n.getTextoNota().setText(nota);
						   
						   add(n);
						   
					    }
					}
				};
				
			cu.mntmCrearNota.addActionListener(MenuListener);
			
			
			Bloques.add(cu);
			CantConexiones.add(0);
		}
		}
	
	private void placeConnections()
	{
		Bloques.get(0).setBounds(10, 100, 200, 150);
		Bloques.get(1).setBounds(300, 10, 200, 150);
		int s = 0;
		int d = 0;
		Cuadro c1 = null;
		Cuadro c2 = null;
		//Itero para recoger todas las conexiones
		java.util.List<Connection> list = cd.getConnections();
		for (int i = 0; i < list.size(); i++)
		{
			Connection c = list.get(i);
			
			for(int j = 0; j<Bloques.size(); j++){  // primer cuadro
				if(Bloques.get(j).getID().equals(c.getidFrom())){
					c1 = Bloques.get(j);
					s = CantConexiones.get(j);
					CantConexiones.set(j, s+20);
					break;
				}
			}
			for(int j = 0; j<Bloques.size(); j++){  // segundo cuadro
				if(Bloques.get(j).getID().equals(c.getidTo())){
					c2 = Bloques.get(j);
					d = CantConexiones.get(j);
					CantConexiones.set(j, d+20);
					break;
				}
			}
		
			switch(c.getType()){
				case "dependency":
					lineArrow = Flecha.LINE_ARROW_DEPENDENCY;
					break;
				case "association":
					lineArrow = Flecha.LINE_ARROW_ASSOCIATION;
					break;
				case "aggregation":
					lineArrow = Flecha.LINE_ARROW_AGGREGATION;
					break;
				case "composition":
					lineArrow = Flecha.LINE_ARROW_COMPOSITION;
					break;
				case "inheritance":
					lineArrow = Flecha.LINE_ARROW_INHERITANCE;
					break;
			} // end switch
			
			Uniones.add(new Union(c1, c2, lineArrow,s,d));
			
			
		} // end for i
		
		
		
	}
	
	public Contenedor getCon() {
		return con;
	}
	
	public void setCon(Contenedor con) {
		this.con = con;
	}
	
	
	
	
	
	
}