package game.backend.move;

import game.backend.Grid;

public class NothingMove extends Move {
    public NothingMove(Grid grid) {
        super(grid);
    }
    public void removeElements(){}

    public boolean internalValidation(){
        return false;
    }
}
