package jfx.game.Library.Bejeweled;

import java.net.URI;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tmge.engine.Screen;
import tmge.engine.gameComponents.Board;
import tmge.engine.gameComponents.Tile;
import tmge.engine.gameComponents.TileGenerator;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BejeweledScreen implements Screen {
	public final static URI link = Paths.get("src/main/java/jfx/game/resources/fxml/bejeweled-singleplayer.fxml").toUri();
	boolean ready = false;
	Tile empty = TileGenerator.emptyTile();
	final Color[] palette = {Color.AQUA, Color.BLUEVIOLET, Color.CHARTREUSE,
				Color.DARKORANGE, Color.CRIMSON, Color.POWDERBLUE};
	BejeweledBoard board;
	double screenWidth, screenHeight;
	VBox[][] gameBox;
	int mouseClickX;
	int mouseClickY;
	int numOfClicks = 0;
	
	public BejeweledScreen()
	{
		createScreen(720.0 * 0.6, 640.0 - 48);
	}
	
	void createScreen(double screenWidth, double screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;

		board = new BejeweledBoard(this);
		new Thread(board).start();
		gameBox = new VBox[board.getRows()][board.getColumns()];

		new TileGenerator(screenWidth, screenHeight, board.getRows(), board.getColumns());		
		TileGenerator.registerPalette(palette);
		
//		Random seed = new Random();
//		seed.setSeed(LocalTime.now().toNanoOfDay());
		System.out.println("Screen created");
	}
	
	@FXML VBox leftVBox;
	@FXML VBox rightVBox;
	@FXML GridPane gameGrid;
	@FXML Menu fileMenu;
	@FXML Menu helpMenu;
	
	@Override
	@FXML
	public void initialize() {
		new TileGenerator(screenWidth, screenHeight, board.getRows(), board.getColumns());
		TileGenerator.registerPalette(palette);

		// init all vboxes, add them to a tracking data structure and the visual
		for (int row = 0; row < board.getRows(); row++) {
			for (int column = 0; column < board.getColumns(); column++) {
				VBox box = new VBox();
				gameBox[row][column] = box;
				int targetRow = row;
				int targetCol = column;
				gameBox[row][column].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						//Node test = gameBox[row][column]
						numOfClicks += 1;
//						System.out.println(numOfClicks);
						mouseClickX = targetRow;
						mouseClickY = targetCol;
						if(numOfClicks == 2) numOfClicks = 0;
						System.out.println(Integer.toString(targetRow) + " " + Integer.toString(targetCol));
					}
				});
				gameGrid.add(box, column, row);
				setVBox(row, column, TileGenerator.emptyTile());
			}
		}
		
		leftVBox.getChildren().add(new Label("LEFT"));
		rightVBox.getChildren().add(new Label("RIGHT"));

		ready = true;
		System.out.println("Screen initialized");
	}

	public int getNumOfClicks() {
		return numOfClicks;
	}

	public int getMouseClickX() {
		return mouseClickX;
	}

	public int getMouseClickY() {
		return mouseClickY;
	}

	private void setVBox(int row, int column, Tile tile) {
		gameBox[row][column].getChildren().clear();
		gameBox[row][column].getChildren().add(tile.getNode());
	}
	
	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public void draw() {
		Tile[][] gameState = board.getBoard();
		System.out.println(board);
		for (int row = 0; row < board.getRows(); row++) {
			for (int column = 0; column < board.getColumns(); column++) {
				Tile t = gameState[row][column];
				setVBox(row, column, t);
			}
		}
		this.ready = true;
		System.out.println("draw");
	}
	
	@FXML 
	private void minimize() {
		Stage stage = (Stage) leftVBox.getScene().getWindow();
		stage.setIconified(true);

	}
	
	@FXML 
	private void maximize() {
		Stage stage = (Stage) leftVBox.getScene().getWindow();
		stage.setMaximized(true);
	}

	@Override
	@FXML
	public void exit() {
		Stage stage = (Stage) leftVBox.getScene().getWindow();
        stage.close();
	}

	@Override
	public void setReady(boolean ready) {
		this.ready = ready;
	}

	@Override
	public boolean ready() {
		return ready;
	}

}
