package dad;

import dad.game.controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	private static String ruta = "/imagenes/objetos/";
	private static String imagenExtension = ".gif";
	
	GameController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new GameController();

		Scene scene = new Scene(controller.getView());

		primaryStage.setTitle("DAD IS YOU");
		//primaryStage.setFullScreen(true);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		controller.setScene(scene);
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
	
}
