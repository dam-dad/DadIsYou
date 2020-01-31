package dad.game.model;

import dad.App;

public class Objeto {

	private String imagen;
	private Posicion posicion;
	
	public Objeto() {}

	public Objeto(Posicion posicion) {
		this.posicion = posicion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String nombre) {
		this.imagen = App.getRuta() + nombre + App.getImagenExtension();
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	
}
