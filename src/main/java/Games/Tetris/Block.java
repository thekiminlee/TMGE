package Games.Tetris;

import tmge.engine.boardComponents.Coordinate;
import tmge.engine.boardComponents.Tile;

public class Block {
	Tile[] tiles;
    private int value;

    public Block(Tile[] tiles) {
    	int v = 0;
    	for (Tile t: tiles)
    		v += t.getValue();
        this.value = v;
        this.tiles = tiles;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    public Tile[] getTiles() {
    	return tiles;
    }

	public int size() {
		return tiles.length;
	}

	public Tile get(int i) {
		return tiles[i];
	}
	
	public boolean contains(Coordinate c) {
		for (Tile t: tiles)
			if (c.getX() == t.getCoords().getX() && c.getY() == t.getCoords().getY())
				return true;
		return false;
	}
}