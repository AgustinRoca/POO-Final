package game.backend.move;

import game.backend.Grid;

public class JailMove extends Move {
    public JailMove(Grid grid) {
        super(grid);
    }
    public void removeElements(){}

    public boolean internalValidation(){
        return false;
    }
}
