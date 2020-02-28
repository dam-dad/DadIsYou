package dad;

import dad.game.controller.AjustesController;
import dad.game.controller.GameController;
import dad.game.controller.MenuNivelController;
import dad.game.controller.MenuPrincipalController;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;

public class App extends Application {

	private static String ruta = "/imagenes/objetos/";
	private static String imagenExtension = ".gif";
	private static double volumenMusic = 0.3, volumenSound = 1;
	private static HiloMusic hiloMusic;
	private static HiloSound hiloSound;

	private static ScreenController screenController;
	private static MenuPrincipalController menuPrincipalController;
	private static MenuNivelController menuNivelController;
	private static GameController gameController;
	private static AjustesController ajustesController;

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
		
		FXMLLoader ajustesView = new FXMLLoader(getClass().getResource("/fxml/AjustesView.fxml"));
		ajustesController = new AjustesController();
		ajustesView.setController(ajustesController);
		screenController.addScreen("ajustes", ajustesView.load(), ajustesController);

		primaryStage.setTitle("DAD IS YOU");
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		primaryStage.setFullScreen(true);
		primaryStage.setScene(screenController.getScene());
		primaryStage.show();
		
		playMusic("melodia_4");
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

	public static ScreenController getScreenController() {
		return screenController;
	}

	public static GameController getGameController() {
		return gameController;
	}

	public static AjustesController getAjustesController() {
		return ajustesController;
	}
	
	public static double getVolumenMusic() {
		return volumenMusic;
	}

	public static void setVolumenMusic(double volumenMusic) {
		App.volumenMusic = volumenMusic;
	}

	public static double getVolumenSound() {
		return volumenSound;
	}

	public static void setVolumenSound(double volumenSound) {
		App.volumenSound = volumenSound;
	}

	public static void playMusic(String file) {
		hiloMusic = new HiloMusic(file);
		hiloMusic.run();
	}

	public static void stopMusic() {
		hiloMusic.parar();
	}

	public static void playSound(String file) {
		hiloSound = new HiloSound(file);
		hiloSound.run();
	}

	public static void stopSound() {
		hiloSound.parar();
	}

}

class HiloSound extends Thread {

	String file;
	MediaPlayer mediaPlayer;
	
	public HiloSound(String file) {
		this.file = file;
	}

	@Override
	public void run() {
		String path = "src/main/resources/sonidos/" + file + ".mp3";
		Media sound = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setVolume(App.getVolumenSound());
		mediaPlayer.play();
	}
	
	public void parar() {
		try {
			mediaPlayer.stop();
		} catch(Exception e) {};
	}
}

class HiloMusic extends Thread {

	String file;
	MediaPlayer mediaPlayer;

	public HiloMusic(String file) {
		this.file = file;
	}

	@Override
	public void run() {
		String path = "src/main/resources/sonidos/" + file + ".mp3";
		Media sound = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(Transition.INDEFINITE);
		mediaPlayer.setVolume(App.getVolumenMusic());
		mediaPlayer.play();
	}
	
	public void parar() {
		try {
			mediaPlayer.stop();
		} catch(Exception e) {};
	}
}
