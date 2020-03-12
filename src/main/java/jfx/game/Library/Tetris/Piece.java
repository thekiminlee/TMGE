package jfx.game.Library.Tetris;

import java.util.concurrent.Callable;
import javafx.scene.Node;

import jfx.game.GameEnv.Factory;

public class Piece implements Factory<Node> {
	Callable<Node> function;
	int row, column;
	public Piece(Callable<Node> funct, int r, int c) {
		function = funct;
		row = r;
		column = c;
	}
	
	@Override
	public Node getNode() throws Exception {
		return function.call();
	}
	
	void moveDown() {
		row++;
	}
	
	void setRow(int r) {
		row = r;
	}
	
	void setColumn(int c) {
		column = c;
	}
	
	int getRow() {
		return row;
	}
	
	int getColumn() {
		return column;
	}
}
