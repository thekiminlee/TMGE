package jfx.game.Library.Tetris;

import java.net.URI;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TetrisBase {
	public static URI link = Paths.get("src/main/java/jfx/game/Library/Tetris/base.fxml").toUri();
	final int ROWS = 10, COLUMNS = 20;
	
	@FXML VBox leftVBox;
	@FXML VBox rightVBox;
	@FXML VBox gameVBox;
	@FXML Menu fileMenu;
	@FXML Menu helpMenu;
	
	@FXML
	public void initialize() {
		GridPane gp = new GridPane();
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLUMNS; c++) {
				gp.add(new Label(String.format("(%d, %d)", r, c)), r, c);
			}
		}
		gameVBox.getChildren().add(gp);
		leftVBox.getChildren().add(new Label("LEFT"));
		rightVBox.getChildren().add(new Label("RIGHT"));
	}
	
	private void sendError(String err) {
		Alert alertMessage = new Alert(Alert.AlertType.ERROR);
        alertMessage.setHeaderText(null);
        alertMessage.setContentText(err);
        alertMessage.showAndWait();
	}
	
	@FXML
	public void exit(MouseEvent event) {
		close();
	}
	
	private void close() {
		Stage stage = (Stage) leftVBox.getScene().getWindow();
        stage.close();
	}
	
}
