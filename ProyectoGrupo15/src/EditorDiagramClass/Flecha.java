package EditorDiagramClass;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import javax.swing.JSlider;

public class Flecha {
	
	
	public Point getP1() {
		return p1;
	}

	public void setP1(Point inicio) {
		p1 = inicio;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point destino) {
		p2 = destino;
	}

	Point p1;
	Point p2;
	

    public static final int LINE_START_HORIZONTAL = 0;
    public static final int LINE_START_VERTICAL = 1;
    
    public static final int LINE_TYPE_RECT_1BREAK = 1;
    public static final int LINE_TYPE_RECT_2BREAK = 2;
    
    public static final int LINE_ARROW_DEPENDENCY = 0;
    public static final int LINE_ARROW_ASSOCIATION = 1; 
    public static final int LINE_ARROW_AGGREGATION = 2;
    public static final int LINE_ARROW_COMPOSITION = 3;
    public static final int LINE_ARROW_INHERITANCE = 4;
    
    int lineStart;
    int lineType;
    int lineArrow;
    
   
	
	public Flecha(Point p1, Point p2, int lineType, int lineStart, int lineArrow)
	{
		this.lineArrow = lineArrow;
		this.lineType = lineType;
		this.lineStart = lineStart;
		this.p1 = p1;
		this.p2 = p2;
	
	}
	
	  public void paint(Graphics2D g2d) {
	        switch (lineType) {
	            case LINE_TYPE_RECT_1BREAK:
	                paint1Break(g2d);
	                break;
	            case LINE_TYPE_RECT_2BREAK:
	                paint2Breaks(g2d);
	                break;
	        }
	    }

