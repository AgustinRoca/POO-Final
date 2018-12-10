package game.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class RightLevelPanel extends BorderPane{
    private final Label cherriesLabel;
    private final Label hazelnutsLabel;
    public RightLevelPanel(int cherriesRequired, int hazelntusRequired){
        cherriesLabel = new Label(String.format("Cherries left to Explode: %d   ", cherriesRequired));
        cherriesLabel.setAlignment(Pos.TOP_CENTER);
        cherriesLabel.setStyle("-fx-font-size: 15");
        setTop(cherriesLabel);

        hazelnutsLabel = new Label(String.format("Hazelnuts left to Explode: %d   ", hazelntusRequired));
        hazelnutsLabel.setAlignment(Pos.BOTTOM_CENTER);
        hazelnutsLabel.setStyle("-fx-font-size: 15");
        setCenter(hazelnutsLabel);

    }
    public void update(int cherriesLeftToExplode, int hazelnutsLeftToExplode){
        cherriesLabel.setText(String.format("Cherries left to Explode: %d   ", cherriesLeftToExplode));
        hazelnutsLabel.setText(String.format("Hazelnuts left to Explode: %d   ", hazelnutsLeftToExplode));

    }
}
