package dad;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	
	private static String ruta;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public static String getRuta() {
		return App.ruta;
	}
}
