package jfx.game.GameEnv;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
//import javafx.media;

import java.nio.file.Paths;

import javafx.scene.image.Image;
/**
 * JavaFX App
 */
public class App extends Application {

    Button bejeweledGameStartButton;
    Button tetrisGameStartButton;
    Button clickToStart;

    Scene scene1,scene2;

    void begin(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        //Title Screen
        StackPane titleScreen = new StackPane();
        titleScreen.setMaxSize(900,480);
        Image titleScreenBackgroundImg = new Image(getClass().getResourceAsStream("/TitleScreen.png"));
        titleScreen.setBackground(new Background(new BackgroundImage(titleScreenBackgroundImg, BackgroundRepeat.REPEAT,
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
        stage.setTitle("TMGE Main Menu");
        stage.setScene(scene);
        stage.show();

    }
    public BorderPane addChooseGameScreen()
    {
        BorderPane chooseGame = new BorderPane();
        HBox buttonBox = addHBox();
        Image chooseScreenImg = new Image(getClass().getResourceAsStream("/ChooseGameScreen.png"));
        chooseGame.setBackground(new Background(new BackgroundImage(chooseScreenImg, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        buttonBox.setAlignment(Pos.CENTER);
        chooseGame.setCenter(buttonBox);


        return chooseGame;
    }
    public HBox addHBox(){
        HBox hbox = new HBox();
        //hbox.setPadding(new Insets(15, 12, 15, 12));
       // hbox.setSpacing(10);
        //hbox.setStyle("-fx-background-color: #336699;");

        hbox.setAlignment(Pos.CENTER);
        //Bejeweled Button
        Image bejeweledImg = new Image(getClass().getResourceAsStream("/img1.png"));
        bejeweledGameStartButton = new Button();
        bejeweledGameStartButton.setStyle("-fx-background-color: transparent;  -fx-text-fill: #d926ed; -fx-font-size: 17px; -fx-font-family: 'Comic Sans MS'; -fx-height: 100px; -fx-weight: 100px");
        bejeweledGameStartButton.setText("Bejeweled");
        bejeweledGameStartButton.setContentDisplay(ContentDisplay.TOP);
        bejeweledGameStartButton.setGraphic(new ImageView(bejeweledImg));

        //***********ADD ACTION LISTNER FOR BUTTON
        //AKA START BEJEWELED

        //Tetris Button
        Image tetrisImg = new Image(getClass().getResourceAsStream("/img2.png"));
        tetrisGameStartButton = new Button();
        tetrisGameStartButton.setStyle("-fx-background-color: transparent;  -fx-text-fill: #e81010; -fx-font-size: 17px; -fx-font-family: 'Comic Sans MS'; -fx-height: 100px; -fx-weight: 100px");
        tetrisGameStartButton.setText("Tetris");
        tetrisGameStartButton.setContentDisplay(ContentDisplay.TOP);
        tetrisGameStartButton.setGraphic(new ImageView(tetrisImg));

        //***********ADD ACTION LISTNER FOR BUTTON
        //AKA START TETRIS

        hbox.getChildren().addAll(bejeweledGameStartButton, tetrisGameStartButton);

        return hbox;
    }

//        var javaVersion = SystemInfo.javaVersion();
//        var javafxVersion = SystemInfo.javafxVersion();
//
//        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
//        var scene = new Scene(new StackPane(label), 640, 480);
//        stage.setScene(scene);
//        stage.show();

    public static void main(String args[]) {
        launch();
    }

}