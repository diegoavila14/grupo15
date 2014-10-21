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

import net.miginfocom.swing.MigLayout;
import UseCase.Actor;
import UseCase.Connection;

public class DiagramaDeClase extends JFrame {

	JButton PNGButton;
	ClassDiagram cd;
	Cuadro cu ;
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
		
		setLayout(new MigLayout("", "[grow]", "[][][][][]"));
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
				String temp2 = m.nombre + " retorno: " + m.retorno + " visibilidad: " + m.visibilidad + "\n";
				
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
			add(cu);
		}
	
	
	}
	
	private void placeConnections()
	{
		
		
	
		
		//Itero para recoger todas las conexiones
		java.util.List<Connection> list = cd.getConnections();
		for (int i = 0; i < list.size(); i++)
		{
			Connection c = list.get(i);
			String temp = "("+c.getidFrom()+","+c.getidTo()+") "+c.getType()+"\n";
	
			
		}
	
	}
	
}
