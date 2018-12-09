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
            if (getGrid().takeCherry()) {
                return new Cherry();
            } else
                return new CandyGeneratorCell(getGrid()).getContent();
        }
        if(getGrid().takeHazelnut()){
            return new Hazelnut();
        } else
            return new CandyGeneratorCell(getGrid()).getContent();
    }
}