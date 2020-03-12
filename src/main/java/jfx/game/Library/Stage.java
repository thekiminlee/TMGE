package jfx.game.Library;

public abstract class Stage {
    Tile[][] board;
    Stage(Game game) {
        board = new Tile[game.getRows()][game.getColumns()];
    }

    /* This method allows the user to be able to add a Piece to the board. User is able to extend Tile class and be able to
    * make more complicated Tile pieces such as a Group of Tiles which would be used for a game such as Tetris. */
    public abstract void addPiece(Tile p);

    /* This method allows the user to be able to remove a Piece to the board. User is able to extend Tile class and be able to
     * make more complicated Tile pieces such as a Group of Tiles which would be used for a game such as Tetris. */
    public abstract void removePiece(Tile p);

    /* This method will handle updating the board. Any updates to the board depending on clicks of the user will end up going into here */
    public abstract void update();

    /* A way to score the player in the end */
    public abstract int score();

    /*A way to detect when the stage(which is the board) game has ended */
    public abstract boolean end();
}
