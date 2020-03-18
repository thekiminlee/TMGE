package jfx.game.Library.Tetris;

import java.net.URI;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Random;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import jfx.game.Library.App;
import tmge.engine.Screen;
import tmge.engine.gameComponents.Board;
import tmge.engine.gameComponents.Tile;
import tmge.engine.gameComponents.TileGenerator;

public class TetrisScreen implements Screen {
	public final static URI link = Paths.get("src/main/java/jfx/game/resources/fxml/tetris-singleplayer.fxml").toUri();
	boolean ready = false;
	Tile empty = TileGenerator.emptyTile();
	final Color[] palette = {Color.AQUA, Color.BLUEVIOLET, Color.CHARTREUSE,
				Color.DARKORANGE, Color.CRIMSON, Color.POWDERBLUE, Color.LIGHTCORAL}; 
	TetrisBoard board;
	double screenWidth, screenHeight;
	VBox[][] gameBox;
	
	public TetrisScreen() { createScreen(720.0 * 0.6, 640.0 - 48); }
	
	void createScreen(double screenWidth, double screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;

		board = new TetrisBoard();
		gameBox = new VBox[board.getRows()][board.getColumns()];
		
		new TileGenerator(screenWidth, screenHeight, board.getRows(), board.getColumns());
		TileGenerator.registerPalette(palette);
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
		
//		Random seed = new Random();
//		seed.setSeed(LocalTime.now().toNanoOfDay());
		
		// init all vboxes, add them to a tracking data structure and the visual
		for (int row = 0; row < board.getRows(); row++) {
			for (int column = 0; column < board.getColumns(); column++) {
				VBox box = new VBox();
				gameBox[row][column] = box;
				gameGrid.add(box, column, row);
				setVBox(row, column, TileGenerator.emptyTile());
			}
		}
		
		leftVBox.getChildren().add(new Label("LEFT"));
		rightVBox.getChildren().add(new Label("RIGHT"));
		ready = true;
		System.out.println("initialized");
	}
	
	public void setVBox(int row, int column, Tile t) {
		gameBox[row][column].getChildren().clear();
		gameBox[row][column].getChildren().add(t.getNode());
	}
	
	// TODO: time permitting, make everything resizable
	private void resizeElements() {
		Stage stage = (Stage) leftVBox.getScene().getWindow();
		
		screenWidth = stage.getWidth();
		screenHeight = stage.getHeight();
		TileGenerator.setWindowDimensions(screenWidth, screenHeight);
		
//		leftVBox.layoutXProperty().addListener(new ChangeListener<Number>() {
//			@Override
//			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//				// TODO Auto-generated method stub	
//			}
//		});
//		rightVBox;
//		gameGrid;
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
	public Board getBoard() {
		return this.board;
	}

	@Override
	public void draw() {
		Tile[][] gameState = board.getBoard();
		System.out.println(board);
		for (int row = 0; row < board.getRows(); row++) {
			for (int column = 0; column < board.getColumns(); column++) {
				setVBox(row, column, gameState[row][column]);
			}
		}
		this.ready = true;
		System.out.println("draw");
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
