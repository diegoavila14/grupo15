package UseCase;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.Enumeration;
import javax.swing.AbstractButton;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class ConnectionBuilderWindow extends JFrame {

	private JPanel contentPane;

	public ConnectionBuilderWindow(List<String> ids) 
	{
		setTitle("Crear Conexi\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 442, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final ButtonGroup group = new ButtonGroup();
		JRadioButton rBasic = new JRadioButton("basic");
		rBasic.setSelected(true);
		rBasic.setBounds(6, 53, 109, 23);
		group.add(rBasic);
		contentPane.add(rBasic);
		
		JRadioButton rExtend = new JRadioButton("extend");
		rExtend.setBounds(6, 79, 109, 23);
		group.add(rExtend);
		contentPane.add(rExtend);
		
		JRadioButton rInclude = new JRadioButton("include");
		rInclude.setBounds(6, 105, 109, 23);
		group.add(rInclude);
		contentPane.add(rInclude);
		
		JRadioButton rIsA = new JRadioButton("isa");
		rIsA.setBounds(6, 131, 109, 23);
		group.add(rIsA);
		contentPane.add(rIsA);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblType.setBounds(10, 32, 46, 14);
		contentPane.add(lblType);
		
		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setBounds(168, 33, 46, 14);
		contentPane.add(lblDesde);
		
		JLabel lblHacia = new JLabel("Hacia:");
		lblHacia.setBounds(168, 83, 46, 14);
		contentPane.add(lblHacia);
		
		String aux[] = new String[ids.size()]; 
		for (int i=0 ; i< ids.size() ; i++)
		{
			aux[i] = ids.get(i);
		}
		
		final JComboBox comboBox = new JComboBox(aux);
		comboBox.setBounds(229, 30, 73, 20);
		contentPane.add(comboBox);
		
		final JComboBox comboBox_1 = new JComboBox(aux);
		comboBox_1.setBounds(229, 80, 73, 20);
		contentPane.add(comboBox_1);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) 
				{
		            AbstractButton button = buttons.nextElement();

		            if (button.isSelected()) 
		            {
		                System.out.println(button.getText());
		                Connection c = new Connection(button.getText(),comboBox.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString());
		                Manager.ClickEvent.fireEvent(3,c);
		                dispose();
		                
		            }
				}
			}
		});
		btnAceptar.setBounds(305, 177, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		btnCancelar.setBounds(47, 177, 89, 23);
		contentPane.add(btnCancelar);
	}
}
