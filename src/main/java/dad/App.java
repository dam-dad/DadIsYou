package dad;

import java.io.IOException;

import dad.game.controller.GameController;
import dad.game.controller.MenuPrincipalController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	private static String ruta = "/imagenes/objetos/";
	private static String imagenExtension = ".gif";
	
	private static GameController gameController;
	MenuPrincipalController menuPrincipalController;
	
	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		
		menuPrincipalController = new MenuPrincipalController();

		Scene scene = new Scene(menuPrincipalController.getView());

		primaryStage.setTitle("DAD IS YOU");
		primaryStage.setFullScreen(true);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		menuPrincipalController.setScene(scene);
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
	
	public static void startGameController () throws IOException {
		gameController = new GameController();
		Scene scene = new Scene(gameController.getView());
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		gameController.setScene(scene);
	}
	
	public static GameController getGameController () {
		return gameController;
	}
	
}
