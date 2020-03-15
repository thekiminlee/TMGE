package jfx.game.Library.Tetris;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import jfx.game.GameEnv.Factory;

public class Piece implements Factory<Node> {
	Callable<Node> function;
	int row, column;
	Color color;
	ScheduledFuture<?> future;
	
	public Piece(int r, int c, Color col, Callable<Node> funct) {
		function = funct;
		row = r;
		column = c;
		color = col;
	}
	
	@Override
	public Node getNode() {
		try {
			return function.call();
		} catch (Exception e) {
			e.printStackTrace();
			return new Label("getNode failed.");
		}
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
	
	void setFuture(ScheduledFuture<?> future) {
		this.future = future;
	}
	
	void stop() {
		this.future.cancel(true);
	}
	
	String getColor() {
		return color.toString();
	}
}
