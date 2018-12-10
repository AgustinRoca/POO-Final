package game.frontend;

import game.backend.CandyGame;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class LevelRunner {
	
    private static Stage stage = new Stage();

    public static void run(Class<?> levelClass) {
        if (levelClass == null) {
            wonTheGame();
            return;
        }
        Platform.runLater(() -> {
            stage.setResizable(false);
            CandyGame game = new CandyGame(levelClass);
            CandyFrame frame = new CandyFrame(game);
            stage.setScene(new Scene(frame));
            stage.show();
        });
    }
    
    private static void wonTheGame(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The God himself");
        alert.setHeaderText("You have won the game");
        alert.setContentText("More levels coming soon");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.CLOSE) {
                Platform.exit();
            }
        }
    }
    
}