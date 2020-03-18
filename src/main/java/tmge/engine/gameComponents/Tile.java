package tmge.engine.gameComponents;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;

import javafx.scene.Node;
/*
    This is a tile class where the board  will be made up of tile pieces
 */
public class Tile implements Factory<Node> {
	Coordinate coords;
	Callable<Node> function;
	ScheduledFuture<?> future;
	int value;

	public Tile(int value, Coordinate coords, Callable<Node> function){
		this.value = value;
		this.coords = coords;
		this.function = function;
	}

	@Override
	public Node getNode() {
		try {
			return function.call();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	void setFuture(ScheduledFuture<?> future) {
		this.future = future;
	}
	
	void stop() {
		future.cancel(true);
	}
	
	public int getValue() {
		return value;
	}
	
	public Coordinate getCoords() {
		return coords;
	}
	
	public void setX(int x) {
		this.coords.setX(x);
	}
	
	public void setY(int y) {
		this.coords.setX(y);
	}
	
	public boolean compareTo(Tile t) {
		return this.coords.getX() == t.getCoords().getX() &&
				this.coords.getY() == t.getCoords().getY() &&
				this.getValue() == t.getValue();
	}
}
