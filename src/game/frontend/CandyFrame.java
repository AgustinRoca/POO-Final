
package game.frontend;

import game.backend.CandyGame;
import game.backend.GameListener;
import game.backend.cell.Cell;
import game.backend.element.Element;
import game.backend.level.Level1;
import game.backend.level.Level2;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class CandyFrame extends VBox {

	private static final int CELL_SIZE = 65;

	private BoardPanel boardPanel;
	private ScorePanel scorePanel;
	private ImageManager images;
	private Point2D lastPoint;
	private LevelPanel levelPanel;
	private CandyGame game;
	private AppMenu appMenu;

	public CandyFrame(CandyGame game) {
		this.game = game;
		game.initGame();
		appMenu = new AppMenu();
		getChildren().add(appMenu);
		levelPanel = new LevelPanel(game.getLevelName(), game.getRequiredScore(), game.getMaxMoves(), game.getCherriesLeftToExplode(), game.getHazelnutsLeftToExplode());
		getChildren().add(levelPanel);
		images = new ImageManager();
		boardPanel = new BoardPanel(game.getSize(), game.getSize(), CELL_SIZE);
		getChildren().add(boardPanel);
		scorePanel = new ScorePanel();
		getChildren().add(scorePanel);
		GameListener listener;
		game.addGameListener(listener = new GameListener() {
			@Override
			public void gridUpdated() {
				Timeline timeLine = new Timeline();
				Duration frameGap = Duration.millis(100);
				Duration frameTime = Duration.ZERO;
				for (int i = game().getSize() - 1; i >= 0; i--) {
					for (int j = game().getSize() - 1; j >= 0; j--) {
						int finalI = i;
						int finalJ = j;
						Cell cell = CandyFrame.this.game.get(i, j);
						Element element = cell.getContent();
						if(cell.isJailed()){
							Image combined = images.getImage("JAIL-" + element.getFullKey());
							timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, null)));
							timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, combined)));
						}
						else {
							Image image = images.getImage(element);
							timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, null)));
							timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, image)));
						}
					}
					frameTime = frameTime.add(frameGap);
				}
				timeLine.play();
			}
			@Override
			public void cellExplosion(Element e) {
				//
			}
		});
		listener.gridUpdated();

		addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (lastPoint == null || lastPoint.getY() < 0) {
				lastPoint = translateCoords((event.getX()), event.getY());
				System.out.println("Get first = " +  lastPoint);
			} else {
				Point2D newPoint = translateCoords(event.getX(), event.getY());
				if (newPoint != null && newPoint.getY() > 0) {
					System.out.println("Get second = " +  newPoint);
					if (game().tryMove((int)lastPoint.getX(), (int)lastPoint.getY(), (int)newPoint.getX(), (int)newPoint.getY()))
						levelPanel.update(game.getCherriesLeftToExplode(), game.getHazelnutsLeftToExplode());
					String message = ((Long)game().getScore()).toString();
					if (game().isFinished()) {
						if (game().playerWon()) {
							message = message + " Finished - Player Won!";
							Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
							alert.setTitle("Nivel ganado");
							alert.setHeaderText("You Won!");
							alert.setContentText("¿Quiere ir al siguiente nivel?");
							Optional<ButtonType> result = alert.showAndWait();
							if(result.isPresent()) {
								if (result.get() == ButtonType.OK) {
									new LevelRunner(game.nextLevel()).start(new Stage());
									Platform.exit();
								}
								else
									Platform.exit();
							}
						} else {
							message = message + " Finished - Loser !";
							Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
							alert.setTitle("Nivel Perdido");
							alert.setHeaderText("You Lost!");
							alert.setContentText("¿Quiere reintentar el nivel?");
							Optional<ButtonType> result = alert.showAndWait();
							if(result.isPresent()) {
								if (result.get() == ButtonType.OK) {
									//insert this level again here
								}
								else
									Platform.exit();
							}

						}
					}
					scorePanel.updateScore(message);
					lastPoint = null;
				}
			}
		});

	}

	private CandyGame game() {
		return game;
	}

	private Point2D translateCoords(double x, double y) {
		double i = x / CELL_SIZE;
		double j = (y-levelPanel.getHeight() - appMenu.getHeight()) / CELL_SIZE;
		return (i >= 0 && i < game.getSize() && j >= 0 && j < game.getSize()) ? new Point2D(j, i) : null;
	}

}
