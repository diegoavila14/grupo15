package DiagramCase;

public class Atributos {

	String visibilidad;
	String tipo;
	String nombre;
	
	public String getVisibilidad() {
		return visibilidad;
	}


	public void setVisibilidad(String visibilidad) {
		this.visibilidad = visibilidad;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	
	
	public Atributos(String v, String t, String n){
		
		visibilidad = v;
		tipo = t;
		nombre = n;
	}
	
}
