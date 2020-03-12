package jfx.game.Library.Tetris;

import java.net.URI;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TetrisBase {
	public static URI link = Paths.get("src/main/java/jfx/game/Library/Tetris/base.fxml").toUri();
	final int COLUMNS = 10, ROWS = 20;
	final static double DEFAULT_WIDTH = 720.0 * 0.6, DEFAULT_HEIGHT = 640.0 - 48;
	final Color[] palette = {Color.AQUA, Color.BLUEVIOLET, Color.CHARTREUSE,
			Color.DARKORANGE, Color.CRIMSON, Color.POWDERBLUE}; 
	final Color empty = Color.valueOf("040d06");
	ArrayList<ArrayList<VBox>> gameBox;
	ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();
	ScheduledFuture<?> future;
	
	@FXML Stage stage;
	@FXML VBox leftVBox;
	@FXML VBox rightVBox;
	@FXML GridPane gameGrid;
	@FXML Menu fileMenu;
	@FXML Menu helpMenu;
	
	@FXML
	public void initialize() {
		Random seed = new Random();
		seed.setSeed(LocalTime.now().toNanoOfDay());
		gameBox = new ArrayList<ArrayList<VBox>>();
		resetBoard();
//		executorTest(new Piece(() -> {
//			return createRectangle(Color.AQUA);
//		}, 0, 0));
		
		
		for (int c = 0; c < COLUMNS; c++)
			executorTest(new Piece(() -> {
				return createRectangle(Color.AQUA);
			}, 0, c));
		
		leftVBox.getChildren().add(new Label("LEFT"));
		rightVBox.getChildren().add(new Label("RIGHT"));
	}
	
	private void executorTest(Piece p) {
		future = es.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				step(p);
			});
		}, 0, 1, TimeUnit.SECONDS);
	}
	
	private void createPiece(Color c) {
		
	}
	
	private boolean pieceCanMoveDown(Piece p) {
		int row = p.getRow();
		// there is not a piece below and it is in range
		System.out.println("size: " + gameBox.get(row + 1).get(p.getColumn()).getChildren().size() +
							"\nrow: " + row);
		return !(gameBox.get(row + 1).get(p.getColumn()).getChildren().size() > 1)
				&& !(row >= ROWS - 1);
	}
	
	synchronized private void step(Piece p) {
		try {
			if (pieceCanMoveDown(p)) {
				int row = p.getRow();
				deleteElementsAt(row, p.getColumn());
				p.setRow(row + 1);
				addElementAt(row + 1, p.getColumn(), p.getNode());
			} else {
				future.cancel(true);
			}
		} catch (Exception e) {
			System.err.println("TetrisBase.step, p.getNode() failed.");
			e.printStackTrace();
		}
	}
	
	private void resetBoard() {
		for (int r = 0; r < ROWS; r++) {
			if (gameBox.size() < ROWS)
				gameBox.add(new ArrayList<VBox>());
			for (int c = 0; c < COLUMNS; c++) {
				VBox rcBox;
				if (gameBox.get(r).size() < COLUMNS) {
					rcBox = new VBox();
					gameBox.get(r).add(rcBox);
					gameGrid.add(rcBox, c, r);
				} else {
					rcBox = gameBox.get(r).get(c);
					rcBox.getChildren().clear();
				}
				rcBox.getChildren().add(new Rectangle(DEFAULT_WIDTH / COLUMNS, DEFAULT_HEIGHT / ROWS, empty));
			}
		}
	}
	
	private Rectangle createRectangle(Color c) {
		return new Rectangle(DEFAULT_WIDTH / COLUMNS, DEFAULT_HEIGHT / ROWS, c);
	}
	
	private void deleteElementsAt(int row, int column) {
		gameBox.get(row).get(column).getChildren().clear();
		gameBox.get(row).get(column).getChildren().add(new Rectangle(DEFAULT_WIDTH / COLUMNS, DEFAULT_HEIGHT / ROWS, empty));
	}
	
	private void deleteRow(int row) {
		for (int c = 0; c < COLUMNS; c++)
			deleteElementsAt(row, c);
	}
	
	private void addElementAt(int row, int column, Node element) {
		gameBox.get(row).get(column).getChildren().clear();
		gameBox.get(row).get(column).getChildren().add(element);
	}
	
	private void sendError(String err) {
		Alert alertMessage = new Alert(Alert.AlertType.ERROR);
        alertMessage.setHeaderText(null);
        alertMessage.setContentText(err);
        alertMessage.showAndWait();
	}
	
	@FXML 
	private void minimize() {
		Screen screen = Screen.getPrimary();
		Stage stage = (Stage) leftVBox.getScene().getWindow();
		stage.setIconified(true);
	}
	
	@FXML 
	private void maximize() {
		Screen screen = Screen.getPrimary();
		Stage stage = (Stage) leftVBox.getScene().getWindow();
		stage.setMaximized(true);
	}
	
	@FXML
	private void close() {
		Stage stage = (Stage) leftVBox.getScene().getWindow();
        stage.close();
	}
	
}
