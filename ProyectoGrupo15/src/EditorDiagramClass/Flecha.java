package EditorDiagramClass;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.Polygon;

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
    
    public static int LINE_ARROW_WIDTH = 20;
    
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
		    int[] Dif = new int[2];
		    Stroke s = g2d.getStroke();
	        if (lineStart == LINE_START_HORIZONTAL) {
	            switch (lineArrow) {
	                case LINE_ARROW_DEPENDENCY:
	                	g2d.setStroke( new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f, dash, 0.0f)); // Linea punteada
	                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
	     	            g2d.drawLine(p2.x, p1.y, p2.x, p2.y);
	     	            g2d.setStroke(s);
	                    paintArrowDependency(g2d, new Point(p2.x, p1.y), p2);
	                    break;
	                case LINE_ARROW_ASSOCIATION:
	                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
	     	            g2d.drawLine(p2.x, p1.y, p2.x, p2.y);
	                    break;
	                case LINE_ARROW_AGGREGATION:
	                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
	     	       
	     	            Dif = paintArrowAggregation(g2d, new Point(p2.x, p1.y), p2);
	     	            g2d.drawLine(p2.x, p1.y, p2.x, p2.y-2*Dif[1]);
	     	            break;
	                case LINE_ARROW_COMPOSITION:
	                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
	     	
	    	            Dif = paintArrowComposition(g2d, new Point(p2.x, p1.y), p2);
	    	            g2d.drawLine(p2.x, p1.y, p2.x, p2.y-2*Dif[1]);
	    	            break;
	                case LINE_ARROW_INHERITANCE:
	                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
	     	           
	     	            Dif = paintArrowInheritance(g2d, new Point(p2.x, p1.y), p2);
	     	            g2d.drawLine(p2.x, p1.y, p2.x, p2.y-Dif[1]);
	     	            
	    	            break;
	            } 
	        }
	        else if (lineStart == LINE_START_VERTICAL) {
	         
	            switch (lineArrow) {
                case LINE_ARROW_DEPENDENCY:
                	g2d.setStroke( new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f, dash, 0.0f)); // Linea punteada
                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
                    g2d.drawLine(p1.x, p2.y, p2.x, p2.y);
     	            g2d.setStroke(s);
                    paintArrowDependency(g2d, new Point(p1.x, p2.y), p2);
                    break;
                case LINE_ARROW_ASSOCIATION:
                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
                    g2d.drawLine(p1.x, p2.y, p2.x, p2.y);
                    break;
                case LINE_ARROW_AGGREGATION:
                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
               
     	            Dif = paintArrowAggregation(g2d, new Point(p1.x, p2.y), p2);
     	            g2d.drawLine(p1.x, p2.y, p2.x-2*Dif[0], p2.y);
                    break;
                case LINE_ARROW_COMPOSITION:
                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
  
    	            paintArrowComposition(g2d, new Point(p1.x, p2.y), p2);
    	            g2d.drawLine(p1.x, p2.y, p2.x-2*Dif[0], p2.y);
    	            break;
                case LINE_ARROW_INHERITANCE:
                	g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
          
     	            Dif = paintArrowInheritance(g2d, new Point(p1.x, p2.y), p2);
     	            
     	            g2d.drawLine(p1.x, p2.y, p2.x-Dif[0], p2.y);
    	            break;
            } 
	        }
	    }
	  
	  protected void paint2Breaks(Graphics2D g2d) {
		    float dash[] = {10};
		    int[] Dif = new int[2];
		    Stroke s = g2d.getStroke();
	        if (lineStart == LINE_START_HORIZONTAL) {
	        
	            switch (lineArrow) {
                case LINE_ARROW_DEPENDENCY:
                	g2d.setStroke( new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f, dash, 0.0f)); // Linea punteada
                	g2d.drawLine(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p1.y, p1.x + (p2.x - p1.x) / 2, p2.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p2.y, p2.x, p2.y);
     	            g2d.setStroke(s);
     	            paintArrowDependency(g2d, new Point(p1.x + (p2.x - p1.x) / 2, p2.y), p2);
     	            break;
                case LINE_ARROW_ASSOCIATION:
                	g2d.drawLine(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p1.y, p1.x + (p2.x - p1.x) / 2, p2.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p2.y, p2.x, p2.y);
                    break;
                case LINE_ARROW_AGGREGATION:
                	g2d.drawLine(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p1.y, p1.x + (p2.x - p1.x) / 2, p2.y);
     	      
     	            Dif = paintArrowAggregation(g2d, new Point(p1.x + (p2.x - p1.x) / 2, p2.y), p2);   
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p2.y, p2.x-2*Dif[0], p2.y);
                    break;
                case LINE_ARROW_COMPOSITION:
                	g2d.drawLine(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p1.y, p1.x + (p2.x - p1.x) / 2, p2.y);
     	   
    	            paintArrowComposition(g2d, new Point(p1.x + (p2.x - p1.x) / 2, p2.y), p2);
    	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p2.y, p2.x-2*Dif[0], p2.y);
    	            break;
                case LINE_ARROW_INHERITANCE:
                	g2d.drawLine(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y);
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p1.y, p1.x + (p2.x - p1.x) / 2, p2.y);
     	            
     	            Dif = paintArrowInheritance(g2d, new Point(p1.x + (p2.x - p1.x) / 2, p2.y), p2);  
     	            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p2.y, p2.x-Dif[0], p2.y);
     	            break;
            } 
	        }
	        else if (lineStart == LINE_START_VERTICAL) {
	           
	        	switch (lineArrow) {
	        	
                case LINE_ARROW_DEPENDENCY:
                	g2d.setStroke( new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f, dash, 0.0f)); // Linea punteada
                	g2d.drawLine(p1.x, p1.y, p1.x, p1.y + (p2.y - p1.y) / 2);
                    g2d.drawLine(p1.x, p1.y + (p2.y - p1.y) / 2, p2.x, p1.y + (p2.y - p1.y) / 2);
                    g2d.drawLine(p2.x, p1.y + (p2.y - p1.y) / 2, p2.x, p2.y);
     	            g2d.setStroke(s);
     	            paintArrowDependency(g2d, new Point(p2.x, p1.y + (p2.y - p1.y) / 2), p2);
     	            break;
     	            
                case LINE_ARROW_ASSOCIATION:
               	    g2d.drawLine(p1.x, p1.y, p1.x, p1.y + (p2.y - p1.y) / 2);
                    g2d.drawLine(p1.x, p1.y + (p2.y - p1.y) / 2, p2.x, p1.y + (p2.y - p1.y) / 2);
                    g2d.drawLine(p2.x, p1.y + (p2.y - p1.y) / 2, p2.x, p2.y);
                    break;
                    
                case LINE_ARROW_AGGREGATION:
                	 g2d.drawLine(p1.x, p1.y, p1.x, p1.y + (p2.y - p1.y) / 2);
                     g2d.drawLine(p1.x, p1.y + (p2.y - p1.y) / 2, p2.x, p1.y + (p2.y - p1.y) / 2);
                     
                     Dif = paintArrowAggregation(g2d, new Point(p2.x, p1.y + (p2.y - p1.y) / 2), p2);   
                     g2d.drawLine(p2.x, p1.y + (p2.y - p1.y) / 2, p2.x, p2.y-2*Dif[1]);
                    break;
                    
                case LINE_ARROW_COMPOSITION:
                	 g2d.drawLine(p1.x, p1.y, p1.x, p1.y + (p2.y - p1.y) / 2);
                     g2d.drawLine(p1.x, p1.y + (p2.y - p1.y) / 2, p2.x, p1.y + (p2.y - p1.y) / 2);
                     g2d.drawLine(p2.x, p1.y + (p2.y - p1.y) / 2, p2.x, p2.y);
    	             paintArrowComposition(g2d, new Point(p2.x, p1.y + (p2.y - p1.y) / 2), p2);
    	             g2d.drawLine(p2.x, p1.y + (p2.y - p1.y) / 2, p2.x, p2.y-2*Dif[1]);
    	            break;
    	            
                case LINE_ARROW_INHERITANCE:
                	 g2d.drawLine(p1.x, p1.y, p1.x, p1.y + (p2.y - p1.y) / 2);
                     g2d.drawLine(p1.x, p1.y + (p2.y - p1.y) / 2, p2.x, p1.y + (p2.y - p1.y) / 2);
                  
     	            Dif = paintArrowInheritance(g2d, new Point(p2.x, p1.y + (p2.y - p1.y) / 2), p2);  
     	            g2d.drawLine(p2.x, p1.y + (p2.y - p1.y) / 2, p2.x, p2.y-Dif[1]);
    	            break;
            } 
	        }
	    }
	  
	  protected int[] paintArrowAggregation(Graphics2D g2d, Point p1, Point p2) {
	        return paintArrowAggregation(g2d, p1, p2, getRestrictedArrowWidth(p1, p2));
	    }
	  
	  protected int[] paintArrowAggregation(Graphics2D g2d, Point p1, Point p2, int width) {
	        Point2D.Float pp1 = new Point2D.Float(p1.x, p1.y);
	        Point2D.Float pp2 = new Point2D.Float(p2.x, p2.y);
	        Point2D.Float left = getLeftArrowPoint(pp1, pp2, width);
	        Point2D.Float right = getRightArrowPoint(pp1, pp2, width);
	        int Difx = (p2.x - Math.round(left.x));
	        int Dify = (p2.y - Math.round(left.y));
	        int[] d = new int[]{Difx,Dify};
	 
	        
	     
	        if(Math.abs(Difx) > Math.abs(Dify)){
	       
	        int[] xpoints = new int[]{p2.x, Math.round(left.x),  p2.x-2*Difx, Math.round(right.x)};
	        int[] ypoints = new int[]{p2.y, Math.round(left.y), p2.y, Math.round(right.y)};
	        Polygon p = new Polygon(xpoints, ypoints, xpoints.length);
	        
	        g2d.drawPolygon(p);
	        
	      
	        }
	        
	        else{
	        	
	        	int[] xpoints = new int[]{p2.x, Math.round(left.x),  p2.x, Math.round(right.x)};
	        	int[] ypoints = new int[]{p2.y, Math.round(left.y), p2.y-2*Dify, Math.round(right.y)};
	        	Polygon p = new Polygon(xpoints, ypoints, xpoints.length);
	        	g2d.drawPolygon(p);   
	        
		    
	        }
	    
	        return d;
	     }
	  
	  protected int[] paintArrowComposition(Graphics2D g2d, Point p1, Point p2) {
	        return paintArrowComposition(g2d, p1, p2, getRestrictedArrowWidth(p1, p2));
	    }
	  
	  protected int[] paintArrowComposition(Graphics2D g2d, Point p1, Point p2, int width) {
	        Point2D.Float pp1 = new Point2D.Float(p1.x, p1.y);
	        Point2D.Float pp2 = new Point2D.Float(p2.x, p2.y);
	        Point2D.Float left = getLeftArrowPoint(pp1, pp2, width);
	        Point2D.Float right = getRightArrowPoint(pp1, pp2, width);
	        int Difx = (p2.x - Math.round(left.x));
	        int Dify = (p2.y - Math.round(left.y));
	        int[] d = new int[]{Difx,Dify};
	 
	        
	     
	        if(Math.abs(Difx) > Math.abs(Dify)){
	       
	        int[] xpoints = new int[]{p2.x, Math.round(left.x),  p2.x-2*Difx, Math.round(right.x)};
	        int[] ypoints = new int[]{p2.y, Math.round(left.y), p2.y, Math.round(right.y)};
	        Polygon p = new Polygon(xpoints, ypoints, xpoints.length);
	        
	        g2d.fillPolygon(p);
	        
	      
	        }
	        
	        else{
	        	
	        	int[] xpoints = new int[]{p2.x, Math.round(left.x),  p2.x, Math.round(right.x)};
	        	int[] ypoints = new int[]{p2.y, Math.round(left.y), p2.y-2*Dify, Math.round(right.y)};
	        	Polygon p = new Polygon(xpoints, ypoints, xpoints.length);
	        	g2d.fillPolygon(p);   
	        
		    
	        }
	    
	        return d;
	     }
	  
	  protected int[] paintArrowInheritance(Graphics2D g2d, Point p1, Point p2) {
	        return paintArrowInheritance(g2d, p1, p2, getRestrictedArrowWidth(p1, p2));
	    }
	  
	  protected int[] paintArrowInheritance(Graphics2D g2d, Point p1, Point p2, int width) {
	        Point2D.Float pp1 = new Point2D.Float(p1.x, p1.y);
	        Point2D.Float pp2 = new Point2D.Float(p2.x, p2.y);
	        Point2D.Float left = getLeftArrowPoint(pp1, pp2, width);
	        Point2D.Float right = getRightArrowPoint(pp1, pp2, width);
	        int Difx = (p2.x - Math.round(left.x));
	        int Dify = (p2.y - Math.round(left.y));
	        
	        int[] d = new int[]{Difx, Dify};
	 
	        
	        g2d.drawLine(p2.x, p2.y, Math.round(left.x), Math.round(left.y));
	        g2d.drawLine(p2.x, p2.y, Math.round(right.x), Math.round(right.y));
	        g2d.drawLine(Math.round(left.x), Math.round(left.y), Math.round(right.x), Math.round(right.y));
	      
	        return d;
	        
	    
	     }
	  
	  protected void paintArrowDependency(Graphics2D g2d, Point p1, Point p2) {
	        paintArrowDependency(g2d, p1, p2, getRestrictedArrowWidth(p1, p2));
	    }
	  
	  protected void paintArrowDependency(Graphics2D g2d, Point p1, Point p2, int width) {
	        Point2D.Float pp1 = new Point2D.Float(p1.x, p1.y);
	        Point2D.Float pp2 = new Point2D.Float(p2.x, p2.y);
	        Point2D.Float left = getLeftArrowPoint(pp1, pp2, width);
	        Point2D.Float right = getRightArrowPoint(pp1, pp2, width);

	        g2d.drawLine(p2.x, p2.y, Math.round(left.x), Math.round(left.y));
	        g2d.drawLine(p2.x, p2.y, Math.round(right.x), Math.round(right.y));
  
	    }
	  
	  protected static Point2D.Float getLeftArrowPoint(Point2D.Float p1, Point2D.Float p2) {
	        return getLeftArrowPoint(p1, p2, LINE_ARROW_WIDTH);
	    }

	    protected static Point2D.Float getLeftArrowPoint(Point2D.Float p1, Point2D.Float p2, float w) {
	        Point2D.Float res = new Point2D.Float();
	        double alpha = Math.PI / 2;
	        if (p2.x != p1.x) {
	            alpha = Math.atan( (p2.y - p1.y) / (p2.x - p1.x));
	        }
	        alpha += Math.PI / 10;
	        float xShift = Math.abs(Math.round(Math.cos(alpha) * w));
	        float yShift = Math.abs(Math.round(Math.sin(alpha) * w));
	        if (p1.x <= p2.x) {
	            res.x = p2.x - xShift;
	        }
	        else {
	            res.x = p2.x + xShift;
	        }
	        if (p1.y < p2.y) {
	            res.y = p2.y - yShift;
	        }
	        else {
	            res.y = p2.y + yShift;
	        }
	        return res;
	    }

	    protected static Point2D.Float getRightArrowPoint(Point2D.Float p1, Point2D.Float p2) {
	        return getRightArrowPoint(p1, p2, LINE_ARROW_WIDTH);
	    }

	    protected static Point2D.Float getRightArrowPoint(Point2D.Float p1, Point2D.Float p2, float w) {
	        Point2D.Float res = new Point2D.Float();
	        double alpha = Math.PI / 2;
	        if (p2.x != p1.x) {
	            alpha = Math.atan( (p2.y - p1.y) / (p2.x - p1.x));
	        }
	        alpha -= Math.PI / 10;
	        float xShift = Math.abs(Math.round(Math.cos(alpha) * w));
	        float yShift = Math.abs(Math.round(Math.sin(alpha) * w));
	        if (p1.x < p2.x) {
	            res.x = p2.x - xShift;
	        }
	        else {
	            res.x = p2.x + xShift;
	        }
	        if (p1.y <= p2.y) {
	            res.y = p2.y - yShift;
	        }
	        else {
	            res.y = p2.y + yShift;
	        }
	        return res;
	    }
	  
	  protected int getRestrictedArrowWidth(Point p1, Point p2) {
	        return Math.min(LINE_ARROW_WIDTH, (int) Math.sqrt( (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y)));
	    }

}
