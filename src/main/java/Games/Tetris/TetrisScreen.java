package Games.Tetris;

import java.net.URI;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tmge.engine.Game;
import tmge.engine.Screen;
import tmge.engine.boardComponents.Board;
import tmge.engine.boardComponents.Tile;
import tmge.engine.boardComponents.TileGenerator;

public class TetrisScreen implements Screen {
	public final static URI link = Paths.get("src/main/java/jfx/game/resources/fxml/tetris-singleplayer.fxml").toUri();
	boolean ready = false;
	//Tile empty = TileGenerator.emptyTile();
	final Color[] palette = {Color.AQUA, Color.BLUEVIOLET, Color.CHARTREUSE,
				Color.DARKORANGE, Color.CRIMSON, Color.POWDERBLUE, Color.LIGHTCORAL}; 
	//TetrisBoard board;
	double screenWidth, screenHeight;
	VBox[][] gameBox;

	TetrisGame game;

	/*
	public TetrisScreen() {
		System.out.println("When does this run?");
		createScreen(720.0 * 0.6, 640.0 - 48); }
	*/

	public TetrisScreen(){
		createScreen(720.0 * 0.6, 640.0 - 48);
	}

	void createScreen(double screenWidth, double screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	/*
	@FXML VBox leftVBox;
	@FXML VBox rightVBox;
	@FXML GridPane gameGrid;
	@FXML Menu fileMenu;
	@FXML Menu helpMenu;*/

	public void initialize(Game controller) {
		//new TileGenerator(screenWidth, screenHeight, board.getRows(), board.getColumns());
		//TileGenerator.registerPalette(palette);
		
//		Random seed = new Random();
//		seed.setSeed(LocalTime.now().toNanoOfDay());
		
		// init all vboxes, add them to a tracking data structure and the visual

		game = (TetrisGame)controller;
		gameBox = new VBox[game.getRows()][game.getColumns()];

		for (int row = 0; row < game.getRows(); row++) {
			for (int column = 0; column < game.getColumns(); column++) {
				VBox box = new VBox();
				gameBox[row][column] = box;
				game.getGameGrid().add(box, column, row);
				setVBox(row, column, TileGenerator.emptyTile());
			}
		}
		game.getLeftVBox().getChildren().add(new Label("LEFT"));
		game.getRightVBox().getChildren().add(new Label("RIGHT"));
		ready = true;
		System.out.println("initialized");
	}
	
	public void setVBox(int row, int column, Tile t) {
		gameBox[row][column].getChildren().clear();
		gameBox[row][column].getChildren().add(t.getNode());
	}
	
	// TODO: time permitting, make everything resizable
//	private void resizeElements() {
//		Stage stage = (Stage) leftVBox.getScene().getWindow();
//		
//		screenWidth = stage.getWidth();
//		screenHeight = stage.getHeight();
//		TileGenerator.setWindowDimensions(screenWidth, screenHeight);
//		
//		leftVBox.layoutXProperty().addListener(new ChangeListener<Number>() {
//			@Override
//			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//				// TODO Auto-generated method stub	
//			}
//		});
//		rightVBox;
//		gameGrid;
//	}

	private void minimize() {
		Stage stage = (Stage) game.getLeftVBox().getScene().getWindow();
		stage.setIconified(true);
	}
	

	private void maximize() {
		Stage stage = (Stage) game.getLeftVBox().getScene().getWindow();
		stage.setMaximized(true);
	}

	@Override
	public Board getBoard() {
		return this.board;
	}

	@Override
	public void draw() {
		Tile[][] gameState = game.getBoard();
		System.out.println(game.getBoard());
		for (int row = 0; row < game.getRows(); row++) {
			for (int column = 0; column < game.getColumns(); column++) {
				Tile t = gameState[row][column];
				setVBox(row, column, t);
			}
		}
		this.ready = true;
		System.out.println("draw");
	}
	
	@Override
	@FXML
	public void exit() {
		Stage stage = (Stage) game.getLeftVBox().getScene().getWindow();
        stage.close();
        System.exit(0);
	}
	
	@Override
	public void setReady(boolean ready) {
		this.ready = ready;
	}

	@Override
	public boolean ready() {
		return ready;
	}

	public double getScreenWidth() {
		return screenWidth;
	}

	public double getScreenHeight() {
		return screenHeight;
	}

	public Color[] getPalette() {
		return palette;
	}

	public void test(){
		System.out.print("test");
	}
}
