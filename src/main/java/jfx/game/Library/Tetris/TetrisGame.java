package jfx.game.Library.Tetris;

import javafx.application.Platform;
import tmge.engine.Game;
import tmge.engine.gameComponents.Tile;
import tmge.engine.gameComponents.TileGenerator;

import java.net.URI;
import java.nio.file.Paths;

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

    @Override
    public void run() {
        update();
    }

    /*in theory this will end up calling both the screen and board update methods */
    public void update() {
        System.out.println("testing");
        while (currentBoard.isPlaying()) {
            System.out.println("update");
            currentScreen.setReady(false);
            Platform.runLater(() -> {
                if (currentBoard.getActiveBlock() == null)
                    currentBoard.createMovableBlock();
                else {
                    currentBoard.attemptAction(TetrisBoard.Moves.TRANSLATE_VERTICAL, 1);
                }
                currentScreen.draw();
            });
            while(!currentScreen.ready()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!currentBoard.isPlaying()) break;
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

    int getScore() {
        return currentBoard.getScore();
    }


    void clearBoard() {
       currentBoard.clearBoard();
    }

}