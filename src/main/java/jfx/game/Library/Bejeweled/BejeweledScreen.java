package jfx.game.Library.Bejeweled;

import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.function.Function;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import jfx.game.Library.Tetris.TetrisBoard;
import jfx.game.Library.Tetris.TetrisGame;
import tmge.engine.gameComponents.Board;
import tmge.engine.gameComponents.Coordinate;
import tmge.engine.gameComponents.Screen;
import tmge.engine.gameComponents.Tile;
import tmge.engine.gameComponents.TileGenerator;

public class BejeweledScreen implements Screen {
	public final static URI link = Paths.get("src/main/java/jfx/game/resources/fxml/bejeweled-singleplayer.fxml")
			.toUri();
	boolean ready;
	TileGenerator generator;
	final Color[] palette = { Color.RED, Color.BLUEVIOLET, Color.CHARTREUSE, Color.DARKORANGE, Color.CRIMSON,
			Color.POWDERBLUE };
	double screenWidth, screenHeight;
	VBox[][] gameBox;
	Coordinate lastClicked;
	private final int PADDING = 3;
	Label timerLabel = new Label("TIMER");
	Label scoreLabel = new Label("SCORE");
	int timer = 60;
	int score = 0;
	Label gameTime = new Label();
	Label gameScore = new Label();

	BejeweledGame game;

	public BejeweledScreen() {
		ready = false;
		lastClicked = null;
		screenWidth = 720.0 * 0.6;
		screenHeight = 640.0 - 48;

		game = new BejeweledGame();
		game.setScreen(this);
		game.setBoard(new BejeweledBoard(game));
		game.createGenerator();

		//generator = new TileGenerator(screenWidth, screenHeight, 3, palette);
		//board = new BejeweledBoard(this);
		//new Thread(board).start();
		gameBox = new VBox[game.getRows()][game.getColumns()];

		game.getGenerator().addPolygon((color) -> {
			return createDiamond(color);
		});
		game.getGenerator().addPolygon((color) -> {
			return createCircle(color);
		});
		game.getGenerator().addPolygon((color) -> {
			return createTriangle(color);
		});

	}

	@FXML
	VBox leftVBox;
	@FXML
	VBox rightVBox;
	@FXML
	GridPane gameGrid;
	@FXML
	Menu fileMenu;
	@FXML
	Menu helpMenu;

