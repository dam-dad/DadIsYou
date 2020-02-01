package dad.game.model;

import dad.App;

public class Objeto {

	private String imagen;
	private Posicion posicion;
	private char codigo;
	
	public Objeto() {}

	public Objeto(char codigo) {
		this.codigo = codigo;
	}

	public Objeto(Posicion posicion) {
		this.posicion = posicion;
	}

	public Objeto(char codigo, Posicion posicion) {
		this.codigo = codigo;
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
	
	@Override
	public String toString() {
		return "" + codigo;
	}
	
}
