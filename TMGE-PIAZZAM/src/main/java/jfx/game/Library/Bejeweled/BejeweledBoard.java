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
    private int[][][] configurations = {{{0,0}},{{0,0}},{{0,0}},{{0,0}},{{0,0}},{{0,0}}};
    private int[] values = {1,2,3,4,5,6};
    private static final int ROWS = 20, COLUMNS = 10;

    private Random seed = new Random(LocalTime.now().toNanoOfDay());

    BejeweledScreen screen;

    
    public BejeweledBoard(BejeweledScreen screen){
    	super(new TileGame(ROWS, COLUMNS));
		seed = new Random(LocalTime.now().toNanoOfDay());
    	TileGenerator.registerTileConfigurations(configurations, values);
		this.screen = screen;
		for (int row = 0; row < ROWS; row++)
			for (int col = 0; col < COLUMNS; col++)
				board[row][col] = TileGenerator.emptyTile();
		System.out.println("Board created");
    }

    //Matching on board
    public int applyMatch(ArrayList<Coordinate> list) {
        int points = 0;
        for (Coordinate coords : list) {
            Tile tile = board[coords.getX()][coords.getY()];
            points += tile.getValue();

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
    			else if (board[row][column] == TileGenerator.emptyTile(new Coordinate(row, column))) {
    				addTile(row, column, board[row - 1][column]);
    				removeTile(row - 1, column);
    			}
    		}
    	}
    	fillTopRow();
    }
    
    public void fillTopRow() {
    	for (int column = 0; column < this.getColumns(); column++)
    		if (!occupied(board[0][column]))
    			board[0][column] = TileGenerator.createTile(seed.nextInt(configurations.length), new Coordinate(0, column));
    }
    public void fillAll(){
    	for(int row = 0; row < this.getRows(); row++){
			for(int column = 0; column < this.getColumns(); column++)			{
				if(!occupied(board[row][column]))				{
					board[row][column] = TileGenerator.createTile(seed.nextInt(configurations.length), new Coordinate(0, column));
				}
			}
		}

	}

    public boolean occupied(Tile t) {
    	return (t != null) && (t != TileGenerator.emptyTile());
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
		this.board[row][column] = TileGenerator.emptyTile();
		
	}

	@Override
	public void update() {
		// TODO Main loop for game
		//while(true)
		//{
			System.out.println("UPDATE IN BJ board");

			Platform.runLater(() -> {
				if(this.screen.ready())
				{

					fillAll();
					this.screen.draw();
				}
			});
		//}

	}

	@Override
	public void run() {
    	System.out.println("I am in run");
		update();
	}
}
