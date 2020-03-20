package jfx.game.Library.Bejeweled;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.*;
import java.lang.Math;


import javafx.application.Platform;
import tmge.engine.gameComponents.Board;
import tmge.engine.gameComponents.Coordinate;
import tmge.engine.gameComponents.Tile;
import tmge.engine.gameComponents.TileGame;
import tmge.engine.gameComponents.TileGenerator;

public class BejeweledBoard extends Board {
	private int score;
	private int[][][] configurations = { { { 0, 0 } }, { { 0, 0 } }, { { 0, 0 } }, { { 0, 0 } } };
	private int[] values = { 1, 2, 3, 4 };
	private static final int ROWS = 8, COLUMNS = 8;
	private ArrayList<Coordinate> listOfCoords;
	private TileGenerator generator;
	private boolean playing;
	private Random seed;
	private Tile t1, t2 = null;
	static final int startTime = 60;
	int timeSeconds = startTime;
	private int[] shapeScore = new int[]{20, 30, 40, 50};
	private Set<Tile> matchSet = new HashSet<Tile>();

	BejeweledScreen screen;

	public BejeweledBoard(BejeweledScreen screen) {
		super(new TileGame(ROWS, COLUMNS));
		seed = new Random(LocalTime.now().toNanoOfDay());
		this.screen = screen;
		generator = screen.getGenerator();
		generator.setGridDimensions(ROWS, COLUMNS);
		for (int row = 0; row < ROWS; row++)
			for (int col = 0; col < COLUMNS; col++) {
				// Coordinate c = new Coordinate(row, col);
				board[row][col] = generator.emptyTile();
				// listOfCoords.add(new Coordinate(row, col));
	}
	playing = true;
		System.out.println("Board created");
}
	// Vertical Matching After Swap:
	public void verticalMatch(Tile originTile1, Tile originTile2, Set matchSet) {
		// Checklist for Origin Tiles:
		List<Tile> checkList = new ArrayList<Tile>();
		checkList.add(originTile1);
		checkList.add(originTile2);

		for (Tile originTile : checkList) {
			Coordinate originTileCoord = originTile.getCoords();
			// Check Upward:
			List<Tile> matchList = new ArrayList<Tile>();
			matchList.add(originTile);
			int originValue = originTile.getValue();
			for ( int i = originTileCoord.getX() - 1; i > -1; i-- ) {
				if (originValue == board[i][originTileCoord.getY()].getValue()){
					matchList.add(board[i][originTileCoord.getY()]);
				} else{ break; }
			}

			// Check Downward:
			for ( int i = originTileCoord.getX() + 1; i < this.getRows(); i++ ){
				if (originValue == board[i][originTileCoord.getY()].getValue()){
					matchList.add(board[i][originTileCoord.getY()]);
				} else { break; }
			}

			// Add Tiles to MatchSet:
			if (matchList.size() >= 3){
				matchSet.addAll(matchList);
			}
		}
//

	}
	// Horizontal Matching After Swap:
	public void horizontalMatch(Tile originTile1, Tile originTile2, Set matchSet) {
		List<Tile> checkList = new ArrayList<Tile>();
		checkList.add(originTile1);
		checkList.add(originTile2);

		for ( Tile originTile : checkList ) {
			Coordinate originTileCoord = originTile.getCoords();
			// Check LEFT:
			List<Tile> matchList = new ArrayList<Tile>();
			matchList.add(originTile);
			int originValue = originTile.getValue();
			for (int i = originTileCoord.getY()-1; i > -1; i--){
				if(originValue == board[originTileCoord.getX()][i].getValue()){
					matchList.add(board[originTileCoord.getX()][i]);
				} else{ break; }
			}
			// Check RIGHT:
			for (int i = originTileCoord.getY()+1; i < this.getColumns(); i++){
				if(originValue == board[originTileCoord.getX()][i].getValue()){
					matchList.add(board[originTileCoord.getX()][i]);
				} else { break; }
			}

			// Add Tiles to MatchSet:
			if (matchList.size() >= 3){
				matchSet.addAll(matchList);
			}

			// Testing:
//			System.out.println("Origin Tile: " + originTile);
//			System.out.println("Match List Identified: " + matchList);
		}
	}

	// Clear Tiles and Apply Gravity
	public void clearAndGravity( Set matchSet ){

		for (Object matchTile : matchSet ) {
			Tile match = Tile.class.cast(matchTile);
			System.out.println(match.getCoords());
			board[match.getCoords().getX()][match.getCoords().getY()] = null;
			//applyGravity();
		}
	}

	// Reset Tiles (No Matches)
	public void resetTiles(Tile originTile1, Tile originTile2){
		System.out.println("No Matches, reverting to original.");
		Tile temp = originTile1;
		setTileAt(originTile1.getCoords(), originTile2);
		setTileAt(originTile2.getCoords(), temp);
	}

	public void fillRow(int row) {
		for (int column = 0; column < this.getColumns(); column++) {
			int rand = seed.nextInt(configurations.length);
			if (!occupied(board[row][column])) {
				board[row][column] = generator.createCustomTile(rand, rand, values[rand], new Coordinate(row, column));
			}
		}
	}

	public void fillAll() {
		for (int row = 0; row < this.getRows(); row++) {
			fillRow(row);
		}
	}

	private boolean isValid(Coordinate coords, Coordinate lastClicked) {
		if ((Math.abs(coords.getX() - lastClicked.getX()) == 1) && (coords.getY() == lastClicked.getY()))
			return true;
		else if ((Math.abs(coords.getY() - lastClicked.getY()) == 1) && (coords.getX() == lastClicked.getX()))
			return true;
		else if ((Math.abs(coords.getX() - lastClicked.getX()) == 1)
				&& (Math.abs(coords.getY() - lastClicked.getY()) == 1))
			return true;
		return false;
	}

	public void swap(Coordinate coords, Coordinate lastClicked) {
		if (isValid(coords, lastClicked)) {
			Tile temp1 = getTileAt(coords), temp2 = getTileAt(lastClicked);
			temp1.setCoords(lastClicked);
			setTileAt(lastClicked, temp1);
			temp2.setCoords(coords);
			setTileAt(coords, temp2);
			//
			verticalMatch(temp1, temp2, matchSet);
			horizontalMatch(temp1, temp2, matchSet);
			if (matchSet.size() >= 3){
				clearAndGravity(matchSet);
				matchSet.clear();
			} else {
				resetTiles(temp1, temp2);
			}
		} else {
			System.out.println("Invalid move");
		}
	}

	public Tile getTileAt(Coordinate coords) {
		return board[coords.getX()][coords.getY()];
	}

	public void setTileAt(Coordinate coords, Tile t) {
		board[coords.getX()][coords.getY()] = t;
	}

	public boolean occupied(Tile t) {
		return (t != null) && (t != generator.emptyTile());
	}

	@Override
	public void addTile(int row, int column, Tile t) {
		if (t == null)
			removeTile(row, column);
		else
			this.board[row][column] = t;
	}

	@Override
	public void removeTile(int row, int column) {
		this.board[row][column] = generator.emptyTile();
	}

	@Override
	public void update() {

		while (playing) {
			// System.out.println("UPDATE IN BJ board");
			this.screen.setReady(false);
			this.timeSeconds -= 1;
			this.screen.updateTimer(this.timeSeconds);
			this.screen.updateScore(this.score);
			if(this.timeSeconds == 0) setPlaying(false);
			Platform.runLater(() -> {
				fillAll();
				this.screen.draw();
			});

			while (!this.screen.ready() && playing)
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public void run() {
		System.out.println("I am in run");
		update();
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

}
