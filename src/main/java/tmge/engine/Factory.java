package tmge.engine;

import java.util.concurrent.Callable;

public interface Factory<T> {
	Callable<?> function = null;
	T getNode() throws Exception;
}
