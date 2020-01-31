package dad.game.model;

import dad.App;

public class Accion extends Palabra {
	
	private AccionEnum nombre;

	public Accion() {
		super();
	}
	
	public Accion(AccionEnum nombre, String imagen, Posicion posicion) {
		super(posicion);
		this.nombre = nombre;
		this.setImagen(App.getRuta() + nombre.toString().toLowerCase() + ".png");
	}

	public AccionEnum getNombre() {
		return nombre;
	}

	public void setNombre(AccionEnum nombre) {
		this.nombre = nombre;
		this.setImagen(App.getRuta() + nombre.toString().toLowerCase() + ".png");
	}
}
