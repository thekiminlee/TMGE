package tmge.engine.gameComponents;

public class Block {
	Tile[] tiles;
    private int value;

    public Block(Tile[] tiles) {
    	int v = 0;
    	for (Tile t: tiles)
    		v += t.value;
        this.value = v;
        this.tiles = tiles;
    }
    
    public Block clone() {
    	Tile[] arr = new Tile[tiles.length];
    	for (int i = 0; i < tiles.length; i++)
    		arr[i] = tiles[i].clone();
    	return new Block(arr);
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

	public void add(int row, int column) {
		for (Tile t: tiles)
			t.setCoords(t.getCoords().getX() + row, t.getCoords().getY() + column);		
	}
	
	public boolean contains(Coordinate c) {
		for (Tile t: tiles)
			if (c.getX() == t.getCoords().getX() && c.getY() == t.getCoords().getY())
				return true;
		return false;
	}
	
	public int[] getBounds() {
		// returns  [top left, bottom right]
		Coordinate coords = tiles[0].getCoords();
		int smallestX, smallestY, largestX, largestY; 
		smallestX = largestX = coords.getX();
		smallestY = largestY = coords.getY();
		for (int i = 1; i < tiles.length; i++) {
			coords = tiles[i].getCoords();
			if (smallestX > coords.getX()) smallestX = coords.getX();
			else if (largestX < coords.getX()) largestX = coords.getX();
			if (smallestY > coords.getY()) smallestY = coords.getY();
			else if (largestY < coords.getY()) largestY = coords.getY();
		}
		return new int[]{smallestX, smallestY, largestX, largestY};
	}
	
	public String toString() {
		String s = "Block:\n\t";
		for (Tile t: tiles) {
			s += t + "\n\t";
		}
		return s;
	}
}