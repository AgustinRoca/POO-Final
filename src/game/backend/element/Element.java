package game.backend.element;

import game.backend.move.Direction;

public abstract class Element {

	/** Devuelve true si el element se puede mover */
	public abstract boolean isMovable();

	public abstract String getKey();
	
	public String getFullKey() {
		return getKey();
	}

	/** Devuelve true si es un elemento solido */
	public boolean isSolid() {
		return true;
	}

	/** Devuelve las direccione que deberian explotar */
	public Direction[] explode() {
		return null;
	}

	/** Devuelve cuantos puntos hay que sumar si se explota el caramelo */
	public long getScore() {
		return 0;
	}
	
}
