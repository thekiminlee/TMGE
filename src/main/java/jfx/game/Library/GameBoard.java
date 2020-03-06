package jfx.game.Library;

import java.util.ArrayList;

public class GameBoard {
	GameBoard(){}
	Level currentLevel;
	ArrayList<Level> listOfLevels;


	void setBoard(Level board){
		//some way to load it into the gameBoard
	}

	/*
	Loads in the current set board of the game
	 */
	void loadBoard(){

	}

	void addLevel(Level level){
		listOfLevels.add(level);
	}

	
	void addPiece(Piece p) {
		if (p.length() == 1) {
			
		} else if (p.length() > 1) {
			
		}
	}

	void update()
	{
		//for each in tile: if tile implemnets updatable.update()
	}
	
	public String toString() {
		return "";
	}
}
