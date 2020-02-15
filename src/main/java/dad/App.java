package dad;

import dad.game.controller.GameController;
import dad.game.controller.MenuNivelController;
import dad.game.controller.MenuPrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class App extends Application {

	private static String ruta = "/imagenes/objetos/";
	private static String imagenExtension = ".gif";

	private static ScreenController screenController;
	private static MenuPrincipalController menuPrincipalController;
	private static MenuNivelController menuNivelController;
	private static GameController gameController;

	@Override
	public void start(Stage primaryStage) throws Exception {
		screenController = new ScreenController(new Scene(FXMLLoader.load(getClass().getResource("/fxml/ScreenView.fxml"))));
		
		FXMLLoader menuPrincipalView = new FXMLLoader(getClass().getResource("/fxml/MenuPrincipalView.fxml"));
		menuPrincipalController = new MenuPrincipalController();
		menuPrincipalView.setController(menuPrincipalController);
		screenController.addScreen("menuPrincipal", menuPrincipalView.load(), menuPrincipalController);
		screenController.activate("menuPrincipal");
		
		FXMLLoader menuNivelView = new FXMLLoader(getClass().getResource("/fxml/MenuNivelView.fxml"));
		menuNivelController = new MenuNivelController();
		menuNivelView.setController(menuNivelController);
		screenController.addScreen("menuNivel", menuNivelView.load(), menuNivelController);
		
		FXMLLoader gameView = new FXMLLoader(getClass().getResource("/fxml/GameView.fxml"));
		gameController = new GameController();
		gameView.setController(gameController);
		screenController.addScreen("game", gameView.load(), gameController);

		primaryStage.setTitle("DAD IS YOU");
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		primaryStage.setFullScreen(true);
		primaryStage.setScene(screenController.getScene());
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	/*public static Stage getStage() {
		return primaryStage;
	}*/

	public static String getRuta() {
		return ruta;
	}

	public static String getImagenExtension() {
		return imagenExtension;
	}

	public static ScreenController getScreenController() {
		return screenController;
	}

	public static GameController getGameController() {
		return gameController;
	}

}
