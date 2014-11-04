package EditorDiagramClass;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;


public class Contenedor extends JPanel{
	 List<Union> connectors;
	   

	    public Contenedor(List<Union> connectors) {
	        this.connectors = connectors;
	    }

	    public void setConnectors(List<Union> connectors) {
	        this.connectors = connectors;
	    }

	    public List<Union> getConnectors() {
	        return connectors;
	    }

	    public void paint(Graphics g) {
	        super.paint(g);
	        if (connectors != null) {
	            for (int i = 0; i < connectors.size(); i++) {
	                if (connectors.get(i) != null) {
	                    connectors.get(i).paint(g);
	                }
	            }
	        }
	    }
}
