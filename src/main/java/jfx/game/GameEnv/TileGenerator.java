package jfx.game.GameEnv;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TileGenerator {
	
	private static int[][][] tileConfigurations;
	static Color[] palette;
	
	private static Tile empty = new Tile(() -> {
		return createNode(Color.valueOf("040d06"));
	});
	static Random seed;
	static double screenWidth, screenHeight;

	static int columns, rows;
	
	public TileGenerator(double defaultWidth, double defaultHeight, int r, int c) {
		screenWidth = defaultWidth;
		screenHeight = defaultHeight;
		columns = c;
		rows = r;
		seed = new Random(LocalTime.now().toNanoOfDay());
	}
	
	public static void registerTileConfigurations(int[][][] configs) {
		tileConfigurations = configs;
	}
	
	public static void registerPalette(Color[] colors) {
		palette = colors;
	}
	
	public static ArrayList<Tile> createTiles() {
		ArrayList<Tile> list = new ArrayList<Tile>();
		int index = seed.nextInt(tileConfigurations.length);
		for (int i = 0; i < tileConfigurations[index].length; i++) {
			list.add(new Tile(() -> {
				return createNode(palette[index]);
			}));
		}
		return list;
	}
	
	static Node createNode(Color c) {
		return new Rectangle(getWidth() / getColumns(), getHeight() / getRows(), c);
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
