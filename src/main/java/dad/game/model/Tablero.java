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
		this.objetos = new Objeto[cantidadColumnas][cantidadFilas];
		this.historial = new ArrayList<Objeto<?>[][]>();
		this.frases = new ArrayList<Objeto<?>[]>();
		this.objetosSegundoPlano = new ArrayList<Objeto<?>>();
	}

	public Tablero(Objeto<?>[][] nivel) {
		this();
		this.cargarNivel(nivel);
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
		this.cantidadFilas = nivel.length;
		this.cantidadColumnas = nivel[0].length;
		this.objetos = nivel;
		comprobarFrases();
		asignarEstados();
		System.out.println("TO DO : arreglar empujar varios objetos");
	}

	public void mover(DireccionEnum direccion) {
		ArrayList<Objeto<?>> elementosYou = buscarElemento(AccionEnum.YOU);

		if (elementosYou.size() > 0) {
			int[] direccionXY = direccionXY(direccion);
			for (Objeto<?> you : elementosYou) {
				int posObjetoYouX = you.getPosicion().getX();
				int posObjetoYouY = you.getPosicion().getY();
				int posNuevaPosicionX = posObjetoYouX + direccionXY[0];
				int posNuevaPosicionY = posObjetoYouY + direccionXY[1];
				// Comprobar limite del mapa
				if (comprobarBordeMapa(posNuevaPosicionX, posNuevaPosicionY)) {
					// Comprobar si la nueva posicion es STOP
					if (comprobarStop(you, direccionXY[0], direccionXY[1])) { // true puede pasar
						if (objetos[posNuevaPosicionY][posNuevaPosicionX] != null) {
							moverColindantes(direccion, you, false);
						}
						// Si puede moverse
						if (objetos[posNuevaPosicionY][posNuevaPosicionX] == null) {
							objetos[posNuevaPosicionY][posNuevaPosicionX] = objetos[posObjetoYouY][posObjetoYouX]; // Movimiento
							objetos[posObjetoYouY][posObjetoYouX] = null;
							// Colocar el objeto que está en segundo plano (si lo hubiese)
							for (Objeto<?> objetoSegundoPlano : objetosSegundoPlano) {
								if (objetoSegundoPlano.getPosicion().getX() == you.getPosicion().getX()
										&& objetoSegundoPlano.getPosicion().getY() == you.getPosicion().getY()) {
									objetos[posObjetoYouY][posObjetoYouX] = objetoSegundoPlano;
								}
							}
							objetosSegundoPlano.remove(objetos[posObjetoYouY][posObjetoYouX]);
							you.getPosicion().mover(direccion, 1);
							comprobarDefeat(you);
							comprobarWin(you);
						}
					}
				}
			}
			
			// mostrarTablero();
			comprobarFrases();
			// mostrarFrases();
			asignarEstados();
			// mostrarEstados();
		}
	}

	private void moverColindantes(DireccionEnum direccion, Objeto<?> objetoEmpujado, boolean mover) {
		int[] direccionXY = direccionXY(direccion);
		int posObjetoEmpujadoX = objetoEmpujado.getPosicion().getX();
		int posObjetoEmpujadoY = objetoEmpujado.getPosicion().getY();
		int posNuevaPosicionX = posObjetoEmpujadoX + direccionXY[0];
		int posNuevaPosicionY = posObjetoEmpujadoY + direccionXY[1];
		if (comprobarBordeMapa(posNuevaPosicionX, posNuevaPosicionY)) {
			Objeto<?> nuevaPosicion = objetos[posNuevaPosicionY][posNuevaPosicionX];
			if (nuevaPosicion == null) {
				objetos[posNuevaPosicionY][posNuevaPosicionX] = objetoEmpujado; // Movimiento
				objetos[posObjetoEmpujadoY][posObjetoEmpujadoX] = null;
				objetoEmpujado.getPosicion().mover(direccion, 1);
			} else {
				if (comprobarStop(objetoEmpujado, direccionXY[0], direccionXY[1])) { // true pude pasar
					boolean isPush = false;
					for (int i = 0; i < nuevaPosicion.getEstados().size(); i++) {
						if (nuevaPosicion.getEstados().get(i) == AccionEnum.PUSH) {
							isPush = true;
						}
					}
					// La nueva posicion es PUSH
					if (nuevaPosicion.getTipo() == TipoEnum.SUJETO || nuevaPosicion.getTipo() == TipoEnum.VERBO
							|| nuevaPosicion.getTipo() == TipoEnum.ACCION || isPush) {
						if (mover) {
							moverColindantes(direccion, objetos[posNuevaPosicionY][posNuevaPosicionX], true);
							objetos[posNuevaPosicionY][posNuevaPosicionX] = objetoEmpujado; // Movimiento
							objetos[posObjetoEmpujadoY][posObjetoEmpujadoX] = null;
							objetoEmpujado.getPosicion().mover(direccion, 1);
						} else {
							moverColindantes(direccion, objetos[posNuevaPosicionY][posNuevaPosicionX], true);
						}
					} else {
						objetosSegundoPlano.add(nuevaPosicion);
						objetos[posNuevaPosicionY][posNuevaPosicionX] = null;
						if (mover) {
							objetos[posNuevaPosicionY][posNuevaPosicionX] = objetoEmpujado; // Movimiento
							objetos[posObjetoEmpujadoY][posObjetoEmpujadoX] = null;
							objetoEmpujado.getPosicion().mover(direccion, 1);
						}
					}
				}
			}
		}
	}

	private boolean comprobarStop(Objeto<?> elementoAMover, int direccionX, int direccionY) {
		boolean stop = true;
		Objeto<?> elementoColision = objetos[elementoAMover.getPosicion().getY() + direccionY][elementoAMover
				.getPosicion().getX() + direccionX];
		if (elementoColision != null) {
			ArrayList<AccionEnum> estados = elementoColision.getEstados();
			for (int i = 0; i < estados.size(); i++) {
				if (estados.get(i) == AccionEnum.STOP) {
					stop = false;
				}
			}
		}
		return stop;
	}

	private void comprobarDefeat(Objeto<?> you) {
		ArrayList<Objeto<?>> elementosYou = buscarElemento(AccionEnum.YOU);
		boolean defeat = false;
		for (Objeto<?> elemento : objetosSegundoPlano) {
			for (int i = 0; i < elemento.getEstados().size(); i++) {
				if (elemento.getEstados().get(i) == AccionEnum.DEFEAT
						&& elemento.getPosicion().getX() == you.getPosicion().getX()
						&& elemento.getPosicion().getY() == you.getPosicion().getY()) {
					defeat = true;
				}
			}
		}
		if (elementosYou.size() == 0) {
			defeat = true;
		}
		if (defeat) {
			System.out.println("¡¡¡¡ HAS PERDIDO !!!!");
		}
	}

	private void comprobarWin(Objeto<?> you) {
		for (Objeto<?> elemento : objetosSegundoPlano) {
			for (int i = 0; i < elemento.getEstados().size(); i++) {
				if (elemento.getEstados().get(i) == AccionEnum.WIN
						&& elemento.getPosicion().getX() == you.getPosicion().getX()
						&& elemento.getPosicion().getY() == you.getPosicion().getY()) {
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

	private int[] direccionXY(DireccionEnum direccion) {
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
		return new int[] { x, y };
	}

	private boolean comprobarBordeMapa(int x, int y) {
		return y >= 0 && y < cantidadFilas && x >= 0 && x < cantidadColumnas;
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
