package dad;

import java.io.IOException;

import dad.game.controller.GameController;
import dad.game.controller.MenuNivelController;
import dad.game.controller.MenuPrincipalController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class App extends Application {
	
	private static String ruta = "/imagenes/objetos/";
	private static String imagenExtension = ".gif";

	private static MenuPrincipalController menuPrincipalController;
	private static MenuNivelController menuNivelController;
	private static GameController gameController;
	
	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		menuPrincipalController = new MenuPrincipalController();

		primaryStage.setTitle("DAD IS YOU");
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		primaryStage.setFullScreen(true);
		primaryStage.setScene(menuPrincipalController.getScene());
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage getStage () {
		return primaryStage;
	}
	
	public static String getRuta() {
		return ruta;
	}
	
	public static String getImagenExtension() {
		return imagenExtension;
	}
	
	public static void startMenuNivelController () throws IOException {
		menuNivelController = new MenuNivelController();
		primaryStage.setScene(menuNivelController.getScene());
		primaryStage.setFullScreen(true);
	}
	
	public static void startGameController () throws IOException {
		gameController = new GameController();
		primaryStage.setScene(gameController.getScene());
		primaryStage.setFullScreen(true);
	}
	
	public static GameController getGameController () {
		return gameController;
	}
	
}
