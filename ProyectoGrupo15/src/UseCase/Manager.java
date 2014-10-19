package UseCase;
import java.io.*;

public class Manager 
{
	Diagram diagram;
	XmlManager xM;
	ModoTextoManager MT;
	ModoGraficoManager MG;
	
	public Manager(File f)
	{
		xM = new XmlManager(f);
		diagram = xM.getDiagram();
		MT = new ModoTextoManager(diagram); //Modo editor de texto
		//MG = new ModoGraficoManager(); //Editor modo gráfico
		//No se si instanciarlo altiro
		
	}
	
	public void interact()
	{
		System.out.println("Desea exportar como PNG? (Si)/(No)");		
	}
}
