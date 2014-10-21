package UseCase;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EventObject;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import pEventsUtil.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class ModoTextoManager extends JFrame
{
	JButton PNGButton;
	Diagram d;
	JTextArea jtaPr;
	JTextArea jtaSec;
	JTextArea jtaCon;
	JTextArea jtaUC;
	// Se crea el evento
	//public static pEvent ClickEvent;
	
	public ModoTextoManager(Diagram diagram)
	{
		//super("Diagrama De Casos de Uso");
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
		
		jtaPr = new JTextArea();
		jtaPr.setEditable(false);
		jtaPr.setBounds(35,80,250,381);
		
		JLabel l2 = new JLabel("Actores secundarios");
		l2.setFont(new Font("Courier New", Font.ITALIC, 15));
		l2.setBounds(830,50,250,35);
		getContentPane().add(l2);
		
		jtaSec = new JTextArea();
		jtaSec.setEditable(false);
		jtaSec.setBounds(830,80,250,381);
		
		//Itero para recoger todos los actores
		java.util.List<Actor> list = d.getActors();
		for (int i = 0; i < list.size(); i++)
		{
			Actor a = list.get(i);
			String temp = a.id+" "+a.name+"\n";
			if (a.type.equals("primary"))
			{
				jtaPr.append(temp);				
			}
			else
			{
				jtaSec.append(temp);				
			}
			
		}
		getContentPane().add(jtaPr);
		getContentPane().add(jtaSec);
	}
	
	private void placeUserCases()
	{
		JLabel l = new JLabel("Casos de Uso");
		l.setFont(new Font("Courier New", Font.ITALIC, 15));
		l.setBounds(300,50,250,35);
		getContentPane().add(l);
		
		jtaUC = new JTextArea();
		jtaUC.setEditable(false);
		jtaUC.setBounds(300,80,250,381);
		
		//Itero para recoger todos los Casos de Uso
		java.util.List<UserCase> list = d.getUserCases();
		//int lastAprox = 0; //Para hacer la separación de los bloques de acuerdo a su largo
		for (int i = 0; i < list.size(); i++)
		{
			UserCase uc = list.get(i);
			String temp = uc.id+" "+uc.name+"\n";
			jtaUC.append(temp);
			
			//Entity entity = new Entity();
			//entity.setLabel(uc.name);
			//Double n = (uc.name.length()*6.54) + 20; // Para ver el tamaño de los bloques
			//int aprox = n.intValue();
			//entity.setBounds(lastAprox,600,aprox,70);
			//lastAprox += aprox + 20;
			//getContentPane().add(entity);
			
		}
		getContentPane().add(jtaUC);
	}
	
	private void placeConnections()
	{
		JLabel l = new JLabel("Conexiones");
		l.setFont(new Font("Courier New", Font.ITALIC, 15));
		l.setBounds(565,50,250,35);
		getContentPane().add(l);
		
		jtaCon = new JTextArea();
		jtaCon.setEditable(false);
		jtaCon.setBounds(565,80,250,381);	
		
		//Itero para recoger todas las conexiones
		java.util.List<Connection> list = d.getConnections();
		for (int i = 0; i < list.size(); i++)
		{
			Connection c = list.get(i);
			String temp = "("+c.idFrom+","+c.idTo+") "+c.type+"\n";
			jtaCon.append(temp);
			
		}
		getContentPane().add(jtaCon);
		
		
		//Constructor del DesignMode
		
		//Agragar Actor primario
		JButton bAdd1 = new JButton("Agregar");
		bAdd1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EntityBuilderWindow ab = new EntityBuilderWindow(d.ids);
				ab.id = 0;
				ab.setVisible(true);
				ab.setTitle("Crear Actor Primario");
			}
		});
		bAdd1.setBounds(95, 472, 115, 23);
		getContentPane().add(bAdd1);
		
		//Agregar User Case
		JButton bAdd2 = new JButton("Agregar");
		bAdd2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EntityBuilderWindow ab = new EntityBuilderWindow(d.ids);
				ab.id = 2;
				ab.setVisible(true);
				ab.setTitle("Crear Caso de Uso");
			}
		});
		bAdd2.setBounds(364, 472, 115, 23);
		getContentPane().add(bAdd2);
		
		JButton bAdd3 = new JButton("Agregar");
		bAdd3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ConnectionBuilderWindow cbw = new ConnectionBuilderWindow(d.ids);
				cbw.setVisible(true);
			}
		});
		bAdd3.setBounds(616, 472, 115, 23);
		getContentPane().add(bAdd3);
		
		JButton bAdd4 = new JButton("Agregar");
		bAdd4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EntityBuilderWindow ab = new EntityBuilderWindow(d.ids);
				ab.setTitle("Crear Actor Secundario");
				ab.id = 1;
				ab.setVisible(true);
			}
		});
		bAdd4.setBounds(887, 472, 115, 23);
		getContentPane().add(bAdd4);
		
		JButton btnModoGrfico = new JButton("Modo gr\u00E1fico");
		btnModoGrfico.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) 
			{
				ModoGraficoManager mgm = new ModoGraficoManager(d);
				mgm.setVisible(true);
				setVisible(false);
			}
		});
		btnModoGrfico.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModoGrfico.setBounds(1069, 11, 115, 35);
		getContentPane().add(btnModoGrfico);
	}
	
	public void addActor(Actor a, boolean primary)
	{
	    d.addActor(a);
	    if (primary)
	    {
	    	jtaPr.append(a.id+" "+a.name+"\n");
	    }
	    else
	    {
	    	jtaSec.append(a.id+" "+a.name+"\n");
	    }
	}
	
	public void addUserCase(UserCase uc)
	{
		d.addUserCase(uc);
		jtaUC.append(uc.id+" "+uc.name);
	}
}
