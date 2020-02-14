package dad.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

public class MenuNivelController implements Initializable {
	
	@FXML
    private VBox menu;

	Scene scene;

	public MenuNivelController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuFuncionaView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*root.setBackground(
				new Background(new BackgroundImage(new Image("/imagenes/niveles/menuSeleccionarNivel.jpg"),
						BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
						new BackgroundSize(100, 100, true, true, true, true))));*/
	}

	private void pulsarTecla(String key) {
		if (key.equals("UP") || key.equals("RIGHT")) {
			
		} else if (key.equals("DOWN") || key.equals("LEFT")) {
			
		}
	}

	public Scene getScene() {
		return new Scene(menu);
	}

}
