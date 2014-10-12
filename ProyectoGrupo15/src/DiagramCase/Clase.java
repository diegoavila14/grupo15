package DiagramCase;
import java.util.*;



public class Clase {
	
	
	String id;
	String nombre;
	String etiqueta;
	List<Atributos> att;
	List<Metodos> methods; 
	
	public Clase(String i, String n){
		
		id = i;
		nombre = n;
		etiqueta = "";
		att = new ArrayList<Atributos>();
		methods = new ArrayList<Metodos>();
	}
	
	
	public void addAtt(Atributos a)
	{
		att.add(a);		
	}
	
	public void addMethod(Metodos m)
	{
		methods.add(m);		
	}
	
	public List<Atributos> getAtt(){
		
		return att;
	}
	
	public List<Metodos> getMethods(){
		
		return methods;
	}
}
