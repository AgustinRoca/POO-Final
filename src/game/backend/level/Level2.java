package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;

public class Level2 extends Grid {
    private static int REQUIRED_SCORE = 5000;
    private static int MAX_MOVES = 20;
    private static int REQUIRED_CHERRIES = 0;
    private static int REQUIRED_HAZELNUTS = 0;


    public Level2(){
        newGrid(REQUIRED_CHERRIES,REQUIRED_HAZELNUTS);
    }
    @Override
    protected GameState newState() {
        return new Level2.Level2State(REQUIRED_SCORE, MAX_MOVES);
    }

    @Override
    public void initialize() {
        super.initialize();

        //Enjaula todas las celdas de la fila de la mitad
        for(int j= 0; j<SIZE; j++){
            g()[SIZE/2][j].setJailed(true);
            incrementJailsLeft();
        }
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
            return getScore() >= requiredScore && getJailsLeft() == 0;
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

}
