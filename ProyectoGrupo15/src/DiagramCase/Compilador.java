package DiagramCase;
import java.io.*;



public class Compilador {

	
	ClassDiagram classdiagram;
	public ClassDiagram getClassdiagram() {
		return classdiagram;
	}

	public void setClassdiagram(ClassDiagram classdiagram) {
		this.classdiagram = classdiagram;
	}

	XMLClassDiagram xcd;
	

	public Compilador(File f)
	{
		xcd = new XMLClassDiagram(f);
		classdiagram = xcd.getDiagram();
		
	}
}
