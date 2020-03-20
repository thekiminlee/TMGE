package Games.Tetris;

import java.time.LocalTime;
import java.util.Random;

import javafx.application.Platform;
import tmge.engine.Game;
import tmge.engine.boardComponents.*;

public class TetrisBoard extends Board {
	static final int ROWS = 20, COLUMNS = 10;
	static int delay = 1000;
	static final int minimumDelay = 500;
	TetrisScreen screen;
	Block activeBlock = null;
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

	/*
	public TetrisBoard(TetrisScreen screen) {
		super(new TileGame(ROWS, COLUMNS));
		seed = new Random(LocalTime.now().toNanoOfDay());
		TileGenerator.registerTileConfigurations(configurations, values);
		this.screen = screen;
		for (int row = 0; row < ROWS; row++)
			for (int col = 0; col < COLUMNS; col++)
				board[row][col] = TileGenerator.emptyTile();
	}*/

	public TetrisBoard() {
		super(new TileGame(ROWS, COLUMNS));
		seed = new Random(LocalTime.now().toNanoOfDay());
		TileGenerator.registerTileConfigurations(configurations, values);
		for (int row = 0; row < ROWS; row++)
			for (int col = 0; col < COLUMNS; col++)
				board[row][col] = TileGenerator.emptyTile();
	}


	@Override
	public void update() {
		while (true) {
			System.out.println("update");
			this.screen.setReady(false);
			Platform.runLater(() -> {
				if (activeBlock == null)
					createMovableBlock();
				else {
					settle();
				}
				this.screen.draw();
			});
			while(!this.screen.ready())
				try {
					Thread.sleep(1000);
					System.out.println("hm");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
	
	void createMovableBlock() {
		int config = seed.nextInt(configurations.length);
		int column = findFirstAvailableColumn(config);

		System.out.println("Creating tiles");
		if (column < 0) { // no place to drop the tile
			gameover();
		} else { // add tile to board, register movement
			Block movableBlock = TileGenerator.createBlock(config, column);
			activeBlock = movableBlock;
			for (int i = 0; i < activeBlock.size(); i++) {
				// add to board
				int row = configurations[config][i][0];
				int col = configurations[config][i][1] + column;
				System.out.println("inserted tile at: " + row + ", " + col);
				board[row][col] = activeBlock.get(i);
				// make Block movable, set empty tiles above
			}
		}
	}
	
	void settle() {
		boolean canSettle = blockCanMove(activeBlock, 1, 0);
		if (!canSettle) {
			activeBlock = null;
			return;
		} else {
			for (Tile t: activeBlock.getTiles()) {
				Coordinate c = t.getCoords();	
				makeMove(c.getX(), c.getY(), c.getX() + 1, c.getY());
			}
		}
	}
	
	boolean blockCanMove(Block block, int row, int column) {
		boolean canMove = true;
		for (Tile t: activeBlock.getTiles()) {
			Coordinate c = t.getCoords();
			Coordinate cPrime = new Coordinate(c.getX() + row, c.getY() + column);
			if (!rangeCheck(cPrime.getX(), cPrime.getY()) ||
				(occupied(cPrime.getX(), cPrime.getY()) && !activeBlock.contains(cPrime))) {
				canMove = false;
			}
		}
		return canMove;
	}
	
	void makeMove(int fromRow, int fromColumn, int toRow, int toColumn) {
		Tile t = board[fromRow][fromColumn];
		t.setCoords(toRow, toColumn);
		board[toRow][toColumn] = t;
		board[fromRow][fromColumn] = TileGenerator.emptyTile();
	}

	@Override
	public void run() {
		update();
	}


	public int[][][] getConfigurations() {
		return configurations;
	}

	public int[] getValues() {
		return values;
	}
}
