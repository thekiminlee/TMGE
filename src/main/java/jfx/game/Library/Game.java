package jfx.game.Library;

import tmge.engine.gameComponents.Board;
import tmge.engine.gameComponents.Screen;

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

	public Game(){}


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
