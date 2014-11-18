package UseCase;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;

import UseCaseEditor.*;

import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.DebugGraphics;





public class ModoGraficoManager extends JFrame 
{

	Canvas c = new Canvas();
	//ConnectorPropertiesPanel props;
	
	
	
	JScrollPane jsp;
	private JPanel contentPane;
	final JButton btnPng;
	final JButton btnVolver;
	Diagram d;
	List<Union> Uniones;
	List<Integer> CantConexiones;
	int lineArrow;
	Contenedor con;
	List<Entity> entidades;
	
	Map<String, JLabel> map; //diccionario para linkear id con entidad

	public ModoGraficoManager(Diagram D) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		map = new HashMap<String, JLabel>();
		this.d = D;
		
		getContentPane().setLayout(new GridBagLayout());
        init();
        
        getContentPane().add(new JLabel("Modo Gráfico"),new GridBagConstraints(0, 0, 2, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
        getContentPane().add(initConnectors(),new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        //getContentPane().add(btnVolver, new GridBagConstraints(1, 1, 1, 1, 0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL, new Insets(5, 0, 5, 5), 0, 0));
        
        btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) //VolverModoTexto
			{
				Manager.ClickEvent.fireEvent(4);
				dispose();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVolver.setBounds(1084, 11, 90, 42);
		contentPane.add(btnVolver);
		
		btnPng = new JButton("PNG");
		btnPng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
//				GuardadorWindow gw = new GuardadorWindow(false);
//				gw.setVisible(true);
				getPNG();
			}
		});
		btnPng.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPng.setBounds(1097, 608, 77, 42);
		contentPane.add(btnPng);
        
