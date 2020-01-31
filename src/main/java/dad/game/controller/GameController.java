package dad.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameController implements Initializable {

	@FXML
	private VBox root;
	@FXML
	private VBox gamePane;
	@FXML
	private GridPane gameGrid;

	public GameController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

}
