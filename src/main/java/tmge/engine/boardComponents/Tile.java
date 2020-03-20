package tmge.engine.boardComponents;

import java.util.concurrent.ScheduledFuture;
import java.util.function.Function;

import javafx.scene.Node;
import javafx.scene.paint.Color;

/*
    This is a tile class where the board  will be made up of tile pieces
 */
public class Tile implements Factory<Node> {
	Coordinate coords;
	Function<Color, Node> function;
	Color color;
	ScheduledFuture<?> future;
	int value;

	public Tile(int value, Coordinate coords, Color color, Function<Color, Node> function){
		this.value = value;
		this.coords = coords;
		this.color = color;
		this.function = function;
	}

	@Override
	public Node getNode() {
		try {
			return function.apply(color);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Tile clone() {
		return new Tile(value, coords.clone(), color, function);
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

	public void setCoords(int x, int y) {
		this.coords = new Coordinate(x,y);
	}

	public boolean compareTo(Tile t) {
		return this.coords.getX() == t.getCoords().getX() &&
				this.coords.getY() == t.getCoords().getY() &&
				this.getValue() == t.getValue();
	}

	public String toString() {
		return "<" + coords.getX() + "," + coords.getY() + ">:" + value;
	}

	public Color getColor() {
		return color;
	}

	public void setCoords(Coordinate coords) {
		this.coords = coords;
	}
}
