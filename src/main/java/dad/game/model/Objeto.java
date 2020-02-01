package dad.game.model;

import java.util.ArrayList;

import dad.App;

public class Objeto<T> {

	private T nombre;
	private String imagen;
	private Posicion posicion;
	private char codigo;
	private ArrayList<AccionEnum> estados;
	private TipoEnum tipo;

	public Objeto() {}
	
	public Objeto(T nombre, TipoEnum tipo) {
		this(nombre, tipo, ' ', null);
	}

	public Objeto(T nombre, TipoEnum tipo, char codigo) {
		this(nombre, tipo, codigo, null);
	}

	public Objeto(T nombre, TipoEnum tipo, Posicion posicion) {
		this(nombre, tipo, ' ', posicion);
	}

	public Objeto(T nombre, TipoEnum tipo, char codigo, Posicion posicion) {
		setNombre(nombre);
		this.tipo = tipo;
		this.codigo = codigo;
		this.posicion = posicion;
		this.estados = new ArrayList<AccionEnum>();
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
	
	public ArrayList<AccionEnum> getEstados() {
		return estados;
	}

	public void setEstados(ArrayList<AccionEnum> estados) {
		this.estados = estados;
	}

	public void setEstado(AccionEnum estado) {
		this.estados.add(estado);
	}
	
	public void limpiarEstados() {
		this.estados.clear();
	}

	public TipoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoEnum tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "" + codigo;
	}
	
}
