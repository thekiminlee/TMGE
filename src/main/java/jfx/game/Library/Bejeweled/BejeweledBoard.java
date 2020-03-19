package jfx.game.Library.Bejeweled;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Platform;
import tmge.engine.gameComponents.Board;
import tmge.engine.gameComponents.Coordinate;
import tmge.engine.gameComponents.Tile;
import tmge.engine.gameComponents.TileGame;
import tmge.engine.gameComponents.TileGenerator;

public class BejeweledBoard extends Board {
    private int score;
    private int[][][] configurations = {{{0,0}},{{0,0}},{{0,0}},{{0,0}}};
    private int[] values = {1,2,3,4};
    private static final int ROWS = 8, COLUMNS = 8;
    private ArrayList<Coordinate> listOfCoords;
    private TileGenerator generator;
    private boolean playing;
    private Random seed = new Random(LocalTime.now().toNanoOfDay());

    BejeweledScreen screen;

    public BejeweledBoard(BejeweledScreen screen){
    	super(new TileGame(ROWS, COLUMNS));
		seed = new Random(LocalTime.now().toNanoOfDay());
		this.screen = screen;
		generator = screen.getGenerator();
		generator.setGridDimensions(ROWS, COLUMNS);
		for (int row = 0; row < ROWS; row++)
			for (int col = 0; col < COLUMNS; col++){
				//Coordinate c = new Coordinate(row, col);
				board[row][col] = generator.emptyTile();
				//listOfCoords.add(new Coordinate(row, col));
			}
		playing = true;
		System.out.println("Board created");
    }

    //Matching on board
    public int applyMatch(ArrayList<Coordinate> list) {
        int points = 0;
        for (Coordinate coords : list) {
            Tile tile = board[coords.getX()][coords.getY()];
            points += tile.getValue();
            System.out.println(list);

            // Set empty Tile at the coordinate
            removeTile(coords.getX(), coords.getY());
        }

        return points;
    }

    //GRAVITY OF THE GAME THAT HANDLES DROPPING
    public void applyGravity() {
    	for (int row = this.getRows() - 1; row > 0; row++) {
    		for (int column = this.getColumns() - 1; column >= 0; column++) {
    			if (board[row][column] == null)
    				removeTile(row, column);
    			else if (board[row][column] == generator.emptyTile()) {
    				addTile(row, column, board[row - 1][column]);
    				removeTile(row - 1, column);
    			}
    		}
    	}
    	fillRow(0);
    }
    
    public void fillRow(int row) {
    	for (int column = 0; column < this.getColumns(); column++) {
    		int rand = seed.nextInt(configurations.length);
    		if (!occupied(board[row][column])) {
    			board[0][column] = generator.createCustomTile(rand, rand,
    					values[rand], new Coordinate(row, column));
    		}
    	}
    }
    
    public void fillAll() {
    	for(int row = 0; row < this.getRows(); row++){
			for(int column = 0; column < this.getColumns(); column++) {
				int rand = seed.nextInt(configurations.length);
				if(!occupied(board[row][column])) {
					board[row][column] = generator.createCustomTile(rand, rand,
							values[rand], new Coordinate(row, column));
				}
			}
		}

	}

	public void swap(Tile t1, Tile t2){

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

    	while(playing) {

			System.out.println("UPDATE IN BJ board");
			this.screen.setReady(false);
			Platform.runLater(() -> {
				if(this.screen.ready())
				{

					fillAll();
					this.screen.draw();
					System.out.println(screen.getNumOfClicks());
					//applyMatch(TileGenerator.createTiles(ROWS,COLUMNS));
				}
			});

//			int clicks = screen.getNumOfClicks();
			System.out.println(screen.getNumOfClicks());
//			int firstClickX, firstClickY, secondClickX, secondClickY;
//			if(clicks == 1){
//				firstClickX = screen.getMouseClickX();
//				firstClickY = screen.getMouseClickY();
//				System.out.println(firstClickX);
//				System.out.println(firstClickY);
			while(!this.screen.ready() && playing)
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