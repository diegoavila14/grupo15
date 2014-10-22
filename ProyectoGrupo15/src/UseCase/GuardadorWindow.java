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

public class GuardadorWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	public GuardadorWindow() {
		setTitle("Guardar Archivo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 156);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleccioneElNombre = new JLabel("Seleccione el nombre del archivo:");
		lblSeleccioneElNombre.setBounds(10, 11, 169, 14);
		contentPane.add(lblSeleccioneElNombre);
		
		textField = new JTextField();
		textField.setBounds(10, 40, 257, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Manager.ClickEvent.fireEvent(6,textField.getText()); //Manda el nombre del archivo a guardar
				dispose();
			}
		});
		btnAceptar.setBounds(315, 83, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		btnCancelar.setBounds(30, 83, 89, 23);
		contentPane.add(btnCancelar);
	}
}
