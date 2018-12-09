package game.frontend;

import game.backend.CandyGame;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LevelRunner {
    private static Stage stage = new Stage();

    public static void run(Class<?> levelClass) {
        Platform.runLater(() -> {
            stage.setResizable(false);
            CandyGame game = new CandyGame(levelClass);
            CandyFrame frame = new CandyFrame(game);
            stage.setScene(new Scene(frame));
            stage.show();
        });
    }
}