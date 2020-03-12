package Tetris.engine;

import Tetris.engine.Board;
import Tetris.engine.Tile;

public class Environment {
    protected Board board;

    public Environment() {

    }

    public Board createBoard(int x, int y) {
        Board board = new Board(x, y);
        return board;
    }

    public void start() {
    }
}