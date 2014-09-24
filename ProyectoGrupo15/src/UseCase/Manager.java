package UseCase;
import java.io.*;

public class Manager 
{
	Diagram diagram;
	XmlManager xM;
	ExportManager eM;
	
	public Manager(File f)
	{
		xM = new XmlManager(f);
		diagram = xM.getDiagram();
		eM = new ExportManager(diagram);
	}
	
	public void interact()
	{
		System.out.println("Desea exportar como PNG? (Si)/(No)");		
	}
}
