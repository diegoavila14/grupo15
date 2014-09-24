import java.util.*;


public class Clase {
	
	
	String id;
	String nombre;
	String etiqueta;
	ArrayList<Atributos> att;
	ArrayList<Metodos> methods; 
	
	public Clase(String i, String n, String e ){
		
		id = i;
		nombre = i;
		etiqueta = e;
		att = new ArrayList<>();
		methods = new ArrayList<>();
	}
}
