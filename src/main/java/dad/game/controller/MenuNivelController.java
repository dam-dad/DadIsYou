package dad.game.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dad.App;
import dad.game.model.Nivel;
import dad.game.model.Objeto;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MenuNivelController extends Controller implements Initializable {

	@FXML
    private VBox root;
	// 27 x 48
	@FXML
    private GridPane nivelGrid;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		root.setBackground(
				new Background(new BackgroundImage(new Image("/imagenes/niveles/menuSeleccionarNivel.jpg"),
						BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
						new BackgroundSize(100, 100, true, true, true, true))));
		
		String[] numerosBotones = {
				"←",
				"1",
				"2",
				"3",
				"4"
				};
		String[] disenoBotones = {
				"-fx-text-fill: #469; -fx-border-color: #469; -fx-background-color: #246;",
				"-fx-text-fill: #f33; -fx-border-color: #f33; -fx-background-color: #733;",
				"-fx-text-fill: #3f3; -fx-border-color: #3f3; -fx-background-color: #373;",
				"-fx-text-fill: #33f; -fx-border-color: #33f; -fx-background-color: #559;",
				"-fx-text-fill: #f33; -fx-border-color: #f33; -fx-background-color: #733;"
				};
		Objeto<?>[][][] nivelesBotones = {
				null, 
				Nivel.uno(),
				Nivel.dos(),
				Nivel.tres(),
				Nivel.cuatro()
				};
		ArrayList<Button> botones = new ArrayList<Button>();
		
		if (numerosBotones.length == disenoBotones.length && numerosBotones.length == nivelesBotones.length) {
			for (int i = 0; i < numerosBotones.length; i++) {
				Button btn = new Button(numerosBotones[i]);
				btn.setFont(new Font(14.0));
				btn.setStyle(disenoBotones[i]);
				btn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						String i = ((Button)event.getSource()).getText();
						if (!i.equals("←")) {
							App.getScreenController().activate("game");
							App.getGameController().cargarNivel(Integer.parseInt(i), nivelesBotones[Integer.parseInt(i)]);
							if (i.equals("1")) {
								App.getGameController().tutorial(true);
							} else {
								App.getGameController().tutorial(false);
							}
						} else {
							App.getScreenController().activate("menuPrincipal");
						}
					}
				});
				botones.add(btn);
			}
			nivelGrid.add(botones.get(0), 1, 25);
			nivelGrid.add(botones.get(1), 14, 21);
			nivelGrid.add(botones.get(2), 16, 18);
			nivelGrid.add(botones.get(3), 21, 19);
			nivelGrid.add(botones.get(4), 29, 17);
		} else {
			System.out.println("Error: Los niveles están incompletos");
			System.exit(0);
		}
	}

	@Override
	public void evento(String key) {
		if (key.equals("ESCAPE")) {
			App.getScreenController().activate("menuPrincipal");
		}
	}

}
