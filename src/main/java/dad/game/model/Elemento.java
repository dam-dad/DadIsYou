package dad.game.model;

import java.util.ArrayList;

public class Elemento extends Objeto {

	private SujetoEnum nombre;
	private ArrayList<AccionEnum> estados;
	
	public Elemento() {}

	public Elemento(SujetoEnum nombre) {
		this.nombre = nombre;
		this.setImagen(nombre.toString().toLowerCase());
	}

	public Elemento(SujetoEnum nombre, Posicion posicion) {
		super(posicion);
		this.nombre = nombre;
		this.setImagen(nombre.toString().toLowerCase());
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
