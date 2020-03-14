package Tetris.engine;

public class Board {
    Tile<Integer>[][] board;
    int row;
    int col;

    public Board(int x, int y) {
        row = x;
        col = y;
        initializeBoard();
    }

    private void initializeBoard() {
        board = new Tile<Integer>[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = new Tile(0);
            }
        }
    }

    // Debugging purpose. Print current board matrix with corresponding value.
    @Override
    public String toString() {
        String tiles = "";
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                tiles += "[" + board[i][j] + "]";
            }
            tiles += "\n";
        }
        return tiles;
    }
}