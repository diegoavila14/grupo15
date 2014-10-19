package UseCase;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class ModoGraficoManager extends JFrame {

	private JPanel contentPane;
	JButton PNGButton;
	Diagram d;

	public ModoGraficoManager(Diagram D) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		this.d = D;
		setUserCases();
	}
	
	public void setUserCases()
	{
		int lastAprox = 0; //Para hacer la separación de los bloques de acuerdo a su largo
		java.util.List<UserCase> list = d.getUserCases();
		
		for (int i = 0; i < 5/*list.size()*/; i++)
		{
			UserCase uc = list.get(i);
			Entity entity = new Entity();
			entity.setLabel(uc.name);
			Double n = (uc.name.length()*6.54) + 20; // Para ver el tamaño de los bloques
			int aprox = n.intValue();
			entity.setBounds(lastAprox,300,aprox,70);
			lastAprox += aprox + 20;
			add(entity);
		}	
		System.out.println("WTF!");
	}

}
