package jfx.game.GameEnv;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfx.game.Library.Tetris.TetrisBase;

/**
 * JavaFX App
 */
public class App extends Application {
	
	void begin(String args[]) {
		launch(args);
	}

    @Override
    public void start(Stage stage) {
    	stage.initStyle(StageStyle.UNDECORATED);
    	FXMLBuilder.setFXML(TetrisBase.link);
    	Scene scene = FXMLBuilder.buildScene(new AnchorPane());
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String args[]) {
    	launch();
    }

}