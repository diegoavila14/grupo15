package UseCase;
import java.io.*;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import pEventsUtil.pEvent;
import pEventsUtil.pEventListener;

public class Manager implements pEventListener
{
	XmlManager xM;
	ModoTextoManager MT;
	
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
			diagram = new Diagram("");
		}
		MT = new ModoTextoManager(diagram); //Modo editor de texto
		//MG = new ModoGraficoManager(); //Editor modo gráfico
		//No se si instanciarlo altiro
		this.ClickEvent = new pEvent();
		this.ClickEvent.addEventListener(this);
		
		
	}
	
	//Método que escuchará el evento
	@Override
	public void handleEvent(EventObject e, Object... params) 
	{

		switch((Integer)params[0])
		{
		
		//Caso en que se Crea un Actor primario
		case 0: //agregar actor primario
			Actor a1 = new Actor("primary",findIDA(),(String)params[1]);
			MT.addActor(a1,true);
			break;
		case 1: //agregar actor secundario
			Actor a2 = new Actor("secondary",findIDA(),(String)params[1]);
			MT.addActor(a2,false);
			break;
		case 2: //agregar usercase
			UserCase uc = new UserCase(findIDUC(),(String)params[1]);
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
		case 6: //Eliminar entidad	
			eliminarEntidad((String)params[1]);
			break;
		case 7: //Recibir nombre de archivo a guardar en png
			String nn = (String)params[1];
			MT.d.name = "nn";
			MT.exportPNG();
			break;
		}
	}

	public void eliminarEntidad(String idE)
	{
		MT.d.ids.remove(idE);
		int i;
		List<Actor> aux = MT.d.actors;
		Actor a;
		if (aux.size() > 0)
		{
			a = aux.get(0);
			i = 1;
			while (i < aux.size() && !(a.id.equals(idE)))
			{
				a = aux.get(i);
				i++;
			}
			if (idE.equals(a.id))
			{
				MT.d.actors.remove(a);
				MT.placeActors2();
			}
		}
		List<UserCase> aux2= MT.d.userCases;
		UserCase uc;
		if (aux2.size() > 0)
		{
			uc = aux2.get(0);
			i = 1;
			while (i < aux.size() && !(uc.id.equals(idE)))
			{
				uc = aux2.get(i);
				i++;
			}
			if (idE.equals(uc.id))
			{
				MT.d.userCases.remove(uc);
				MT.placeUserCases2();
			}
		}
		eliminarConnections(idE);
	}
	
	private void eliminarConnections(String idE)
	{
		List<Connection> aux = MT.d.connections;
		List<Connection> auxEr = new ArrayList<Connection>();
		Connection c;
		for (int i =0; i < aux.size(); i++)
		{
			c = aux.get(i);
			if (c.idFrom.equals(idE) || c.idTo.equals(idE))
			{
				auxEr.add(c);
			}
		}
		Connection c2;
		for (int i =0; i < auxEr.size(); i++)
		{
			c2 = auxEr.get(i);
			MT.d.connections.remove(c2);
		}
		MT.placeConnections2();
	}
	
	public String findIDA() //Encuentra el id para Actores
	{
		int a = MT.idA;
		String r = "a"+ a;
		while(MT.d.ids.contains(r))
		{
			a++;
			r = "a"+ a;
		}
		MT.idA = a;
		return r;
	}
	
	public String findIDUC()
	{
		int uc = MT.idUC;
		String r = "uc"+ uc;
		while(MT.d.ids.contains(r))
		{
			uc++;
			r = "uc"+ uc;
		}
		MT.idUC = uc;
		return r;
	}
}
