package dad.game.model;

import dad.App;

public class Verbo extends Palabra {
	
	private VerboEnum nombre;

	public Verbo() {
		super();
	}
	
	public Verbo(VerboEnum nombre, String imagen, Posicion posicion) {
		super(posicion);
		this.nombre = nombre;
		this.setImagen(App.getRuta() + nombre.toString().toLowerCase() + ".png");
	}

	public VerboEnum getNombre() {
		return nombre;
	}

	public void setNombre(VerboEnum nombre) {
		this.nombre = nombre;
		this.setImagen(App.getRuta() + nombre.toString().toLowerCase() + ".png");
	}
}
