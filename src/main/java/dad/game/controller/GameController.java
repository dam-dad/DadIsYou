package dad.game.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dad.App;
import dad.game.model.Nivel;
import dad.game.model.Objeto;
import dad.game.model.Posicion;
import dad.game.model.Tablero;
import dad.game.model.enums.AccionEnum;
import dad.game.model.enums.DireccionEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Controlador del juego
 */
public class GameController implements Initializable, Controller {

	@FXML
	private VBox root, gamePane, winPane, defeatPane, pausePane;
	@FXML
	private AnchorPane gameAnchorPane, tutorialPane;
	@FXML
	private GridPane gameGrid;
	@FXML
	private Label nivelLabel, estadosLabel;

	private Tablero tablero;
	private int idNivel;
	private boolean jugando = true, pausa = false, ganar = false, perder = false;
	
	private ImageView tutorialMover, tutorialZ, tutorialP, tutorialR;
	
	private int cantidadFrasesAnterior = 100;
	private Posicion posicionDADAnterior = null;

	/**
	 * Se llama al método que carga las imágenes del tutorial
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imagenesTutorial();
	}
	
	/**
	 * Se carga un nivel para jugar
	 * @param idNivel Id del nivel
	 */
	public void cargarNivel(int idNivel) {
		this.idNivel = idNivel;
		onReiniciarAction(null);
		gameGrid.setBackground(
				new Background(new BackgroundImage(new Image("/imagenes/niveles/" + Nivel.getBackground(this.idNivel) + ".png"), BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		winPane.setBackground(
				new Background(new BackgroundImage(new Image("/imagenes/otros/ganar.png"), BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		defeatPane.setBackground(
				new Background(new BackgroundImage(new Image("/imagenes/otros/perder.png"), BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		// TO DO: cambiar numero de filas y columnas gameGrid y el tamaño total (pixeles ej 530) (dependencias: tutorial())
	}

	/**
	 * Se cargan las imágenes del tutorial
	 */
	private void imagenesTutorial() {
		tutorialMover = new ImageView("/imagenes/tutorial/move.png");
		tutorialMover.setFitWidth(102);
		tutorialMover.setFitHeight(102);
		tutorialZ = new ImageView("/imagenes/tutorial/undo.png");
		tutorialZ.setFitWidth(68);
		tutorialZ.setFitHeight(68);
		tutorialP = new ImageView("/imagenes/tutorial/menu.png");
		tutorialP.setFitWidth(68);
		tutorialP.setFitHeight(68);
		tutorialR = new ImageView("/imagenes/tutorial/restart.png");
		tutorialR.setFitWidth(68);
		tutorialR.setFitHeight(68);
		tutorialPane.getChildren().addAll(tutorialMover, tutorialZ, tutorialP, tutorialR);
	}
	
	/**
	 * Se  activa y desactiva el tutorial
	 * @param activar True si se quiere activar; False si se quiere desactivar
	 */
	public void tutorial(boolean activar) {
		if (activar) {
			AnchorPane.setTopAnchor(tutorialMover, 34.0);
			AnchorPane.setLeftAnchor(tutorialMover, 204.0);
			AnchorPane.setTopAnchor(tutorialZ, 68.0);
			AnchorPane.setLeftAnchor(tutorialZ, 340.0);
			AnchorPane.setTopAnchor(tutorialP, 68.0);
			AnchorPane.setLeftAnchor(tutorialP, 442.0);
			AnchorPane.setTopAnchor(tutorialR, 68.0);
			AnchorPane.setLeftAnchor(tutorialR, 544.0);
		} else {
			AnchorPane.setTopAnchor(tutorialMover, -10000.0);
			AnchorPane.setLeftAnchor(tutorialMover, -10000.0);
			AnchorPane.setTopAnchor(tutorialZ, -10000.0);
			AnchorPane.setLeftAnchor(tutorialZ, -10000.0);
			AnchorPane.setTopAnchor(tutorialP, -10000.0);
			AnchorPane.setLeftAnchor(tutorialP, -10000.0);
			AnchorPane.setTopAnchor(tutorialR, -10000.0);
			AnchorPane.setLeftAnchor(tutorialR, -10000.0);
		}
	}

	/**
	 * Se recogen las teclas pulsadas
	 * @param key Código de la tecla pulsada
	 */
	@Override
	public void evento(String key) {
		if (key.equals("ESCAPE") || key.equals("P")) { // Abre/Cierra el menú de pausa
			if (!pausa) {
				pausePane.setVisible(true);
				jugando = false;
				pausa = true;
			} else {
				onContinuarAction(null);
			}
		} else if (key.equals("R")) { // Reiniciar partida
			onReiniciarAction(null);
		} else if (ganar && key.equals("ENTER")) { // Si ganó, ENTER para salir al menú de niveles
			onSalirMapaAction(null);
		} else if (perder && key.equals("ENTER")) { // Si perdió, ENTER para reiniciar la partida
			onReiniciarAction(null);
		} else {
			if (jugando) { // Si está la partida en curso, se recogen las direcciones del personaje
				if (key.equals("UP")) {
					tablero.mover(DireccionEnum.ARRIBA);
				} else if (key.equals("DOWN")) {
					tablero.mover(DireccionEnum.ABAJO);
				} else if (key.equals("RIGHT")) {
					tablero.mover(DireccionEnum.DERECHA);
				} else if (key.equals("LEFT")) {
					tablero.mover(DireccionEnum.IZQUIERDA);
				}
				refrescarTablero();
			} 
		}
	}

	/**
	 * Se dibuja y actualiza el nivel
	 */
	private void refrescarTablero() {
		// Se obtiene el tablero del nivel del modelo
		Objeto<?>[][] objetos = tablero.getPosicionObjetos();
		// Se obtienen los elementos que se encuentran en segundo plano (debajo de otros elementos)
		ArrayList<Objeto<?>> elementosSegundoPlano = tablero.getElementosSegundoPlano();
		gameGrid.getChildren().clear();
		// Se recorren los objetos del tablero
		for (int i = 0; i < objetos.length; i++) {
			for (int j = 0; j < objetos[i].length; j++) {
				if (objetos[i][j] != null) {
					// Se crean los ImageView de cada coordenada y si hay varios, se agregan a un array para ser añadidos a un AnchorPane
					ArrayList<ImageView> imagenes = new ArrayList<ImageView>();
					for (Objeto<?> elem : elementosSegundoPlano) {
						if (elem.getPosicion().compararPosicion(objetos[i][j].getPosicion())) {
							ImageView imagen = new ImageView(elem.getImagen());
							imagen.setFitWidth(34);
							imagen.setFitHeight(34);
							imagenes.add(imagen);
						}
					}
					ImageView imagen = new ImageView(objetos[i][j].getImagen());
					imagen.setFitWidth(34);
					imagen.setFitHeight(34);
					if (imagenes.size() > 0) { // Se crea el AnchorPane si hay varias imágenes para que se vean una encima de otra
						AnchorPane imagenesPane = new AnchorPane();
						imagenes.add(imagen);
						imagenesPane.getChildren().addAll(imagenes);
						for (ImageView img : imagenes) {
							AnchorPane.setTopAnchor(img, 0.0);
							AnchorPane.setLeftAnchor(img, 0.0);
						}
						gameGrid.add(imagenesPane, j, i);
					} else {
						gameGrid.add(imagen, j, i);
					}
					// Sonido DAD se mueve:
					for (AccionEnum estado : objetos[i][j].getEstados()) {
						if (estado == AccionEnum.YOU) {
							if (posicionDADAnterior != null ) {
								if (!posicionDADAnterior.compararPosicion(objetos[i][j].getPosicion())) {
									App.playSound("movimiento");
								}
							}
							posicionDADAnterior = new Posicion(objetos[i][j].getPosicion().getX(), objetos[i][j].getPosicion().getY());
						}
					}
				}
			}
		}
		// Sonido nueva palabra:
		int cantidadFrases = tablero.getCantidadFrases();
		if (cantidadFrasesAnterior < cantidadFrases) {
			App.playSound("palabra_formada");
		}
		cantidadFrasesAnterior = cantidadFrases;
	}

	/**
	 * Se activan una serie de elementos al ganar el nivel
	 */
	public void ganar() {
		// Se activa la vista "Win"
		winPane.setVisible(true);
		// Se agrega una transición
		App.transitionFade(winPane, 0.0, 1.0, 1, 500);
		// Se para el juego
		jugando = false;
		ganar = true;
		App.playSound("ganar");
	}

	/**
	 * Se activan una serie de elementos al perder el nivel
	 */
	public void perder() {
		// Se activa la vista "Defeat"
		defeatPane.setVisible(true);
		// Se agrega una transición
		App.transitionFade(defeatPane, 0.0, 1.0, 1, 500);
		// Se para el juego
		jugando = false;
		perder = true;
		App.playSound("perder");
	}

	/**
	 * Se ejecuta la vista y el controlador de Ajustes
	 * @param event
	 */
	@FXML
	void onAjustesAction(ActionEvent event) {
		App.getScreenController().activate("ajustes");
		App.getAjustesController().setAnteriorController("game");
	}

	/**
	 * Se reanuda el nivel
	 * @param event
	 */
	@FXML
	void onContinuarAction(ActionEvent event) {
		jugando = true;
		pausa = false;
		pausePane.setVisible(false);
	}

	/**
	 * Se reinicia el nivel
	 * @param event
	 */
	@FXML
	void onReiniciarAction(ActionEvent event) {
		tablero = new Tablero(Nivel.load(idNivel));
		refrescarTablero();
		onContinuarAction(null);
		winPane.setVisible(false);
		defeatPane.setVisible(false);
		pausa = false;
		ganar = false;
		perder = false;
	}

	/**
	 * Salir al menú de niveles
	 * @param event
	 */
	@FXML
	void onSalirMapaAction(ActionEvent event) {
		App.getScreenController().activate("menuNivel");
		onContinuarAction(null);
	}

	/**
	 * Salir al menú pricipal
	 * @param event
	 */
	@FXML
	void onSalirMenuAction(ActionEvent event) {
		App.getScreenController().activate("menuPrincipal");
		onContinuarAction(null);
	}

}
