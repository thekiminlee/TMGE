package jfx.game.GameEnv;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;

import javafx.scene.Node;
/*
    This is a tile class where the board  will be made up of tile pieces
 */
public class Tile implements Factory<Node> {
	Callable<Node> function;
	ScheduledFuture<?> future;

	public Tile(Callable<Node> function){
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
		this.future.cancel(true);
	}
}
