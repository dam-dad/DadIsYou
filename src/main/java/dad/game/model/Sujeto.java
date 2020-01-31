package dad.game.model;

import dad.App;

public class Sujeto extends Palabra {
	
	private SujetoEnum nombre;

	public Sujeto() {
		super();
	}
	
	public Sujeto(SujetoEnum nombre, String imagen, Posicion posicion) {
		super(posicion);
		this.nombre = nombre;
		this.setImagen(App.getRuta() + nombre.toString().toLowerCase() + ".png");
	}

	public SujetoEnum getNombre() {
		return nombre;
	}

	public void setNombre(SujetoEnum nombre) {
		this.nombre = nombre;
		this.setImagen(App.getRuta() + nombre.toString().toLowerCase() + ".png");
	}
	
}
