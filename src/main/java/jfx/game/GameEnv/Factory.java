package jfx.game.GameEnv;

import java.util.concurrent.Callable;

public interface Factory<T> {
	final Callable<?> function = null;
	T getNode() throws Exception;
}
