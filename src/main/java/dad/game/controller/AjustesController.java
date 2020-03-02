package dad.game.controller;

import java.net.URL;
import java.util.ResourceBundle;

import dad.App;
import dad.game.utils.EstadisticasJasperReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

/**
 * Controlador de ajustes
 */
public class AjustesController implements Initializable, Controller {
	
	@FXML
	private VBox root;
	
	@FXML
	private ComboBox<String> idiomaCombo;
	
	private String anteriorController;
	
	/**
	 * Se cargan idiomas por defecto en el ComboBox
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idiomaCombo.getItems().addAll("Español", "Inglés");
		idiomaCombo.getSelectionModel().selectFirst();
	}
	
	/**
	 * Se guarda el controlador anterior para retroceder al cerrar la vista de ajustes
	 * @param controller Controlador JavaFX
	 */
	public void setAnteriorController(String controller) {
		anteriorController = controller;
	}

	/**
	 * Se recoge la tecla pulsada y si es "Escape" llama a "onCancelarAction()"
	 */
	@Override
	public void evento(String key) {
		if (key.equals("ESCAPE")) {
			onCancelarAction();
		}
	}
	
	/**
	 * Se cancelan los cambios hechos y se regresa a la vista anterior
	 */
	@FXML
	void onCancelarAction() {
		App.playSound("boton");
		App.getScreenController().activate(anteriorController);
	}
	
	/**
	 * Método sin implementar
	 */
	@FXML
	void onGuardarAction() {
		App.playSound("boton");
	}
	
    @FXML
    void onDescargarEstadisticasAction(ActionEvent event) {
    	EstadisticasJasperReport.generarPdf();
    }

    @FXML
    void onVerEstadisticasAction(ActionEvent event) {
    	EstadisticasJasperReport.generarVistaPrevia();
    }

}
