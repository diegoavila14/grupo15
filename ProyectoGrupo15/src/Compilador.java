import java.io.*;




public class Compilador {

	
	ClassDiagram classdiagram;
	XMLClassDiagram xcd;
	DiagramaDeClase ddc;
	
	public Compilador(File f)
	{
		xcd = new XMLClassDiagram(f);
		classdiagram = xcd.getDiagram();
		ddc = new DiagramaDeClase(classdiagram);
	}
}
