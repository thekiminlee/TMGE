package tmge.engine.gameComponents;

public abstract class Board {
    protected Tile[][] board;
    TileGame game;
    
    protected Board(TileGame game) {
        board = new Tile[game.getRows()][game.getColumns()];
        this.game = game;
    }

    /* This method allows the user to be able to add a Piece to the board. User is able to extend Tile class and be able to
    * make more complicated Tile pieces such as a Group of Tiles which would be used for a game such as Tetris. */
    public abstract void addTile(int row, int column, Tile p);

    /* This method allows the user to be able to remove a Piece to the board. User is able to extend Tile class and be able to
     * make more complicated Tile pieces such as a Group of Tiles which would be used for a game such as Tetris. */
    public abstract void removeTile(int row, int column);

    /* This method will handle updating the board. Any updates to the board depending on clicks of the user will end up going into here */
    public abstract void update();
    
    public Tile[][] getBoard() {
    	return this.board;
    }
    
    public int getRows() {
		return game.getRows();
	}
	public int getColumns() {
		return game.getColumns();
	}
}
