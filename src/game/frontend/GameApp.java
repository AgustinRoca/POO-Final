package game.frontend;

import game.backend.level.Level1;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class GameApp extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {

		new LevelMenu(primaryStage).setVisible(true);
	}
}