package Games.Bejeweled;

import java.net.URI;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import tmge.engine.Screen;
import tmge.engine.boardComponents.Board;
import tmge.engine.boardComponents.Coordinate;
import tmge.engine.boardComponents.Tile;
import tmge.engine.boardComponents.TileGenerator;

public class BejeweledScreen implements Screen {
	public final static URI link = Paths.get("src/main/java/jfx/game/resources/fxml/bejeweled-singleplayer.fxml").toUri();
	boolean ready;
	TileGenerator generator;
	final Color[] palette = {Color.AQUA, Color.BLUEVIOLET, Color.CHARTREUSE,
				Color.DARKORANGE, Color.CRIMSON, Color.POWDERBLUE};
	BejeweledBoard board;
	double screenWidth, screenHeight;
	VBox[][] gameBox;
	Coordinate lastClicked;
    private final int PADDING = 3;
    boolean clicked = false;
    int clicks = 0;
	
	public BejeweledScreen()
	{
		ready = false;
		lastClicked = null;
		screenWidth = 720.0 * 0.6;
		screenHeight = 640.0 - 48;
		createScreen();
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
		double width = screenWidth / board.getColumns();
		double height = screenHeight / board.getRows();
		
		for (int row = 0; row < board.getRows(); row++) {
			for (int column = 0; column < board.getColumns(); column++) {
				VBox box = new VBox();
				box.setPrefSize(width, height);
				box.setMinSize(width, height);
				box.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGREY,  
					CornerRadii.EMPTY, new Insets(1))));
				gameBox[row][column] = box;
				int targetRow = row;
				int targetCol = column;
				gameBox[row][column].addEventHandler(MouseEvent.MOUSE_CLICKED,
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent e) {
							Coordinate coords = new Coordinate(targetRow, targetCol);
							if (lastClicked == null) {
								lastClicked = coords;
							} else if (!coords.equals(lastClicked)) {
								board.swap(coords, lastClicked);
								lastClicked = null;
							}
//							System.out.println(Integer.toString(targetRow) + " " + Integer.toString(targetCol));
						}
					}
				);
				gameGrid.add(box, column, row);
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
//		System.out.println(board);
		for (int row = 0; row < board.getRows(); row++) {
			for (int column = 0; column < board.getColumns(); column++) {
				Tile t = gameState[row][column];
				updatePane(row, column, t);
			}
		}
		this.ready = true;
//		System.out.println("draw");
	}

	@Override
	public void setReady(boolean ready) {
		this.ready = ready;
	}

	@Override
	public boolean ready() {
		return ready;
	}
	
	void createScreen() {
		generator = new TileGenerator(screenWidth, screenHeight, 3, palette);
		board = new BejeweledBoard(this);
		//new Thread(board).start();
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
		double width = (screenWidth / board.getColumns()) - 2*PADDING;
		double height = (screenHeight / board.getRows()) - 2*PADDING;
		Polygon node = new Polygon();
		node.getPoints().addAll(new Double[] {
			0.0, height/2,
			width/2, 0.0,
			width, height/2,
			width/2, height
		});
		node.setFill(c);
		node.setTranslateX(PADDING);
		node.setTranslateY(PADDING);
		return node;
	}
	
	Node createCircle(Color c) {
		double width = (screenWidth / board.getColumns()) - 2*PADDING;
		double height = (screenHeight / board.getRows()) - 2*PADDING;
		double minValue = Math.min(width, height);
		Circle node = new Circle(minValue*Math.sqrt(2)/2, c);
		double diameter = node.getRadius()*2;
		node.setScaleX(width / diameter);
		node.setScaleY(height / diameter);
		node.setTranslateX((width - diameter)/2 + PADDING);
		node.setTranslateY(PADDING);
		return node;
	}

	Node createTriangle(Color c) {
		Polygon node = new Polygon();
		// upper center, bottom left, bottom right
		double width = (screenWidth / board.getColumns()) - 2*PADDING;
		double height = (screenHeight / board.getRows()) - 2*PADDING;
		node.getPoints().addAll(new Double[]{
			width/2, 0.0,
			0.0, height, 
			width, height
		});
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

}
