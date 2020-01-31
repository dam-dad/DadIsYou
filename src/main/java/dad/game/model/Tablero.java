package dad.game.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Tablero {
	private int cantidadColumnas;
	private int cantidadFilas;
	private Objeto[][] posicionObjetos;
	private ArrayList<Objeto[][]> historial;
	private HashMap<Objeto, Objeto> frases = new HashMap<Objeto, Objeto>();
	
	public Tablero() {}
	
	public Tablero(int cantidadColumnas, int cantidadFilas) {
		this.cantidadColumnas = cantidadColumnas;
		this.cantidadFilas = cantidadFilas;
		this.posicionObjetos = new Objeto[cantidadColumnas][cantidadFilas];
		this.historial = new ArrayList<Objeto[][]>();
	}

	public int getCantidadColumnas() {
		return cantidadColumnas;
	}

	public int getCantidadFilas() {
		return cantidadFilas;
	}

	public ArrayList<Objeto[][]> getHistorial() {
		return historial;
	}

	public void setHistorial(ArrayList<Objeto[][]> historial) {
		this.historial = historial;
	}
	
	public void cargarNivel(Objeto[][] nivel) {
		this.posicionObjetos = nivel;
	}
	
	public void mover(DireccionEnum direccion) {
		comprobarPalabras();
		if(direccion == DireccionEnum.ARRIBA) {
			//this.posicionObjetos;
		}
	}
	
	private void comprobarPalabras() {
		for (int i = 0; i < posicionObjetos.length; i++) {
			for (int j = 0; j < posicionObjetos[i].length; j++) {
				if(posicionObjetos[i][j] instanceof Sujeto) {
					// Comprobar hacia la derecha si se forma una frase
					if (posicionObjetos[i][j+1] instanceof Verbo) {
						if (posicionObjetos[i][j+2] instanceof Accion || posicionObjetos[i][j+2] instanceof Sujeto) {
							frases.put(posicionObjetos[i][j], posicionObjetos[i][j+2]);
						}
					}
					// Comprobar hacia abajo si se forma una frase
					if (posicionObjetos[i+1][j] instanceof Verbo) {
						if (posicionObjetos[i+2][j] instanceof Accion || posicionObjetos[i+2][j] instanceof Sujeto) {
							frases.put(posicionObjetos[i][j], posicionObjetos[i+2][j]);
						}
					}
							// ###########  desbordamiento de array, comprobar si nos salimos del mapa
				}
			}
		}
	}
}
