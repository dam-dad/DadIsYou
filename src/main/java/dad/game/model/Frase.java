package dad.game.model;

import java.util.HashMap;

public class Frase {
	
	private HashMap<Palabra, Palabra> contenido = new HashMap<Palabra, Palabra>();
	
	public Frase() {}

	public Frase(HashMap<Palabra, Palabra> contenido) {
		super();
		this.contenido = contenido;
	}

	public HashMap<Palabra, Palabra> getContenido() {
		return contenido;
	}

	public void setContenido(HashMap<Palabra, Palabra> contenido) {
		this.contenido = contenido;
	}
	
	public void setContenidoFrase(Palabra clave, Palabra valor) {
		this.contenido.put(clave, valor);
	}
	
	public void limpiarContenido() {
		this.contenido.clear();
	}
	
}
