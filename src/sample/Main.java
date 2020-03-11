package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.nio.file.Paths;

public class Main extends Application {

    Button bejeweledGameStartButton;
    Button tetrisGameStartButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        BorderPane border = new BorderPane();
        HBox buttonBox = addHBox();
        border.setCenter(buttonBox);
        border.setMaxSize(500,300);
        border.setStyle("-fx-background-color: #FFFFFF;");

        //Media for background playing video
        String src = Paths.get("src/clip1.mp4").toUri().toString();
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
        //layout.getChildren().add(border);
        layout.getChildren().add(menu);

        Scene scene = new Scene(layout, 1000, 500);

        primaryStage.setTitle("TMGE Main Menu");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public HBox addHBox(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        //hbox.setStyle("-fx-background-color: #336699;");

        hbox.setAlignment(Pos.CENTER);
        //Bejeweled Button
        Image bejeweledImg = new Image(getClass().getResourceAsStream("/img1.png"));
        bejeweledGameStartButton = new Button();
        bejeweledGameStartButton.setGraphic(new ImageView(bejeweledImg));

        //Tetris Button
        Image tetrisImg = new Image(getClass().getResourceAsStream("/img2.png"));
        tetrisGameStartButton = new Button();
        tetrisGameStartButton.setGraphic(new ImageView(tetrisImg));

        hbox.getChildren().addAll(bejeweledGameStartButton, tetrisGameStartButton);

        return hbox;
    }
}
