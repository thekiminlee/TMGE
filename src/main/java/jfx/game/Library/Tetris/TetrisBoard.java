package jfx.game.Library.Tetris;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import tmge.engine.gameComponents.Board;
import tmge.engine.gameComponents.Tile;
import tmge.engine.gameComponents.TileGame;
import tmge.engine.gameComponents.TileGenerator;

public class TetrisBoard extends Board {
	static final int ROWS = 20, COLUMNS = 10;
	static int delay = 1000;
	static final int minimumDelay = 500;
	ArrayList<Tile> activeTiles = null;
	TileGame game;
	Random seed;
	
	int[][][] configurations = {	
		{ // Square
			{1,0}, {1,1},
			{0,0}, {0,1}
		}, 
		{ // Line
			{0,0}, {0,1}, {0,2}, {0,3}
		}, 
		{ // Z
			{0,0},	{0,1},
					{1,1},	{1,2}
		},
		{ // S
					{0,1},	{0,2},
			{1,0},	{1,1}
		},
		{ // T
			{0,0},	{0,1},	{0,2},
					{1,1}
		},
		{ // reverse L
			{0, 0},	{0, 1},	{0, 2},
							{1, 2}
		},
		{ // L
			{0, 0},	{0, 1},	{0, 2},
			{1, 0}
		}
	};
	
	TetrisBoard() {
		super(new TileGame(ROWS, COLUMNS));
		seed = new Random(LocalTime.now().toNanoOfDay());
		TileGenerator.registerTileConfigurations(configurations);
	}

	@Override
	synchronized public void addTile(int row, int column, Tile t) {
		board[row][column] = t;
	}

	@Override
	public void removeTile(int row, int column) {
		board[row][column] = null;
	}

	@Override
	public void update() {
		try {
			if (activeTiles == null)
				createMovableTiles();
			else {
				// TODO: Player has a movable 
			}
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void createMovableTiles() {
		activeTiles = TileGenerator.createTiles(seed.nextInt(configurations.length));
		for (Tile t: activeTiles) {
			// TODO: add tiles to the first available position on the board
			//		register movement options
		}
	}
	
	boolean occupied(int row, int column) {
		return board[row][column] != null;
	}
	
}
