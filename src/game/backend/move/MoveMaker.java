package game.backend.move;

import game.backend.Grid;
import game.backend.element.*;

import java.util.HashMap;
import java.util.Map;

public class MoveMaker {

	private Map<String, Move> map;
	private Grid grid;

	public MoveMaker(Grid grid) {
		this.grid = grid;
		initMap();
	}

	// Todas las posibles combinaciones de movimientos y como explotan
	private void initMap(){
		map = new HashMap<>();
		map.put(new Candy().getKey() + new Candy().getKey(), new CandyMove(grid));
		map.put(new Candy().getKey() + new HorizontalStripedCandy().getKey(), new CandyMove(grid));
		map.put(new Candy().getKey() + new VerticalStripedCandy().getKey(), new CandyMove(grid));
		map.put(new Candy().getKey() + new WrappedCandy().getKey(), new CandyMove(grid));
		map.put(new Candy().getKey() + new Bomb().getKey(), new BombMove(grid));
		map.put(new Candy().getKey() + new Cherry().getKey(), new CandyMove(grid));
		map.put(new Candy().getKey() + new Hazelnut().getKey(), new CandyMove(grid));

		map.put(new HorizontalStripedCandy().getKey() + new Candy().getKey(), new CandyMove(grid));
		map.put(new HorizontalStripedCandy().getKey() + new HorizontalStripedCandy().getKey(), new TwoStripedMove(grid));
		map.put(new HorizontalStripedCandy().getKey() + new VerticalStripedCandy().getKey(), new TwoStripedMove(grid));
		map.put(new HorizontalStripedCandy().getKey() + new WrappedCandy().getKey(), new WrappedStripedMove(grid));
		map.put(new HorizontalStripedCandy().getKey() + new Bomb().getKey(), new BombStrippedMove(grid));
		map.put(new HorizontalStripedCandy().getKey() + new Cherry().getKey(), new CandyMove(grid));
		map.put(new HorizontalStripedCandy().getKey() + new Hazelnut().getKey(), new CandyMove(grid));


		map.put(new VerticalStripedCandy().getKey() + new Candy().getKey(), new CandyMove(grid));
		map.put(new VerticalStripedCandy().getKey() + new HorizontalStripedCandy().getKey(), new TwoStripedMove(grid));
		map.put(new VerticalStripedCandy().getKey() + new VerticalStripedCandy().getKey(), new TwoStripedMove(grid));
		map.put(new VerticalStripedCandy().getKey() + new WrappedCandy().getKey(), new WrappedStripedMove(grid));
		map.put(new VerticalStripedCandy().getKey() + new Bomb().getKey(), new BombStrippedMove(grid));
		map.put(new VerticalStripedCandy().getKey() + new Cherry().getKey(), new CandyMove(grid));
		map.put(new VerticalStripedCandy().getKey() + new Hazelnut().getKey(), new CandyMove(grid));

		map.put(new WrappedCandy().getKey() + new Candy().getKey(), new CandyMove(grid));
		map.put(new WrappedCandy().getKey() + new HorizontalStripedCandy().getKey(), new WrappedStripedMove(grid));
		map.put(new WrappedCandy().getKey() + new VerticalStripedCandy().getKey(), new WrappedStripedMove(grid));
		map.put(new WrappedCandy().getKey() + new WrappedCandy().getKey(), new TwoWrappedMove(grid));
		map.put(new WrappedCandy().getKey() + new Bomb().getKey(), new BombWrappedMove(grid));
		map.put(new WrappedCandy().getKey() + new Cherry().getKey(), new CandyMove(grid));
		map.put(new WrappedCandy().getKey() + new Hazelnut().getKey(), new CandyMove(grid));

		map.put(new Bomb().getKey() + new Candy().getKey(), new BombMove(grid));
		map.put(new Bomb().getKey() + new HorizontalStripedCandy().getKey(), new BombStrippedMove(grid));
		map.put(new Bomb().getKey() + new VerticalStripedCandy().getKey(), new BombStrippedMove(grid));
		map.put(new Bomb().getKey() + new WrappedCandy().getKey(), new BombWrappedMove(grid));
		map.put(new Bomb().getKey() + new Bomb().getKey(), new TwoBombMove(grid));
		map.put(new Bomb().getKey() + new Cherry().getKey(), new FruitMove(grid));
		map.put(new Bomb().getKey() + new Hazelnut().getKey(), new FruitMove(grid));

		map.put(new Cherry().getKey() + new Candy().getKey(), new CandyMove(grid));
		map.put(new Cherry().getKey() + new HorizontalStripedCandy().getKey(), new CandyMove(grid));
		map.put(new Cherry().getKey() + new VerticalStripedCandy().getKey(), new CandyMove(grid));
		map.put(new Cherry().getKey() + new WrappedCandy().getKey(), new CandyMove(grid));
		map.put(new Cherry().getKey() + new Bomb().getKey(), new FruitMove(grid));
		map.put(new Cherry().getKey() + new Cherry().getKey(), new FruitMove(grid));
		map.put(new Cherry().getKey() + new Hazelnut().getKey(), new FruitMove(grid));

		map.put(new Hazelnut().getKey() + new Candy().getKey(), new CandyMove(grid));
		map.put(new Hazelnut().getKey() + new HorizontalStripedCandy().getKey(), new CandyMove(grid));
		map.put(new Hazelnut().getKey() + new VerticalStripedCandy().getKey(), new CandyMove(grid));
		map.put(new Hazelnut().getKey() + new WrappedCandy().getKey(), new CandyMove(grid));
		map.put(new Hazelnut().getKey() + new Bomb().getKey(), new FruitMove(grid));
		map.put(new Hazelnut().getKey() + new Cherry().getKey(), new FruitMove(grid));
		map.put(new Hazelnut().getKey() + new Hazelnut().getKey(), new FruitMove(grid));
	}

	// devuelve un movimiento
	public Move getMove(int i1, int j1, int i2, int j2) {
		if(!(grid.getCell(i1, j1).isJailed() || grid.getCell(i2, j2).isJailed())) {
			Move move = map.get(grid.get(i1, j1).getKey() + grid.get(i2, j2).getKey());
			move.setCoords(i1, j1, i2, j2);
			return move;
		}
		return new JailMove(grid);
	}
}
