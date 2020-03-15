package tmge.engine.gameComponents;

import tmge.engine.gameComponents.Tile;

public class Board {
    Tile[][] board;
    int row;
    int col;

    public Board(int x, int y) {
        board = new Tile[x][y];
        row = x;
        col = y;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                board[i][j] = new Tile(0);
    }

    // Manipulation of the board
    public Tile[] getRow(int rowIndex) {
        return board[rowIndex];
    }

    public Tile[][] setRow(int rowIndex, int[] Row) {
        if (this.row == Row.length) {
            for (int i = 0; i < this.row; i++) {
                board[rowIndex][i].setValue(Row[i]);
            }
        }
        return board;
    }

    public Tile[] getCol(int colIndex) {
        Tile[] currentCol = new Tile[col];
        for (int i = 0; i < row; i++)
            currentCol[i] = board[i][colIndex];
        return currentCol;
    }

    public Tile[][] setCol(int colIndex, int[] Col) {
        if (this.col == Col.length) {
            for (int i = 0; i < this.col; i++) {
                board[i][colIndex].setValue(Col[i]);
            }
        }
        return board;
    }

    public Tile getTile(int rowIndex, int colIndex) {
        return board[rowIndex][colIndex];
    }

    public Tile[][] setTile(int row, int col, int value) {
        board[row][col].setValue(value);
        return board;
    }

    // Getters & Setters for row/col count
    public int getRowCount() {
        return row;
    }

    public void setRowCount(int row) {
        this.row = row;
    }

    public int getColCount() {
        return col;
    }

    public void setColCount(int col) {
        this.col = col;
    }

    // Debugging purpose. Print current board matrix with corresponding value.
    @Override
    public String toString() {
        String tiles = "";
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++)
                tiles += "[" + board[i][j].getValue() + "]";
            tiles += "\n";
        }
        return tiles;
    }
}