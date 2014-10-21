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
	
	public InterfazDiagClase(ClassDiagram cdd) {
		

		cd = cdd;
	
		placeComponents();


	}
	private void placeComponents() {
		
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
	
		jp = new JPanel();
		jp.setLayout(new MigLayout("", "[1px,grow][500px][250px]", "[1px][330px]"));
		setViewportView(jp);
		placeClasses();
		placeConnections();
		
		
			
		
	}
	
	private void placeClasses(){
		JLabel l = new JLabel("Clases");
		l.setFont(new Font("Courier New", Font.ITALIC, 15));
		jp.add(l, "cell 1 1,alignx left,aligny top");
		
		JTextArea jta = new JTextArea();
		
		java.util.List<Clase> list = cd.getClasses();
		
		for (int i = 0; i < list.size(); i++)
		{
			jta.append("Clase: \n ");
			Clase c = list.get(i);
			String temp = c.id+" "+c.nombre+"\n";
			jta.append(temp);
			
			java.util.List<Atributos> list2= c.getAtt();
			jta.append("Atributos: \n ");
			for (int j = 0; j < list2.size(); j++)
			{
				Atributos a = list2.get(j);
				String temp2 = a.nombre + " " + a.tipo + " visibilidad: " + a.visibilidad + "\n";
				jta.append(temp2);
			}
			
			java.util.List<Metodos> list3= c.getMethods();
			jta.append("Metodos: \n ");
			for (int j = 0; j < list3.size(); j++)
			{
				Metodos m = list3.get(j);
				String temp2 = m.nombre + " retorno: " + m.retorno + " visibilidad: " + m.visibilidad + "\n";
				jta.append(temp2);
				
				java.util.List<Parametros> list4= m.getParam();
				jta.append("Parametros: \n ");
				
				for (int k = 0; k < list4.size(); k++)
				{
					Parametros p = list4.get(j);
					String temp3 = p.nombre + " " + p.tipo + "\n";
					jta.append(temp3);
				}
			}
			
		}
		jp.add(jta, "cell 1 1,grow");
	}
	
	private void placeConnections()
	{
		JLabel l = new JLabel("Conexiones");
		l.setFont(new Font("Courier New", Font.ITALIC, 15));
		jp.add(l, "cell 2 1,growx,aligny top");
		
		JTextArea jta = new JTextArea();
		
		//Itero para recoger todas las conexiones
		java.util.List<Connection> list = cd.getConnections();
		for (int i = 0; i < list.size(); i++)
		{
			Connection c = list.get(i);
			String temp = "("+c.getidFrom()+","+c.getidTo()+") "+c.getType()+"\n";
			jta.append(temp);
			
		}
		jp.add(jta, "cell 2 1,growx,aligny center");
	}
	
}
