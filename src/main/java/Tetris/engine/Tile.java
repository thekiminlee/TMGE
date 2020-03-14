package Tetris.engine;

public class Tile<T> {
    T value;

    public Tile(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}