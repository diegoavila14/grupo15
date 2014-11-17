package UseCase;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;

import UseCaseEditor.Contenedor;
import UseCaseEditor.Union;

import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.DebugGraphics;
import javax.swing.JScrollPane;

public class ModoGraficoManager extends JFrame {

	private JPanel contentPane;
	final JButton btnPng;
	final JButton btnVolver;
	Diagram d;
	List<Union> Uniones;
	List<Integer> CantConexiones;
	int lineArrow;
	Contenedor con;
	List<Entity> entidades;
	
	Map<String, Entity> map; //diccionario para linkear id con entidad

	public ModoGraficoManager(Diagram D) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Manager.ClickEvent.fireEvent(4);
				dispose();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVolver.setBounds(1084, 11, 90, 42);
		contentPane.add(btnVolver);
		
		btnPng = new JButton("PNG");
		btnPng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GuardadorWindow gw = new GuardadorWindow(false);
				gw.setVisible(true);
			}
		});
		btnPng.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPng.setBounds(1097, 608, 77, 42);
		contentPane.add(btnPng);
		
		map = new HashMap<String, Entity>();
				
		
		this.d = D;
		
		entidades = new ArrayList<Entity>();
		Uniones = new ArrayList<Union>();
		lineArrow = 10;
		CantConexiones = new ArrayList<Integer>();
		
		setUserCases();
		setActors();
		
		con = new Contenedor(Uniones);
		
		for(int i = 0 ; i< entidades.size(); i++){
			con.add(entidades.get(i));
		}
		//setViewportView(con);
		con.setLayout(null);
	}
	
	public void getPNG(String nFile)
	{
		btnPng.setVisible(false);
		btnVolver.setVisible(false);
		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		
		paint(image.getGraphics());
		File file = new File(nFile+".png");
		try 
		{
			ImageIO.write(image, "png", file);
		} 
		catch (IOException e1) 
		{				
			e1.printStackTrace();
		}
		btnPng.setVisible(true);
		btnVolver.setVisible(true);
	}
	
	public void setUserCases()
	{
		int auxY = 10;
		java.util.List<UserCase> list = d.getUserCases();
		
		for (int i = 0; i < list.size(); i++)
		{
			UserCase uc = list.get(i);
			Entity entity = new Entity();
			entity.setLabel(uc.name);
			Double n = (uc.name.length()*6.54) + 20; // Para ver el tamaño de los bloques
			int aprox = n.intValue();
			entity.setBounds(450,auxY,aprox,70);
			auxY += 90;
			map.put(uc.id, entity);
			entidades.add(entity);
			getContentPane().add(entity);
		}
	}
	
	public void setActors()
	{
		int auxP = 10;
		int auxS = 10;
		java.util.List<Actor> list = d.getActors();
		for (int i = 0; i < list.size(); i++)
		{
			Actor a = list.get(i);
			Entity entity = new Entity();
			entity.setBackground(Color.cyan);
			entity.setLabel(a.name);
			Double n = (a.name.length()*6.54) + 20; // Para ver el tamaño de los bloques
			int aprox = n.intValue();
			if (a.type.equals("primary"))
			{
				entity.setBounds(15,auxP,aprox,70);
				auxP += 90;
			}
			else 
			{
				entity.setBounds(850,auxS,aprox,70);
				auxS += 90;
			}
			map.put(a.id, entity);
			entidades.add(entity);
			getContentPane().add(entity);
		}
		
	}
}
