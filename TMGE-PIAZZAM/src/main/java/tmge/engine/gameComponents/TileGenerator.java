package tmge.engine.gameComponents;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

public class TileGenerator {
	private static Random seed = new Random();
	private static int[][][] tileConfigurations;
	private static int[] tileScores;
	static Color[] palette;
	enum Shapes {SQUARE, DIAMOND, CIRCLE, STAR};
	
	private static Tile empty = emptyTile(new Coordinate(0,0));
	
	static double screenWidth, screenHeight;

	static int columns, rows;
	
	public TileGenerator(double defaultWidth, double defaultHeight, int r, int c) {
		screenWidth = defaultWidth;
		screenHeight = defaultHeight;
		columns = c;
		rows = r;
	}
	
	public static void registerTileConfigurations(int[][][] configs, int[] values) {
		tileConfigurations = configs;
		tileScores = values;
	}
	
	public static void registerPalette(Color[] colors) {
		palette = colors;
	}
	
	public static Tile createTile(int index, Coordinate coord) {
		return new Tile(tileScores[index], coord, () -> {
			return createSquare(palette[index]);
		});
	}
	public static Tile createRandomTile(int index, Coordinate coord){
		Node n = randomShape(index);
		return new Tile(tileScores[index],coord, () -> {
			return n;
		});
	}
	public static Node randomShape(int index){
		int randomInt = seed.nextInt(4);
		if(randomInt == 0){
			return createSquare(palette[0]);
		}
		if(randomInt == 1){
			return createCircle(palette[1]);
		}
		if(randomInt == 2){
			return createDiamond(palette[2]);
		}
		if(randomInt == 3){
			return  createTriangle(palette[3]);
		}
		return null;
	}
	
	public static ArrayList<Tile> createTiles(int index, int column) {
		ArrayList<Tile> list = new ArrayList<Tile>();
		for (int i = 0; i < tileConfigurations[index].length; i++) {
			list.add(
				new Tile(tileScores[index],
					new Coordinate(tileConfigurations[index][i][0], tileConfigurations[index][i][1] + column),
					() -> { return createSquare(palette[index]); }
				)
			);
		}
		return list;
	}

	public static Block createBlock(int index, int column) {
		Tile[] tiles = new Tile[tileConfigurations[index].length];
		for (int i = 0; i < tileConfigurations[index].length; i++) {
			tiles[i] = new Tile(
				tileScores[index],
				new Coordinate(tileConfigurations[index][i][0], tileConfigurations[index][i][1] + column),
				() -> { return createSquare(palette[index]); }
			);
		}
		return new Block(tiles);
	}
	
	static Node createSquare(Color c)
	{
		return new Rectangle(getWidth() / getColumns(), getHeight() / getRows(), c);
	}
	
	static Node createDiamond(Color c)
	{
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
		return new Tile(0, coords, () -> {
			return createSquare(Color.valueOf("040d06"));
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
