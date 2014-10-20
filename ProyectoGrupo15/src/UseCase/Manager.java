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
		
	public Manager(File f)
	{
		xM = new XmlManager(f);
		Diagram diagram = xM.getDiagram();
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
			case 0:
				Actor a1 = new Actor("primary",(String)params[1],(String)params[2]);
				MT.addActor(a1,true);
				break;
			case 1:
				Actor a2 = new Actor("secondary",(String)params[1],(String)params[2]);
				MT.addActor(a2,false);
				break;
			case 2:
				UserCase uc = new UserCase((String)params[1],(String)params[2]);
				MT.addUserCase(uc);
			case 4:
				MT.setVisible(true);
			}
		}
}
