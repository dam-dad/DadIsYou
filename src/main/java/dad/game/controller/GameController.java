package dad.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.App;
import dad.game.model.DireccionEnum;
import dad.game.model.Nivel;
import dad.game.model.Objeto;
import dad.game.model.Tablero;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameController implements Initializable {

	@FXML
	private VBox root;
	@FXML
	private VBox gamePane;
	@FXML
	private GridPane gameGrid;

	Tablero tablero;

	Scene scene;

	public GameController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gameGrid.setBackground(
				new Background(new BackgroundImage(new Image("/imagenes/niveles/uno.png"), BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		tablero = new Tablero(App.getCantidadColumnas(), App.getCantidadFilas());
		tablero.cargarNivel(Nivel.uno());
		Objeto[][] posicionObjetos = tablero.getPosicionObjetos();
		for (int i = 0; i < posicionObjetos.length; i++) {
			for (int j = 0; j < posicionObjetos[i].length; j++) {
				if (posicionObjetos[i][j] != null) {
					gameGrid.add(new ImageView(posicionObjetos[i][j].getImagen()), j, i);
				}
			}
		}
	}

	public void setScene(Scene e) {
		scene = e;

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				pulsarTecla(code);
			}
		});
	}
	
	private void pulsarTecla(String key) {
		if(key.equals("UP")) {
			tablero.mover(DireccionEnum.ARRIBA);
		} else if(key.equals("DOWN")) {
			tablero.mover(DireccionEnum.ABAJO);
		} else if(key.equals("RIGHT")) {
			tablero.mover(DireccionEnum.DERECHA);
		} else if(key.equals("LEFT")) {
			tablero.mover(DireccionEnum.IZQUIERDA);
		}
	}

	public VBox getView() {
		return root;
	}

}
