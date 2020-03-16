package tmge.engine.gameComponents;

public class Tile {
    Block block;

    public Tile(int value) {
        block = new Block(value);
    }

    public Block getBlock() {
        return block;
    }

    public int getValue() {
        return block.getValue();
    }

    public void setValue(int value) {
        block.setValue(value);
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}