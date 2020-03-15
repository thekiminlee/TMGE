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
	
	private boolean playing = true;
	
	ArrayList<ArrayList<VBox>> gameBox;
	ArrayList<ArrayList<Piece>> pieces;
	
	ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();
	
	@FXML Stage stage;
	@FXML VBox leftVBox;
	@FXML VBox rightVBox;
	@FXML GridPane gameGrid;
	@FXML Menu fileMenu;
	@FXML Menu helpMenu;
	
	@FXML
	public void initialize() {
		//screenWidth, screenHeight, columns, rows
		PieceGenerator pg = new PieceGenerator(DEFAULT_WIDTH, DEFAULT_HEIGHT, ROWS, COLUMNS);
		Random seed = new Random();
		seed.setSeed(LocalTime.now().toNanoOfDay());
		gameBox = new ArrayList<ArrayList<VBox>>();
		resetBoard();		
		
		es.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				for (Piece p: PieceGenerator.createPieces()) {
					addPiece(p);
					executorTest(p);
				}
			});
		}, 0, 500, TimeUnit.MILLISECONDS);
		
		leftVBox.getChildren().add(new Label("LEFT"));
		rightVBox.getChildren().add(new Label("RIGHT"));
	}
	
	private void gameover() {
		
	}
	
	synchronized private void executorTest(Piece p) {
		p.setFuture(es.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				step(p);
			});
		}, 0, 1, TimeUnit.SECONDS));
	}
	
	synchronized private boolean pieceCanMoveDown(Piece p) {
		int row = p.getRow();
		// piece is in range and piece below is "empty"
		return playing && row + 1 < ROWS && occupied(row + 1, p.getColumn());
	}
	
	synchronized private boolean occupied(int row, int column) {
		return ((Rectangle)gameBox.get(row).get(column).getChildren().get(0)).getFill().equals(PieceGenerator.empty.color);
	}
	
	synchronized private void step(Piece p) {
		if (pieceCanMoveDown(p)) {
			deleteElementsAt(p.getRow(), p.getColumn());
			p.moveDown();
			addElementAt(p.getRow(), p.getColumn(), p.getNode());
			p.stop();
		} else {
			p.stop();
//		} else { // piece is at the top row
//			p.stop();
//			playing = false;
//			System.out.println("game has ended.");
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
				rcBox.getChildren().add(PieceGenerator.getEmptyNode());
			}
		}
	}
	
	private Rectangle createRectangle(Color c) {
		return new Rectangle(DEFAULT_WIDTH / COLUMNS, DEFAULT_HEIGHT / ROWS, c);
	}
	
	private void deleteElementsAt(int row, int column) {
		gameBox.get(row).get(column).getChildren().clear();
		gameBox.get(row).get(column).getChildren().add(PieceGenerator.getEmptyNode());
	}
	
	private void deleteRow(int row) {
		for (int c = 0; c < COLUMNS; c++)
			deleteElementsAt(row, c);
	}
	
	private void addElementAt(int row, int column, Node element) {
		gameBox.get(row).get(column).getChildren().clear();
		gameBox.get(row).get(column).getChildren().add(element);
	}
	
	synchronized private void addPiece(Piece p) {
		int row = p.getRow(), column = p.getColumn();
		gameBox.get(row).get(column).getChildren().clear();
		gameBox.get(row).get(column).getChildren().add(p.getNode());
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
