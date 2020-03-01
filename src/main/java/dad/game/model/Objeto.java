package dad.game.model;

import java.util.ArrayList;

import dad.App;
import dad.game.model.enums.PropiedadEnum;
import dad.game.model.enums.SujetoEnum;
import dad.game.model.enums.TipoEnum;

/**
 * Objeto del juego
 * @param <T> Tipo de objeto
 */
public class Objeto<T> {

	private T nombre;
	private String imagen;
	private Posicion posicion;
	private char codigo;
	private ArrayList<PropiedadEnum> estados;
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
		this.tipo = tipo;
		setNombre(nombre);
		this.codigo = codigo;
		this.posicion = posicion;
		this.estados = new ArrayList<PropiedadEnum>();
	}

	public T getNombre() {
		return nombre;
	}

	/**
	 * @param nombre Nombre del objeto
	 */
	public void setNombre(T nombre) {
		this.nombre = nombre;
		this.setImagen(nombre.toString().toLowerCase());
	}

	/**
	 * @param nombre Nombre (SujetoEnum) del objeto
	 */
	@SuppressWarnings("unchecked")
	public void setNombre(SujetoEnum nombre) {
		this.nombre = (T) nombre;
		this.setImagen(nombre.toString().toLowerCase());
	}

	public String getImagen() {
		return imagen;
	}

	/**
	 * Crea la ruta de la imagen del objeto
	 * @param nombre Nombre del objeto
	 */
	private void setImagen(String nombre) {
		this.imagen = App.getRuta() + tipo.toString().toLowerCase() + "_" + nombre + App.getImagenExtension();
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	
	public ArrayList<PropiedadEnum> getEstados() {
		return estados;
	}

	public void setEstados(ArrayList<PropiedadEnum> estados) {
		this.estados = estados;
	}

	public void setEstado(PropiedadEnum estado) {
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
