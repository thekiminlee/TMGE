package Games.Tetris;

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
import tmge.engine.Game;
import tmge.engine.Screen;
import tmge.engine.boardComponents.Board;
import tmge.engine.boardComponents.Coordinate;
import tmge.engine.boardComponents.Tile;
import tmge.engine.boardComponents.TileGenerator;
/*
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


	public TetrisScreen() {
		System.out.println("When does this run?");
		createScreen(720.0 * 0.6, 640.0 - 48); }


	public TetrisScreen(){
		createScreen(720.0 * 0.6, 640.0 - 48);
	}

	void createScreen(double screenWidth, double screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;


	}


	@FXML VBox leftVBox;
	@FXML VBox rightVBox;
	@FXML GridPane gameGrid;
	@FXML Menu fileMenu;
	@FXML Menu helpMenu;

	public void initialize(Game controller) {
		//new TileGenerator(screenWidth, screenHeight, board.getRows(), board.getColumns());
		//TileGenerator.registerPalette(palette);
		
//		Random seed = new Random();
//		seed.setSeed(LocalTime.now().toNanoOfDay());
		
		// init all vboxes, add them to a tracking data structure and the visual

		game = (TetrisGame) controller;
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
*/

public class TetrisScreen implements Screen {
	public final static URI link = Paths.get("src/main/java/jfx/game/resources/fxml/tetris-singleplayer.fxml").toUri();
	boolean ready = false;
	final Color[] palette = {Color.AQUA, Color.BLUEVIOLET, Color.CHARTREUSE,
			Color.DARKORANGE, Color.CRIMSON, Color.POWDERBLUE, Color.LIGHTCORAL};
	double screenWidth, screenHeight;
	VBox[][] gameBox;

	TetrisGame game;

	public TetrisScreen() { createScreen(720.0 * 0.6, 640.0 - 48); }

	void createScreen(double screenWidth, double screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;


		game = new TetrisGame();
		game.setScreen(this);
		game.createGenerator();
		game.setBoard(new TetrisBoard(game));
		game.getGenerator();

		//new Thread(game).start();
		System.out.println(game.getRows());
		System.out.println(game.getColumns());
		//generator = new TileGenerator(screenWidth, screenHeight, 0, palette);
		//board = new TetrisBoard(game.getRows(),game.getColumns());
		//new Thread(board).start();
		gameBox = new VBox[game.getRows()][game.getColumns()];
	}

	/*TileGenerator getGenerator() {
		return generator;
	}*/

	@FXML VBox leftVBox;
	@FXML VBox rightVBox;
	@FXML GridPane gameGrid;
	@FXML Menu fileMenu;
	@FXML Menu helpMenu;

	@Override
	@FXML
	public void initialize() {
		// init all vboxes, add them to a tracking data structure and the visual

		for (int row = 0; row < game.getRows(); row++) {
			for (int column = 0; column < game.getColumns(); column++) {
				VBox box = new VBox();
				gameBox[row][column] = box;
				gameGrid.add(box, column, row);
				setVBox(row, column, game.getGenerator().emptyTile());
			}
		}

		leftVBox.getChildren().add(new Label("LEFT"));
		rightVBox.getChildren().add(new Label("RIGHT"));
		ready = true;
		System.out.println("initialized");
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
		game.setPlaying(false);
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

	@Override
	public void draw() {
		Tile[][] gameState = game.getBoard();
		for (int row = 0; row < game.getRows(); row++) {
			for (int column = 0; column < game.getColumns(); column++) {
				Tile t = gameState[row][column];
				setVBox(row, column, t);
			}
		}
		Block activeBlock = game.getActiveBlock();
		if (activeBlock != null)
			for (Tile t: activeBlock.getTiles()) {
				Coordinate coords = t.getCoords();
				setVBox(coords.getX(), coords.getY(), t);
			}
		this.ready = true;
	}

	@Override
	public Board getBoard() {
		return this.board;
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

	public void onEnd() {
		// TODO: popup, start again?
		exit();
	}

	public static URI getLink() {
		return link;
	}

	public boolean isReady() {
		return ready;
	}

	public Color[] getPalette() {
		return palette;
	}

	public double getScreenWidth() {
		return screenWidth;
	}

	public double getScreenHeight() {
		return screenHeight;
	}

	public VBox[][] getGameBox() {
		return gameBox;
	}

	public TetrisGame getGame() {
		return game;
	}

	public VBox getLeftVBox() {
		return leftVBox;
	}

	public VBox getRightVBox() {
		return rightVBox;
	}

	public GridPane getGameGrid() {
		return gameGrid;
	}

	public Menu getFileMenu() {
		return fileMenu;
	}

	public Menu getHelpMenu() {
		return helpMenu;
	}
}