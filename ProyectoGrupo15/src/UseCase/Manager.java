package UseCase;
import java.io.*;
import java.util.EventObject;

import pEventsUtil.pEvent;
import pEventsUtil.pEventListener;

public class Manager implements pEventListener
{
	XmlManager xM;
	ModoTextoManager MT;
	ModoGraficoManager MG; //Dudosa posesión
	
	// Se crea el evento
	public static pEvent ClickEvent;
		
	public Manager(boolean nuevo, File f)
	{
		Diagram diagram;
		if (!nuevo)
		{
			xM = new XmlManager(f);
			diagram = xM.getDiagram();
		}
		else
		{
			diagram = new Diagram("Hola");
		}
		MT = new ModoTextoManager(diagram); //Modo editor de texto
		//MG = new ModoGraficoManager(); //Editor modo gráfico
		//No se si instanciarlo altiro
		this.ClickEvent = new pEvent();
		this.ClickEvent.addEventListener(this);
		
	}
	
	public void interact()
	{
		System.out.println("Desea exportar como PNG? (Si)/(No)");		
	}
	
	//Método que escuchará el evento
		@Override
		public void handleEvent(EventObject e, Object... params) 
		{

			switch((Integer)params[0])
			{
			
			//Caso en que se Crea un Actor primario
			case 0: //agregar actor primario
				Actor a1 = new Actor("primary",(String)params[1],(String)params[2]);
				MT.addActor(a1,true);
				break;
			case 1: //agregar actor secundario
				Actor a2 = new Actor("secondary",(String)params[1],(String)params[2]);
				MT.addActor(a2,false);
				break;
			case 2: //agregar usercase
				UserCase uc = new UserCase((String)params[1],(String)params[2]);
				MT.addUserCase(uc);
				break;
			case 3: //Agregar conexion
				MT.addConnection((Connection)params[1]);
				break;
			case 4: //Pasar de modo gráfico a modo texto (Boton Volver)
				MT.setVisible(true);
				break;
			case 5: //Boton exportar XML apretado
				GuardadorWindow gw = new GuardadorWindow(true);
				gw.setVisible(true);
				break;
			case 6: //Recibir nombre de archivo a guardar en xml
				String n = (String)params[1];
				try 
				{
					MT.exportXML(n);
				} 
				catch (Exception e1) 
				{
					e1.printStackTrace();
				}
				break;
			case 7: //Recibir nombre de archivo a guardar en png
				String nn = (String)params[1];
				MT.exportPNG(nn);
				break;
			}
		}
}
