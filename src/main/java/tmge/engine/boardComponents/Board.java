package tmge.engine.boardComponents;

import tmge.engine.Game;

public abstract class Board {

    protected Tile[][] board;
    long delay = 1000;
    int rows;
    int columns;

    /*
    protected Board(TileGame game) {
        board = new Tile[game.getRows()][game.getColumns()];
        this.game = game;
    }*/
	/*
    protected Board(Game game) {
    	board = new Tile[game.getRows()][game.getColumns()];

	}*/

	protected Board(int rows, int columns){
		board = new Tile[rows][columns];
		this.rows = rows;
		this.columns = columns;
	}

    /* This method allows the user to be able to add a Piece to the board. User is able to extend Tile class and be able to
    * make more complicated Tile pieces such as a Group of Tiles which would be used for a game such as Tetris. */
    public void addTile(int row, int column, Tile p) {
    	board[row][column] = p;
    }

    /* This method allows the user to be able to remove a Piece to the board. User is able to extend Tile class and be able to
     * make more complicated Tile pieces such as a Group of Tiles which would be used for a game such as Tetris. */
    public abstract void removeTile(int row, int column);

    /* This method will handle updating the board. Any updates to the board depending on clicks of the user will end up going into here */
	/* write this into the Controller part */
    //public abstract void update();
    
    public Tile[][] getBoard() {
    	return this.board;
    }

    public int getRows() {
		return rows;
	}
	public int getColumns() {
		return columns;
	}
	
	public long getDelay() {
		return delay;
	}
	
	public String toString() {
		String s = "Values:\n";
		for (int row = 0; row < getRows(); row++) {
			s += "[";
			for (int col = 0; col < getColumns(); col++)
				s += "(" + Integer.toString(board[row][col].value) + "), ";
			s += "]\n";
		}
		return s;
	}

}
