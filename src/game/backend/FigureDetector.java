package game.backend;

import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;


public class FigureDetector {

	private Grid grid;

	public FigureDetector(Grid grid) {
		this.grid = grid;
	}

	/** se fija si se formo una forma a partir del punto (i,j) */
	public Figure checkFigure(int i, int j) {
		int acum = readCheckpoints(i, j);
		if (acum > 0) {
			for(Figure f: Figure.values()) {
				if (f.matches(acum)) {
					return f;
				}
			}
		}
		return null;
	}

	/** Devuelve la cantidad de checkpoints que son equals al cell i,j */
	private int readCheckpoints(int i, int j) {
		Element curr = grid.get(i,j);
		int acum = 0;
		for (Checkpoint cp: Checkpoint.values()) {
			int newI = i + cp.getI();
			int newJ = j + cp.getJ();
			if (newI >= 0 && newI < Grid.SIZE && newJ >= 0 && newJ < Grid.SIZE) {
				if (curr.equals(grid.get(newI, newJ))) {
					acum += cp.getValue();
				}
			}
		}
		return acum;
	}

	/** Borra la figura del grid y si es necesario, reemplaza por el que corresponda */
	public void removeFigure(int i, int j, Figure f) {
		if(grid.get(i,j) instanceof Candy) {
			CandyColor color = ((Candy) grid.get(i, j)).getColor();
			grid.clearContent(i, j);
			if (f.hasReplacement()) {
				grid.setContent(i, j, f.generateReplacement(color));
			}
			for (Checkpoint c : f.getCheckpoints()) {
				grid.clearContent(i + c.getI(), j + c.getJ());
			}
		}
	}

}
