package tmge.engine;

import tmge.engine.gameComponents.Board;
import tmge.engine.gameComponents.Screen;


public abstract class Game {
	protected Screen currentScreen;
	protected Board currentBoard;
	protected boolean playing;

	public Game(Screen currentScreen, Board currentBoard) {
		this.currentScreen = currentScreen;
		this.currentBoard = currentBoard;
	}

	public Game(){}


	public int getRows() {
		return currentBoard.getRows();
	}

	public int getColumns() {
		return currentBoard.getColumns();
	}

	/*in theory this will end up calling both the screen and board update methods */
	protected abstract void update();
	
	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

}
