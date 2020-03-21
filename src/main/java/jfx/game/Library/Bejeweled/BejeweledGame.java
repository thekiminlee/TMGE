package jfx.game.Library.Bejeweled;

import javafx.application.Platform;
import jfx.game.Library.Tetris.Block;
import jfx.game.Library.Tetris.TetrisBoard;
import jfx.game.Library.Tetris.TetrisScreen;
import tmge.engine.Game;
import tmge.engine.gameComponents.Tile;
import tmge.engine.gameComponents.TileGenerator;

import java.net.URI;
import java.nio.file.Paths;

public class BejeweledGame extends Game implements Runnable {
    public final static URI link = Paths.get("src/main/java/jfx/game/resources/fxml/bejeweled-singleplayer.fxml").toUri();

    double screenWidth;
    double screenHeight;

    TileGenerator generator;

    TetrisScreen currentScreen;
    TetrisBoard currentBoard;


    public BejeweledGame(){
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

        while (playing) {
            // System.out.println("UPDATE IN BJ board");
            this.screen.setReady(false);
            this.timeSeconds -= 1;
            this.screen.updateTimer(this.timeSeconds);
            this.screen.updateScore(this.score);

            Platform.runLater(() -> {
                fillAll();
                this.screen.draw();
                if (this.timeSeconds == 0) {
                    if (firstGame) {
                        scoreFirst = score;
                        this.firstGame = false;
                        screen.displayAlertBox();
                        resetGame();
                    } else {
                        scoreSecond = score;
                        setPlaying(false);
                        screen.gameEnd(scoreFirst, scoreSecond);
                    }
                }

            });

            while (!this.screen.ready() && playing)
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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