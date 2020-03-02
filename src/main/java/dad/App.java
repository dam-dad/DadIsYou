package dad;

import dad.game.controller.AjustesController;
import dad.game.controller.GameController;
import dad.game.controller.MenuNivelController;
import dad.game.controller.MenuPrincipalController;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App extends Application {

	// Atributos de configuración
	private static String ruta = "/imagenes/objetos/";
	private static String imagenExtension = ".gif";
	private static double volumenMusic = 0.3, volumenSound = 1;

	private static ScreenController screenController;
	private static MenuPrincipalController menuPrincipalController;
	private static MenuNivelController menuNivelController;
	private static GameController gameController;
	private static AjustesController ajustesController;
	
	private static HiloMusic hiloMusic;
	private static HiloSound hiloSound;
	
	private static List<String> nivelesCompletados = new ArrayList<String>();
	private static Date horaInicioDelJuego = new Date();
	private static int numeroDeVictorias = 0, numeroDeDerrotas = 0;

	/**
	 * Carga todos los controladores y los guarda en ScreenController para gestionar las vistas e inicia la aplicación
	 */
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
		primaryStage.getIcons().add(new Image("/imagenes/otros/favicon.png"));
		primaryStage.show();
		
		// Música de fondo
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

	/**
	 * Ejecuta un hilo reproduciendo la canción
	 * @param file Nombre del archivo de sonido MP3 sin la extensión
	 */
	public static void playMusic(String file) {
		hiloMusic = new HiloMusic(file);
		hiloMusic.run();
	}

	/**
	 * Detiene el hilo de la canción
	 */
	public static void stopMusic() {
		hiloMusic.parar();
	}


	/**
	 * Ejecuta un hilo reproduciendo el sonido
	 * @param file Nombre del archivo de sonido MP3 sin la extensión
	 */
	public static void playSound(String file) {
		hiloSound = new HiloSound(file);
		hiloSound.run();
	}

	/**
	 * Detiene el hilo del sonido
	 */
	public static void stopSound() {
		hiloSound.parar();
	}
	
	/**
	 * Ejecuta una transición de tipo "fade"
	 * @param node Nodo de JavaFX al que se le aplica la transición
	 * @param from Valor inicial
	 * @param to Valor final
	 * @param cycle Número de repeticiones
	 * @param time Duración en milisegundos
	 */
	public static void transitionFade(Node node, double from, double to, int cycle, int time) {
		FadeTransition transicionFade = new FadeTransition();
		transicionFade.setAutoReverse(true);
		transicionFade.setCycleCount(cycle);
		transicionFade.setDuration(Duration.millis(time));
		transicionFade.setFromValue(from);
		transicionFade.setToValue(to);
		transicionFade.setNode(node);
		transicionFade.setInterpolator(Interpolator.LINEAR);
		transicionFade.play();
	}
	
	public static void nivelCompletado(String nivel) {
		boolean yaGuardado = false;
		for (String nivelGuardado : nivelesCompletados) {
			if (nivelGuardado.equals(nivel)) {
				yaGuardado = true;
			}
		}
		if (!yaGuardado) {
			nivelesCompletados.add(nivel);
		}
	}

	public static List<String> getNivelesCompletados() {
		return nivelesCompletados;
	}

	public static Date getHoraInicioDelJuego() {
		return horaInicioDelJuego;
	}

	public static int getNumeroDeVictorias() {
		return numeroDeVictorias;
	}

	public static void agregarVictoria() {
		numeroDeVictorias++;
	}

	public static int getNumeroDeDerrotas() {
		return numeroDeDerrotas;
	}

	public static void agregarDerrota() {
		numeroDeDerrotas++;
	}

}

/**
 * Clase que reproduce un sonido en JavaFX
 */
class HiloSound extends Thread {

	String file;
	MediaPlayer mediaPlayer;
	
	/**
	 * Constructor
	 * @param file Nombre del archivo de sonido MP3 sin la extensión
	 */
	public HiloSound(String file) {
		this.file = file;
	}

	/**
	 * Método que inicia un hilo que reproduce el sonido
	 */
	@Override
	public void run() {
		URL path = getClass().getResource("/sonidos/" + file + ".mp3");
		Media sound;
		try {
			sound = new Media(path.toURI().toString());
			mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.setVolume(App.getVolumenSound());
			mediaPlayer.play();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Método que para la reproducción del sonido
	 */
	public void parar() {
		try {
			mediaPlayer.stop();
		} catch(Exception e) {};
	}
}

/**
 * Clase que reproduce una canción en JavaFX
 */
class HiloMusic extends Thread {

	String file;
	MediaPlayer mediaPlayer;

	/**
	 * Constructor
	 * @param file Nombre del archivo de sonido MP3 sin la extensión
	 */
	public HiloMusic(String file) {
		this.file = file;
	}

	/**
	 * Método que inicia un hilo que reproduce la canción
	 */
	@Override
	public void run() {
		URL path = getClass().getResource("/sonidos/" + file + ".mp3");
		Media sound;
		try {
			sound = new Media(path.toURI().toString());
			mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.setVolume(App.getVolumenMusic());
			mediaPlayer.setCycleCount(Transition.INDEFINITE);
			mediaPlayer.play();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que para la reproducción de la canción
	 */
	public void parar() {
		try {
			mediaPlayer.stop();
		} catch(Exception e) {};
	}
}
