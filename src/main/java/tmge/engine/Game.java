package tmge.engine;

import tmge.engine.Screen;
import tmge.engine.boardComponents.Board;

public class Game {
	Screen currentScreen;
	Board currentBoard;
	int rows;
	int columns;

	public Game(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		this.currentScreen = null;
	}

	public void setCurrentScene(Screen screen){
		currentScreen = screen;
	}

	public String toString() {
		return "";
	}

	public Screen getCurrentScene() {
		return currentScreen;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
}
