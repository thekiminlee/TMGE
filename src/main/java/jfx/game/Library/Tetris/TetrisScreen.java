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
import jfx.game.GameEnv.Screen;
import jfx.game.GameEnv.Tile;
import jfx.game.GameEnv.TileGenerator;
import jfx.game.Library.App;

public class TetrisScreen implements Screen {
	public final static URI link = Paths.get("src/main/java/jfx/game/Library/Tetris/base.fxml").toUri();

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

		board = new TetrisBoard();
		gameBox = new VBox[board.getRows()][board.getColumns()];
		
		new TileGenerator(screenWidth, screenHeight, board.getRows(), board.getColumns());
		TileGenerator.registerPalette(palette);
		
		Random seed = new Random();
		seed.setSeed(LocalTime.now().toNanoOfDay());
		
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
	}
	
	Rectangle createRectangle(Color color) {
		return new Rectangle(screenWidth / board.getColumns(), screenHeight / board.getRows(), color);
	}
	
	public void setVBox(int row, int column, Tile t) {
		gameBox[row][column].getChildren().clear();
		gameBox[row][column].getChildren().add(t.getNode());
	}

	@Override
	public void draw() {
		Tile[][] gameState = board.getBoard();
		for (int row = 0; row < board.getRows(); row++) {
			for (int column = 0; column < board.getColumns(); column++) {
				setVBox(row, column, gameState[row][column]);
			}
		}
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
	@FXML
	public void exit() {
		Stage stage = (Stage) leftVBox.getScene().getWindow();
        stage.close();
	}

}
