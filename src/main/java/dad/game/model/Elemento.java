package dad.game.model;

import java.util.ArrayList;

public class Elemento extends Objeto<SujetoEnum> {

	private ArrayList<AccionEnum> estados;

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
