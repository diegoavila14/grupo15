package UseCaseEditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.JComponent;
import javax.swing.JPanel;

import UseCase.Entity;

public class Union extends JPanel {

	Entity inicio;
	Entity destino;
	Flecha line ;
	protected int lineArrow;
	int posiciond;
	int posicions; // para correr la flecha
	
	// Se creo algo similar a lo que se encuentra en esta pagina http://java-sl.com/connector.html, para evitar problemas de plagio
	public Union(Entity c1, Entity c2, int lineArrow, int ps, int pd)
	{
		posiciond = pd;
		posicions = ps;
		this.lineArrow = lineArrow;
		inicio = c1;
		destino =c2;
	}
	
	private void CalcularLinea()
	{
		 Rectangle rSource = inicio.getBounds();
	     Rectangle rDest = destino.getBounds();
	     if (rSource.intersects(rDest)) {
	    	
	           line = null;
	            return;
	        }

	        boolean xIntersect = (rSource.x <= rDest.x && rSource.x + rSource.width >= rDest.x)
	            || (rDest.x <= rSource.x && rDest.x + rDest.width >= rSource.x);
	        boolean yIntersect = rSource.y <= rDest.y && rSource.y + rSource.height >= rDest.y
	            || (rDest.y <= rSource.y && rDest.y + rDest.height >= rSource.y);

	        if (xIntersect) {
	            int y1;
	            int y2;
	            int x1 = rSource.x + posicions;
	            int x2 = rDest.x + posiciond;
	            if (rSource.y + rSource.height <= rDest.y) {
	                //source higher
	                y1 = rSource.y + rSource.height;
	                y2 = rDest.y;
	            }
	            else {
	                y1 = rSource.y;
	                y2 = rDest.y + rDest.height;
	            }
	            line = new Flecha(new Point(x1, y1), new Point(x2, y2), Flecha.LINE_TYPE_RECT_2BREAK, Flecha.LINE_START_VERTICAL, lineArrow);
	           
	        }
	        else if (yIntersect) {
	            int y1 = rSource.y + posicions;
	            ;
	            int y2 = rDest.y + posiciond;
	            ;
	            int x1;
	            int x2;
	            if (rSource.x + rSource.width <= rDest.x) {
	                x1 = rSource.x + rSource.width;
	                x2 = rDest.x;
	            }
	            else {
	                x1 = rSource.x;
	                x2 = rDest.x + rDest.width;
	            }
	            line = new Flecha(new Point(x1, y1), new Point(x2, y2), Flecha.LINE_TYPE_RECT_2BREAK, Flecha.LINE_START_HORIZONTAL, lineArrow);
	         
	        }
	        else {
	            int y1;
	            int y2;
	            int x1;
	            int x2;
	            if (rSource.y + rSource.height <= rDest.y) {
	                //source higher
	                y1 = rSource.y + posicions; //ver
	                y2 = rDest.y;
	                if (rSource.x + rSource.width <= rDest.x) {
	                    x1 = rSource.x + rSource.width;
	                }
	                else {
	                    x1 = rSource.x;
	                }
	                x2 = rDest.x + posiciond; // ver
	            }
	            else {
	                y1 = rSource.y+ posicions; // ver
	                y2 = rDest.y + rDest.height;
	                if (rSource.x + rSource.width <= rDest.x) {
	                    x1 = rSource.x + rSource.width;
	                }
	                else {
	                    x1 = rSource.x;
	                }
	                x2 = rDest.x+ posiciond; // ver
	            }
	            line = new Flecha(new Point(x1, y1), new Point(x2, y2), Flecha.LINE_TYPE_RECT_1BREAK, Flecha.LINE_START_HORIZONTAL, lineArrow);
	        
	        }
	     
	}
	
	  public void paint(Graphics g) {
		  	super.paint(g);
	        Graphics2D g2d = (Graphics2D) g;
	        CalcularLinea();
	
	        if (line != null) {
	        	
	            Shape oldClip = g2d.getClip();
	            g2d.setClip(getLineBounds());
	            g2d.setColor(Color.BLACK);
	            line.paint(g2d);
	            g2d.setClip(oldClip);
	        }
	    }
	  
	  protected Rectangle getLineBounds() {
	        int add = 10;
	        int maxX = Math.max(line.getP1().x, line.getP2().x);
	        int minX = Math.min(line.getP1().x, line.getP2().x);
	        int maxY = Math.max(line.getP1().y, line.getP2().y);
	        int minY = Math.min(line.getP1().y, line.getP2().y);

	        Rectangle res = new Rectangle(minX - add, minY - add, maxX - minX + 2 * add, maxY - minY + 2 * add);
	        return res;
	    }
}
