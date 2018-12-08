package game.backend.cell;

import game.backend.Grid;
import game.backend.element.*;

/** Es una celda que va arriba de toda la pantalla que va a ir generando los caramelos que caen */
public class GeneratorCell extends Cell {
	
	public GeneratorCell(Grid grid) {
		super(grid);
	}
	
	@Override
	public boolean isMovable(){
		return true;
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Element getContent() {
		if(Math.random() > 0.95){
			if(getGrid().getCherriesGenerated() < getGrid().getMaxCherries() && Math.random() > 0.5){
				getGrid().incrementCherriesGenerated();
			    return new Cherry();
			}
			else if(getGrid().getHazelnutsGenerated() < getGrid().getMaxHazelnuts()){
			    getGrid().incrementHazelnutsGenerated();
				return new Hazelnut();
			}
			else {
				int i = (int) (Math.random() * CandyColor.values().length);
				return new Candy(CandyColor.values()[i]);
			}
		}
		else {
			int i = (int) (Math.random() * CandyColor.values().length);
			return new Candy(CandyColor.values()[i]);
		}
	}
	
	@Override
	public Element getAndClearContent() {
		return getContent();
	}

	@Override
	public boolean fallUpperContent() {
		throw new IllegalStateException();
	}
	
	@Override
	public void setContent(Element content) {
		throw new IllegalStateException();
	}
	
	@Override
	public boolean equals(Object obj) {
		return false;
	}

}
