package dad.game.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Controlador del menú principal
 */
public class MenuPrincipalController implements Initializable, Controller {

	@FXML
	private VBox root;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		root.setBackground(
				new Background(new BackgroundImage(new Image("/imagenes/niveles/menuSeleccionarNivelOscuro.jpg"),
						BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
						new BackgroundSize(100, 100, true, true, true, true))));
	}

	/**
	 * Se ejecuta la vista y el controlador de Ajustes
	 * 
	 * @param event
	 */
	@FXML
	void onAjustesAction(ActionEvent event) {
		App.getScreenController().activate("ajustes");
		App.getAjustesController().setAnteriorController("menuPrincipal");
	}

	/**
	 * Se inicia el juego y se ejecuta la vista y el controlador de Game
	 * 
	 * @param event
	 */
	@FXML
	void onIniciarPartidaAction(ActionEvent event) {
		App.playSound("boton");
		App.getScreenController().activate("menuNivel");
	}

	/**
	 * Salir del juego
	 * 
	 * @param event
	 */
	@FXML
	void onSalirJuegoAction(ActionEvent event) {
		App.playSound("boton");
		System.exit(0);
	}

}
