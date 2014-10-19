package UseCase;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class Gui extends JFrame implements ActionListener {

	//fuente método: http://stackoverflow.com/questions/144892/how-to-center-a-window-in-java
	
	/**
	* este método permite centrar las ventanas en la pantalla del computador
	*/
	public void centreWindow() {
		    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		    this.setLocation(x, y);
	}

}
