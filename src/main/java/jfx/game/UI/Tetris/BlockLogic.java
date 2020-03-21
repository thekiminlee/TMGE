package jfx.game.Library.Tetris;

import java.time.LocalTime;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

import tmge.engine.gameComponents.Coordinate;
import tmge.engine.gameComponents.Tile;
import tmge.engine.gameComponents.TileGenerator;

public class BlockLogic {

	int[][][] configurations = {	
		{ // Square
			{1,1}, {1,0},
			{0,1}, {0,0}
		}, 
		{ // Line
			{0,3}, {0,2}, {0,1}, {0,0}
		}, 
		{ // Z
			{1,2},	{1,1},
					{0,1},	{0,0}
		},
		{ // S
					{1,1},	{1,0},
			{0,2},	{0,1}
		},
		{ // T
			{1,2},	{1,1},	{1,0},
					{0,1}
		},
		{ // L
			{1, 2},	{1, 1},	{1, 0},
							{0, 0}
		},
		{ // reverse L
			{1, 2},	{1, 1},	{1, 0},
			{0, 2}
		}
	};
	public enum BlockType { SQUARE, LINE, Z, S, T, L, REVERSE_L };

	Random seed;
	
	BlockLogic() {
		seed = new Random(LocalTime.now().toNanoOfDay());
	}
	
	Block getRandomBlock(TileGenerator generator) {
		int index = seed.nextInt(configurations.length);
		return createBlock(index, 0, generator);
	}
	
	BlockType getBlockType(int index) {
		return BlockType.values()[index];
	}
	
	public Block createBlock(int index, int offset, TileGenerator generator) {
		Tile[] tiles = new Tile[configurations[index].length];
		for (int i = 0; i < configurations[index].length; i++) {
			tiles[i] = generator.createTile(index, 1,
					new Coordinate(configurations[index][i][0], configurations[index][i][1] + offset));
		}
		return new Block(tiles, getBlockType(index));
	}
	
	public Block rotateBlock(Block block, int maxRows, int maxCols) {
		int[] bounds = block.getBounds(); // bounds[0] = leftmost X, bounds[1] = topmost Y
		
		// translate block into a vertically and horizontally flipped 4x4, block appears at the bottom
		BiFunction<Coordinate, Coordinate, Coordinate> f = (c, coordOffset) -> {
			// treat tile as if it had a minimum of <0,0>
			int index = 4*(c.getX() - coordOffset.getX()) + (c.getY() - coordOffset.getY());
			int col = index%4;
			int row = index/4;
			
			// put into inverted 4x4
			index = 3 - row + 4 * col;
			return new Coordinate(index/4 + coordOffset.getX(), index%4 + coordOffset.getY());	
		};
		
		// apply above for each tile
		Coordinate temp;
		for (Tile t: block.getTiles()) {
			temp = new Coordinate(bounds[0], bounds[1]);
			t.setCoords(f.apply(t.getCoords(), temp));
		}
		
		// shift each tile back up to the top left of the 4x4
		int[] newBounds = block.getBounds();
		int rowFix = newBounds[0] - bounds[0];
		int colFix = newBounds[1] - bounds[1];
		for (Tile t: block.getTiles()) {
			temp = t.getCoords();
			t.setCoords(temp.getX() - rowFix, temp.getY() - colFix);
		}
		
		return block;
	}
}
