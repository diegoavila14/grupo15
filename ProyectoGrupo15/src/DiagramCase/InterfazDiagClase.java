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
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import EditorDiagramClass.Contenedor;
import EditorDiagramClass.Flecha;
import EditorDiagramClass.Union;
import UseCase.Connection;

import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;
import javax.swing.DebugGraphics;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;

import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;


	public class InterfazDiagClase extends JScrollPane {
	
	ClassDiagram cd;

	List<Cuadro> Bloques;
	List<Union> Uniones;
	List<Note> Notas;
	public List<Note> getNotas() {
		return Notas;
	}

	public void setNotas(List<Note> notas) {
		Notas = notas;
	}

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
		
		getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener(){  // evento que soluciona error con drag and drop al mover la barra
			 public void adjustmentValueChanged(AdjustmentEvent ae) {

				   for(int i = 0 ; i< Bloques.size(); i++){
						
				    	if(Bloques.get(i).getPos().equals("")){
				    		
				    	}
				    	else{
						con.remove(Bloques.get(i));
						con.add(Bloques.get(i), Bloques.get(i).getPos());
				    	}
						
					}	
				   for(int i = 0 ; i< Notas.size(); i++){
						
				    	if(Notas.get(i).getPos().equals("")){
				    		
				    	}
				    	else{
						con.remove(Notas.get(i));
						con.add(Notas.get(i), Notas.get(i).getPos());
				    	}
						
					}

			 }
			
		});
		getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){  // evento que soluciona error con drag and drop al mover la barra
			 public void adjustmentValueChanged(AdjustmentEvent ae) {

				   for(int i = 0 ; i< Bloques.size(); i++){
						
				    	if(Bloques.get(i).getPos().equals("")){
				    		
				    	}
				    	else{
						con.remove(Bloques.get(i));
						con.add(Bloques.get(i), Bloques.get(i).getPos());
				    	}
						
					}
				   for(int i = 0 ; i< Notas.size(); i++){
						
				    	if(Notas.get(i).getPos().equals("")){
				    		
				    	}
				    	else{
						con.remove(Notas.get(i));
						con.add(Notas.get(i), Notas.get(i).getPos());
				    	}
						
					}

			 }
			
		});

		Notas = new ArrayList<Note>();
		Bloques = new ArrayList<Cuadro>();
		Uniones = new ArrayList<Union>();
		CantConexiones = new ArrayList<Integer>();
	
		placeClasses();
		
		placeConnections();
		
		con = new Contenedor(Uniones);
		con.addHierarchyBoundsListener(new HierarchyBoundsAdapter() { // evento que soluciona problema con drag and drop al cambiar el tamaño de la ventana
			@Override
			public void ancestorResized(HierarchyEvent e) {
				
				   for(int i = 0 ; i< Bloques.size(); i++){
						
				    	if(Bloques.get(i).getPos().equals("")){
				    		
				    	}
				    	else{
						con.remove(Bloques.get(i));
						con.add(Bloques.get(i), Bloques.get(i).getPos());
				    	}
						
					}
				    for(int i = 0 ; i< Notas.size(); i++){
						
				    	if(Notas.get(i).getPos().equals("")){
				    		
				    	}
				    	else{
						con.remove(Notas.get(i));
						con.add(Notas.get(i), Notas.get(i).getPos());
				    	}
						
					}

			}
		});
		con.setAutoscrolls(true);
		setViewportView(con);
		con.setLayout(new MigLayout("wrap 2, gap 100\r\n", "[nogrid]"));
		for(int i = 0 ; i< Bloques.size(); i++){
			con.add(Bloques.get(i), Bloques.get(i).getPos());
			
	
		}
	
		
		
	}
	
	
	public List<Cuadro> getBloques() {
		return Bloques;
	}

	public void setBloques(List<Cuadro> bloques) {
		Bloques = bloques;
	}

	private void placeClasses(){
						
		java.util.List<Clase> list = cd.getClasses();
		
		for (int i = 0; i < list.size(); i++)
		{
			
			cu = new Cuadro(InterfazDiagClase.this);
			
			Clase c = list.get(i);
			
		
			cu.Nombre.setText(c.nombre);
			cu.ID = c.id;
		
			add(cu);
			java.util.List<Atributos> list2= c.getAtt();
			
			cu.Atributos.setText("<html>");
			for (int j = 0; j < list2.size(); j++)
			{
				Atributos a = list2.get(j);
			
				
				cu.Atributos.setText(cu.Atributos.getText()+ a.visibilidad + a.nombre + ":" + a.tipo );				
				if(j+1 != list2.size()){
				cu.Atributos.setText(cu.Atributos.getText() + "<br>");	
				}
			}
			
			java.util.List<Metodos> list3= c.getMethods();
		
			cu.Metodos.setText("<html>");
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
						cu.Metodos.setText(cu.Metodos.getText() + ", ");	
						}
					
				
			
				}
				
				cu.Metodos.setText(cu.Metodos.getText()+ ")" + ":" + m.retorno);
				if(j+1 != list3.size()){
					cu.Metodos.setText(cu.Metodos.getText() + "<br>");	
					}
			}
			
						
			cu.mntmCrearNota.addActionListener(new ActionListener() { // agregar notas
				@Override
				public void actionPerformed(ActionEvent e) {
					
						Note n = new Note();
					    
					    n.setVisible(true);
					 
					    n.setDes(cu);
					    Notas.add(n);
					    placeNote(n);
					    con.add(n, "pos 100 0");
						
					    for(int i = 0 ; i< Bloques.size(); i++){
							
					    	if(Bloques.get(i).getPos().equals("")){
					    		
					    	}
					    	else{
							con.remove(Bloques.get(i));
							con.add(Bloques.get(i), Bloques.get(i).getPos());
					    	}
							
						}
					    for(int i = 0 ; i< Notas.size(); i++){
							
					    	if(Notas.get(i).getPos().equals("")){
					    		
					    	}
					    	else{
							con.remove(Notas.get(i));
							con.add(Notas.get(i), Notas.get(i).getPos());
					    	}
							
						}
					    con.validate();
				}
			});
				
		
			
		   
			Bloques.add(cu);
			CantConexiones.add(0);
		}
		}
	
	private void placeConnections()
	{
		
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
	
	private void placeNote(Note n){
		
			for(int j = 0 ; j < Bloques.size(); j++){
	    	if(Bloques.get(j).getID().equals(n.getDes().getID())){
	    		 int d = CantConexiones.get(j);
	    		 Uniones.add(new Union(n, n.getDes(), Flecha.LINE_ARROW_DEPENDENCY,0,d));
	    		 CantConexiones.set(j, d+20);
	    		 break;
	    	}
			}
	}
	
	public Contenedor getCon() {
		return con;
	}
	
	public void setCon(Contenedor con) {
		this.con = con;
	}
	
	

}