	@Override
	@FXML
	public void initialize() {
		// init all vboxes, add them to a tracking data structure and the visual
		double width = screenWidth / game.getColumns();
		double height = screenHeight / game.getRows();

		for (int row = 0; row < game.getRows(); row++) {
			for (int column = 0; column < game.getColumns(); column++) {
				VBox box = new VBox();
				box.setPrefSize(width, height);
				box.setMinSize(width, height);
				box.setBackground(
						new Background(new BackgroundFill(Color.DARKSLATEGREY, CornerRadii.EMPTY, new Insets(1))));
				gameBox[row][column] = box;
				int targetRow = row;
				int targetCol = column;
				gameBox[row][column].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						Coordinate coords = new Coordinate(targetRow, targetCol);
						if (lastClicked == null) {
							lastClicked = coords;
						} else if (!coords.equals(lastClicked)) {
							game.swap(coords, lastClicked);
							lastClicked = null;
						}
						// System.out.println(Integer.toString(targetRow) + " " +
						// Integer.toString(targetCol));
					}
				});
				gameGrid.add(box, column, row);
			}
		}

		updateGameTimeAndScore();
		changeLabelSetting(timerLabel);
		changeLabelSetting(scoreLabel);
		changeLabelSetting(gameScore);
		changeLabelSetting(gameTime);
		gameScore.setTextFill(Color.RED);
		gameTime.setTextFill(Color.RED);
		leftVBox.setAlignment(Pos.CENTER);
		leftVBox.getChildren().addAll(timerLabel, gameTime);
		rightVBox.setAlignment(Pos.CENTER);
		rightVBox.getChildren().addAll(scoreLabel, gameScore);

		ready = true;
		System.out.println("Screen initialized");
		new Thread(game).start();
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
	public Board getBoard() {
		return board;
	}

	@Override
	public void draw() {
		Tile[][] gameState = game.getBoard();

		for (int row = 0; row < game.getRows(); row++) {
			for (int column = 0; column < game.getColumns(); column++) {
				Tile t = gameState[row][column];
				updatePane(row, column, t);
			}
		}
		this.ready = true;
		updateGameTimeAndScore();
	}

	@Override
	public void setReady(boolean ready) {
		this.ready = ready;
	}

	@Override
	public boolean ready() {
		return ready;
	}



	void changeLabelSetting(Label l) {
		l.setStyle("-fx-font-size: 2em;");
	}

	Node createDiamond(Color c) {
		double width = (screenWidth / game.getColumns()) - 2 * PADDING;
		double height = (screenHeight / game.getRows()) - 2 * PADDING;
		Polygon node = new Polygon();
		node.getPoints().addAll(new Double[] { 0.0, height / 2, width / 2, 0.0, width, height / 2, width / 2, height });
		node.setFill(c);
		node.setTranslateX(PADDING);
		node.setTranslateY(PADDING);
		return node;
	}

	Node createCircle(Color c) {
		double width = (screenWidth / game.getColumns()) - 2 * PADDING;
		double height = (screenHeight / game.getRows()) - 2 * PADDING;
		double minValue = Math.min(width, height);
		Circle node = new Circle(minValue * Math.sqrt(2) / 2, c);
		double diameter = node.getRadius() * 2;
		node.setScaleX(width / diameter);
		node.setScaleY(height / diameter);
		node.setTranslateX((width - diameter) / 2 + PADDING);
		node.setTranslateY(PADDING);
		return node;
	}

	Node createTriangle(Color c) {
		Polygon node = new Polygon();
		// upper center, bottom left, bottom right
		double width = (screenWidth / game.getColumns()) - 2 * PADDING;
		double height = (screenHeight / game.getRows()) - 2 * PADDING;
		node.getPoints().addAll(new Double[] { width / 2, 0.0, 0.0, height, width, height });
		node.setFill(c);
		node.setTranslateX(PADDING);
		node.setTranslateY(PADDING);
		return node;
	}

	TileGenerator getGenerator() {
		return generator;
	}

	private void updatePane(int row, int column, Tile tile) {
		gameBox[row][column].getChildren().clear();
		gameBox[row][column].getChildren().add(tile.getNode());
	}

	void updateTimer(int time) {
		this.timer = time;
	}

	void updateScore(int score) {
		this.score = score;
	}

	void updateGameTimeAndScore() {
		gameTime.setText(Integer.toString(this.timer));
		gameScore.setText(Integer.toString(this.score));
	}

	void displayAlertBox() {
		Stage window = new Stage();

		window.initModality((Modality.APPLICATION_MODAL));
		window.setTitle("GAME OVER");
		window.setMinWidth(550);
		window.setMinHeight(550);

		Label label = new Label("TIME HAS RUN OUT. GAME IS OVER");
		label.setStyle("-fx-font-size: 2em;");

		Button newGameButton = new Button("Click button for 2nd game");
		newGameButton.setStyle("-fx-font-size: 2em;");
		newGameButton.setOnAction(e -> this.makeNewGame(window));

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, newGameButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}

	void makeNewGame(Stage window) {
		window.close();
	}

	public void gameEnd(int scoreFirst, int scoreSecond) {
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("GAME OVER");
		window.setMinWidth(550);
		window.setMinHeight(550);

		Label label = new Label("GAME 2 ENDED");
		label.setStyle("-fx-font-size: 2em;");
		Label scores = new Label("First Player: " + scoreFirst + "\nSecond Player: " + scoreSecond);

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, scores);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
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

	public Coordinate getLastClicked() {
		return lastClicked;
	}

	public int getPADDING() {
		return PADDING;
	}

	public Label getTimerLabel() {
		return timerLabel;
	}

	public Label getScoreLabel() {
		return scoreLabel;
	}

	public int getTimer() {
		return timer;
	}

	public int getScore() {
		return score;
	}

	public Label getGameTime() {
		return gameTime;
	}

	public Label getGameScore() {
		return gameScore;
	}

	public BejeweledGame getGame() {
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