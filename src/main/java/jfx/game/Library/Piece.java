package jfx.game.Library;

/*
 * A Piece can be either a single tile or a group of tiles.
 */
public class Piece {
	Tile[] tiles;
	
	
	int length() {
		return tiles.length;
	}
	
	
}
