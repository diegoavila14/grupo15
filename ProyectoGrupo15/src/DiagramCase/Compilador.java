package DiagramCase;
import java.io.*;

import org.w3c.dom.Document;





public class Compilador {

	
	ClassDiagram classdiagram;
	public ClassDiagram getClassdiagram() {
		return classdiagram;
	}

	public void setClassdiagram(ClassDiagram classdiagram) {
		this.classdiagram = classdiagram;
	}

	XMLClassDiagram xcd;
	

	public Compilador(Document d)
	{
		xcd = new XMLClassDiagram(d);
		classdiagram = xcd.getDiagram();
		
	}
}
