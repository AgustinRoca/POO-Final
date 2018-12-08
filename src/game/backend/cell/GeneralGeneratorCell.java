package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Element;


public class GeneralGeneratorCell extends GeneratorCell {
private Grid grid;
	public GeneralGeneratorCell(Grid grid) {
		super(grid);
		this.grid = grid;
	}

	@Override
	public Element getContent() {
		if (Math.random() > 0.97) {
			return new FruitGeneratorCell(grid).getContent();
		} else {
			return new CandyGeneratorCell(grid).getContent();
		}
	}

}