        setSize(1200, 700);
        setLocationRelativeTo(null);
        
        
/*        
        //Para el JScrollPane
//		jsp = new JScrollPane();
		//jsp.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		//jsp.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		
		btnPng = new JButton("PNG");
		btnPng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GuardadorWindow gw = new GuardadorWindow(false);
				gw.setVisible(true);
			}
		});
		btnPng.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPng.setBounds(1097, 608, 77, 42);
//		contentPane.add(btnPng);
		
		map = new HashMap<String, Entity>();
				
		
		this.d = D;
		
/*		entidades = new ArrayList<Entity>();
		Uniones = new ArrayList<Union>();
		lineArrow = 10;
		CantConexiones = new ArrayList<Integer>();
		
		setUserCases();
		setActors();
		setConnections();
		
		con = new Contenedor(Uniones);
		
		for(int i = 0 ; i< entidades.size(); i++){
			con.add(entidades.get(i));
		}
		jsp.setViewportView(con);
		con.setLayout(null);
		contentPane.remove(jsp);
		//con.setBounds(0,0,200,200);
		//contentPane.removeAll();
		contentPane.add(jsp, "cell 2 0,grow");
		contentPane.paint(getGraphics());
*/
	}
	
	protected void init() 
	{
        ConnectLine[] lines = new ConnectLine[5];
        lines[0] = new ConnectLine(new Point(200, 10), new Point(50, 300), ConnectLine.LINE_TYPE_SIMPLE, ConnectLine.LINE_START_HORIZONTAL, ConnectLine.LINE_ARROW_BOTH);
        lines[1] = new ConnectLine(new Point(200, 10), new Point(200, 150), ConnectLine.LINE_TYPE_SIMPLE, ConnectLine.LINE_START_HORIZONTAL, ConnectLine.LINE_ARROW_BOTH);
        lines[2] = new ConnectLine(new Point(50, 150), new Point(100, 100), ConnectLine.LINE_TYPE_SIMPLE, ConnectLine.LINE_START_HORIZONTAL, ConnectLine.LINE_ARROW_BOTH);
        lines[3] = new ConnectLine(new Point(150, 120), new Point(60, 70), ConnectLine.LINE_TYPE_SIMPLE, ConnectLine.LINE_START_HORIZONTAL, ConnectLine.LINE_ARROW_BOTH);
        c.setLines(lines, Color.blue);
	}
	
	 protected ConnectorContainer initConnectors() 
	 {
		 		 	 
	    JConnector[] connectors = new JConnector[50];
	    ConnectorContainer cc = new ConnectorContainer(connectors);
        cc.setLayout(null);
        
        //Situando los UserCases
        int auxY = 10;
		java.util.List<UserCase> list = d.getUserCases();
		
		for (int i = 0; i < list.size(); i++)
		{
			UserCase uc = list.get(i);
			JLabel jl = new DraggableLabel(uc.name);
			Double n = (uc.name.length()*6.54) + 20; // Para ver el tamaño de los bloques
			int aprox = n.intValue();
			jl.setBounds(450,auxY,aprox,70);
			auxY += 90;
			jl.setBackground(Color.yellow);
			jl.setOpaque(true);
			map.put(uc.id, jl);
			cc.add(jl);
		}
		
		int auxP = 10;
		int auxS = 10;
		java.util.List<Actor> list2 = d.getActors();
		for (int i = 0; i < list2.size(); i++)
		{
			Actor a = list2.get(i);
			JLabel jl = new DraggableLabel(a.name);
			Double n = (a.name.length()*6.54) + 20; // Para ver el tamaño de los bloques
			int aprox = n.intValue();
			if (a.type.equals("primary"))
			{
				jl.setBounds(15,auxP,aprox,70);
				auxP += 90;
			}
			else 
			{
				jl.setBounds(850,auxS,aprox,70);
				auxS += 90;
			}
			jl.setBackground(Color.cyan);
			jl.setOpaque(true);
			map.put(a.id, jl);
			cc.add(jl);
		}	
        
        
        JLabel c1 = null;
		JLabel c2 = null;
		
        java.util.List<Connection> list3 = this.d.getConnections();
		for (int i = 0; i < list3.size(); i++)
		{
			Connection c = list3.get(i);
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) 
			{
				Map.Entry e = (Map.Entry)it.next();
				if(e.getKey().equals(c.getidFrom()))
				{
					c1 = map.get(e.getKey());
					break;
				}
			}
			Iterator it2 = map.entrySet().iterator();
			while (it2.hasNext()) 
			{
				Map.Entry e = (Map.Entry)it2.next();
				if(e.getKey().equals(c.getidTo()))
				{
					c2 = map.get(e.getKey());
					break;
				}
			}
			
			switch(c.getType()){
			case "basic":
				lineArrow = ConnectLine.LINE_ARROW_BASIC;
				break;
			case "extend":
				lineArrow = ConnectLine.LINE_ARROW_EXTEND;
				break;
			case "include":
				lineArrow = ConnectLine.LINE_ARROW_INCLUDE;
				break;
			case "isa":
				lineArrow = ConnectLine.LINE_ARROW_ISA;
				break;
		} // end switch
		
			connectors[i] = new JConnector(c1, c2, lineArrow, JConnector.CONNECT_LINE_TYPE_SIMPLE, Color.red);
		}
        cc.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        return cc;
    }
	 
	//temp class to test lines drawing
    protected static class Canvas extends JPanel {
        ConnectLine[] lines;
        Color color;
        public void setLines(ConnectLine[] lines, Color color) {
            this.lines = lines;
            this.color = color;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.white);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.black);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

            g.setColor(color);
            for (int i = 0; i < lines.length; i++) {
                if (lines[i] != null) {
                    lines[i].paint( (Graphics2D) g);
                }
            }
        }
    }
        
	
	
	
	public void getPNG(/*String nFile*/)
	{
		//btnPng.setVisible(false);
		//btnVolver.setVisible(false);
		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		
		paint(image.getGraphics());
		
		JFileChooser c = new JFileChooser();
		int rVal = c.showSaveDialog(null);
		String name = c.getSelectedFile().getAbsolutePath() + ".png";
       
        File f = new File(name);
        if (rVal == JFileChooser.APPROVE_OPTION) 
        {			
			try 
			{
				ImageIO.write(image, "png",  f);
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
        }
        else
        {
        	btnPng.setVisible(true);
            btnVolver.setVisible(true);
        }
		btnPng.setVisible(true);
        btnVolver.setVisible(true);
	}
	
	public Contenedor getCon() {
		return con;
	}
	
	public void setCon(Contenedor con) {
		this.con = con;
	}
}
