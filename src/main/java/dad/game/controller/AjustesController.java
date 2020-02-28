package dad.game.controller;

import java.net.URL;
import java.util.ResourceBundle;

import dad.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class AjustesController extends Controller implements Initializable {
	
	@FXML
	private VBox root;
	private String anteriorController;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setAnteriorController(String controller) {
		anteriorController = controller;
	}

	@Override
	public void evento(String key) {
		if (key.equals("ESCAPE")) {
			onCancelarAction();
		}
	}
	
	@FXML
	public void onCancelarAction() {
		App.getScreenController().activate(anteriorController);
	}
	
	@FXML
	public void onGuardarAction() {
		
	}

}
