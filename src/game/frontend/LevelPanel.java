package game.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class LevelPanel extends BorderPane{
    private Label levelLabel;
    private LeftLevelPanel leftPanel;
    private RightLevelPanel rightPanel;

    public LevelPanel(String level,long requiredScore, long maxMoves,int cherriesLeftToExplode, int hazelnutsLeftToExplode ) {
        setStyle("-fx-background-color: #ff5bdd");
        levelLabel = new Label(level);
        levelLabel.setAlignment(Pos.TOP_CENTER);
        levelLabel.setStyle("-fx-font-size: 35");
        setCenter(levelLabel);

        leftPanel = new LeftLevelPanel(requiredScore, maxMoves);
        setLeft(leftPanel);

        rightPanel = new RightLevelPanel(cherriesLeftToExplode, hazelnutsLeftToExplode);
        setRight(rightPanel);
    }
    public void update(int cherriesLeftToExplode, int hazelnutsLeftToExplode){
        leftPanel.update();
        rightPanel.update(cherriesLeftToExplode, hazelnutsLeftToExplode);
    }

}
