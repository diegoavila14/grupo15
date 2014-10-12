package DiagramCase;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import UseCase.Actor;
import UseCase.Connection;

public class DiagramaDeClase extends JFrame {

	JButton PNGButton;
	ClassDiagram cd;
	
	public DiagramaDeClase(ClassDiagram cdd){
		
		super("Diagrama De Clase");
		cd = cdd;
		setSize(1200, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		placeComponents();
		setVisible(true);
		this.setResizable(false);
	}
	
	private void placeComponents() {
		
		setLayout(null);
		placeClasses();
		placeConnections();
		PNGButton = new JButton("PNG");
		PNGButton.setBounds(1100, 630, 90, 35);
		add(PNGButton);
		
		ActionListener PNGButtonListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (e.getSource() == PNGButton){
				
				 {
				BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
					
				paint(image.getGraphics());
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
	}
	
	private void placeClasses(){
		
		JLabel l = new JLabel("Clases");
		l.setFont(new Font("Courier New", Font.ITALIC, 15));
		l.setBounds(35,50,250,35);
		add(l);
		
		JTextArea jta = new JTextArea();
		jta.setBounds(35,80,500,500);
		
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
		add(jta);
	}
	
	private void placeConnections()
	{
		JLabel l = new JLabel("Conexiones");
		l.setFont(new Font("Courier New", Font.ITALIC, 15));
		l.setBounds(565,50,250,35);
		add(l);
		
		JTextArea jta = new JTextArea();
		jta.setBounds(565,80,250,250);	
		
		//Itero para recoger todas las conexiones
		java.util.List<Connection> list = cd.getConnections();
		for (int i = 0; i < list.size(); i++)
		{
			Connection c = list.get(i);
			String temp = "("+c.getidFrom()+","+c.getidTo()+") "+c.getType()+"\n";
			jta.append(temp);
			
		}
		add(jta);
	}
	
}
