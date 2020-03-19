package tmge.engine.gameComponents;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

public class TileGenerator {
	
	static Color[] palette;
	enum Shapes {SQUARE, DIAMOND, CIRCLE, STAR};
	
	private static Tile empty = emptyTile(new Coordinate(0,0));
	
	static double screenWidth, screenHeight;

	static int columns, rows;
	
	public TileGenerator(double defaultWidth, double defaultHeight, int r, int c, Color[] colors) {
		screenWidth = defaultWidth;
		screenHeight = defaultHeight;
		columns = c;
		rows = r;
		palette = colors;
	}
	
	public static Tile createTile(int index, int score, Coordinate coord) {
		return new Tile(score, coord, palette[index], (color) -> {
			return createSquare(color);
		});
	}
	
	static Node createSquare(Color c) {
		return new Rectangle(getWidth() / getColumns(), getHeight() / getRows(), c);
	}
	
	static Node createDiamond(Color c) {
		Rectangle node = new Rectangle(getWidth() / getColumns(), getHeight() / getRows(), c);
		node.setRotate(45.0);
		return node;
	}
	
	static Node createCircle(Color c) {
		double minValue = Math.min(getWidth() / getColumns(), getHeight() / getRows());
		return new Circle(minValue, c);
	}

	static Node createTriangle(Color c) {
		Polygon triangle = new Polygon();
		triangle.getPoints().addAll(new Double[]{
			getWidth() / getColumns(), 0.0, 0.0, getHeight() / getRows(), 
				(getWidth() / getColumns())*2, 0.0
		});
		triangle.setFill(c);
		return triangle;
	}
	
	public static Tile emptyTile(Coordinate coords) {
		return new Tile(0, coords, Color.valueOf("040d06"), (color) -> {
			return createSquare(color);
		});
	}
	
	public static Tile emptyTile() {
		return empty;
	}
	
	public static void setWindowDimensions(double width, double height) {
		screenWidth = width;
		screenHeight = height;
	}
	
	public static void setGridDimensions(int rows, int columns) {
		TileGenerator.rows = rows;
		TileGenerator.columns = columns;
	}
	
	private static int getRows() {
		return rows;
	}
	private static int getColumns() {
		return columns;
	}
	private static double getWidth() {
		return screenWidth;
	}
	private static double getHeight() {
		return screenHeight;
	}
	
}
