package dad.game.model;

import java.util.ArrayList;

public class Tablero {
	private int cantidadColumnas;
	private int cantidadFilas;
	private Objeto<?>[][] objetos;
	private ArrayList<Objeto<?>[][]> historial;
	private ArrayList<Objeto<?>[]> frases;
	private ArrayList<Objeto<?>> objetosSegundoPlano;

	public Tablero() {
	}

	public Tablero(int cantidadColumnas, int cantidadFilas) {
		this.cantidadColumnas = cantidadColumnas;
		this.cantidadFilas = cantidadFilas;
		this.objetos = new Objeto[cantidadColumnas][cantidadFilas];
		this.historial = new ArrayList<Objeto<?>[][]>();
		this.frases = new ArrayList<Objeto<?>[]>();
		this.objetosSegundoPlano = new ArrayList<Objeto<?>>();
	}

	public int getCantidadColumnas() {
		return cantidadColumnas;
	}

	public int getCantidadFilas() {
		return cantidadFilas;
	}

	public ArrayList<Objeto<?>[][]> getHistorial() {
		return historial;
	}

	public void setHistorial(ArrayList<Objeto<?>[][]> historial) {
		this.historial = historial;
	}

	public Objeto<?>[][] getPosicionObjetos() {
		return objetos;
	}

	public void setPosicionObjetos(Objeto<?>[][] posicionObjetos) {
		this.objetos = posicionObjetos;
	}

	public void cargarNivel(Objeto<?>[][] nivel) {
		this.objetos = nivel;
		comprobarFrases();
		asignarEstados();
	}

	public void mover(DireccionEnum direccion) {
		ArrayList<Objeto<?>> elementosYou = buscarElemento(AccionEnum.YOU);

		if (elementosYou.size() > 0) {
			int x = 0, y = 0;
			if (direccion == DireccionEnum.ARRIBA) {
				y = -1;
			} else if (direccion == DireccionEnum.ABAJO) {
				y = 1;
			} else if (direccion == DireccionEnum.DERECHA) {
				x = 1;
			} else if (direccion == DireccionEnum.IZQUIERDA) {
				x = -1;
			}
			for (Objeto<?> you : elementosYou) {
				if (you.getPosicion().getY() + y >= 0 && you.getPosicion().getY() + y < cantidadFilas
						&& you.getPosicion().getX() + x >= 0 && you.getPosicion().getX() + x < cantidadColumnas) {
					if (objetos[you.getPosicion().getY() + y][you.getPosicion().getX() + x] != null) {
						objetosSegundoPlano.add(objetos[you.getPosicion().getY() + y][you.getPosicion().getX() + x]);
					}
					objetos[you.getPosicion().getY() + y][you.getPosicion().getX()
							+ x] = objetos[you.getPosicion().getY()][you.getPosicion().getX()];
					objetos[you.getPosicion().getY()][you.getPosicion().getX()] = null;
					for (Objeto<?> objetoSegundoPlano : objetosSegundoPlano) {
						if (objetoSegundoPlano.getPosicion().getX() == you.getPosicion().getX()
								&& objetoSegundoPlano.getPosicion().getY() == you.getPosicion().getY()) {
							objetos[you.getPosicion().getY()][you.getPosicion().getX()] = objetoSegundoPlano;
						}
					}
					objetosSegundoPlano.remove(objetos[you.getPosicion().getY()][you.getPosicion().getX()]);
					you.getPosicion().mover(direccion, 1);
				}
			}

			// mostrarTablero();
			comprobarWin();
			comprobarFrases();
			// mostrarFrases();
			asignarEstados();
			// mostrarEstados();
			comprobarDefeat();
		}
	}

	private void comprobarDefeat() {
		ArrayList<Objeto<?>> elementosYou = buscarElemento(AccionEnum.YOU);
		if (elementosYou.size() == 0) {
			System.out.println("¡¡¡¡ HAS PERDIDO !!!! :( ");
		}
	}

