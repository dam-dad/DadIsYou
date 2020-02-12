package dad.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.App;
import dad.game.model.DireccionEnum;
import dad.game.model.Nivel;
import dad.game.model.Objeto;
import dad.game.model.Tablero;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MenuPrincipalController implements Initializable {

	@FXML
	private VBox root;

	Scene scene;

	public MenuPrincipalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuPrincipal.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		root.setBackground(
				new Background(new BackgroundImage(new Image("/imagenes/niveles/menuSeleccionarNivelOscuro.jpg"),
						BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
						new BackgroundSize(100, 100, true, true, true, true))));
	}

	public void setScene(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				pulsarTecla(code);
			}
		});
	}

	private void pulsarTecla(String key) {
		if (key.equals("UP")) {

		} else if (key.equals("DOWN")) {
		} else if (key.equals("RIGHT")) {
		} else if (key.equals("LEFT")) {
		}
	}

	@FXML
	void onAjustesAction(ActionEvent event) {

	}

	@FXML
	void onIniciarPartidaAction(ActionEvent event) throws IOException {
		App.startGameController();
	}

	@FXML
	void onSalirJuegoAction(ActionEvent event) {
		System.exit(0);
	}

	public VBox getView() {
		return root;
	}

}
