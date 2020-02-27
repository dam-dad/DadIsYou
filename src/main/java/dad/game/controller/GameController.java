package dad.game.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dad.App;
import dad.game.model.DireccionEnum;
import dad.game.model.Nivel;
import dad.game.model.Objeto;
import dad.game.model.Tablero;
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

public class GameController extends Controller implements Initializable {

	@FXML
	private VBox root, gamePane, winPane, defeatPane, pausePane;
	@FXML
	private AnchorPane gameAnchorPane, tutorialPane;
	@FXML
	private GridPane gameGrid;
	@FXML
	private Label nivelLabel, estadosLabel;

	Tablero tablero;
	int idNivel;
	boolean jugando = true;
	
	ImageView tutorialMover, tutorialZ, tutorialP, tutorialR;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gameGrid.setBackground(
				new Background(new BackgroundImage(new Image("/imagenes/niveles/uno.png"), BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		imagenesTutorial();
	}
	
	public void cargarNivel(int idNivel, Objeto<?>[][] nivel) {
		this.idNivel = idNivel;
		onReiniciarAction(null);
		// TO DO: cambiar numero de filas y columnas gameGrid y el tamaño (pixeles) (dependencias: tutorial())
	}
	
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

	@Override
	public void evento(String key) {
		if (jugando) {
			if (key.equals("UP")) {
				tablero.mover(DireccionEnum.ARRIBA);
			} else if (key.equals("DOWN")) {
				tablero.mover(DireccionEnum.ABAJO);
			} else if (key.equals("RIGHT")) {
				tablero.mover(DireccionEnum.DERECHA);
			} else if (key.equals("LEFT")) {
				tablero.mover(DireccionEnum.IZQUIERDA);
			} else if (key.equals("ESCAPE") || key.equals("P")) {
				pausePane.setVisible(true);
				jugando = false;
			}
			refrescarTablero();
		} else if (key.equals("ESCAPE") || key.equals("P")) {
			pausePane.setVisible(false);
			jugando = true;
		}
	}

	private void refrescarTablero() {
		Objeto<?>[][] posicionObjetos = tablero.getPosicionObjetos();
		ArrayList<Objeto<?>> objetosSegundoPlano = tablero.getObjetosSegundoPlano();
		gameGrid.getChildren().clear();
		for (int i = 0; i < posicionObjetos.length; i++) {
			for (int j = 0; j < posicionObjetos[i].length; j++) {
				if (posicionObjetos[i][j] != null) {
					ArrayList<ImageView> imagenes = new ArrayList<ImageView>();
					for (Objeto<?> elem : objetosSegundoPlano) {
						if (elem.getPosicion().compararPosicion(posicionObjetos[i][j].getPosicion())) {
							ImageView imagen = new ImageView(elem.getImagen());
							imagen.setFitWidth(34);
							imagen.setFitHeight(34);
							imagenes.add(imagen);
						}
					}
					ImageView imagen = new ImageView(posicionObjetos[i][j].getImagen());
					imagen.setFitWidth(34);
					imagen.setFitHeight(34);
					if (imagenes.size() > 0) {
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
				}
			}
		}
	}

	public void ganar() {
		winPane.setVisible(true);
		jugando = false;
	}

	public void perder() {
		defeatPane.setVisible(true);
		jugando = false;
	}

	@FXML
	void onAjustesAction(ActionEvent event) {

	}

	@FXML
	void onContinuarAction(ActionEvent event) {
		jugando = true;
		pausePane.setVisible(false);
	}

	@FXML
	void onReiniciarAction(ActionEvent event) {
		tablero = new Tablero(Nivel.load(idNivel));
		refrescarTablero();
		onContinuarAction(null);
		winPane.setVisible(false);
		defeatPane.setVisible(false);
	}

	
	@FXML
	void onSalirMapaAction(ActionEvent event) {
		App.getScreenController().activate("menuNivel");
		onContinuarAction(null);
	}

	@FXML
	void onSalirMenuAction(ActionEvent event) {
		App.getScreenController().activate("menuPrincipal");
		onContinuarAction(null);
	}

}
