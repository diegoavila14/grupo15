package DiagramCase;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextPane;
import java.awt.GridBagConstraints;
import java.awt.Color;

public class Note extends JPanel {
	
	public JTextPane TextoNota;

	public JTextPane getTextoNota() {
		return TextoNota;
	}

	public void setTextoNota(JTextPane textoNota) {
		TextoNota = textoNota;
	}

	public Note() {
		
		setBackground(new Color(255, 255, 224));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		TextoNota = new JTextPane();
		TextoNota.setEditable(false);
		TextoNota.setBackground(new Color(255, 255, 224));
		GridBagConstraints gbc_txtpnHola = new GridBagConstraints();
		gbc_txtpnHola.anchor = GridBagConstraints.NORTH;
		gbc_txtpnHola.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpnHola.gridx = 0;
		gbc_txtpnHola.gridy = 0;
		add(TextoNota, gbc_txtpnHola);

		
		
	}

}
