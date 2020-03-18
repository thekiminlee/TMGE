package jfx.game.Library.Tetris;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import tmge.engine.gameComponents.Board;
import tmge.engine.gameComponents.Coordinate;
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
	int[] values = {1,1,1,1,1,1,1};
	
	TetrisBoard() {
		super(new TileGame(ROWS, COLUMNS));
		seed = new Random(LocalTime.now().toNanoOfDay());
		TileGenerator.registerTileConfigurations(configurations, values);
		for (int row = 0; row < ROWS; row++)
			for (int col = 0; col < COLUMNS; col++)
				board[row][col] = TileGenerator.emptyTile();
	}

	@Override
	public void update() {
		System.out.println("update");
		try {
			if (activeTiles == null)
				createMovableTiles();
			else {
				settle();
			}
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void gameover() {
		// TODO: show popup with results
		System.out.println("Game has ended");
	}
	
	boolean rangeCheck(int row, int column) {
		return row >= 0 && row < ROWS &&
				column >= 0 && column < COLUMNS;
	}
	
	boolean occupied(int row, int column) {
		return board[row][column] != TileGenerator.emptyTile();
	}
	
	// Can a tile be dropped at this location?
	boolean canDropTile(int row, int column) {
		return rangeCheck(row, column) && !occupied(row, column);
	}
	
	synchronized int findFirstAvailableColumn(int config) {
		int column;
		
		boolean valid = false;
		for (column = 0; column < COLUMNS; column++) {
			for (int tile = 0; tile < configurations[config].length; tile++) {
				int row = configurations[config][tile][0];
				int col = configurations[config][tile][1];
				if (!canDropTile(row, col + column)) {
					valid = false;
					break;
				} else valid = true;
			}
			if (valid) return column;
		}
		return -1;
	}
	
	void createMovableTiles() {
		int config = seed.nextInt(configurations.length);
		int column = findFirstAvailableColumn(config);

		System.out.println("Creating tiles");
		if (column < 0) { // no place to drop the tile
			gameover();
		} else { // add tile to board, register movement
			ArrayList<Tile> movableTiles = TileGenerator.createTiles(config, column);
			activeTiles = movableTiles;
			for (int i = 0; i < activeTiles.size(); i++) {
				// add to board
				int row = configurations[config][i][0];
				int col = configurations[config][i][1] + column;
				System.out.println("inserted tile at: " + row + ", " + col);
				board[row][col] = activeTiles.get(i);
				// make Block movable, set empty tiles above
			}
		}
	}
	
	void settle() {
		for (Tile t: activeTiles) {
			Coordinate c = t.getCoords();
			System.out.println(c);
			if (makeMove(c.getX(), c.getY(), c.getX() + 1, c.getY()))
				activeTiles = null;
		}
	}
	
	boolean makeMove(int fromRow, int fromColumn, int toRow, int toColumn) {
		if (rangeCheck(toRow, toColumn) && !occupied(toRow, toColumn)) {
			Tile t = board[fromRow][fromColumn];
			t.setCoords(toRow, toColumn);
			board[toRow][toColumn] = t;
			board[fromRow][fromColumn] = TileGenerator.emptyTile();
			return false;
		}
		return true;
	}
	
}
