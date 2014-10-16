package DiagramCase;
import java.util.*;


public class Metodos {

	String nombre;
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRetorno() {
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	public String getVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(String visibilidad) {
		this.visibilidad = visibilidad;
	}

	public void setParam(List<Parametros> param) {
		this.param = param;
	}

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
