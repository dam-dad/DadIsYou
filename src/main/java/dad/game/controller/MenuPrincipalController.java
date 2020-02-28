package dad.game.controller;

import java.net.URL;
import java.util.ResourceBundle;
import dad.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

public class MenuPrincipalController extends Controller implements Initializable {

	@FXML
	private VBox root;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		root.setBackground(
				new Background(new BackgroundImage(new Image("/imagenes/niveles/menuSeleccionarNivelOscuro.jpg"),
						BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
						new BackgroundSize(100, 100, true, true, true, true))));
	}

	@FXML
	void onAjustesAction(ActionEvent event) {
		App.getScreenController().activate("ajustes");
		App.getAjustesController().setAnteriorController("menuPrincipal");
	}

	@FXML
	void onIniciarPartidaAction(ActionEvent event) {
		App.getScreenController().activate("menuNivel");
	}

	@FXML
	void onSalirJuegoAction(ActionEvent event) {
		System.exit(0);
	}

}
