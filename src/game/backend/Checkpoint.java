package game.backend;

public enum Checkpoint {
	//pensar si no conviene cambiar el value con un 2^ordinal()
	U(-1,0, 1), // arriba
	UU(-2,0, 2), // arriba x2
	D(1,0, 4), // abajo
	DD(2,0, 8), // abajo x2
	R(0,1, 16), // der
	RR(0,2, 32), // der x2
	L(0,-1, 64), // izq
	LL(0,-2, 128); // izq x2
	
	private int i;
	private int j;
	private int value;
	
	Checkpoint(int i, int j, int value) {
		this.i = i;
		this.j = j;
		this.value = value;
	}
	
	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}
	
	// de nuevo no se que representa value je
	public int getValue() {
		return value;
	}

}
