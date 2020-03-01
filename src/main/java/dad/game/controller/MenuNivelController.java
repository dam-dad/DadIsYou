package dad.game.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dad.App;
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
import javafx.scene.text.FontWeight;

/**
 * Controlador del menú seleccionar nivel
 */
public class MenuNivelController implements Initializable, Controller {

	@FXML
    private VBox root;
	// Tamaño del GridPane: 27 x 48
	@FXML
    private GridPane nivelGrid;

	/**
	 * Se crean los botones de los niveles
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		root.setBackground(
				new Background(new BackgroundImage(new Image("/imagenes/niveles/menuSeleccionarNivel.jpg"),
						BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
						new BackgroundSize(100, 100, true, true, true, true))));
		
		// Lista del contenido de los botones de los niveles
		String[] numerosBotones = {
				"<",
				"0",
				"2",
				"3",
				"4",
				"5",
				"6",
				"7",
				"8",
				"9"
				};
		// Lista del diseño de los botones de los niveles
		String[] disenoBotones = {
				"-fx-text-fill: #469; -fx-border-color: #469; -fx-background-color: #246;",
				"-fx-text-fill: #f33; -fx-border-color: #f33; -fx-background-color: #733;",
				"-fx-text-fill: #3f3; -fx-border-color: #3f3; -fx-background-color: #373;",
				"-fx-text-fill: #33c; -fx-border-color: #33c; -fx-background-color: #559;",
				"-fx-text-fill: #f33; -fx-border-color: #f33; -fx-background-color: #733;",
				"-fx-text-fill: #3f3; -fx-border-color: #3f3; -fx-background-color: #373;",
				"-fx-text-fill: #33c; -fx-border-color: #33c; -fx-background-color: #559;",
				"-fx-text-fill: #f33; -fx-border-color: #f33; -fx-background-color: #733;",
				"-fx-text-fill: #3f3; -fx-border-color: #3f3; -fx-background-color: #373;",
				"-fx-text-fill: #33c; -fx-border-color: #33c; -fx-background-color: #559;"
				};
		// Lista de botones JavaFX
		ArrayList<Button> botones = new ArrayList<Button>();
		
		if (numerosBotones.length == disenoBotones.length) {
			// Se crean los botones
			for (int i = 0; i < numerosBotones.length; i++) {
				Button btn = new Button(numerosBotones[i]);
				btn.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 14.0));
				btn.setStyle(disenoBotones[i]);
				// Se añade su evento "OnAction"
				btn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						// Se recoge el contenido del botón para diferenciarlos
						String i = ((Button)event.getSource()).getText();
						if (!i.equals("<")) { // Botones niveles
							App.playSound("entrar_nivel");
							App.getScreenController().activate("game");
							App.getGameController().cargarNivel(Integer.parseInt(i));
							if (i.equals("1")) { // Si es el nivel 1, se activa el tutorial
								App.getGameController().tutorial(true);
							} else {
								App.getGameController().tutorial(false);
							}
						} else { // Botón "Ir Atrás"
							App.playSound("boton");
							App.getScreenController().activate("menuPrincipal");
						}
					}
				});
				botones.add(btn);
			}
			botones.get(0).setFont(Font.font("Consolas", FontWeight.EXTRA_BOLD, 24.0));
			// Se colocan los botones en el GridPane
			nivelGrid.add(botones.get(0), 1, 25);
			nivelGrid.add(botones.get(1), 14, 21);
			nivelGrid.add(botones.get(2), 16, 18);
			nivelGrid.add(botones.get(3), 21, 19);
			nivelGrid.add(botones.get(4), 29, 17);
			nivelGrid.add(botones.get(5), 33, 14);
			nivelGrid.add(botones.get(6), 26, 8);
			nivelGrid.add(botones.get(7), 22, 4);
			nivelGrid.add(botones.get(8), 16, 7);
			nivelGrid.add(botones.get(9), 18, 11);
		} else {
			System.out.println("Error: Los niveles están incompletos");
			System.exit(0);
		}
	}

	/**
	 * Se recoge la tecla pulsada y si es "Escape" carga el menú principal
	 */
	@Override
	public void evento(String key) {
		if (key.equals("ESCAPE")) {
			App.getScreenController().activate("menuPrincipal");
		}
	}

}
