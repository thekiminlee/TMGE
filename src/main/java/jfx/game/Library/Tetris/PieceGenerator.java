package jfx.game.Library.Tetris;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PieceGenerator {
	
	private final static int[][][] configurations = 	{	
			{ // Square
				{0,0}, {0,1},
				{1,0}, {1,1}
			}, 
			{ // Line
				{0,0}, {0,1}, {0,2}, {0,3}
			}, 
			{ // Z
				{0,0},	{0,1},
						{1,1},	{1,2}
			},
			{ // S
						{0,1},	{0,2},
				{1,0},	{1,1}
			},
			{ // T
				{0,0},	{0,1},	{0,2},
						{1,1}
			},
			{ // reverse L
				{0, 0},	{0, 1},	{0, 2},
								{1, 2}
			},
			{ // L
				{0, 0},	{0, 1},	{0, 2},
				{1, 0}
			}
		};

	final static Color[] palette = {Color.AQUA, Color.BLUEVIOLET, Color.CHARTREUSE,
	Color.DARKORANGE, Color.CRIMSON, Color.POWDERBLUE, Color.LIGHTCORAL}; 
	final Color emptyColor = Color.valueOf("040d06");
	static Piece empty;
	
	static Random seed;
	static double screenWidth, screenHeight;

	static int columns, rows;
	
	PieceGenerator(double defaultWidth, double defaultHeight, int r, int c) {
		screenWidth = defaultWidth;
		screenHeight = defaultHeight;
		columns = c;
		rows = r;
		empty = new Piece(0, 0, emptyColor, () -> {
			return createNode(emptyColor);
		});
		seed = new Random(LocalTime.now().toNanoOfDay());
	}
	
	static ArrayList<Piece> createPieces() {
		ArrayList<Piece> list = new ArrayList<Piece>();
		int index = seed.nextInt(configurations.length);
		for (int i = 0; i < configurations[index].length; i++) {
			list.add(new Piece(configurations[index][i][0], configurations[index][i][1], palette[index], () -> {
				return createNode(palette[index]);
			}));
		}
		return list;
	}
	
	static Node createNode(Color c) {
		return new Rectangle(screenWidth / columns, screenHeight / rows, c);
	}
	
	static Node getEmptyNode() {
		return empty.getNode();
	}
	
}
