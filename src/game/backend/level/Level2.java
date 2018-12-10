package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
public class Level2 extends Grid {
    private static final int REQUIRED_SCORE = 8500;
    private static final int MAX_MOVES = 30;
    private static final int REQUIRED_CHERRIES = 3;
    private static final int REQUIRED_HAZELNUTS = 1;

    public Level2(){
        super(REQUIRED_CHERRIES,REQUIRED_HAZELNUTS);
    }
    @Override
    protected GameState newState() {
        return new Level2State(REQUIRED_SCORE, MAX_MOVES);
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
        }
        return ret;
    }

    private class Level2State extends GameState {
        public Level2State(long requiredScore, long maxMoves) {
            newGameState(requiredScore,maxMoves);
        }

        public boolean playerWon() {
            return getScore() >= requiredScore && getCherriesLeftToExplode() == 0 && getHazelnutsLeftToExplode() == 0;
        }
    }
    public String getLevelName(){
        return "Level 2";
    }
    public long getMaxMoves(){
        return MAX_MOVES;
    }
    public long getRequiredScore(){
        return REQUIRED_SCORE;
    }
    public Class<?> nextLevel() {return Level3.class;}
}
