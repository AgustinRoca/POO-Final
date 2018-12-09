package game.frontend;

import game.backend.level.Level1;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameApp extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {
	//new LevelMenu(primaryStage).setVisible(true);
	LevelRunner.run(Level1.class);
	}
}