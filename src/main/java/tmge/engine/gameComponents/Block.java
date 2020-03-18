package tmge.engine.gameComponents;

public class Block {
	Tile[] tiles;
    private int value;

    public Block(int value, Tile[] tiles) {
        this.value = value;
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
}