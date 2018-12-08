package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Hazelnut;
import game.backend.element.Cherry;
import game.backend.element.Element;


public class FruitGeneratorCell extends GeneratorCell {
    public FruitGeneratorCell(Grid grid) {
        super(grid);
    }

    @Override
    public Element getContent() {
        if (Math.random() > 0.5){
            return new Hazelnut();
        }
        return new Cherry();
    }

}