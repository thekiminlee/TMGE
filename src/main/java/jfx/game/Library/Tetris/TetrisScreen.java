package jfx.game.Library.Tetris;

import java.net.URI;
import java.nio.file.Paths;
import java.util.function.BiFunction;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tmge.engine.gameComponents.Board;
import tmge.engine.gameComponents.Coordinate;
import tmge.engine.gameComponents.Screen;
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

		board = new TetrisBoard(this);
		new Thread(board).start();
		gameBox = new VBox[board.getRows()][board.getColumns()];
		
		new TileGenerator(screenWidth, screenHeight, board.getRows(), board.getColumns(), palette);
	}
	
	@FXML VBox leftVBox;
	@FXML VBox rightVBox;
	@FXML GridPane gameGrid;
	@FXML Menu fileMenu;
	@FXML Menu helpMenu;
	
	@Override
	@FXML
	public void initialize() {
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
	
	public void translateMovableBlock(BiFunction<TetrisBoard.Moves, Integer, Boolean> function) {
		(leftVBox.getScene()).setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            	switch (event.getCode()) {
            	case SPACE:
            		function.apply(TetrisBoard.Moves.TRANSLATE_VERTICAL, board.getRows());
            		break;
            	case S:
					function.apply(TetrisBoard.Moves.TRANSLATE_VERTICAL, 1);
					break;
            	case A:
            		function.apply(TetrisBoard.Moves.TRANSLATE_HORIZONTAL, -1);
            		break;
            	case D:
            		function.apply(TetrisBoard.Moves.TRANSLATE_HORIZONTAL, 1);
            		break;
            	case W:
            		function.apply(TetrisBoard.Moves.ROTATE_CLOCKWISE, 1);
            	default:
            		break;
            	}
            	draw();
            }
        });
	}

	@Override
	public void draw() {
		Tile[][] gameState = board.getBoard();
		for (int row = 0; row < board.getRows(); row++) {
			for (int column = 0; column < board.getColumns(); column++) {
				Tile t = gameState[row][column];
				setVBox(row, column, t);
			}
		}
		Block activeBlock = board.getActiveBlock();
		if (activeBlock != null)
			for (Tile t: activeBlock.getTiles()) {
				Coordinate coords = t.getCoords();
				setVBox(coords.getX(), coords.getY(), t);
			}
		this.ready = true;
	}
	
	public void onEnd() {
		// TODO: popup, start again?
		exit();
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
