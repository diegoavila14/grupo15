package UseCase;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.JFrame;

public class ExportManager extends JFrame 
{
	JButton PNGButton;
	Diagram d;
	
	public ExportManager(Diagram diagram)
	{
		super("Diagrama De Casos de Uso");
		setSize(1200, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.d = diagram;
		placeComponents();
		setVisible(true);
		this.setResizable(false);
	}
	
	private void placeComponents() 
	{
		
		getContentPane().setLayout(null);
		
		PNGButton = new JButton("PNG");
		PNGButton.setBounds(1100, 630, 90, 35);
		getContentPane().add(PNGButton);
		
		placeActors();
		placeUserCases();
		placeConnections();
		
		ActionListener PNGButtonListener = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (e.getSource() == PNGButton)
				{
					BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
						
					paint(image.getGraphics());
					File file = new File("diagUC.png");
					try 
					{
						ImageIO.write(image, "png", file);
					} 
					catch (IOException e1) 
					{				
						e1.printStackTrace();
					}			 
				
				} 
			}
		};
		
		PNGButton.addActionListener(PNGButtonListener);
	}
	
	private void placeActors()
	{
		JLabel l = new JLabel("Actores primarios");
		l.setFont(new Font("Courier New", Font.ITALIC, 15));
		l.setBounds(35,50,250,35);
		getContentPane().add(l);
		
		JTextArea jta = new JTextArea();
		jta.setBounds(35,80,250,250);
		
		JLabel l2 = new JLabel("Actores secundarios");
		l2.setFont(new Font("Courier New", Font.ITALIC, 15));
		l2.setBounds(830,50,250,35);
		getContentPane().add(l2);
		
		JTextArea jta2 = new JTextArea();
		jta2.setBounds(830,80,250,250);
		
		//Itero para recoger todos los actores
		java.util.List<Actor> list = d.getActors();
		for (int i = 0; i < list.size(); i++)
		{
			Actor a = list.get(i);
			String temp = a.id+" "+a.name+"\n";
			if (a.type.equals("primary"))
			{
				jta.append(temp);				
			}
			else
			{
				jta2.append(temp);				
			}
			
		}
		getContentPane().add(jta);
		getContentPane().add(jta2);
	}
	
	private void placeUserCases()
	{
		JLabel l = new JLabel("Casos de Uso");
		l.setFont(new Font("Courier New", Font.ITALIC, 15));
		l.setBounds(300,50,250,35);
		getContentPane().add(l);
		
		JTextArea jta = new JTextArea();
		jta.setBounds(300,80,250,250);
		
		//Itero para recoger todos los Casos de Uso
		java.util.List<UserCase> list = d.getUserCases();
		int lastAprox = 0; //Para hacer la separación de los bloques de acuerdo a su largo
		for (int i = 0; i < list.size(); i++)
		{
			UserCase uc = list.get(i);
			String temp = uc.id+" "+uc.name+"\n";
			jta.append(temp);
			
			Entity entity = new Entity();
			entity.setLabel(uc.name);
			Double n = (uc.name.length()*6.54) + 20; // Para ver el tamaño de los bloques
			int aprox = n.intValue();
			entity.setBounds(lastAprox,411,aprox,70);
			lastAprox += aprox + 20;
			getContentPane().add(entity);
			System.out.println("largo: " + n);
			
		}
		getContentPane().add(jta);
	}
	
	private void placeConnections()
	{
		JLabel l = new JLabel("Conexiones");
		l.setFont(new Font("Courier New", Font.ITALIC, 15));
		l.setBounds(565,50,250,35);
		getContentPane().add(l);
		
		JTextArea jta = new JTextArea();
		jta.setBounds(565,80,250,250);	
		
		//Itero para recoger todas las conexiones
		java.util.List<Connection> list = d.getConnections();
		for (int i = 0; i < list.size(); i++)
		{
			Connection c = list.get(i);
			String temp = "("+c.idFrom+","+c.idTo+") "+c.type+"\n";
			jta.append(temp);
			
		}
		getContentPane().add(jta);
	}
}
