package jfx.game.UI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import Games.Tetris.TetrisBoard;
import Games.Tetris.TetrisGame;
import Games.Tetris.TetrisScreen;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import tmge.engine.Game;
import tmge.engine.Screen;

public class FXMLBuilder {
	private static URL fxmlString = null;
	private static Game controller = null;
	
	public static void setFXML(URI link) {
		try {
			fxmlString = link.toURL();
		} catch (MalformedURLException e) {
			System.out.println(link);
			e.printStackTrace();
		}
	}
	
	public static Game getController() {
		return controller;
	}
	
	/*	buildScene
	 * 	Loads an fxml file and attaches a pane that is filled in based on the FXML file
	 * 	Verifies the FXML file recognizes the appropriate controller (loader.getController
	 * 		errors out when the fxml file does not match the controller class).
	 *  Useful when javafx stages need to be customized (i.e. new window)
	 */
	public static <T> Scene buildScene(Parent pane) {
		try {
			FXMLLoader loader =  new FXMLLoader();
			loader.setLocation(fxmlString);
			System.out.println(loader.getLocation());
			pane = (Parent) loader.load();
			controller = loader.getController();
			controller.setCurrentBoard(new TetrisBoard());
			controller.setCurrentScene(new TetrisScreen());
			return new Scene(pane);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static <T> Stage buildStage(Scene scene, boolean isModal) {
		Stage stage;
		if (isModal) {
			stage = new Stage(StageStyle.UNDECORATED);
			stage.initModality(Modality.APPLICATION_MODAL);
		} else {
			stage = new Stage();
		}
		return stage;
	}
	
	public <T> void build(Scene scene, boolean isModal) {
		Stage stage = buildStage(scene, isModal);
		
		stage.setScene(scene);
		stage.show();
	}
	
	public static <T> void build(Parent pane, boolean isModal) {
		Scene scene = buildScene(pane);
		Stage stage = buildStage(scene, isModal);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				System.out.println("Stage is closing");
				System.exit(0);
			}
		});
		stage.setScene(scene);
		stage.show();
	}

}