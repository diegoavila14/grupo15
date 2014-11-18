package UseCase;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class EliminarEntidadWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public EliminarEntidadWindow(List<String> ids) 
	{
		setTitle("Eliminar Entidad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 197);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String aux[] = new String[ids.size()]; 
		for (int i=0 ; i< ids.size() ; i++)
		{
			aux[i] = ids.get(i);
		}
		
		final JComboBox comboBox = new JComboBox(aux);
		comboBox.setBounds(159, 43, 73, 20);
		contentPane.add(comboBox);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(43, 111, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Manager.ClickEvent.fireEvent(6,comboBox.getSelectedItem().toString());
				dispose();
			}
		});
		btnAceptar.setBounds(163, 111, 89, 23);
		contentPane.add(btnAceptar);
		
		JLabel lblID = new JLabel("Seleccione id:");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(43, 46, 79, 14);
		contentPane.add(lblID);
		
	}
}
