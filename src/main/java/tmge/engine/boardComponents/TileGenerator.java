package tmge.engine.boardComponents;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TileGenerator {

	static Color[] palette = null;
	enum Shapes {SQUARE, DIAMOND, CIRCLE, STAR};

	final Color emptyColor = Color.valueOf("040d06");
	private Tile empty;

	double screenWidth, screenHeight;
	int columns, rows;

	ArrayList<Function<Color, Node>> shapesAvailable;

	public TileGenerator(double defaultWidth, double defaultHeight, int padding, Color[] colors) {
		screenWidth = defaultWidth;
		screenHeight = defaultHeight;
		palette = colors;
		shapesAvailable = new ArrayList<Function<Color, Node>>();
		shapesAvailable.add((color) -> {
			return createSquare(color, padding);
		});
		empty = new Tile(0, new Coordinate(0,0), emptyColor, (color) -> {
			return createSquare(color, padding);
		});;
	}

	public Tile createTile(int index, int score, Coordinate coord) {
		return createCustomTile(index, 0, score, coord);
	}

	public void addPolygon(Function<Color, Node> function) {
		shapesAvailable.add(function);
	}

	public void addAllPolygons(Collection<Function<Color, Node>> functionCollection) {
		shapesAvailable.addAll(functionCollection);
	}

	public Tile createCustomTile(int colorIndex, int shapeIndex, int score, Coordinate coord) {
		Color tileColor = palette[colorIndex];
		System.out.println("Color: " + Integer.valueOf(colorIndex) + ", Shape: " + Integer.valueOf(shapeIndex));
		Tile t = new Tile(score, coord, tileColor, shapesAvailable.get(shapeIndex));
//		System.out.println(t.getColor());
//		if (palette != null && colorIndex < palette.length)
//			tileColor = palette[colorIndex];
//		else
//			tileColor = emptyColor;
		return t;
	}

	Node createSquare(Color c, int padding) {
		Rectangle rect = new Rectangle((getWidth() / getColumns()) - 2*padding,
										(getHeight() / getRows()) - 2*padding, c);
		rect.setTranslateX(padding);
		rect.setTranslateY(padding);
		return rect;
	}

	public Tile emptyTile() {
		return empty;
	}

	public void setWindowDimensions(double width, double height) {
		screenWidth = width;
		screenHeight = height;
	}

	public void setGridDimensions(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
	}

	private int getRows() {
		return rows;
	}
	private int getColumns() {
		return columns;
	}
	private double getWidth() {
		return screenWidth;
	}
	private double getHeight() {
		return screenHeight;
	}

}
