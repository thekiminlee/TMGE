package Tetris.engine;

import Tetris.engine.Board;
import Tetris.engine.Tile;

public class Environment {
    protected Board board;
    protected boolean running;
    protected Configuration config;

    public Environment(Configuration config) {
        this.config = config;
    }

    public Board createBoard(int x, int y) {
        Board board = new Board(x, y);
        return board;
    }

    // Game will implement this method for primary game logic
    public void render() {
    }

    // Main loop
    public void run() {
        while (true) {
            this.render();
        }
    }
}