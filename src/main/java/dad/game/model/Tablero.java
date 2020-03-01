package dad.game.model;

import dad.App;
import dad.game.model.enums.PropiedadEnum;
import dad.game.model.enums.DireccionEnum;
import dad.game.model.enums.OperadorEnum;
import dad.game.model.enums.SujetoEnum;
import dad.game.model.enums.TipoEnum;
import java.util.ArrayList;

/**
 * Tablero del juego
 */
public class Tablero {
	
	private int cantidadColumnas;
	private int cantidadFilas;
	private Objeto<?>[][] objetos; // Mapa de objetos
	private ArrayList<Objeto<?>[][]> historial; // No implementado
	private ArrayList<Objeto<?>[]> frases;
	private ArrayList<Objeto<?>> elementosSegundoPlano;

	public Tablero() {
		this.historial = new ArrayList<Objeto<?>[][]>();
		this.frases = new ArrayList<Objeto<?>[]>();
		this.elementosSegundoPlano = new ArrayList<Objeto<?>>();
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
	
	public ArrayList<Objeto<?>> getElementosSegundoPlano() {
		return elementosSegundoPlano;
	}
	
	public int getCantidadFrases() {
		return frases.size();
	}

	/**
	 * Carga un nivel
	 * @param nivel Mapa de objetos
	 */
	public void cargarNivel(Objeto<?>[][] nivel) {
		this.cantidadFilas = nivel.length;
		this.cantidadColumnas = nivel[0].length;
		this.objetos = nivel;
		comprobarFrases();
		asignarEstados();
	}
	
	/**
	 * Método que mueve a los personajes (si se pudiese)
	 * @param direccion Dirección de movimiento
	 */
	public void mover(DireccionEnum direccion) {
		ArrayList<Objeto<?>> elementosYou = buscarElemento(PropiedadEnum.YOU, direccion); // Personajes (elementos con el estado YOU)

		if (elementosYou.size() > 0) {
			int[] direccionXY = direccionXY(direccion);
			for (Objeto<?> you : elementosYou) {
				int posObjetoYouX = you.getPosicion().getX();
				int posObjetoYouY = you.getPosicion().getY();
				int posNuevaPosicionX = posObjetoYouX + direccionXY[0];
				int posNuevaPosicionY = posObjetoYouY + direccionXY[1];
				// Comprobar límite del mapa
				if (comprobarBordeMapa(posNuevaPosicionX, posNuevaPosicionY)) {
					// Comprobar si la nueva posicion es STOP
					if (comprobarStop(you, direccionXY[0], direccionXY[1])) { // True no es STOP
						if (objetos[posNuevaPosicionY][posNuevaPosicionX] != null) { // Si no hay aire
							moverColindantes(direccion, you, false); // Mueve los objetos que tiene delante
						}
						// Si tiene aire delante puede moverse 
						if (objetos[posNuevaPosicionY][posNuevaPosicionX] == null) {
							objetos[posNuevaPosicionY][posNuevaPosicionX] = objetos[posObjetoYouY][posObjetoYouX]; // Se mueve
							objetos[posObjetoYouY][posObjetoYouX] = null;
							// Coloca el elemento que está en segundo plano en la posición anterior (si lo hubiese)
							for (Objeto<?> objetoSegundoPlano : elementosSegundoPlano) {
								if (objetoSegundoPlano.getPosicion().compararPosicion(you.getPosicion())) {
									objetos[posObjetoYouY][posObjetoYouX] = objetoSegundoPlano;
								}
							}
							// Se elimina del array de elementos si fue colocado en el mapa
							elementosSegundoPlano.remove(objetos[posObjetoYouY][posObjetoYouX]);
							you.getPosicion().mover(direccion, 1); // Actualiza la posición en el atributo del objeto
							comprobaciones(you, direccion);
						} else {
							if (buscarEstado(objetos[posNuevaPosicionY][posNuevaPosicionX], PropiedadEnum.YOU)) {
								you.getPosicion().mover(direccion, 1); // Actualiza la posición en el atributo del objeto
							}
						}
					}
				}
			}
		}

		// mostrarTablero();
		// mostrarSegundoPlano();
		comprobarFrases();
		// mostrarFrases();
		asignarEstados();
		// mostrarEstados();
		comprobaciones(null, direccion);
	}
	
	/**
	 * Realiza una serie de comprobaciones en el mapa
	 * @param objeto Objeto con el que se realizarán las comprobaciones
	 */
	private void comprobaciones(Objeto<?> objeto, DireccionEnum direccion) {
		comprobarDefeat(objeto);
		comprobarSink(null, null);
		comprobarWin(objeto);
	}

	/**
	 * Mueve de forma recursiva los objetos que son empujados
	 * @param direccion Dirección de movimiento
	 * @param objetoEmpujado Objeto que es empujado
	 * @param mover True: se desea mover el objeto; False: no queremos moverlo ya que es el objeto original que empuja y se mueve en el método "mover()"
	 */
	private void moverColindantes(DireccionEnum direccion, Objeto<?> objetoEmpujado, boolean mover) {
		int[] direccionXY = direccionXY(direccion);
		int posObjetoEmpujadoX = objetoEmpujado.getPosicion().getX();
		int posObjetoEmpujadoY = objetoEmpujado.getPosicion().getY();
		int posNuevaPosicionX = posObjetoEmpujadoX + direccionXY[0];
		int posNuevaPosicionY = posObjetoEmpujadoY + direccionXY[1];
		comprobarSink(objetos[posObjetoEmpujadoY][posObjetoEmpujadoX], direccion);
		// Comprobar límite del mapa
		if (comprobarBordeMapa(posNuevaPosicionX, posNuevaPosicionY)) { 
			Objeto<?> elementoPrimerPlanoNuevaPosicon = objetos[posNuevaPosicionY][posNuevaPosicionX];
			if (elementoPrimerPlanoNuevaPosicon == null) { // En la siguiente posición hay aire
				objetos[posNuevaPosicionY][posNuevaPosicionX] = objetos[posObjetoEmpujadoY][posObjetoEmpujadoX]; // Se mueve
				objetos[posObjetoEmpujadoY][posObjetoEmpujadoX] = null;
				objetoEmpujado.getPosicion().mover(direccion, 1);
			} else { // En la siguiente posición no hay aire
				boolean isElementoYou = buscarEstado(objetos[posNuevaPosicionY][posNuevaPosicionX], PropiedadEnum.YOU);
				if (!isElementoYou) { // Si el objeto siguiente no es YOU
					// Comprobar si la nueva posicion es STOP
					if (comprobarStop(objetos[posObjetoEmpujadoY][posObjetoEmpujadoX], direccionXY[0], direccionXY[1])) { // True no es STOP
						ArrayList<Objeto<?>> elementosNuevaPosicion = new ArrayList<Objeto<?>>(); // Elementos en la nueva posicion (tablero y segundo plano)
						elementosNuevaPosicion.add(elementoPrimerPlanoNuevaPosicon);
						elementosNuevaPosicion.addAll(buscarElementoSegundoPlano(elementoPrimerPlanoNuevaPosicon.getPosicion()));
						for (int i = 0; i < elementosNuevaPosicion.size(); i++) {
							Objeto<?> elementoNuevaPosicion = elementosNuevaPosicion.get(i);
							// TRUE si la siguiente posición puede ser empujado (tiene el estado PUSH):
							boolean isPush = buscarEstado(elementoNuevaPosicion, PropiedadEnum.PUSH);
							// La nueva posicion puede ser empujada (tiene el estado PUSH o es una palabra)
							if (elementoNuevaPosicion.getTipo() == TipoEnum.SUJETO || elementoNuevaPosicion.getTipo() == TipoEnum.OPERADOR
									|| elementoNuevaPosicion.getTipo() == TipoEnum.PROPIEDAD || isPush) {
								if (mover) { // Si se desea mover:
									// Llama de nuevo a este método para empujar el siguiente objeto
									if (i == 0) {
										moverColindantes(direccion, objetos[posNuevaPosicionY][posNuevaPosicionX], true);
									} else {
										elementosSegundoPlano.remove(elementoNuevaPosicion);
										moverColindantes(direccion, elementoNuevaPosicion, true);
									}
									if (objetos[posNuevaPosicionY][posNuevaPosicionX] == null) { // Si la nueva posición ahora es aire:
										objetos[posNuevaPosicionY][posNuevaPosicionX] = objetos[posObjetoEmpujadoY][posObjetoEmpujadoX]; // Se mueve
										objetos[posObjetoEmpujadoY][posObjetoEmpujadoX] = null;
										// Coloca el elemento que está en segundo plano en la posición anterior (si lo hubiese)
										for (Objeto<?> objetoSegundoPlano : elementosSegundoPlano) {
											if (objetoSegundoPlano.getPosicion().compararPosicion(objetoEmpujado.getPosicion())) {
												objetos[posObjetoEmpujadoY][posObjetoEmpujadoX] = objetoSegundoPlano;
											}
										}
										// Se elimina del array de elementos si fue colocado en el mapa
										elementosSegundoPlano.remove(objetos[posObjetoEmpujadoY][posObjetoEmpujadoX]);
										objetoEmpujado.getPosicion().mover(direccion, 1); // Actualiza la posición en el atributo del objeto	
									}
								} else { // Si no deseamos que se mueva
									// Llama de nuevo a este método para empujar el siguiente objeto
									if (i == 0) {
										moverColindantes(direccion, objetos[posNuevaPosicionY][posNuevaPosicionX], true);
									} else {
										elementosSegundoPlano.remove(elementoNuevaPosicion);
										moverColindantes(direccion, elementoNuevaPosicion, true);
									}
								}
							} else { // No se puede empujar, se colocará en el segundo plano
								if (i == 0 || (!mover && objetos[posNuevaPosicionY][posNuevaPosicionX] != null)) {
									elementosSegundoPlano.add(elementoNuevaPosicion);
									objetos[posNuevaPosicionY][posNuevaPosicionX] = null;
									if (mover) { // Si se desea mover:
										objetos[posNuevaPosicionY][posNuevaPosicionX] = objetoEmpujado; // Se mueve
										objetos[posObjetoEmpujadoY][posObjetoEmpujadoX] = null;
										objetoEmpujado.getPosicion().mover(direccion, 1);
									}
								}
							}
						}
					}
				} else {
					// Si somos un elemento YOU, el siguiente elemento también es YOU y no está en segundo plano:
					if (!mover && objetoEmpujado == objetos[posObjetoEmpujadoY][posObjetoEmpujadoX]) {
						elementosSegundoPlano.add(objetos[posNuevaPosicionY][posNuevaPosicionX]);
						objetos[posNuevaPosicionY][posNuevaPosicionX] = null;
					}
				}
			}
		}
	}

	/**
	 * Se comprueba si el objeto a mover tiene el estado STOP
	 * @param elementoAMover Elemento a mover
	 * @param direccionX Posición X a la que te mueves
	 * @param direccionY Posición Y a la que te mueves
	 * @return True no es STOP (puedes moverte); False es STOP (no puedes moverte)
	 */
	private boolean comprobarStop(Objeto<?> elementoAMover, int direccionX, int direccionY) {
		boolean stop = true;
		ArrayList<Objeto<?>> elementos = new ArrayList<Objeto<?>>();
		elementos.add(objetos[elementoAMover.getPosicion().getY() + direccionY][elementoAMover
				.getPosicion().getX() + direccionX]); // Elemento siguiente del que se quiere mover
		elementos.addAll(buscarElementoSegundoPlano(new Posicion(elementoAMover.getPosicion().getX() + direccionX, 
				elementoAMover.getPosicion().getY() + direccionY))); // Elementos siguientes en el segundo plano del que se quiere mover
		for (Objeto<?> elementoColision : elementos) {
			if (elementoColision != null) { // Si no es aire:
				stop = !buscarEstado(elementoColision, PropiedadEnum.STOP);
			}
		}
		return stop;
	}

	/**
	 * Comprobar si el jugador pierde la partida
	 * @param you Objeto jugador (YOU)
	 */
	private void comprobarDefeat(Objeto<?> you) {
		boolean defeat = false;
		// Si los estados YOU y DEFEAT coinciden en el mismo elemento se pierde la partida
		ArrayList<Objeto<?>> elementosYou = buscarElemento(PropiedadEnum.YOU, null);
		if (elementosYou.size() > 0) {
			defeat = buscarEstado(elementosYou.get(0), PropiedadEnum.DEFEAT);
		}
		// Si YOU está sobre el elemento DEFEAT se pierde la partida
		if (you != null) {
			for (Objeto<?> elemento : elementosSegundoPlano) {
				for (int i = 0; i < elemento.getEstados().size(); i++) {
					if (buscarEstado(elemento, PropiedadEnum.DEFEAT) && elemento.getPosicion().compararPosicion(you.getPosicion())) {
						defeat = true;
					}
				}
			}
		}
		// Si no existe ningun elemento YOU se pierde la partida
		if (elementosYou.size() == 0) {
			defeat = true;
		}
		if (defeat) {
			App.getGameController().perder(); // Se avisa al controlador de que ha perdido la partida
		}
	}
	
	/**
	 * Comprueba si hay objetos que tienen que ser desintegrados
	 * @param objeto Objeto a comprobar
	 */
	private void comprobarSink(Objeto<?> objeto, DireccionEnum direccion) {
		boolean isSink = false;
		ArrayList<Objeto<?>> elementos = buscarElemento(PropiedadEnum.SINK, null);
		for (Objeto<?> elemento : elementos) {
			ArrayList<Objeto<?>> elementosToSink = buscarElementoSegundoPlano(elemento.getPosicion());
			for (Objeto<?> elementoToSink : elementosToSink) {
				objetos[elemento.getPosicion().getY()][elemento.getPosicion().getX()] = null;
				elementosSegundoPlano.remove(elementoToSink);
				isSink = true;
			}
		}
		if (objeto != null) {
			int[] direccionXY = direccionXY(direccion);
			int posObjetoEmpujadoX = objeto.getPosicion().getX();
			int posObjetoEmpujadoY = objeto.getPosicion().getY();
			int posNuevaPosicionX = posObjetoEmpujadoX + direccionXY[0];
			int posNuevaPosicionY = posObjetoEmpujadoY + direccionXY[1];
			if (comprobarBordeMapa(posNuevaPosicionX, posNuevaPosicionY)) { 
				if (objetos[posNuevaPosicionY][posNuevaPosicionX] != null
						&& objetos[posNuevaPosicionY][posNuevaPosicionX].getTipo() == TipoEnum.ELEMENTO) {
					if (buscarEstado(objeto, PropiedadEnum.SINK)) {
						objetos[posObjetoEmpujadoY][posObjetoEmpujadoX] = null;
						objetos[posNuevaPosicionY][posNuevaPosicionX] = null;
						isSink = true;
					}
				}
			}
		}
		if (isSink) {
			App.getGameController().desintegrar();
		}
	}

	/**
	 * Comprobar si el jugador gana la partida
	 * @param you Objeto jugador (YOU)
	 */
	private void comprobarWin(Objeto<?> you) {
		boolean win = false;
		// Si los estados YOU y WIN coinciden en el mismo elemento se gana la partida
		ArrayList<Objeto<?>> elementosYou = buscarElemento(PropiedadEnum.YOU, null);
		if (elementosYou.size() > 0) {
			win = buscarEstado(elementosYou.get(0), PropiedadEnum.WIN);
		}
		// Si YOU está sobre el elemento WIN se gana la partida
		if (you != null) {
			for (Objeto<?> elemento : elementosSegundoPlano) {
				for (int i = 0; i < elemento.getEstados().size(); i++) {
					if (buscarEstado(elemento, PropiedadEnum.WIN) && elemento.getPosicion().compararPosicion(you.getPosicion())) {
						win = true;
					}
				}
			}
		}
		if (win) {
			App.getGameController().ganar(); // Se avisa al controlador de que ha ganado la partida
		}
	}

	/**
	 * Busca los elementos que tengan el estado que se pasa por parámetro
	 * @param estado Estado de los elementos a buscar
	 * @return Lista de elementos que cumplen la condición que se pasa por parámetro
	 */
	private ArrayList<Objeto<?>> buscarElemento(Object estado, DireccionEnum direccion) {
		ArrayList<Objeto<?>> elementos = new ArrayList<Objeto<?>>();
		int inicioI = 0, inicioJ = 0, maxI = objetos.length, maxJ = objetos[0].length, paso = 1;
		boolean esMenor = true;
		if (direccion == DireccionEnum.ABAJO || direccion == DireccionEnum.DERECHA) {
			inicioI = objetos.length - 1;
			inicioJ = objetos[0].length - 1;
			maxI = 0;
			maxJ = 0;
			paso = -1;
			esMenor = false;
		}
		for (int i = inicioI; (esMenor)?(i < maxI):(i >= maxI); i = i + paso) {
			for (int j = inicioJ; (esMenor)?(j < maxJ):(j >= maxJ); j = j + paso) {
				if (objetos[i][j] != null && objetos[i][j].getTipo() == TipoEnum.ELEMENTO) {
					for (int x = 0; x < objetos[i][j].getEstados().size(); x++) {
						if (objetos[i][j].getEstados().get(x) == estado) {
							elementos.add(objetos[i][j]);
						}
					}
				}
			}
		}
		for (Objeto<?> elemento : elementosSegundoPlano) {
			for (int x = 0; x < elemento.getEstados().size(); x++) {
				if (elemento.getEstados().get(x) == estado) {
					elementos.add(elemento);
				}
			}
		}
		return elementos;
	}

	/**
	 * Busca los elementos en segundo plano que estén en una posición concreta
	 * @param posicion Posicion a buscar
	 * @return Lista de elementos que cumplen la condición que se pasa por parámetro
	 */
	private ArrayList<Objeto<?>> buscarElementoSegundoPlano(Posicion posicion) {
		ArrayList<Objeto<?>> elementos = new ArrayList<Objeto<?>>();
		for (Objeto<?> elemento : elementosSegundoPlano) {
			if (elemento.getPosicion().compararPosicion(posicion)) {
				elementos.add(elemento);
			}
		}
		return elementos;
	}

	/**
	 * Busca en un objeto el estado
	 * @param objeto Objeto en el que buscar
	 * @param estado Estado que se desea encontrar
	 * @return True si lo encuentra; False si no lo encuentra
	 */
	private boolean buscarEstado(Objeto<?> objeto, PropiedadEnum estadoDeseado) {
		boolean encontrado = false;
		for (PropiedadEnum estado : objeto.getEstados()) {
			if (estado == estadoDeseado) {
				encontrado = true;
			}
		}
		return encontrado;
	}

	/**
	 * Se convierte el enumerado de dirección en un número
	 * @param direccion Direccion de movimiento
	 * @return Array de dos posiciones: x, y (las posibilidades son 0, 1 o -1)
	 */
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

	/**
	 * Comprueba que las coordenadas que se pasan por parámetros estén dentro del mapa
	 * @param x Coordenada X
	 * @param y Coordenada Y
	 * @return True si está dentro del mapa; False si está fuera del mapa
	 */
	private boolean comprobarBordeMapa(int x, int y) {
		return y >= 0 && y < cantidadFilas && x >= 0 && x < cantidadColumnas;
	}

	/**
	 * Comprueba las frases que están formadas en el mapa
	 */
	private void comprobarFrases() {
		frases.clear(); // Se borran las frases anteriores
		// Se recorren los objetos del mapa
		for (int i = 0; i < objetos.length; i++) {
			for (int j = 0; j < objetos[i].length; j++) {
				if (objetos[i][j] != null && objetos[i][j].getTipo() == TipoEnum.SUJETO) { // Si es un sujeto:
					// Comprobar hacia la derecha y abajo si se forma una frase
					int[] direccion = {i, j};
					int[][] posiciones = {{1, 0}, {0, 1}};
					for (int k = 0; k < 2; k++) {
						if (direccion[k] + 2 < ((k == 0)?cantidadFilas:cantidadColumnas)) { // Comprobar si la posible frase está dentro del escenario
							// Si la siguiente posición es un verbo:
							if (objetos[i + posiciones[k][0]][j + posiciones[k][1]] != null 
									&& objetos[i + posiciones[k][0]][j + posiciones[k][1]].getTipo() == TipoEnum.OPERADOR) {
								// Si la siguiente posición es una acción o un sujeto:
								if (objetos[i + posiciones[k][0] * 2][j + posiciones[k][1] * 2] != null 
										&& (objetos[i + posiciones[k][0] * 2][j + posiciones[k][1] * 2].getTipo() == TipoEnum.PROPIEDAD
										|| objetos[i + posiciones[k][0] * 2][j + posiciones[k][1] * 2].getTipo() == TipoEnum.SUJETO)) {
									boolean frase5palabras = false;
									if (direccion[k] + 4 < ((k == 0)?cantidadFilas:cantidadColumnas)) {
										// Si la siguiente posición es un verbo:
										if (objetos[i + posiciones[k][0] * 3][j + posiciones[k][1] * 3] != null 
												&& objetos[i + posiciones[k][0] * 3][j + posiciones[k][1] * 3].getTipo() == TipoEnum.OPERADOR) {
											// Si la siguiente posición es una acción o un sujeto:
											if (objetos[i + posiciones[k][0] * 4][j + posiciones[k][1] * 4] != null 
													&& (objetos[i + posiciones[k][0] * 4][j + posiciones[k][1] * 4].getTipo() == TipoEnum.PROPIEDAD
													|| objetos[i + posiciones[k][0] * 4][j + posiciones[k][1] * 4].getTipo() == TipoEnum.SUJETO)) {
												frase5palabras = true;
											}
										}
									}
									if (frase5palabras) {
										if (objetos[i + posiciones[k][0]][j + posiciones[k][1]].getNombre() == OperadorEnum.AND
												&& objetos[i + posiciones[k][0] * 3][j + posiciones[k][1] * 3].getNombre() == OperadorEnum.IS) {
											Objeto<?>[] frase = { 
													objetos[i][j],
													objetos[i + posiciones[k][0] * 3][j + posiciones[k][1] * 3], 
													objetos[i + posiciones[k][0] * 4][j + posiciones[k][1] * 4] };
											frases.add(frase); // Se guarda la frase
										} else if (objetos[i + posiciones[k][0]][j + posiciones[k][1]].getNombre() == OperadorEnum.IS
												&& objetos[i + posiciones[k][0] * 3][j + posiciones[k][1] * 3].getNombre() == OperadorEnum.AND) {
											if (!(objetos[i][j].getTipo() == TipoEnum.SUJETO
													&& objetos[i + posiciones[k][0] * 2][j + posiciones[k][1] * 2].getTipo() == TipoEnum.SUJETO
													&& objetos[i + posiciones[k][0] * 4][j + posiciones[k][1] * 4].getTipo() == TipoEnum.SUJETO)) {
												Objeto<?>[] frase = { 
														objetos[i][j],
														objetos[i + posiciones[k][0] * 1][j + posiciones[k][1] * 1], 
														objetos[i + posiciones[k][0] * 4][j + posiciones[k][1] * 4] };
												frases.add(frase); // Se guarda la frase
											}
										}
									}
									if (objetos[i + posiciones[k][0]][j + posiciones[k][1]].getNombre() == OperadorEnum.IS) {
										if (!(frase5palabras
												&& objetos[i][j].getTipo() == TipoEnum.SUJETO
												&& objetos[i + posiciones[k][0] * 2][j + posiciones[k][1] * 2].getTipo() == TipoEnum.SUJETO
												&& objetos[i + posiciones[k][0] * 4][j + posiciones[k][1] * 4].getTipo() == TipoEnum.SUJETO)) {
											Objeto<?>[] frase = { 
													objetos[i][j], 
													objetos[i + posiciones[k][0]][j + posiciones[k][1]], 
													objetos[i + posiciones[k][0] * 2][j + posiciones[k][1] * 2] };
											frases.add(frase); // Se guarda la frase
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Se asignan los estados a los sujetos o se convierte los sujetos a otros sujetos
	 */
	private void asignarEstados() {
		ArrayList<Objeto<?>> elementos = new ArrayList<Objeto<?>>();
		// Coge todos los elementos y borra los estados
		for (int i = 0; i < objetos.length; i++) {
			for (int j = 0; j < objetos[i].length; j++) {
				Objeto<?> elemento = objetos[i][j];
				if (elemento != null && elemento.getTipo() == TipoEnum.ELEMENTO) {
					elemento.limpiarEstados();
					elementos.add(elemento);
				}
			}
		}
		for (Objeto<?> elemento : elementosSegundoPlano) {
			if (elemento != null && elemento.getTipo() == TipoEnum.ELEMENTO) {
				elemento.limpiarEstados();
				elementos.add(elemento);
			}
		}
		// Se asignan a los elemento los estados de las frases formadas
		for (Objeto<?>[] frase : frases) {
			for (Objeto<?> elemento : elementos) {
				// frase[0] = sujeto; frase[1] = verbo; frase[2] = accion/sujeto a convertir
				if (elemento.getNombre() == frase[0].getNombre()) {
					if(frase[2].getNombre() instanceof PropiedadEnum) { // Si es una acción, se agrega el estado
						elemento.setEstado((PropiedadEnum) frase[2].getNombre());
					} else { // Si es un sujeto, se convierte a ese sujeto
						boolean hayContradiccion = false;
						for (Objeto<?>[] fraseContradiccion : frases) {
							if (elemento.getNombre() == fraseContradiccion[0].getNombre()
									&& elemento.getNombre() == fraseContradiccion[2].getNombre()) {
								hayContradiccion = true;
							}
						}
						if (!hayContradiccion) {
							@SuppressWarnings("unchecked")
							Objeto<SujetoEnum> conversor = (Objeto<SujetoEnum>) frase[2];
							elemento.setNombre((SujetoEnum) conversor.getNombre());
						}
						
					}
					
				}
			}
		}
	}

	/**
	 * Mostrar las frases formadas por consola
	 */
	@SuppressWarnings("unused")
	private void mostrarFrases() {
		System.out.println("Frases: ");
		for (int i = 0; i < frases.size(); i++) {
			for (Objeto<?> palabra : frases.get(i)) {
				System.out.print(palabra.getNombre() + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Mostrar los estados de los elementos por consola
	 */
	@SuppressWarnings("unused")
	private void mostrarEstados() {
		System.out.println("Estados: ");
		for (int i = 0; i < objetos.length; i++) {
			for (int j = 0; j < objetos[i].length; j++) {
				Objeto<?> elemento = objetos[i][j];
				if (elemento != null && elemento.getTipo() == TipoEnum.ELEMENTO) {
					System.out.println(elemento.getNombre() + " => " + elemento.getEstados());
				}
			}
		}
	}

	/**
	 * Mostrar el tablero por consola
	 */
	@SuppressWarnings("unused")
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

	/**
	 * Mostrar el segundo plano por consola
	 */
	@SuppressWarnings("unused")
	private void mostrarSegundoPlano() {
		System.out.println("Segundo Plano: ");
		for (Objeto<?> elemento : elementosSegundoPlano) {
			System.out.println(elemento.getNombre().toString() + " (" + elemento.getPosicion().getX() + ", " + elemento.getPosicion().getY() + ")");
		}
	}
}