	private void comprobarWin() {
		for (Objeto<?> elemento : objetosSegundoPlano) {
			for (int i = 0; i < elemento.getEstados().size(); i++) {
				if (elemento.getEstados().get(i) == AccionEnum.WIN) {
					System.out.println("¡¡¡¡ HAS GANADO !!!!");
				}
			}
		}
	}

	private ArrayList<Objeto<?>> buscarElemento(Object tipo) {
		ArrayList<Objeto<?>> elementos = new ArrayList<Objeto<?>>();
		for (int i = 0; i < objetos.length; i++) {
			for (int j = 0; j < objetos[i].length; j++) {
				if (objetos[i][j] != null && objetos[i][j].getTipo() == TipoEnum.ELEMENTO) {
					for (int x = 0; x < objetos[i][j].getEstados().size(); x++) {
						if (objetos[i][j].getEstados().get(x) == tipo) {
							elementos.add(objetos[i][j]);
						}
					}
				}
			}
		}
		return elementos;
	}

	private void comprobarFrases() {
		frases.clear();
		for (int i = 0; i < objetos.length; i++) {
			for (int j = 0; j < objetos[i].length; j++) {
				if (objetos[i][j] != null && objetos[i][j].getTipo() == TipoEnum.SUJETO) {
					// Comprobar hacia la derecha si se forma una frase
					if (j + 2 <= cantidadColumnas) { // Comprobar si la posible frase está dentro del escenario
						if (objetos[i][j + 1] != null && objetos[i][j + 1].getTipo() == TipoEnum.VERBO) {
							if (objetos[i][j + 2] != null && (objetos[i][j + 2].getTipo() == TipoEnum.ACCION
									|| objetos[i][j + 2].getTipo() == TipoEnum.SUJETO)) {
								Objeto<?>[] frase = { objetos[i][j], objetos[i][j + 1], objetos[i][j + 2] };
								frases.add(frase);
							}
						}
					}
					// Comprobar hacia abajo si se forma una frase
					if (i + 2 <= cantidadFilas) { // Comprobar si la posible frase está dentro del escenario
						if (objetos[i + 1][j] != null && objetos[i + 1][j].getTipo() == TipoEnum.VERBO) {
							if (objetos[i + 2][j] != null && (objetos[i + 2][j].getTipo() == TipoEnum.ACCION
									|| objetos[i + 2][j].getTipo() == TipoEnum.SUJETO)) {
								Objeto<?>[] frase = { objetos[i][j], objetos[i + 1][j], objetos[i + 2][j] };
								frases.add(frase);
							}
						}
					}
				}
			}
		}
	}

	private void asignarEstados() {
		ArrayList<Objeto<?>> elementos = new ArrayList<Objeto<?>>();
		for (int i = 0; i < objetos.length; i++) {
			for (int j = 0; j < objetos[i].length; j++) {
				Objeto<?> elemento = objetos[i][j];
				if (elemento != null && elemento.getTipo() == TipoEnum.ELEMENTO) {
					elemento.limpiarEstados();
					elementos.add(elemento);
				}
			}
		}
		for (Objeto<?>[] frase : frases) {
			for (Objeto<?> elemento : elementos) {
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
		for (int i = 0; i < objetos.length; i++) {
			for (int j = 0; j < objetos[i].length; j++) {
				Objeto elemento = objetos[i][j];
				if (elemento != null && elemento.getTipo() == TipoEnum.ELEMENTO) {
					System.out.println(elemento.getNombre() + " => " + elemento.getEstados());
				}
			}
		}
	}

	private void mostrarTablero() {
		System.out.println("Tablero: ");
		for (int i = 0; i < objetos.length; i++) {
			for (int j = 0; j < objetos[i].length; j++) {
				if (objetos[i][j] != null) {
					System.out.print(objetos[i][j].toString());
				} else {
					System.out.print("·");
				}
			}
			System.out.println();
		}
	}
}
