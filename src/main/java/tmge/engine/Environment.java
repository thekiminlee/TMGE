package tmge.engine;

import tmge.engine.gameComponents.*;

public class Environment {
    protected Board board;
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