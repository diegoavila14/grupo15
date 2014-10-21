package UseCase;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JScrollBar;

public class ModoGraficoManager extends JFrame {

	private JPanel contentPane;
	JButton PNGButton;
	Diagram d;
	
	Map<String, Entity> map; //diccionario para linkear id con entidad

	public ModoGraficoManager(Diagram D) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		map = new HashMap<String, Entity>();
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Manager.ClickEvent.fireEvent(4);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(1058, 11, 116, 45);
		contentPane.add(btnNewButton);
		
		this.d = D;
		setUserCases();
		setActors();
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
			getContentPane().add(entity);
		}
		
	}
}
