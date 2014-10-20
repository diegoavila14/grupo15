package UseCase;

import java.awt.BorderLayout;
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
	}
	
	public void setUserCases()
	{
		int lastAprox = 0; //Para hacer la separación de los bloques de acuerdo a su largo
		java.util.List<UserCase> list = d.getUserCases();
		
		for (int i = 0; i < list.size(); i++)
		{
			UserCase uc = list.get(i);
			Entity entity = new Entity();
			entity.setLabel(uc.name);
			Double n = (uc.name.length()*6.54) + 20; // Para ver el tamaño de los bloques
			int aprox = n.intValue();
			entity.setBounds(lastAprox,300,aprox,70);
			lastAprox += aprox + 20;
			map.put(uc.id, entity);
			getContentPane().add(entity);
		}	
		System.out.println("WTF!");
	}
}
