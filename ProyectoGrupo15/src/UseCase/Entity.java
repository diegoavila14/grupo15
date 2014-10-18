package UseCase;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class Entity extends JPanel {

	/**
	 * Create the panel.
	 */
	private JLabel label;
	
	public Entity() {
		setBorder(new LineBorder(new Color(0, 0, 0), 3));
		setBackground(Color.YELLOW);
		setLayout(null);
		
		label = new JLabel("hola como esatas que es de tu vida");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 26, 284, 29);
		add(label);
	}
	
	public void setLabel(String text)
	{
		label.setText(text);		
	}
}
