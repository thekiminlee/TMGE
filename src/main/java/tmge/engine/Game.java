package tmge.engine;

import tmge.engine.Screen;
import tmge.engine.boardComponents.Board;
import tmge.engine.boardComponents.Tile;

public class Game {
	private Screen currentScreen;
	private Board currentBoard;


	public Game(Screen currentScreen, Board currentBoard) {
		this.currentScreen = currentScreen;
		this.currentBoard = currentBoard;
	}

	public void setCurrentScene(Screen screen){
		currentScreen = screen;
	}

	public void setCurrentBoard(Board board) {currentBoard = board;}

	/*
	private Screen getCurrentScene() {
		return currentScreen;
	}

	private Board getCurrentBoard() { return currentBoard;}
	*/

	public int getRows() {
		return this.currentBoard.getRows();
	}

	public int getColumns() {
		return this.currentBoard.getColumns();
	}

	public Tile[][] getCurrentBoard() { return this.currentBoard.getBoard();}


}
