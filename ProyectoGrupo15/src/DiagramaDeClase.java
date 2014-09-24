import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class DiagramaDeClase extends JFrame {

	JButton PNGButton;
	
	public DiagramaDeClase(){
		
		super("Diagrama De Clase");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		placeComponents();
		setVisible(true);
	}
	
	private void placeComponents() {
		
		setLayout(null);
		
		PNGButton = new JButton("PNG");
		PNGButton.setBounds(180, 80, 80, 25);
		add(PNGButton);
		
		ActionListener PNGButtonListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (e.getSource() == PNGButton){
				
				 {
				BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
					
				paint(image.getGraphics());
				File file = new File("diagclase.png");
				try {
					ImageIO.write(image, "png", file);
				} catch (IOException e1) {
				
					e1.printStackTrace();
				}
				 }
				 
			} 
			}
		};
		
		PNGButton.addActionListener(PNGButtonListener);
	}
	
	
	
}
