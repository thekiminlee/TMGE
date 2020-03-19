package jfx.game.Library.Bejeweled;

import java.net.URI;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import tmge.engine.gameComponents.Board;
import tmge.engine.gameComponents.Screen;
import tmge.engine.gameComponents.Tile;
import tmge.engine.gameComponents.TileGenerator;

public class BejeweledScreen implements Screen {
	public final static URI link = Paths.get("src/main/java/jfx/game/resources/fxml/bejeweled-singleplayer.fxml").toUri();
	boolean ready = false;
	TileGenerator generator;
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
				int targetRow = row;
				int targetCol = column;
				gameBox[row][column].addEventHandler(MouseEvent.MOUSE_CLICKED,
					new EventHandler<MouseEvent>() {
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
					}
				);
				gameGrid.add(box, column, row);
				setVBox(row, column, generator.emptyTile());
			}
		}
		
		leftVBox.getChildren().add(new Label("LEFT"));
		rightVBox.getChildren().add(new Label("RIGHT"));

		ready = true;
		System.out.println("Screen initialized");
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

	@Override
	public void setReady(boolean ready) {
		this.ready = ready;
	}

	@Override
	public boolean ready() {
		return ready;
	}
	
	void createScreen(double screenWidth, double screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
		generator = new TileGenerator(screenWidth, screenHeight, palette);
		board = new BejeweledBoard(this);
		new Thread(board).start();
		gameBox = new VBox[board.getRows()][board.getColumns()];

		generator.addPolygon((color) -> {
			return createDiamond(color);
		});
		generator.addPolygon((color) -> {
			return createCircle(color);
		});
		generator.addPolygon((color) -> {
			return createTriangle(color);
		});
		
		System.out.println("Screen created");
	}
	
	Node createDiamond(Color c) {
		double minValue = Math.min(screenWidth / board.getColumns(),
			screenHeight / board.getRows());
		double maxValue = Math.max(screenWidth / board.getColumns(),
			screenHeight / board.getRows());
		Rectangle node = new Rectangle(minValue, minValue, c);
		node.setRotate(45.0);
		node.setTranslateY((maxValue - minValue)/2);
		return node;
	}
	
	Node createCircle(Color c) {
		double minValue = Math.min(screenWidth / board.getColumns(),
				screenHeight / board.getRows());
		return new Circle(minValue*Math.sqrt(2)/2, c);
	}

	Node createTriangle(Color c) {
		Polygon triangle = new Polygon();
		// upper center, bottom left, bottom right
		double width = screenWidth / board.getColumns();
		double height = screenHeight / board.getRows();
		triangle.getPoints().addAll(new Double[]{
			width/2, 0.0,
			0.0, height, 
			width, height
		});
		triangle.setFill(c);
		return triangle;
	}

	TileGenerator getGenerator() {
		return generator;
	}
	
	private void setVBox(int row, int column, Tile tile) {
		gameBox[row][column].getChildren().clear();
		gameBox[row][column].getChildren().add(tile.getNode());
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

}
