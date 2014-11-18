package DiagramCase;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;

import javax.swing.SwingConstants;

import java.awt.Component;

import javax.swing.Box;

import java.awt.Color;

import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;

import javax.swing.JPopupMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JMenuItem;

import java.awt.Cursor;
import java.awt.Dimension;

public class Cuadro extends JPanel {
	
	String ID;
	JLabel Nombre;
	JSeparator SeparadorAtributos;
	JSeparator SeparadorMetodos;
	JLabel Atributos;
	JLabel Metodos;
	public JPopupMenu popupMenu;
	public JMenuItem mntmCrearNota;
	InterfazDiagClase idc;
	Point pressPoint;
    Point releasePoint;
	DragProcessor dragProcessor = new DragProcessor();
	String pos;
	
	public JMenuItem getMntmCrearNota() {
		return mntmCrearNota;
	}

	public void setMntmCrearNota(JMenuItem mntmCrearNota) {
		this.mntmCrearNota = mntmCrearNota;
	}

	public Cuadro(InterfazDiagClase ii) {
		pos= "";
		setForeground(Color.LIGHT_GRAY);
		setBorder(new LineBorder(new Color(0, 0, 0), 3));
		setBackground(Color.LIGHT_GRAY);
		idc= ii;
		popupMenu = new JPopupMenu();
		addPopup(this, popupMenu);
		
		mntmCrearNota = new JMenuItem("Crear Nota");
	
				
		popupMenu.add(mntmCrearNota);
		
		setLayout(new MigLayout("", "[grow]", "[][][][][]"));
				
		Nombre = new JLabel("");
		Nombre.setHorizontalAlignment(SwingConstants.CENTER);
		Nombre.setForeground(new Color(0, 0, 0));
		Nombre.setBackground(Color.LIGHT_GRAY);
		add(Nombre, "cell 0 0,alignx center,aligny top");
		
		SeparadorAtributos = new JSeparator();
		SeparadorAtributos.setForeground(Color.BLACK);
		SeparadorAtributos.setBackground(Color.BLACK);
		
		add(SeparadorAtributos, "cell 0 1,grow");
		
		Atributos = new JLabel();
		Atributos.setFocusable(false);
		Atributos.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		Atributos.setVerifyInputWhenFocusTarget(false);
		Atributos.setBackground(Color.LIGHT_GRAY);
		Atributos.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		add(Atributos, "cell 0 2,grow");
		
		SeparadorMetodos = new JSeparator();
		SeparadorMetodos.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		SeparadorMetodos.setForeground(Color.BLACK);
		SeparadorMetodos.setBackground(Color.BLACK);
		add(SeparadorMetodos, "cell 0 3,grow");
		
		Metodos = new JLabel();
		Metodos.setFocusable(false);
		Metodos.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		Metodos.setBackground(Color.LIGHT_GRAY);
		Metodos.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		add(Metodos, "cell 0 4,grow");

		addMouseListener(dragProcessor);
        addMouseMotionListener(dragProcessor);
       
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}	
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	protected class DragProcessor extends MouseAdapter implements MouseListener, MouseMotionListener {
	        Window dragWindow = new JWindow() {
	            public void paint(Graphics g) {
	                super.paint(g);
	                Cuadro.this.paint(g);
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
	            
	    		
	            SwingUtilities.convertPointFromScreen(p, Cuadro.this.getParent());
	            if (p.x <= 0) {
	                p.x = 1;
	            }
	          
	            if (p.y <= 0) {
	                p.y = 1;
	            }
	        
	           
	            setLocation(p);
	            pos = "pos " + Cuadro.this.getLocation().x + " " + Cuadro.this.getLocation().y+"";
	            getParent().repaint(); // paint del contenedor
	   
	        }
	    }




}
