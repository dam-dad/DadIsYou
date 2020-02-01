package dad.game.model;

import dad.App;

public class Objeto<T> {

	private T nombre;
	private String imagen;
	private Posicion posicion;
	private char codigo;

	public Objeto() {}
	
	public Objeto(T nombre) {
		this(nombre, ' ', null);
		setNombre(nombre);
	}

	public Objeto(T nombre, char codigo) {
		this(nombre, codigo, null);
	}

	public Objeto(T nombre, Posicion posicion) {
		this(nombre, ' ', posicion);
	}

	public Objeto(T nombre, char codigo, Posicion posicion) {
		setNombre(nombre);
		this.codigo = codigo;
		this.posicion = posicion;
	}

	public T getNombre() {
		return nombre;
	}

	public void setNombre(T nombre) {
		this.nombre = nombre;
		this.setImagen(nombre.toString().toLowerCase());
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
