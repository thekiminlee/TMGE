package jfx.game.Library.GameSelect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import jfx.game.Library.App;
import jfx.game.Library.FXMLBuilder;
import jfx.game.Library.Bejeweled.BejeweledScreen;
import jfx.game.Library.Tetris.TetrisScreen;
import tmge.engine.Engine;
import tmge.engine.gameComponents.Screen;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameSelect {
    Button bejeweledGameStartButton;
    Button tetrisGameStartButton;

    Button clickToStart;
    
    Scene scene, scene1;
    String relativeDirectory = "C:\\Users\\malco\\Desktop\\TMGE\\src\\main\\java\\jfx\\game\\resources\\images\\";
    
    public Scene start(Stage stage) {
    	//Title Screen
        StackPane titleScreen = new StackPane();
        titleScreen.setMaxSize(900,480);
        titleScreen.setBackground(
        		new Background(
        				new BackgroundImage(loadImage(relativeDirectory + "TitleScreen.png"),
        					BackgroundRepeat.REPEAT,
			                BackgroundRepeat.REPEAT,
			                BackgroundPosition.DEFAULT,
			                BackgroundSize.DEFAULT)));

        clickToStart = new Button("Let's Play");
        clickToStart.setStyle("-fx-background-color: linear-gradient(#ffd65b, #e68400),linear-gradient(#ffef84, #f2ba44)," +
                "        linear-gradient(#ffea6a, #efaa22)," +
                "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%)," +
                "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));" +
                "    -fx-background-radius: 30;" +
                "    -fx-background-insets: 0,1,2,3,0;" +
                "    -fx-text-fill: #654b00;" +
                "    -fx-font-weight: bold;" +
                "    -fx-font-size: 17px;" +
                "    -fx-padding: 10 20 10 20;");

        //GridPane to center the Button
        GridPane grid = new GridPane();
        grid.setMinSize(900, 480);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.add(clickToStart,37,20);

        //titleScreen.setCenter(grid);
        titleScreen.getChildren().add(grid);

        //Go to choose game screen
        clickToStart.setOnAction(e -> stage.setScene(scene1));
        scene1 = new Scene(addChooseGameScreen(),654,520);

        Scene scene = new Scene(titleScreen, 900,480);
        return scene;
    }
    	
    public BorderPane addChooseGameScreen() {
        BorderPane border = new BorderPane();
		HBox buttonBox = addGames();
		
		border.setBackground(
			new Background(
				new BackgroundImage(loadImage(relativeDirectory + "ChooseGameScreen.png"),
					BackgroundRepeat.REPEAT,
	                BackgroundRepeat.REPEAT,
	                BackgroundPosition.DEFAULT,
	                BackgroundSize.DEFAULT)));

        buttonBox.setAlignment(Pos.CENTER);
        border.setCenter(buttonBox);
        
        return border;
    }
    
    public HBox addGames(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        //hbox.setStyle("-fx-background-color: #336699;");
        
        hbox.setAlignment(Pos.CENTER);
        
        String buttonStyle = "-fx-background-color: transparent;  -fx-text-fill: #e81010; -fx-font-size: 17px; -fx-font-family: 'Comic Sans MS'; -fx-height: 100px; -fx-weight: 100px";
        //Bejeweled Button
        bejeweledGameStartButton = new Button();
        bejeweledGameStartButton.setStyle(buttonStyle);
        bejeweledGameStartButton.setText("Bejeweled");
        bejeweledGameStartButton.setContentDisplay(ContentDisplay.TOP);
        bejeweledGameStartButton.setGraphic(new ImageView(loadImage(relativeDirectory + "bejeweled.png")));
        bejeweledGameStartButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			// open the new game and close the game select window, behind the scenes a BejeweledScreen is created
    			App.startGame(BejeweledScreen.link, new AnchorPane(), true);
    			exit();
    			Screen screen = FXMLBuilder.getController();
    			loadGame(screen);
    		}
    	});
        
        //Tetris Button
        tetrisGameStartButton = new Button();
        tetrisGameStartButton.setStyle(buttonStyle);
        tetrisGameStartButton.setText("Tetris");
        tetrisGameStartButton.setContentDisplay(ContentDisplay.TOP);
        tetrisGameStartButton.setGraphic(new ImageView(loadImage(relativeDirectory + "tetris.png")));
        tetrisGameStartButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			// open the new game and close the game select window, behind the scenes a TetrisScreen is created
    			App.startGame(TetrisScreen.link, new AnchorPane(), true);
    			exit();
    			System.out.println("Getting TetrisScreen");
    			Screen screen = FXMLBuilder.getController();
    			loadGame(screen);
    		}
    	});
        
        hbox.getChildren().addAll(bejeweledGameStartButton, tetrisGameStartButton);

        return hbox;
    }
    
    private void loadGame(Screen screen) {
    	Engine engine = new Engine();	
		Platform.runLater(() -> {
			while (!screen.ready())
    			try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			engine.startFXMLLoop(screen);
		});
    }

    public Image loadImage(String imagePath) {
    	try {
			return new Image(new FileInputStream(imagePath));
    	} catch(FileNotFoundException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
	public void exit() {
		((Stage)scene1.getWindow()).close();
	}
}
