package jfx.game.Library.Tetris;

import java.time.LocalTime;
import java.util.Random;

import javafx.application.Platform;
import tmge.engine.gameComponents.Board;
import tmge.engine.gameComponents.Coordinate;
import tmge.engine.gameComponents.Tile;
import tmge.engine.gameComponents.TileGame;
import tmge.engine.gameComponents.TileGenerator;

public class TetrisBoard extends Board {
	static final int ROWS = 20, COLUMNS = 10;
	int delay = 1000;
	final int minimumDelay = 500;
	Random seed;
	boolean playing = true;
	TetrisScreen screen;
	TileGenerator generator;
	Block activeBlock = null;
	TileGame game;
	BlockLogic logic;
	int score = 0;
	public enum Moves { TRANSLATE_VERTICAL, TRANSLATE_HORIZONTAL, ROTATE_CLOCKWISE };
	
	TetrisBoard(TetrisScreen screen) {
		super(new TileGame(ROWS, COLUMNS));
		logic = new BlockLogic();
		score = 0;
		seed = new Random(LocalTime.now().toNanoOfDay());
		this.screen = screen;
		generator = screen.getGenerator();
		generator.setGridDimensions(ROWS, COLUMNS);
		
		clearBoard();
	}
	
	@Override
	public void run() {
		update();
	}

	@Override
	public void update() {
		while (playing) {
			this.screen.setReady(false);
			Platform.runLater(() -> {
				if (activeBlock == null)
					createMovableBlock();
				else {
					attemptAction(Moves.TRANSLATE_VERTICAL, 1);
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
	
	void clearBoard() {
		for (int row = 0; row < ROWS; row++)
			for (int col = 0; col < COLUMNS; col++)
				board[row][col] = generator.emptyTile();
	}
	
//	synchronized void attemptAction(Block block, Moves moveType, int n) {
	synchronized void attemptAction(Moves moveType, int n) {
		switch (moveType) {
		case TRANSLATE_VERTICAL:
			while (activeBlock != null && n-- > 0) {
				activeBlock = attemptMoveDown(activeBlock, 1);
			}
			break;
		case TRANSLATE_HORIZONTAL:
			activeBlock = attemptMoveHorizontal(activeBlock, n);
			break;
		case ROTATE_CLOCKWISE:
			activeBlock = rotate();
			break;
		}
	}
	
	Block rotate() {
		return (activeBlock != null) ? logic.rotateBlock(activeBlock, ROWS, COLUMNS) : null;
	}
	
	synchronized Block attemptMoveDown(Block block, int row) {
		if (block == null) return null;
		if (blockCanMove(block, row, 0)) {
			block.add(row, 0);
			return block;
		} else {
			addTiles(block);
			checkForMatches(block);
			return null;
		}
	}
	
	Block attemptMoveHorizontal(Block block, int column) {
		if (block == null) return null;
		if (blockCanMove(block, 0, column)) {
			block.add(0, column);
		}
		return block;
	}
	
	boolean rangeCheck(int row, int column) {
		return row >= 0 && row < ROWS &&
				column >= 0 && column < COLUMNS;
	}
	
	boolean occupied(int row, int column) {
		return board[row][column].getValue() > 0;
	}
	
	// Can a tile be dropped at this location?
	boolean canDropTile(int row, int column) {
		return rangeCheck(row, column) && !occupied(row, column);
	}
	
	synchronized Block findFirstAvailableColumn() {
		Block tileConfig = logic.getRandomBlock(generator);
		int column;
		boolean valid = false;
		for (column = 0; column < COLUMNS; column++) {
			for (Tile t: tileConfig.getTiles()) {
				int row = t.getCoords().getX();
				int col = t.getCoords().getY();
				if (!canDropTile(row, col + column)) {
					valid = false;
					break;
				} else valid = true;
			}
			if (valid) {
				tileConfig.add(0, column);
				return tileConfig;
			}
		}
		return null;
	}
	
	void createMovableBlock() {
		Block block = findFirstAvailableColumn();
		if (block == null) {
			// no place to drop the tile
			gameover();
		} else { 
			activeBlock = block;
			// add tile to board, register movement		
			screen.translateMovableBlock((m, n) -> {
				attemptAction(m, n);
				return true;
			});
		}
	}
	
	void addTiles(Block block) {
		for (Tile t: block.getTiles()) {
			Coordinate coords = t.getCoords();
			board[coords.getX()][coords.getY()] = t;
		}
	}
	
	void checkForMatches(Block block) {
		int[] bounds = block.getBounds();
		int matchScore = score;
		for (int row = bounds[0]; row <= bounds[2]; row++)
			if (rowIsFull(row)) {
				applyMatch(row);
				settleRowsAbove(row);
			}
		score = (int) (Math.pow((score - matchScore), 2.0) * 10);
	}

	boolean rowIsFull(int row) {
		for (Tile t: board[row])
			if (t.getValue() == 0)
				return false;
		return true;
	}
	
	void applyMatch(int row) {
		int column = 0;
		for (Tile t: board[row]) {
			score += t.getValue();
			board[row][column++] = generator.emptyTile();
		}
		score += 1;
	}
	
	private void settleRowsAbove(int row) {
		for (int currentRow = row; currentRow > 0; currentRow--) {
			for (int column = 0; column < COLUMNS; column++) {
				board[currentRow][column] = board[currentRow - 1][column];
				removeTile(currentRow - 1, column);
			}
		}
	}
	
	boolean blockCanMove(Block block, int row, int column) {
		if (block == null) return false;
		for (Tile t: block.getTiles()) {
			Coordinate c = t.getCoords();
			Coordinate cPrime = new Coordinate(c.getX() + row, c.getY() + column);
			if (!canDropTile(cPrime.getX(), cPrime.getY())) {
				return false;
			}
		}
		return true;
	}
	
	void makeMove(Tile t, int toRow, int toColumn) {
		Coordinate previousCoords = t.getCoords();
		board[toRow][toColumn] = t;
		board[previousCoords.getX()][previousCoords.getY()] = generator.emptyTile();
		t.setCoords(toRow, toColumn);
	}
	
	Block getActiveBlock() {
		return activeBlock;
	}
	
	int getScore() {
		return score;
	}
	
	void gameover() {
		playing = false;
		screen.onEnd();
	}

	@Override
	public void removeTile(int row, int column) {
		board[row][column] = generator.emptyTile();
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
	
}
