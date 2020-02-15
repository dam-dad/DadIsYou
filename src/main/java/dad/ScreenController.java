package dad;

import java.util.HashMap;

import dad.game.controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class ScreenController {
	private HashMap<String, Controller> controllersMap = new HashMap<>();
	private HashMap<String, Pane> screenMap = new HashMap<>();
	private Scene main;

	public ScreenController(Scene main) {
		this.main = main;
	}

	public void addScreen(String name, Pane pane, Controller controller) {
		screenMap.put(name, pane);
		controllersMap.put(name, controller);
	}

	public void removeScreen(String name) {
		screenMap.remove(name);
		controllersMap.remove(name);
	}

	public void activate(String name) {
		main.setRoot(screenMap.get(name));
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
