package dad.game.model;

import java.util.ArrayList;

public class Tablero {
	private int cantidadColumnas;
	private int cantidadFilas;
	private Objeto[][] posicionObjetos;
	private ArrayList<Objeto[][]> historial;
	private ArrayList<Objeto[]> frases;

	public Tablero() {
	}

	public Tablero(int cantidadColumnas, int cantidadFilas) {
		this.cantidadColumnas = cantidadColumnas;
		this.cantidadFilas = cantidadFilas;
		this.posicionObjetos = new Objeto[cantidadColumnas][cantidadFilas];
		this.historial = new ArrayList<Objeto[][]>();
		this.frases = new ArrayList<Objeto[]>();
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
		if (direccion == DireccionEnum.ARRIBA) {
			// this.posicionObjetos;
		}
		mostrarTablero();
	}

	private void comprobarPalabras() {
		frases.clear();
		for (int i = 0; i < posicionObjetos.length; i++) {
			for (int j = 0; j < posicionObjetos[i].length; j++) {
				if (posicionObjetos[i][j] instanceof Sujeto) {
					// Comprobar hacia la derecha si se forma una frase
					if (j + 2 <= cantidadColumnas) { // Comprobar si la posible frase está dentro del escenario
						if (posicionObjetos[i][j + 1] instanceof Verbo) {
							if (posicionObjetos[i][j + 2] instanceof Accion
									|| posicionObjetos[i][j + 2] instanceof Sujeto) {
								Objeto[] frase = {posicionObjetos[i][j], posicionObjetos[i][j + 2]};
								frases.add(frase);
							}
						}
					}
					// Comprobar hacia abajo si se forma una frase
					if (i + 2 <= cantidadFilas) { // Comprobar si la posible frase está dentro del escenario
						if (posicionObjetos[i + 1][j] instanceof Verbo) {
							if (posicionObjetos[i + 2][j] instanceof Accion
									|| posicionObjetos[i + 2][j] instanceof Sujeto) {
								Objeto[] frase = {posicionObjetos[i][j], posicionObjetos[i + 2][j]};
								frases.add(frase);
							}
						}
					}
				}
			}
		}
		System.out.println("Frases: ");
		for (int i = 0; i < frases.size(); i++) {
			System.out.println(frases.get(i)[0].getNombre() + " => " + frases.get(i)[1]);
		}
	}
	
	private void mostrarTablero() {
		System.out.println("Tablero: ");
		for (int i = 0; i < posicionObjetos.length; i++) {
			for (int j = 0; j < posicionObjetos[i].length; j++) {
				if (posicionObjetos[i][j] != null) {
					System.out.print(posicionObjetos[i][j].toString());
				} else {
					System.out.print("·");
				}
			}
			System.out.println();
		}
	}
}
