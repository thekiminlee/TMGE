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
import jfx.game.Library.Tetris.TetrisBoard;
import tmge.engine.Screen;
import tmge.engine.gameComponents.Tile;
import tmge.engine.gameComponents.TileGenerator;

public class BejeweledScreen implements Screen {
	public final static URI link = Paths.get("src/main/java/jfx/game/resources/fxml/base.fxml").toUri();

	Tile empty = TileGenerator.emptyTile();
	final Color[] palette = {Color.AQUA, Color.BLUEVIOLET, Color.CHARTREUSE,
				Color.DARKORANGE, Color.CRIMSON, Color.POWDERBLUE};
	BejeweledBoard board;
	double screenWidth, screenHeight;
	VBox[][] gameBox;
	
	public BejeweledScreen() { createScreen(720.0 * 0.6, 640.0 - 48); }
	
	void createScreen(double screenWidth, double screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;

		board = new BejeweledBoard();
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

	private void setVBox(int row, int column, Tile tile) {
		gameBox[row][column].getChildren().add(tile.getNode());
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@FXML
	public void exit() {
		Stage stage = (Stage) leftVBox.getScene().getWindow();
        stage.close();
	}

}
