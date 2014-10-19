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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ActorBuilder extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;
	private JTextField textFieldName;
	public boolean primary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActorBuilder frame = new ActorBuilder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ActorBuilder() {
		setTitle("Crear Actor Primario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(21, 46, 30, 14);
		contentPane.add(lblId);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(94, 43, 226, 20);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(21, 85, 46, 14);
		contentPane.add(lblNombre);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(94, 82, 226, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) 
			{
				if (primary)
				{
					ModoTextoManager.ClickEvent.fireEvent(0,textFieldID.getText(),textFieldName.getText());
				}
				else 
				{
					ModoTextoManager.ClickEvent.fireEvent(1,textFieldID.getText(),textFieldName.getText());
				}
				dispose();
			}
		});
		btnNewButton.setBounds(231, 153, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		btnCancelar.setBounds(54, 153, 95, 23);
		contentPane.add(btnCancelar);
	}
}
