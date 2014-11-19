package DiagramCase;

import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import java.awt.GridBagLayout;

import javax.swing.JTextPane;

import DiagramCase.Cuadro.DragProcessor;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import net.miginfocom.swing.MigLayout;

import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Note extends JPanel {
	
	public JTextArea TextoNota;
	Point pressPoint;
    Point releasePoint;
	DragProcessor dragProcessor = new DragProcessor();
	String pos;
	Cuadro des;
	InterfazDiagClase idc;

	public Cuadro getDes() {
		return des;
	}

	public void setDes(Cuadro des) {
		this.des = des;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	

	public Note(InterfazDiagClase ii) {
		idc = ii;
		setPreferredSize(new Dimension(167, 82));
		pos = "";
		setBackground(new Color(255, 255, 224));
		setLayout(new MigLayout("", "[200px]", "[100px]"));
		
		TextoNota = new JTextArea();
		TextoNota.setLineWrap(true);
		TextoNota.setDragEnabled(true);
		TextoNota.setPreferredSize(new Dimension(200, 100));
		TextoNota.setEditable(true);
		TextoNota.setBackground(new Color(255, 255, 224));
		add(TextoNota, "cell 0 0,growx,aligny top");
		TextoNota.getDocument().addDocumentListener(new DocumentListener() {

	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	updateLog(e);
	        }

	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	updateLog(e);
	        }

	        @Override
	        public void changedUpdate(DocumentEvent e) {
	        	
	        }
	    });
		TextoNota.addMouseListener(dragProcessor);
		TextoNota.addMouseMotionListener(dragProcessor);
	}
	
	protected class DragProcessor extends MouseAdapter implements MouseListener, MouseMotionListener {
        Window dragWindow = new JWindow() {
            public void paint(Graphics g) {
                super.paint(g);
                Note.this.paint(g);
            }
        };
        public void mouseDragged(MouseEvent e) {
            Point dragPoint = e.getPoint();
            int xDiff = pressPoint.x - dragPoint.x;
            int yDiff = pressPoint.y - dragPoint.y;

            Rectangle b = e.getComponent().getBounds();
            Point p = b.getLocation();
            SwingUtilities.convertPointToScreen(p, e.getComponent().getParent());
            p.x -= xDiff;
            p.y -= yDiff;

            dragWindow.setLocation(p);
        }

        public void mouseMoved(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            pressPoint = e.getPoint();
            Rectangle b = e.getComponent().getBounds();
            Point p = b.getLocation();
            SwingUtilities.convertPointToScreen(p, e.getComponent().getParent());
            dragWindow.setBounds(b);
            dragWindow.setLocation(p);
            dragWindow.setVisible(true);
        }

        public void mouseReleased(MouseEvent e) {
            releasePoint = e.getPoint();
            dragWindow.setVisible(false);

            int xDiff = pressPoint.x - releasePoint.x;
            int yDiff = pressPoint.y - releasePoint.y;

            Rectangle b = e.getComponent().getBounds();
            Point p = b.getLocation();
            SwingUtilities.convertPointToScreen(p, e.getComponent().getParent());
            p.x -= xDiff;
            p.y -= yDiff;
            
    		
            SwingUtilities.convertPointFromScreen(p, Note.this.getParent());
            if (p.x <= 0) {
                p.x = 1;
            }
          
            if (p.y <= 0) {
                p.y = 1;
            }
        
           
            setLocation(p);
            pos = "pos " + Note.this.getLocation().x + " " + Note.this.getLocation().y+"";
            getParent().repaint(); // paint del contenedor
   
        }
    }

	private void updateLog(DocumentEvent e){
	//	idc.dragAndDropFix();
	}

}
