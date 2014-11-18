package UseCase;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EntityBuilderWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	public int id;
	
	List<String> ids; //Lista de todos los ids

	public EntityBuilderWindow(List<String> list) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 156);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ids = list;
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(21, 35, 46, 14);
		contentPane.add(lblNombre);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(94, 32, 226, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) 
			{
//				String idAux = textFieldID.getText();
				if (id == 0)
				{
					Manager.ClickEvent.fireEvent(0,textFieldName.getText());
				}
				else if (id == 1)
				{
					Manager.ClickEvent.fireEvent(1,textFieldName.getText());
				}
				else 
				{
					Manager.ClickEvent.fireEvent(2,textFieldName.getText());
				}
				dispose();
//				else
//				{
//					String mensajeError = "ID ya utilizado" + "\nInténtelo denuevo con otro ID";
//					JOptionPane.showMessageDialog(null,mensajeError,"Parsing Error",JOptionPane.ERROR_MESSAGE);
//					//e.printStackTrace();
//				}
			}
		});
		btnNewButton.setBounds(231, 83, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		btnCancelar.setBounds(54, 83, 95, 23);
		contentPane.add(btnCancelar);
	}
	
	public boolean checkID(String s)
	{
		if (ids.contains(s))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
