package game.backend.move;

import game.backend.Grid;

public class StationaryMove extends Move {

	public StationaryMove(Grid grid) { super(grid); }
	
    public void removeElements(){}

    public boolean internalValidation(){
        return false;
    }

}
