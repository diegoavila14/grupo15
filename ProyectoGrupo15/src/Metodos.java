import java.util.*;


public class Metodos {

	String nombre;
	String retorno;
	String visibilidad;
	List<Parametros> param;
	
	public Metodos(String n, String r, String v){
		
		nombre = n;
		retorno = r;
		visibilidad = v;
		param = new ArrayList<Parametros>();
	}
	
	public void addParam(Parametros p)
	{
		param.add(p);		
	}
	
	public List<Parametros> getParam(){
		
		return param;
	}
}
