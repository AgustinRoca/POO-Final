package game.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class LeftLevelPanel extends BorderPane {
    private Label scoreLabel;
    private Label movesLabel;
    private long movesLeft;
    public LeftLevelPanel(long requiredScore, long maxMoves){
    scoreLabel = new Label(String.format("   Required Score: %d",requiredScore));
    scoreLabel.setAlignment(Pos.TOP_LEFT);
    scoreLabel.setStyle("-fx-font-size: 20");
    setTop(scoreLabel);

    this.movesLeft = maxMoves;
    movesLabel = new Label(String.format("   Moves Left: %d",maxMoves));
    movesLabel.setAlignment(Pos.BOTTOM_LEFT);
    movesLabel.setStyle("-fx-font-size: 20");
    setBottom(movesLabel);
    }

    public void update(){
        movesLabel.setText(String.format("   Moves Left: %d",--movesLeft));
    }
}
