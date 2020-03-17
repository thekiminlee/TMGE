package jfx.game.Library;

public abstract class Board {
    Tile[][] board;
    int rows;
    int columns;
    Board(Game game) {
        this.rows = game.getRows();
        this.columns = game.getColumns();
        this.board = new Tile[rows][columns];
        fillBoard();
    }

    /* This method allows the user to be able to add a Piece to the board. User is able to extend Tile class and be able to
    * make more complicated Tile pieces such as a Group of Tiles which would be used for a game such as Tetris. */
    public abstract void addPiece(Tile p);

    /* This method allows the user to be able to remove a Piece to the board. User is able to extend Tile class and be able to
     * make more complicated Tile pieces such as a Group of Tiles which would be used for a game such as Tetris. */
    public abstract void removePiece(Tile p);

    /* A way to score the player in the end */
    public abstract int score();

    /*A way to detect when the stage(which is the board) game has ended */
    public abstract boolean end();

    // Manipulation of the board
    public Tile[] getRow(int rowIndex) {
        return board[rowIndex];
    }

    //Method will be called inside of the constructor
    abstract void fillBoard();

    public Tile[][] setRow(int rowIndex, Tile[] Row) {
        if (this.rows == Row.length) {
            for (int i = 0; i < this.rows; i++) {
                board[rowIndex][i] = Row[i];
            }
        }
        return board;
    }

    public Tile[] getCol(int colIndex) {
        Tile[] currentCol = new Tile[this.columns];
        for (int i = 0; i < this.rows; i++)
            currentCol[i] = board[i][colIndex];
        return currentCol;
    }

    public Tile[][] setCol(int colIndex, Tile[] Col) {
        if (this.columns == Col.length) {
            for (int i = 0; i < this.columns; i++) {
                board[i][colIndex] = (Col[i]);
            }
        }
        return board;
   }

    public Tile getTile(int rowIndex, int colIndex) {
        return board[rowIndex][colIndex];
    }

    public void setTile(int row, int col, Tile tile) {
        board[row][col] = (tile);
    }

    // Debugging purpose. Print current board matrix with corresponding value.
    @Override
    public String toString() {
        String tileString = "";
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++)
                 tileString += "[" + board[i][j] + "]";
            tileString += "\n";
        }
        return tileString;
    }
}
