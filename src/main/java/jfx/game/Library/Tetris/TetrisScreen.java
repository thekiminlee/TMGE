package jfx.game.Library.Tetris;

import java.net.URI;
import java.nio.file.Paths;
import java.util.function.BiFunction;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tmge.engine.gameComponents.Board;
import tmge.engine.gameComponents.Coordinate;
import tmge.engine.gameComponents.Screen;
import tmge.engine.gameComponents.Tile;
import tmge.engine.gameComponents.TileGenerator;

public class TetrisScreen implements Screen {
	public final static URI link = Paths.get("src/main/java/jfx/game/resources/fxml/tetris-singleplayer.fxml").toUri();
	boolean ready = false;
	boolean playerOneFinished = false;
	int currentScore = 0;
	int playerOneScore = 0;
	TileGenerator generator;
	final Color[] palette = {Color.AQUA, Color.BLUEVIOLET, Color.CHARTREUSE,
				Color.DARKORANGE, Color.CRIMSON, Color.POWDERBLUE, Color.LIGHTCORAL}; 
	TetrisBoard board;
	double screenWidth, screenHeight;
	VBox[][] gameBox;
	
	public TetrisScreen() { createScreen(720.0 * 0.6, 640.0 - 48); }
	
	void createScreen(double screenWidth, double screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;

		generator = new TileGenerator(screenWidth, screenHeight, 0, palette);
		board = new TetrisBoard(this);
		new Thread(board).start();
		gameBox = new VBox[board.getRows()][board.getColumns()];
	}
	
	TileGenerator getGenerator() {
		return generator;
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
				setVBox(row, column, generator.emptyTile());
			}
		}

		Label label = new Label("0");
		IntegerProperty scoreProperty = new SimpleIntegerProperty(currentScore);
		scoreProperty.addListener(new ChangeListener<Number>() {
			
			@Override
		    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		        label.setText(Integer.toString(board.getScore()));
		    }
			
		});
		
		leftVBox.getChildren().add(label);
		ready = true;
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
		board.setPlaying(false);
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
		currentScore = board.getScore();
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
	
	@Override
	public Board getBoard() {
		return this.board;
	}
	
	public void setVBox(int row, int column, Tile t) {
		gameBox[row][column].getChildren().clear();
		gameBox[row][column].getChildren().add(t.getNode());
	}
	
	void displayAlertBox() {
		Stage window = new Stage();

		window.initModality((Modality.APPLICATION_MODAL));
		window.setTitle("GAME OVER");
		window.setMinWidth(550);
		window.setMinHeight(550);

		Label label = new Label("GAME OVER");
		label.setStyle("-fx-font-size: 2em;");
		
		Label score = new Label("You scored: " + board.getScore());
		label.setStyle("-fx-font-size: 2em;");

		Button newGameButton = new Button("Click button for 2nd game");
		newGameButton.setStyle("-fx-font-size: 2em;");
		newGameButton.setOnAction(e -> this.makeNewGame(window));

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, score, newGameButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
	void makeNewGame(Stage window) {
		window.close();
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
	
	public void onEnd() {
		if (!playerOneFinished) {
			playerOneFinished = true;
			playerOneScore = board.getScore();
			displayAlertBox();
			board.clearBoard();
			board.setPlaying(true);
		}
		else {
			gameEnd();
		}
	}
	
	public void gameEnd() {
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("GAME OVER");
		window.setMinWidth(550);
		window.setMinHeight(550);

		Label label = new Label("GAME 2 ENDED");
		label.setStyle("-fx-font-size: 2em;");
		Label scores = new Label("First Player: " + playerOneScore + "\nSecond Player: " + board.getScore());

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, scores);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		exit();
	}
}
