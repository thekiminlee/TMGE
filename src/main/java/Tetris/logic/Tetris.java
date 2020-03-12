package Tetris.logic;

import Tetris.engine.Configuration;
import Tetris.engine.Environment;

public class Tetris extends Environment {
    public Tetris(Configuration config) {
        super(config);
        board = createBoard(5, 5);
    }

    public void render() {
        // board.toString();
        System.out.println(board);
    }
}