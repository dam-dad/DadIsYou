package dad;

import java.util.HashMap;

import dad.game.controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * Controlador de vistas y controladores de JavaFX
 */
public class ScreenController {
	
	private HashMap<String, Controller> controllersMap = new HashMap<>();
	private HashMap<String, Pane> screenMap = new HashMap<>();
	private Scene main;

	public ScreenController(Scene main) {
		this.main = main;
	}

	/**
	 * Agrega una vista y un controlador
	 * @param name Clave con la que se guarda la vista y el controlador
	 * @param pane Vista a guardar
	 * @param controller Controlador a guardar
	 */
	public void addScreen(String name, Pane pane, Controller controller) {
		screenMap.put(name, pane);
		controllersMap.put(name, controller);
	}

	/**
	 * Elimina una vista y un controlador
	 * @param name Clave de la vista y del controlador a eliminar
	 */
	public void removeScreen(String name) {
		screenMap.remove(name);
		controllersMap.remove(name);
	}

	/**
	 * Activa una vista en la escena principal de la aplicación para que se muestre
	 * @param name Clave de la vista y el controlador a activar
	 */
	public void activate(String name) {
		main.setRoot(screenMap.get(name));
		// Se crea un evento "OnKeyPressed" para recoger las teclas pulsadas y enviárselas al método "evento()" del controlador
		main.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				controllersMap.get(name).evento(code);
			}
		});
	}
	
	public Scene getScene() {
		return main;
	}
	
}
