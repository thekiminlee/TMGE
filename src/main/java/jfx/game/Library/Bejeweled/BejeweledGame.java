package jfx.game.Library.Bejeweled;

import javafx.application.Platform;
import jfx.game.Library.Tetris.Block;
import jfx.game.Library.Tetris.TetrisBoard;
import jfx.game.Library.Tetris.TetrisScreen;
import tmge.engine.Game;
import tmge.engine.gameComponents.Coordinate;
import tmge.engine.gameComponents.Tile;
import tmge.engine.gameComponents.TileGenerator;

import java.net.URI;
import java.nio.file.Paths;

public class BejeweledGame extends Game implements Runnable {
    public final static URI link = Paths.get("src/main/java/jfx/game/resources/fxml/bejeweled-singleplayer.fxml").toUri();

    double screenWidth;
    double screenHeight;

    TileGenerator generator;

    BejeweledScreen currentScreen;
    BejeweledBoard currentBoard;


    public BejeweledGame(){
        super();
    }

    public void createGenerator(){
        generator = new TileGenerator(currentScreen.getScreenWidth(),currentScreen.getScreenHeight(),3,currentScreen.getPalette());
    }

    @Override
    public void run() {
        update();
    }

    /*in theory this will end up calling both the screen and board update methods */
    public void update() {

        while (playing) {
            // System.out.println("UPDATE IN BJ board");
            currentScreen.setReady(false);
            //this.timeSeconds -= 1;
            subtractTime(-1);
            currentScreen.updateTimer(currentBoard.getTimeSeconds());
            currentScreen.updateScore(currentBoard.getScore());

            Platform.runLater(() -> {
                currentBoard.fillAll();
                //fillAll();
                //this.screen.draw();
                currentScreen.draw();
                if (currentBoard.getTimeSeconds() == 0) {
                    //if (firstGame) {
                    if (currentBoard.isFirstGame())

                        //scoreFirst = score;
                        currentBoard.scoreFirst = currentBoard.score;
                        //this.firstGame = false;
                        currentBoard.firstGame = false;
                        currentScreen.displayAlertBox();
                        currentBoard.resetGame();
                    } else {
                        currentBoard.scoreSecond = currentBoard.score;
                        //scoreSecond = score;
                        setPlaying(false);
                        currentScreen.gameEnd(currentBoard.scoreFirst, currentBoard.scoreSecond);
                    }
                });
            }
            while (!currentScreen.ready() && playing)
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }


    private void subtractTime(int i) {
        int j = currentBoard.getTimeSeconds();
        j -= i;
        currentBoard.setTimeSeconds(j);
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

    public BejeweledScreen getScreen() {
        return (BejeweledScreen) currentScreen;
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

    public void setScreen(BejeweledScreen screen) {
        this.currentScreen = screen;
    }

    public void setBoard(BejeweledBoard board) {
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

    public void swap(Coordinate coords, Coordinate last){
        currentBoard.swap(coords,last);
    }
}