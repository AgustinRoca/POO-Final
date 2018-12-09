package game.backend;

import game.backend.cell.Cell;
import game.backend.element.*;
import game.backend.move.Move;
import game.backend.move.MoveMaker;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Grid {

	public static final int SIZE = 9;

	private Cell[][] g = new Cell[SIZE][SIZE];
	private Map<Cell, Point> gMap = new HashMap<>();
	private GameState state;
	private List<GameListener> listeners = new ArrayList<>();
	private MoveMaker moveMaker;
	private FigureDetector figureDetector;
	private int hazelnutsLeft;
	private int cherriesLeft;
	private int cherriesExploded = 0;
	private int hazelnutsExploded = 0;

    protected abstract GameState newState();

	/** Rellena todas las celdas del juego*/
	protected abstract void fillCells();
	
	protected Cell[][] g() {
		return g;
	}
	protected void newGrid(int requiredCherries, int requiredHazelnuts){
		this.cherriesLeft = requiredCherries;
		this.hazelnutsLeft = requiredHazelnuts;
	}

	protected GameState state(){
		return state;
	}
	
	public void initialize() {
		moveMaker = new MoveMaker(this);
		figureDetector = new FigureDetector(this);
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				g[i][j] = new Cell(this);
				gMap.put(g[i][j], new Point(i,j));
			}
		}
		fillCells();
		fallElements();
	}	

	/** Devuelve el contenido de la celda */
	public Element get(int i, int j) {
		return g[i][j].getContent();
	}

	/** Devuelve la celda */
	public Cell getCell(int i, int j) {
		return g[i][j];
	}

    public boolean takeCherry(){
		if (cherriesLeft > 0){
			cherriesLeft--;
			return true;
		}
		return false;

    }
    public boolean takeHazelnut(){
		if (hazelnutsLeft > 0){
			hazelnutsLeft--;
			return true;
		}
		return false;
	}
    /** Usa el fallUpperContent de cada cell si la cell no esta vacia */
    private void fallElements() {
		//Porque empieza de arriba a la derecha tho? porque no empezar los dos con 0? o los dos con SIZE?
		int i = SIZE - 1;
		while (i >= 0) {
			int j = 0;
			while (j < SIZE) {
				if (g[i][j].isEmpty()) {
					if (g[i][j].fallUpperContent()) {
						i = SIZE;
						break;
					} 
				}
				j++;
			}	
			i--;
		}
	}


	public void clearContent(int i, int j) {
		g[i][j].clearContent();
	}
	
	public void setContent(int i, int j, Element e) {
		g[i][j].setContent(e);
	}

	/** Swapea los candies de (i1, j1) y (i2, j2), si no es valido vuelve como estaba */
	public boolean tryMove(int i1, int j1, int i2, int j2) {
		Move move = moveMaker.getMove(i1, j1, i2, j2);
		swapContent(i1, j1, i2, j2);
		if (move.isValid()) {
			move.removeElements();
			fallElements();
			return true;
		} else {
			swapContent(i1, j1, i2, j2);
			return false;
		}
	}	

	/** Si con la celda forman una figura entonces remueve la figura */
	public void tryRemove(Cell cell) {
		if (gMap.containsKey(cell)) {
            Point p = gMap.get(cell);
			FruitRemoveCheck(p.x,p.y);


            Figure f = figureDetector.checkFigure(p.x, p.y);
			if (f != null) {
				removeFigure(p.x, p.y, f);
			}
		}
	}

	public void FruitRemoveCheck(int x, int y){
		Element content = getCell(x,y).getContent();
		if ( content instanceof Cherry && x == SIZE - 1){
			cherriesExploded++;
			clearContent(x,y);
		}
		if ( content instanceof Hazelnut && x == SIZE -1 ){
			hazelnutsExploded++;
			clearContent(x,y);
		}
	}

	/** Remueve los caramelos que forman la figura mostrada */
	private void removeFigure(int i, int j, Figure f) {
		CandyColor color = ((Candy)get(i, j)).getColor(); //esto es porque no puede pasar que no haya movido un candy
		if (f.hasReplacement()) {
			setContent(i, j, f.generateReplacement(color));
		} else {
			clearContent(i, j);
		}
		for (Checkpoint c: f.getCheckpoints()) {
			clearContent(i + c.getI(), j + c.getJ());
		}
	}

	/** Swapea los lugares (i1, j1) con (i1, j2) y los muestra en pantalla*/

	private void swapContent(int i1, int j1, int i2, int j2) {
		Element e = g[i1][j1].getContent();
		g[i1][j1].setContent(g[i2][j2].getContent());
		g[i2][j2].setContent(e);
		wasUpdated();
	}
	
	public GameState createState() {
		this.state = newState();
		return this.state;
	}
	
	public void addListener(GameListener listener) {
		listeners.add(listener);
	}

	/** Actualiza en pantalla el grid */
	public void wasUpdated(){
		if (listeners.size() > 0) {
			for (GameListener gl: listeners) {
				gl.gridUpdated();
			}
		}
	}

	/**  */
	public void cellExplosion(Element e) {
		for (GameListener gl: listeners) {
			gl.cellExplosion(e);
		}
	}
	public abstract String getLevelName();
	public abstract long getMaxMoves();
	public abstract long getRequiredScore();
}

