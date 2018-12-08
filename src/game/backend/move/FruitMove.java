package game.backend.move;

import game.backend.Grid;

public class FruitMove extends Move{

    public FruitMove(Grid grid) {
        super(grid);
    }
    public void removeElements(){}

    public boolean internalValidation(){
        return false;
    }
}
