package dad.game.model;

import java.util.ArrayList;

import dad.App;

public class Objeto {

	private SujetoEnum nombre;
	private String imagen;
	private Posicion posicion;
	private ArrayList<AccionEnum> estados;
	
	public Objeto() {}

	public Objeto(SujetoEnum nombre) {
		this.nombre = nombre;
		this.imagen = App.getRuta() + this.nombre.toString().toLowerCase() + ".png";
	}

	public Objeto(SujetoEnum nombre, Posicion posicion) {
		this(nombre);
		this.posicion = posicion;
	}

	public SujetoEnum getNombre() {
		return nombre;
	}

	public void setNombre(SujetoEnum nombre) {
		this.nombre = nombre;
		this.imagen = App.getRuta() + this.nombre.toString().toLowerCase() + ".png";
	}

	public String getImagen() {
		return imagen;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
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
