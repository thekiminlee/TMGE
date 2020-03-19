package jfx.game.Library.Tetris;

import java.time.LocalTime;
import java.util.Random;

import javafx.application.Platform;
import tmge.engine.gameComponents.Board;
import tmge.engine.gameComponents.Coordinate;
import tmge.engine.gameComponents.Tile;
import tmge.engine.gameComponents.Block;
import tmge.engine.gameComponents.TileGame;
import tmge.engine.gameComponents.TileGenerator;

public class TetrisBoard extends Board {
	static final int ROWS = 20, COLUMNS = 10;
	static int delay = 1000;
	static final int minimumDelay = 500;
	boolean playing = true;
	TetrisScreen screen;
	Block activeBlock = null;
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
	
	TetrisBoard(TetrisScreen screen) {
		super(new TileGame(ROWS, COLUMNS));
		seed = new Random(LocalTime.now().toNanoOfDay());
		this.screen = screen;
		for (int row = 0; row < ROWS; row++)
			for (int col = 0; col < COLUMNS; col++)
				board[row][col] = TileGenerator.emptyTile();
	}
	
	@Override
	public void run() {
		update();
	}

	@Override
	public void update() {
		while (playing) {
			System.out.println("update");
			this.screen.setReady(false);
			Platform.runLater(() -> {
				if (activeBlock == null)
					createMovableBlock();
				else {
					attemptAction(activeBlock, Moves.TRANSLATE_VERTICAL, 1);
				}
				this.screen.draw();
			});
			while(!this.screen.ready()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (!playing) break;
		}
	}
	
	public enum Moves { TRANSLATE_VERTICAL, TRANSLATE_HORIZONTAL, ROTATE_CLOCKWISE };
	synchronized void attemptAction(Block block, Moves moveType, int n) {
		System.out.println("action made");
		switch (moveType) {
		case TRANSLATE_VERTICAL:
			activeBlock = attemptMoveDown(block, n);
			break;
		case TRANSLATE_HORIZONTAL:
			activeBlock = attemptMoveHorizontal(block, n);
			break;
		case ROTATE_CLOCKWISE:
			activeBlock = rotateBlock(block);
			break;
		}
	}
	
	synchronized Block attemptMoveDown(Block block, int row) {
		boolean canMove = blockCanMove(block, row, 0);
		if (canMove && block != null) {
			removeTiles(block.getTiles());
			return moveBlock(block, row, 0);
		}
		return null;
	}
	
	Block attemptMoveHorizontal(Block block, int column) {
		boolean canMove = blockCanMove(block, 0, column);
		if (canMove && block != null) {
			removeTiles(block.getTiles());
			return moveBlock(block, 0, column);
		} 
		return null;
	}
	
	public Block createBlock(int index, int column) {
		Tile[] tiles = new Tile[configurations[index].length];
		for (int i = 0; i < configurations[index].length; i++) {
			tiles[i] = TileGenerator.createTile(1, index,
					new Coordinate(configurations[index][i][0], configurations[index][i][1] + column));
		}
		Block block = new Block(tiles);
		System.out.println("created block\n" + block);
		return block;
	}
	
	boolean rangeCheck(int row, int column) {
		return row >= 0 && row < ROWS &&
				column >= 0 && column < COLUMNS;
	}
	
	boolean occupied(int row, int column) {
		return board[row][column].getValue() != 0;
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
		
		if (column < 0) {
			// no place to drop the tile
			gameover();
		} else { 
			// add tile to board, register movement
			Block movableBlock = createBlock(config, column);
			
			screen.translateMovableBlock(movableBlock, (m, n) -> {
				attemptAction(movableBlock, m, n);
				return true;
			});
			activeBlock = movableBlock;
		}
	}
	
	void settleBlock(Block block) {
		for (Tile t: block.getTiles()) {
			Coordinate coords = t.getCoords();
			board[coords.getX()][coords.getY()] = t;
		}
		activeBlock = null;
	}

	void removeTiles(Tile[] block) {
		for (Tile t: block) {
			Coordinate coords = t.getCoords();
			board[coords.getX()][coords.getY()] = TileGenerator.emptyTile();
		}
	}	
	
	Block rotateBlock(Block block) {
		if (block == null) return null;
		// TODO
		
		return block;
	}
	
	Block moveBlock(Block block, int row, int column) {
		if (block == null) return null;
		if (blockCanMove(block, row, column)) {
			block.add(row, column);
			return block;
		} else {
			settleBlock(activeBlock);
			return null;
		}
	}
	
	boolean blockCanMove(Block block, int row, int column) {
		if (block == null) return false;
		boolean canMove = true;
		for (Tile t: block.getTiles()) {
			Coordinate c = t.getCoords();
			Coordinate cPrime = new Coordinate(c.getX() + row, c.getY() + column);
			if (!rangeCheck(cPrime.getX(), cPrime.getY()) ||
				occupied(cPrime.getX(), cPrime.getY())) {
				canMove = false;
			}
		}
		return canMove;
	}
	
	// FIXME: overwriting moving tiles
	void makeMove(Tile t, int toRow, int toColumn) {
		Coordinate previousCoords = t.getCoords();
		board[toRow][toColumn] = t;
		board[previousCoords.getX()][previousCoords.getY()] = TileGenerator.emptyTile();
		t.setCoords(toRow, toColumn);
	}
	
	Block getActiveBlock() {
		return activeBlock;
	}
	
	void gameover() {
		playing = false;
		screen.onEnd();
		System.out.println("Game has ended");
	}
	
}
