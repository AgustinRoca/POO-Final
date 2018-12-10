package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Element;


public class GeneralGeneratorCell extends GeneratorCell {
	public GeneralGeneratorCell(Grid grid) {
		super(grid);
	}

	@Override
	public Element getContent() {
		if (Math.random() > 0.85) {
			return new FruitGeneratorCell(getGrid()).getContent();
		} else {
			return new CandyGeneratorCell(getGrid()).getContent();
		}
	}

}