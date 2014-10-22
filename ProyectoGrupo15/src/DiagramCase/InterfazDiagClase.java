package DiagramCase;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import UseCase.Connection;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;

	public class InterfazDiagClase extends JScrollPane {
	
	
	ClassDiagram cd;
	JPanel jp;
	public JPanel getJp() {
		return jp;
	}
	public void setJp(JPanel jp) {
		this.jp = jp;
	}


	Cuadro cu;
	
	public InterfazDiagClase(ClassDiagram cdd) {
		

		cd = cdd;
	
		placeComponents();


	}
	private void placeComponents() {
		
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
	
		jp = new JPanel();
		jp.setLayout(new MigLayout("", "[grow]", "[]"));
		setViewportView(jp);
		placeClasses();
		placeConnections();
		
		
			
		
	}
	
		private void placeClasses(){
						
		java.util.List<Clase> list = cd.getClasses();
		
		for (int i = 0; i < list.size(); i++)
		{
			
			cu = new Cuadro();
			
			Clase c = list.get(i);
			
		
			cu.Nombre.setText(c.nombre);
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
			
			jp.add(cu);
		}
		}
		
	
	private void placeConnections()
	{
		
	
		
		//Itero para recoger todas las conexiones
		java.util.List<Connection> list = cd.getConnections();
		for (int i = 0; i < list.size(); i++)
		{
			Connection c = list.get(i);
			
		}
	
	}
	
}