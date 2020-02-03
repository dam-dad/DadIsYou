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

	public Objeto[][] getPosicionObjetos() {
		return posicionObjetos;
	}

	public void setPosicionObjetos(Objeto[][] posicionObjetos) {
		this.posicionObjetos = posicionObjetos;
	}

	public void cargarNivel(Objeto[][] nivel) {
		this.posicionObjetos = nivel;
	}

	public void mover(DireccionEnum direccion) {
		comprobarPalabras();
		mostrarFrases();
		asignarEstados();
		mostrarEstados();
		
		ArrayList<Objeto> elementosYOU = new ArrayList<Objeto>();
		for (int i = 0; i < posicionObjetos.length; i++) {
			for (int j = 0; j < posicionObjetos[i].length; j++) {
				if(posicionObjetos[i][j] != null && 
						posicionObjetos[i][j].getTipo() == TipoEnum.ELEMENTO) {
					for(int x = 0; x < posicionObjetos[i][j].getEstados().size(); x++ ) {
						if( posicionObjetos[i][j].getEstados().get(x) == AccionEnum.YOU ) {
							elementosYOU.add(posicionObjetos[i][j]);
						}
					}
				}
			}
		}
			
		if (direccion == DireccionEnum.ARRIBA) {
			for(Objeto you : elementosYOU) {
				posicionObjetos[you.getPosicion().getX()][you.getPosicion().getY()-1] = posicionObjetos[you.getPosicion().getX()][you.getPosicion().getY()];
				posicionObjetos[you.getPosicion().getX()][you.getPosicion().getY()] = null;
				you.getPosicion().mover(DireccionEnum.ARRIBA, 1);
			}
		} else if(direccion == DireccionEnum.ABAJO) {
			for(Objeto you : elementosYOU) {
				posicionObjetos[you.getPosicion().getX()][you.getPosicion().getY()+1] = posicionObjetos[you.getPosicion().getX()][you.getPosicion().getY()];
				posicionObjetos[you.getPosicion().getX()][you.getPosicion().getY()] = null;
				you.getPosicion().mover(DireccionEnum.ARRIBA, 1);
			}
		} else if(direccion == DireccionEnum.DERECHA) {
			for(Objeto you : elementosYOU) {
				posicionObjetos[you.getPosicion().getX()+1][you.getPosicion().getY()] = posicionObjetos[you.getPosicion().getX()][you.getPosicion().getY()];
				posicionObjetos[you.getPosicion().getX()][you.getPosicion().getY()] = null;
				you.getPosicion().mover(DireccionEnum.ARRIBA, 1);
			}
		} else if(direccion == DireccionEnum.IZQUIERDA) {
			for(Objeto you : elementosYOU) {
				posicionObjetos[you.getPosicion().getX()-1][you.getPosicion().getY()] = posicionObjetos[you.getPosicion().getX()][you.getPosicion().getY()];
				posicionObjetos[you.getPosicion().getX()][you.getPosicion().getY()] = null;
				you.getPosicion().mover(DireccionEnum.ARRIBA, 1);
			}
		}
		
		mostrarTablero();
	}

	private void comprobarPalabras() {
		frases.clear();
		for (int i = 0; i < posicionObjetos.length; i++) {
			for (int j = 0; j < posicionObjetos[i].length; j++) {
				if (posicionObjetos[i][j] != null && posicionObjetos[i][j].getTipo() == TipoEnum.SUJETO) {
					// Comprobar hacia la derecha si se forma una frase
					if (j + 2 <= cantidadColumnas) { // Comprobar si la posible frase está dentro del escenario
						if (posicionObjetos[i][j + 1] != null
								&& posicionObjetos[i][j + 1].getTipo() == TipoEnum.VERBO) {
							if (posicionObjetos[i][j + 2] != null
									&& (posicionObjetos[i][j + 2].getTipo() == TipoEnum.ACCION
											|| posicionObjetos[i][j + 2].getTipo() == TipoEnum.SUJETO)) {
								Objeto[] frase = { posicionObjetos[i][j], posicionObjetos[i][j + 1],
										posicionObjetos[i][j + 2] };
								frases.add(frase);
							}
						}
					}
					// Comprobar hacia abajo si se forma una frase
					if (i + 2 <= cantidadFilas) { // Comprobar si la posible frase está dentro del escenario
						if (posicionObjetos[i + 1][j] != null
								&& posicionObjetos[i + 1][j].getTipo() == TipoEnum.VERBO) {
							if (posicionObjetos[i + 2][j] != null
									&& (posicionObjetos[i + 2][j].getTipo() == TipoEnum.ACCION
											|| posicionObjetos[i + 2][j].getTipo() == TipoEnum.SUJETO)) {
								Objeto[] frase = { posicionObjetos[i][j], posicionObjetos[i + 1][j],
										posicionObjetos[i + 2][j] };
								frases.add(frase);
							}
						}
					}
				}
			}
		}
	}

	private void asignarEstados() {
		ArrayList<Objeto> elementos = new ArrayList<Objeto>();
		for (int i = 0; i < posicionObjetos.length; i++) {
			for (int j = 0; j < posicionObjetos[i].length; j++) {
				Objeto elemento = posicionObjetos[i][j];
				if (elemento != null && elemento.getTipo() == TipoEnum.ELEMENTO) {
					elemento.limpiarEstados();
					elementos.add(elemento);
				}
			}
		}
		for (Objeto[] frase : frases) {
			for (Objeto elemento : elementos) {
				if (elemento.getNombre() == frase[0].getNombre()) {
					elemento.setEstado((AccionEnum) frase[2].getNombre());
				}
			}
		}
	}

	private void mostrarFrases() {
		System.out.println("Frases: ");
		for (int i = 0; i < frases.size(); i++) {
			System.out.println(frases.get(i)[0].getNombre() + " " + frases.get(i)[1].getNombre() + " "
					+ frases.get(i)[2].getNombre());
		}
	}
	
	private void mostrarEstados() {
		System.out.println("Estados: ");
		for (int i = 0; i < posicionObjetos.length; i++) {
			for (int j = 0; j < posicionObjetos[i].length; j++) {
				Objeto elemento = posicionObjetos[i][j];
				if (elemento != null && elemento.getTipo() == TipoEnum.ELEMENTO) {
					System.out.println(elemento.getNombre() + " => " + elemento.getEstados());
				}
			}
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
