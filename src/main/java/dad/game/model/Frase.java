package dad.game.model;

import java.util.HashMap;

public class Frase {
	
	private HashMap<Objeto, Objeto> contenido = new HashMap<Objeto, Objeto>();
	
	public Frase() {}

	public Frase(HashMap<Objeto, Objeto> contenido) {
		super();
		this.contenido = contenido;
	}

	public HashMap<Objeto, Objeto> getContenido() {
		return contenido;
	}

	public void setContenido(HashMap<Objeto, Objeto> contenido) {
		this.contenido = contenido;
	}
	
	public void setContenidoFrase(Objeto clave, Objeto valor) {
		this.contenido.put(clave, valor);
	}
	
	public void limpiarContenido() {
		this.contenido.clear();
	}
	
}
