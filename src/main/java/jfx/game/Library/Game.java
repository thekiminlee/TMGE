package jfx.game.Library;

import tmge.engine.gameComponents.Screen;

public class Game {
	Screen currentScreen;
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
