package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Element;
import game.backend.element.Fruit;
import game.backend.element.Nothing;
import game.backend.move.Direction;

public class Cell {
	
	private Grid grid;
	private Cell[] around = new Cell[Direction.values().length];
	private Element content;
	
	public Cell(Grid grid) {
		this.grid = grid;
		this.content = new Nothing();
	}

	/** Inicializa las celdas de al lado de la celda*/
	public void setAround(Cell up, Cell down, Cell left, Cell right) {
		this.around[Direction.UP.ordinal()] = up;
		this.around[Direction.DOWN.ordinal()] = down;
		this.around[Direction.LEFT.ordinal()] = left;
		this.around[Direction.RIGHT.ordinal()] = right;
	}

	/** Devuelve true si hay un candy o algo estable abajo */
	private boolean hasFloor() {
		return !around[Direction.DOWN.ordinal()].isEmpty();
	}

	/** Devuelve true si se puede mover */
	public boolean isMovable(){
		return content.isMovable();
	}

	/** Devuelve true si no hay nada solido en la celda */
	public boolean isEmpty() {
		return !content.isSolid();
	}

	public Element getContent() {
		return content;
	}

	/** Explota el contenido de la celda (si hay paquete o rayado hace esa explosion tambien), rellena esos lugares con Nothing */
	public void clearContent() {
		if (content.isMovable()) {
			Direction[] explosionCascade = content.explode();
			grid.cellExplosion(content);
			this.content = new Nothing();
			if (explosionCascade != null) {
				expandExplosion(explosionCascade); 
			}
			this.content = new Nothing();
		}
	}

	/** Por si hay paquete o rayado */
	private void expandExplosion(Direction[] explosion) {
		for(Direction d: explosion) {
			this.around[d.ordinal()].explode(d);
		}
	}

	/** explota lo que tenga que explotar */
	private void explode(Direction d) {
		clearContent();
		if (this.around[d.ordinal()] != null)
			this.around[d.ordinal()].explode(d);
	}

	/** Borra el contenido y devuelve lo que habia */
	public Element getAndClearContent() {
		if (content.isMovable()) {
			Element ret = content;
			this.content = new Nothing();
			return ret;
		}
		return null;
	}

	/** Hace que caigan los candies */
	public boolean fallUpperContent() {
		Cell up = around[Direction.UP.ordinal()];
		if (this.isEmpty() && !up.isEmpty() && up.isMovable()) {
			this.content = up.getAndClearContent();
			grid.wasUpdated();
			if (this.hasFloor()) {
				grid.tryRemove(this);
				return true;
			} else {
				Cell down = around[Direction.DOWN.ordinal()];
				return down.fallUpperContent();
			}
		} 
		return false;
	}

	public void setContent(Element content) {
		this.content = content;
	}

	public Grid getGrid(){
		return grid;
	}

}
