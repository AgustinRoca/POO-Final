package game.frontend;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

public class LevelRunner {
    private static Stage stage = new Stage();
    private Class<?> levelClass;

    public LevelRunner(Class<?> levelClass) {
        this.levelClass = levelClass;
    }

    public void start() {
        try {
            new JFXPanel();

            Platform.runLater(() -> new LevelApp().start(stage, levelClass));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
