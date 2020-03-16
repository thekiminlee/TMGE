package Tetris.logic;

import tmge.engine.Configuration;
import tmge.engine.Environment;

public class Tetris extends Environment {

    public Tetris(Configuration config) {
        super(config);
        board = createBoard(5, 5);
    }

    public void render() {

    }
}