package dad.game.model;

import java.util.ArrayList;

public class Elemento extends Objeto {

	private SujetoEnum nombre;
	private ArrayList<AccionEnum> estados;
	
	public Elemento() {}

	public Elemento(SujetoEnum nombre) {
		setNombre(nombre);
	}

	public Elemento(SujetoEnum nombre, char codigo) {
		super(codigo);
		setNombre(nombre);
	}

	public Elemento(SujetoEnum nombre, Posicion posicion) {
		super(posicion);
		setNombre(nombre);
	}

	public Elemento(SujetoEnum nombre, char codigo, Posicion posicion) {
		super(codigo, posicion);
		setNombre(nombre);
	}

	public SujetoEnum getNombre() {
		return nombre;
	}

	public void setNombre(SujetoEnum nombre) {
		this.nombre = nombre;
		this.setImagen(nombre.toString().toLowerCase());
	}

	public ArrayList<AccionEnum> getEstado() {
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
	
}
