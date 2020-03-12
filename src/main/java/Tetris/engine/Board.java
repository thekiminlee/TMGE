package Tetris.engine;

public class Board {
    Tile[][] board;

    public Board(int x, int y) {
        board = new Tile[x][y];
    }
}