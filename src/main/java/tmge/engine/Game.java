package tmge.engine;

import tmge.engine.boardComponents.Board;
import tmge.engine.boardComponents.Tile;




/* THIS WILL END UP BEING A CONTROLLER CLASS FOR BOTH THE BOARD AND THE SCREEN CLASS */

public abstract class Game {
	private Screen currentScreen;
	private Board currentBoard;

	/*
	public Game(Screen currentScreen, Board currentBoard) {
		this.currentScreen = currentScreen;
		this.currentBoard = currentBoard;
	}*/

	public Game(){}

	public void setCurrentScene(Screen screen){
		currentScreen = screen;
	}

	public void setCurrentBoard(Board board) {currentBoard = board;}

	public int getRows() {
		return this.currentBoard.getRows();
	}

	public int getColumns() {
		return this.currentBoard.getColumns();
	}

	public Tile[][] getCurrentBoard() { return this.currentBoard.getBoard();}

	protected abstract void updateScreen();

	protected abstract void updateBoard();

	/*in theory this will end up calling both the screen and board update methods */
	protected abstract void update();

}
