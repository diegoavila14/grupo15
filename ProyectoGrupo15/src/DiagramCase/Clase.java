package DiagramCase;
import java.util.*;



public class Clase {
	
	
	

	public void setAtt(List<Atributos> att) {
		this.att = att;
	}


	public void setMethods(List<Metodos> methods) {
		this.methods = methods;
	}


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
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEtiqueta() {
		return etiqueta;
	}


	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
}
