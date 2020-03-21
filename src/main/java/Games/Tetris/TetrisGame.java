package Games.Tetris;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class TetrisGame extends Game implements Runnable {
    public final static URI link = Paths.get("src/main/java/jfx/game/resources/fxml/tetris-singleplayer.fxml").toUri();

    double screenWidth;
    double screenHeight;

    TileGenerator generator;

    TetrisScreen currentScreen;
    TetrisBoard currentBoard;

    public TetrisGame(TetrisScreen currentScreen, TetrisBoard currentBoard) {
        super();
    }


    public TetrisGame(){
        super();
    }

    public void createGenerator(){
        generator = new TileGenerator(currentScreen.getScreenWidth(),currentScreen.getScreenHeight(),0,currentScreen.getPalette());
    }

    protected void updateScreen(){

    }

    protected void updateBoard(){


    }

    @Override
    public void run() {
        update();
    }

    /*in theory this will end up calling both the screen and board update methods */
    public void update() {
        System.out.println("testing");
        while (currentBoard.getPlaying()) {
            System.out.println("update");
            currentScreen.setReady(false);
            if (currentBoard.getActiveBlock() == null)
                currentBoard.createMovableBlock();
            else {
                currentBoard.attemptAction(TetrisBoard.Moves.TRANSLATE_VERTICAL, 1);
            }
            Platform.runLater(() -> {
                currentScreen.draw();
            });
            while(!currentScreen.ready()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!currentBoard.getPlaying()) break;
        }
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

    public void setPlaying(boolean playing) {
        currentBoard.setPlaying(playing);
    }

    public Block getActiveBlock(){
        return currentBoard.getActiveBlock();
    }
}
