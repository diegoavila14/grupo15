import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class DiagramaDeClase {

	public static JFrame FrameDiagClase;
	public static JPanel myPanel;
	private JButton button1;
	
	public DiagramaDeClase(){
		
		myPanel = new JPanel();
		button1 = new JButton("Button 1");
		SimpleListener ourListener = new SimpleListener();
		button1.addActionListener(ourListener);
		myPanel.add(button1);
	}
	
	private class SimpleListener implements ActionListener
	 {
	
		public void actionPerformed(ActionEvent e)
		{
		
		 if (e.getSource() == button1){
			
			 {
			BufferedImage image = new BufferedImage(myPanel.getWidth(), myPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
				
			myPanel.paint(image.getGraphics());
			File file = new File("diagclase.png");
			try {
				ImageIO.write(image, "png", file);
			} catch (IOException e1) {
			
				e1.printStackTrace();
			}
			 }
			 
		 }
	 }
	 }
}
