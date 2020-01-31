package dad;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	
	private static String ruta;
	private static int cantidadColumnas = 0;
	private static int cantidadFilas = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public static String getRuta() {
		return ruta;
	}

	public static int getCantidadColumnas() {
		return cantidadColumnas;
	}

	public static int getCantidadFilas() {
		return cantidadFilas;
	}
	
}
