package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;

/** Es una celda que va arriba de toda la pantalla que va a ir generando los caramelos que caen */
public class CandyGeneratorCell extends Cell {
	
	public CandyGeneratorCell(Grid grid) {
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
		int i = (int)(Math.random() * CandyColor.values().length);
		return new Candy(CandyColor.values()[i]);
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