	  protected void paint1Break(Graphics2D g2d) {
		    float dash[] = {10};
	        if (lineStart == LINE_START_HORIZONTAL) {
	   //         g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
	   //         g2d.drawLine(p2.x, p1.y, p2.x, p2.y);
	            switch (lineArrow) {
	                case LINE_ARROW_DEPENDENCY:
	                	g2d.setStroke( new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f, dash, 0.0f));
	                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
	     	            g2d.drawLine(p2.x, p1.y, p2.x, p2.y);
	            //        paintArrow(g2d, new Point(p2.x, p1.y), p2);
	                    break;
	                case LINE_ARROW_ASSOCIATION:
	                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
	     	            g2d.drawLine(p2.x, p1.y, p2.x, p2.y);
	                    break;
	                case LINE_ARROW_AGGREGATION:
	                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
	     	            g2d.drawLine(p2.x, p1.y, p2.x, p2.y);
	        //            paintArrow(g2d, new Point(p2.x, p1.y), p2);
	         //           paintArrow(g2d, new Point(p2.x, p1.y), p1);
	                    break;
	                case LINE_ARROW_COMPOSITION:
	                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
	     	            g2d.drawLine(p2.x, p1.y, p2.x, p2.y);
	    	            //        paintArrow(g2d, new Point(p2.x, p1.y), p2);
	    	            break;
	                case LINE_ARROW_INHERITANCE:
	                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
	     	            g2d.drawLine(p2.x, p1.y, p2.x, p2.y);
	    	            //        paintArrow(g2d, new Point(p2.x, p1.y), p2);
	    	            break;
	            } 
	        }
	        else if (lineStart == LINE_START_VERTICAL) {
	         
	            switch (lineArrow) {
                case LINE_ARROW_DEPENDENCY:
                	g2d.setStroke( new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f, dash, 0.0f));
                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
     	            g2d.drawLine(p2.x, p1.y, p2.x, p2.y);
            //        paintArrow(g2d, new Point(p2.x, p1.y), p2);
                    break;
                case LINE_ARROW_ASSOCIATION:
                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
     	            g2d.drawLine(p2.x, p1.y, p2.x, p2.y);
                    break;
                case LINE_ARROW_AGGREGATION:
                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
     	            g2d.drawLine(p2.x, p1.y, p2.x, p2.y);
        //            paintArrow(g2d, new Point(p2.x, p1.y), p2);
         //           paintArrow(g2d, new Point(p2.x, p1.y), p1);
                    break;
                case LINE_ARROW_COMPOSITION:
                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
     	            g2d.drawLine(p2.x, p1.y, p2.x, p2.y);
    	            //        paintArrow(g2d, new Point(p2.x, p1.y), p2);
    	            break;
                case LINE_ARROW_INHERITANCE:
                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
     	            g2d.drawLine(p2.x, p1.y, p2.x, p2.y);
    	            //        paintArrow(g2d, new Point(p2.x, p1.y), p2);
    	            break;
            } 
	        }
	    }
	  
	  protected void paint2Breaks(Graphics2D g2d) {
		    float dash[] = {10};
	        if (lineStart == LINE_START_HORIZONTAL) {
	        
	            switch (lineArrow) {
                case LINE_ARROW_DEPENDENCY:
                	g2d.setStroke( new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f, dash, 0.0f));
                	g2d.drawLine(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p1.y, p1.x + (p2.x - p1.x) / 2, p2.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p2.y, p2.x, p2.y);
            //        paintArrow(g2d, new Point(p2.x, p1.y), p2);
                    break;
                case LINE_ARROW_ASSOCIATION:
                	g2d.drawLine(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p1.y, p1.x + (p2.x - p1.x) / 2, p2.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p2.y, p2.x, p2.y);
                    break;
                case LINE_ARROW_AGGREGATION:
                	g2d.drawLine(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p1.y, p1.x + (p2.x - p1.x) / 2, p2.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p2.y, p2.x, p2.y);
        //            paintArrow(g2d, new Point(p2.x, p1.y), p2);
         //           paintArrow(g2d, new Point(p2.x, p1.y), p1);
                    break;
                case LINE_ARROW_COMPOSITION:
                	g2d.drawLine(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p1.y, p1.x + (p2.x - p1.x) / 2, p2.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p2.y, p2.x, p2.y);
    	            //        paintArrow(g2d, new Point(p2.x, p1.y), p2);
    	            break;
                case LINE_ARROW_INHERITANCE:
                	g2d.drawLine(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p1.y, p1.x + (p2.x - p1.x) / 2, p2.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p2.y, p2.x, p2.y);
    	            //        paintArrow(g2d, new Point(p2.x, p1.y), p2);
    	            break;
            } 
	        }
	        else if (lineStart == LINE_START_VERTICAL) {
	           
	        	switch (lineArrow) {
                case LINE_ARROW_DEPENDENCY:
                	g2d.setStroke( new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f, dash, 0.0f));
                	g2d.drawLine(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p1.y, p1.x + (p2.x - p1.x) / 2, p2.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p2.y, p2.x, p2.y);
            //        paintArrow(g2d, new Point(p2.x, p1.y), p2);
                    break;
                case LINE_ARROW_ASSOCIATION:
                	g2d.drawLine(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p1.y, p1.x + (p2.x - p1.x) / 2, p2.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p2.y, p2.x, p2.y);
                    break;
                case LINE_ARROW_AGGREGATION:
                	g2d.drawLine(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p1.y, p1.x + (p2.x - p1.x) / 2, p2.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p2.y, p2.x, p2.y);
        //            paintArrow(g2d, new Point(p2.x, p1.y), p2);
         //           paintArrow(g2d, new Point(p2.x, p1.y), p1);
                    break;
                case LINE_ARROW_COMPOSITION:
                	g2d.drawLine(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p1.y, p1.x + (p2.x - p1.x) / 2, p2.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p2.y, p2.x, p2.y);
    	            //        paintArrow(g2d, new Point(p2.x, p1.y), p2);
    	            break;
                case LINE_ARROW_INHERITANCE:
                	g2d.drawLine(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p1.y, p1.x + (p2.x - p1.x) / 2, p2.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p2.y, p2.x, p2.y);
    	            //        paintArrow(g2d, new Point(p2.x, p1.y), p2);
    	            break;
            } 
	        }
	    }

}
