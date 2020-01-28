package dad;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

public class App extends GameApplication  {

	@Override
	protected void initSettings(GameSettings settings) {
        settings.setWidth(1000);
        settings.setHeight(600);
        settings.setTitle("Dad Is You");
        //settings.setFullScreenFromStart(true);
        //settings.setFullScreenAllowed(true);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
