package Tetris.engine;

public class Board {
    Tile[][] tiles;

    public Board(int x, int y) {
        tiles = new Tile[x][y];
    }

    @Override
    public String toString() {
        String _tiles = "board";
        return _tiles;
    }
}