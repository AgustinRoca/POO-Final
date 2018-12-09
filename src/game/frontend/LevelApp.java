package game.frontend;

import game.backend.CandyGame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LevelApp extends Application{

    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage s){}
    public void start(Stage stage, Class<?> levelClass) {
        stage.setResizable(false);
        CandyGame game = new CandyGame(levelClass);
        CandyFrame frame = new CandyFrame(game);
        stage.setScene(new Scene(frame));
        stage.show();

    }
}