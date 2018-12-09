package game.frontend;

import game.backend.CandyGame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class LevelRunner {
    private static Stage stage = new Stage();
    private Class<?> levelClass;

    public LevelRunner(Class<?> levelClass) {
        this.levelClass = levelClass;
    }

    public static void main(String[] args) {
        launch(args);
    }
    public void start(){ Platform.runLater(this::startAux);}

    private void startAux() {
        stage.setResizable(false);
        CandyGame game = new CandyGame(levelClass);
        CandyFrame frame = new CandyFrame(game);
        stage.setScene(new Scene(frame));
        stage.show();

    }
}