package jfx.game.Library;

import java.net.URI;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Games.GameSelect.GameSelect;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
    	GameSelect firstScreen = new GameSelect();
    	Scene scene = firstScreen.start(stage);

        stage.setTitle("TMGE Main Menu");
        stage.setScene(scene);
        stage.show();
    }

	public static void startGame(URI link, Parent pane, boolean undecorated) {
		FXMLBuilder.setFXML(link);
    	FXMLBuilder.build(pane, undecorated);
	}
	
    public static void main(String args[]) {
    	launch();
    }


}