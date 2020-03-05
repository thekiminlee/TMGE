package jfx.game.Library;

public class GameBoard {
	Piece[][] board;
	GameBoard(int rows, int columns) {
		board = new Piece[rows][columns];
	}
	
	void addPiece(Piece p) {
		if (p.length() == 1) {
			
		} else if (p.length() > 1) {
			
		}
	}
	
	public String toString() {
		return "";
	}
}
