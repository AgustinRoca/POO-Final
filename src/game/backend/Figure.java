package game.backend;

import game.backend.element.*;

public enum Figure {
	
	// todas las posibles figuras
	//5 en linea
	F6(new Checkpoint[]{ Checkpoint.LL, Checkpoint.L, Checkpoint.R, Checkpoint.RR}, Bomb.class, false),
	F15(new Checkpoint[]{ Checkpoint.UU, Checkpoint.U, Checkpoint.D, Checkpoint.DD}, Bomb.class, false),
	//5 en linea y 3 en linea al mismo tiempo (en forma de T)
	F29(new Checkpoint[]{Checkpoint.UU, Checkpoint.U, Checkpoint.D, Checkpoint.DD, Checkpoint.R, Checkpoint.RR}, Bomb.class),
	F30(new Checkpoint[]{Checkpoint.UU, Checkpoint.U, Checkpoint.D, Checkpoint.DD, Checkpoint.L, Checkpoint.LL}, Bomb.class),
	F31(new Checkpoint[]{Checkpoint.LL, Checkpoint.L, Checkpoint.R, Checkpoint.RR, Checkpoint.U, Checkpoint.UU}, Bomb.class),
	F32(new Checkpoint[]{Checkpoint.LL, Checkpoint.L, Checkpoint.R, Checkpoint.RR, Checkpoint.D, Checkpoint.DD}, Bomb.class),
	//L y 4 en linea al mismo tiempo
	F21(new Checkpoint[]{ Checkpoint.L, Checkpoint.R, Checkpoint.RR, Checkpoint.D, Checkpoint.DD}, WrappedCandy.class),
	F22(new Checkpoint[]{ Checkpoint.LL, Checkpoint.L, Checkpoint.R, Checkpoint.D, Checkpoint.DD }, WrappedCandy.class),
	F23(new Checkpoint[]{ Checkpoint.LL, Checkpoint.L, Checkpoint.U, Checkpoint.D, Checkpoint.DD }, WrappedCandy.class),
	F24(new Checkpoint[]{ Checkpoint.LL, Checkpoint.L, Checkpoint.UU, Checkpoint.U, Checkpoint.D}, WrappedCandy.class),
	F25(new Checkpoint[]{ Checkpoint.LL, Checkpoint.L, Checkpoint.R, Checkpoint.U, Checkpoint.UU}, WrappedCandy.class),
	F26(new Checkpoint[]{ Checkpoint.L, Checkpoint.R, Checkpoint.RR, Checkpoint.U, Checkpoint.UU}, WrappedCandy.class),
	F27(new Checkpoint[]{ Checkpoint.R, Checkpoint.RR, Checkpoint.UU, Checkpoint.U, Checkpoint.D}, WrappedCandy.class),
	F28(new Checkpoint[]{ Checkpoint.R, Checkpoint.RR, Checkpoint.U, Checkpoint.D, Checkpoint.DD}, WrappedCandy.class),
    //L, T y variantes
	F7(new Checkpoint[]{ Checkpoint.LL, Checkpoint.L, Checkpoint.R, Checkpoint.D, Checkpoint.DD}, WrappedCandy.class),
    F8(new Checkpoint[]{ Checkpoint.L, Checkpoint.R, Checkpoint.D, Checkpoint.DD}, WrappedCandy.class),
    F9(new Checkpoint[]{ Checkpoint.LL, Checkpoint.L, Checkpoint.D, Checkpoint.DD}, WrappedCandy.class),
    F16(new Checkpoint[]{ Checkpoint.U, Checkpoint.D, Checkpoint.R, Checkpoint.RR}, WrappedCandy.class),
    F17(new Checkpoint[]{ Checkpoint.UU, Checkpoint.U, Checkpoint.R, Checkpoint.RR}, WrappedCandy.class),
    F18(new Checkpoint[]{ Checkpoint.UU, Checkpoint.U, Checkpoint.L, Checkpoint.LL}, WrappedCandy.class),
    F19(new Checkpoint[]{ Checkpoint.UU, Checkpoint.U, Checkpoint.L, Checkpoint.R}, WrappedCandy.class),
    F20(new Checkpoint[]{ Checkpoint.U, Checkpoint.D, Checkpoint.LL, Checkpoint.L}, WrappedCandy.class),
	//4 en linea vertical
	F4(new Checkpoint[]{ Checkpoint.L, Checkpoint.R, Checkpoint.RR}, VerticalStripedCandy.class),
	F5(new Checkpoint[]{ Checkpoint.LL, Checkpoint.L, Checkpoint.R}, VerticalStripedCandy.class),
	//4 en linea horizontal
	F13(new Checkpoint[]{ Checkpoint.U, Checkpoint.D, Checkpoint.DD}, HorizontalStripedCandy.class),
	F14(new Checkpoint[]{ Checkpoint.UU, Checkpoint.U, Checkpoint.D}, HorizontalStripedCandy.class),
	//3 en linea
	F1(new Checkpoint[]{Checkpoint.R, Checkpoint.RR}),
	F2(new Checkpoint[]{Checkpoint.L, Checkpoint.R}),
	F3(new Checkpoint[]{Checkpoint.L, Checkpoint.LL}),
	F10(new Checkpoint[]{ Checkpoint.D, Checkpoint.DD}),
	F11(new Checkpoint[]{ Checkpoint.U, Checkpoint.D}),
	F12(new Checkpoint[]{ Checkpoint.UU, Checkpoint.U});
	
	
	// La forma en la que explotan los caramelos
	private Checkpoint[] checkpoints;
	// valor
	private int value;
	// Si se transforma en otro caramelo
	private Class<?> replacementClass;
	// si se puede remplazar
	private boolean isCandyRepl = true;

	Figure(Checkpoint[] checkpoints, Class<?> replacementClass) {
		this.checkpoints = checkpoints;
		this.replacementClass = replacementClass;
        this.value = 0;
        for(Checkpoint c: checkpoints){
            this.value += c.getValue();
        }
	}
	
	Figure(Checkpoint[] checkpoints, Class<?> replacementClass, boolean isCandyRepl) {
		this.checkpoints = checkpoints;
        this.value = 0;
        for(Checkpoint c: checkpoints){
            this.value += c.getValue();
        }		this.replacementClass = replacementClass;
		this.isCandyRepl = isCandyRepl;
	}
	
	Figure(Checkpoint[] checkpoints) {
		this.checkpoints = checkpoints;
        this.value = 0;
        for(Checkpoint c: checkpoints){
            this.value += c.getValue();
        }		this.replacementClass = null;
	}
	
	public Checkpoint[] getCheckpoints() {
		return checkpoints;
	}
	
	public int size() {
		return checkpoints.length;
	}

	public boolean hasReplacement() {
		return replacementClass != null;
	}
	
	// el & es como un && pero a nivel de bits
	public boolean matches(int acum) {
		return value == (value & acum);
	}
	
	// hace tranforma un grupo de caramelos a otra cosa, una bomb a ponele
	public Element generateReplacement(CandyColor color) {
		try {
			Element e;
			e = (Element) replacementClass.getDeclaredConstructor().newInstance();
			if (isCandyRepl) {
				((Candy)e).setColor(color);
			}
			return e;
		} catch(Exception ignored) {
		}
		return null;
	}	
}
