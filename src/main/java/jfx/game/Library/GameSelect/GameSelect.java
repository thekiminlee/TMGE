package jfx.game.Library.GameSelect;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jfx.game.GameEnv.Screen;
import jfx.game.Library.App;
import jfx.game.Library.Tetris.TetrisScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;

import javafx.scene.image.Image;

public class GameSelect implements Screen {
    Button bejeweledGameStartButton;
    Button tetrisGameStartButton;
    
    Scene scene;
    String relativeDirectory = "src/main/java/jfx/game/Library/GameSelect/";
    
    public Scene start(Stage stage) {
        //Media for background playing video
        BorderPane border = new BorderPane();
		HBox buttonBox = addHBox();
        border.setCenter(buttonBox);
        border.setMaxSize(500,300);
        border.setStyle("-fx-background-color: #FFFFFF;");
		String src = Paths.get(relativeDirectory + "clip1.mp4").toUri().toString();
        Media media = new Media(src);
        MediaPlayer mp = new MediaPlayer(media);
        mp.setAutoPlay(true);
        MediaView mv = new MediaView(mp);

        StackPane layout = new StackPane();

        BorderPane menu = new BorderPane();

        menu.setMaxSize(500,300);
        menu.setStyle("-fx-background-color: #FFFFFF;");

        menu.setBottom(buttonBox);

        Label playerName = new Label("Enter Your Name:");
        menu.setCenter(playerName);
        
        layout.getChildren().add(mv);
        layout.getChildren().add(border);
        layout.getChildren().add(menu);

        scene = new Scene(layout, 1000, 500);
        return scene;
    }
    
    public HBox addHBox(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        //hbox.setStyle("-fx-background-color: #336699;");

        hbox.setAlignment(Pos.CENTER);
        //Bejeweled Button
        bejeweledGameStartButton = new Button();
        bejeweledGameStartButton.setGraphic(loadImage(relativeDirectory + "img1.png"));
        bejeweledGameStartButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			System.out.println("Open Bejeweled here");
    		}
    	});
        
        //Tetris Button
        tetrisGameStartButton = new Button();
        tetrisGameStartButton.setGraphic(loadImage(relativeDirectory + "img2.png"));
        tetrisGameStartButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			// open the new game and close the game select window
    			System.out.println("Open Tetris Here");
    			App.startGame(TetrisScreen.link, new AnchorPane(), true);
    			exit();
    		}
    	});
        
        hbox.getChildren().addAll(bejeweledGameStartButton, tetrisGameStartButton);

        return hbox;
    }

    public ImageView loadImage(String imagePath) {
    	try {
			return new ImageView(new Image(new FileInputStream(imagePath)));
    	} catch(FileNotFoundException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
	@Override
	public void initialize() {}

	@Override
	public void draw() {
		return;
	}

	@Override
	public void exit() {
		((Stage)scene.getWindow()).close();
	}
}
