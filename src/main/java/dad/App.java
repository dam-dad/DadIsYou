package dad;

import dad.game.controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	private static String ruta;
	private static String imagenExtension = ".png";
	private static int cantidadColumnas = 0;
	private static int cantidadFilas = 0;
	
	GameController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new GameController();

		Scene scene = new Scene(controller.getView());

		primaryStage.setTitle("DAD IS YOU");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public static String getRuta() {
		return ruta;
	}
	
	public static String getImagenExtension() {
		return imagenExtension;
	}

	public static int getCantidadColumnas() {
		return cantidadColumnas;
	}

	public static int getCantidadFilas() {
		return cantidadFilas;
	}
	
}
