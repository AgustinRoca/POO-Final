package game.backend;

public abstract class GameState {
	protected long requiredScore;
	private long maxMoves;

	private long score = 0;
	private int moves = 0;
	
	public void addScore(long value) {
		this.score = this.score + value;
	}
	protected void newGameState(long requiredScore, long maxMoves){
		this.requiredScore = requiredScore;
		this.maxMoves = maxMoves;
	}
	public long getScore(){
		return score;
	}
	
	public void addMove() {
		moves++;
	}
	
	private int getMoves() {
		return moves;
	}

	public boolean gameOver() {
		return playerWon() || getMoves() >= maxMoves;
	}

	public abstract boolean playerWon();

}
