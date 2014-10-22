package DiagramCase;

public class Parametros {

	String nombre;
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	String tipo;
	
	public Parametros(String n, String t){
		
		nombre = n;
		tipo = t;
	}
}
