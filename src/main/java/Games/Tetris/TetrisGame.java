package Games.Tetris;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tmge.engine.Game;
import tmge.engine.Screen;
import tmge.engine.boardComponents.Board;
import tmge.engine.boardComponents.Tile;
import tmge.engine.boardComponents.TileGenerator;

import java.net.URI;
import java.nio.file.Paths;

public class TetrisGame extends Game {
    public final static URI link = Paths.get("src/main/java/jfx/game/resources/fxml/tetris-singleplayer.fxml").toUri();

    double screenWidth;
    double screenHeight;

    TileGenerator generator;

    public TetrisGame(TetrisScreen currentScreen, TetrisBoard currentBoard) {
        super(currentScreen,currentBoard);

        //new TileGenerator(currentScreen.getScreenWidth(),currentScreen.getScreenHeight(),currentBoard.getRows(),currentBoard.getColumns());
        //TileGenerator.registerPalette(currentScreen.getPalette());
        //TileGenerator.registerTileConfigurations(currentBoard.getConfigurations(),currentBoard.getValues());
    }


    public TetrisGame(){
        super();
    }


    @FXML
    public void initialize(){
        /*
        System.out.println("hm");
        setScreen(new TetrisScreen());
        setBoard(new TetrisBoard());
        currentBoard.getBoard();
        currentScreen.initialize(this);
        Platform.runLater(()-> {
            currentScreen.draw();
        });*/
        //screen.initialize();
    }

    protected void updateScreen(){

    }

    protected void updateBoard(){


    }

    /*in theory this will end up calling both the screen and board update methods */
    protected void update(){
        System.out.println("no");
    }



    public static URI getLink() {
        return link;
    }

    public double getScreenWidth() {
        return screenWidth;
    }

    public double getScreenHeight() {
        return screenHeight;
    }

    public TetrisScreen getScreen() {
        return (TetrisScreen) currentScreen;
    }

    public Tile[][] getBoard() {
        System.out.println("testing");
        return currentBoard.getBoard();
    }

    public int getRows() {
        return currentBoard.getRows();
    }

    public int getColumns() {
        return currentBoard.getColumns();
    }

    public TileGenerator getGenerator(){
        return generator;
    }

    public void setScreen(TetrisScreen screen) {
        this.currentScreen = screen;
    }

    public void setBoard(TetrisBoard board) {
        this.currentBoard = board;
    }

    public void setScreenWidth(double screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setScreenHeight(double screenHeight) {
        this.screenHeight = screenHeight;
    }

    public void setGenerator(TileGenerator generator) {
        this.generator = generator;
    }

}
