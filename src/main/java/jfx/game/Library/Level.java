package jfx.game.Library;

public class Level {
    Piece[][] board;
    Integer stage;
    Integer level;
    Level(int level,int rows, int columns) {
        board = new Piece[rows][columns];
        this.stage = null;
        this.level = level;
    }

    Level(int stage, int level, int rows,int columns) {
        board = new Piece[rows][columns];
        this.stage = stage;
        this.level = level;
    }

    void addPiece(Piece p) {
        if (p.length() == 1) {

        } else if (p.length() > 1) {

        }
    }

    void update()
    {
        //for each in tile: if tile implemnets updatable.update()
    }

    public String toString() {
        return "";
    }
}
