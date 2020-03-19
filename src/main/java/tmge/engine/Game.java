package tmge.engine;

import tmge.engine.Screen;
import tmge.engine.boardComponents.Board;
import tmge.engine.boardComponents.Tile;




/* THIS WILL END UP BEING A CONTROLLER CLASS FOR BOTH THE BOARD AND THE SCREEN CLASS */

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

	public int getRows() {
		return this.currentBoard.getRows();
	}

	public int getColumns() {
		return this.currentBoard.getColumns();
	}

	public Tile[][] getCurrentBoard() { return this.currentBoard.getBoard();}


}
