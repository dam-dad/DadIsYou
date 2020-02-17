package dad.game.controller;

import java.net.URL;
import java.util.ResourceBundle;
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
	private GridPane gameGrid;
	@FXML
	private Label nivelLabel, estadosLabel;

	Tablero tablero;
	boolean jugando = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gameGrid.setBackground(
				new Background(new BackgroundImage(new Image("/imagenes/niveles/uno.png"), BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		Objeto<?>[][] nivel = Nivel.uno();
		tablero = new Tablero(nivel);
		refrescarTablero();
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
			} else if (key.equals("ESCAPE")) {
				pausePane.setVisible(true);
				jugando = false;
			}
			refrescarTablero();
		}
	}

	private void refrescarTablero() {
		Objeto<?>[][] posicionObjetos = tablero.getPosicionObjetos();
		gameGrid.getChildren().clear();
		for (int i = 0; i < posicionObjetos.length; i++) {
			for (int j = 0; j < posicionObjetos[i].length; j++) {
				if (posicionObjetos[i][j] != null) {
					gameGrid.add(new ImageView(posicionObjetos[i][j].getImagen()), j, i);
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

	}

	
	@FXML
	void onSalirMapaAction(ActionEvent event) {

	}

	@FXML
	void onSalirMenuAction(ActionEvent event) {

	}

}
