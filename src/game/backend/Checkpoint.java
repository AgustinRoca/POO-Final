package game.backend;

public enum Checkpoint {
	U(-1,0), // arriba
	UU(-2,0), // arriba x2
	D(1,0), // abajo
	DD(2,0), // abajo x2
	R(0,1), // der
	RR(0,2), // der x2
	L(0,-1), // izq
	LL(0,-2); // izq x2
	
	private final int i;
	private final int j;
	private final int value;
	
	Checkpoint(int i, int j) {
		this.i = i;
		this.j = j;
		this.value = (int)Math.pow(2, ordinal());
	}
	
	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}
	
	public int getValue() {
		return value;
	}

}
