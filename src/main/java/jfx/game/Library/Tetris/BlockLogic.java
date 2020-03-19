package jfx.game.Library.Tetris;

import java.time.LocalTime;
import java.util.Random;

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
	enum BlockType { SQUARE, LINE, Z, S, T, L, REVERSE_L };

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
	
	public Block rotateBlock(Block block) {
		return block;
	}
}
