package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;

public class Level1 extends Grid {
	
	private static int REQUIRED_SCORE = 400;
	private static int MAX_MOVES = 30;
	private static int REQUIRED_CHERRIES = 0;
	private static int REQUIRED_HAZELNUTS = 0;

	public Level1(){
		newGrid(REQUIRED_CHERRIES,REQUIRED_HAZELNUTS);
	}
	@Override
	protected GameState newState() {
		return new Level1State(REQUIRED_SCORE, MAX_MOVES);
	}
	
	@Override
	public boolean tryMove(int i1, int j1, int i2, int j2) {
		boolean ret;
		if (ret = super.tryMove(i1, j1, i2, j2)) {
			state().addMove();
		}
		return ret;
	}
	
	private class Level1State extends GameState {
		public Level1State(long requiredScore, long maxMoves) {
			newGameState(requiredScore,maxMoves);
		}

		public boolean playerWon() {
			return getScore() >= requiredScore && getCherriesLeftToExplode() == 0 && getHazelnutsLeftToExplode() == 0;
		}
	}
	public String getLevelName(){
		return "Level 1";
	}
	public long getMaxMoves(){
		return MAX_MOVES;
	}
	public long getRequiredScore(){
		return REQUIRED_SCORE;
	}
	public Class<?> nextLevel() { return Level2.class;}

}
