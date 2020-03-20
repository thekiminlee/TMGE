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

    TetrisScreen currentScreen;
    TetrisBoard currentBoard;

    @FXML
    VBox leftVBox;
    @FXML
    VBox rightVBox;
    @FXML
    GridPane gameGrid;
    @FXML
    Menu fileMenu;
    @FXML Menu helpMenu;

    /*
    TetrisScreen screen;
    TetrisBoard board;*/

    /*
    public TetrisGame(TetrisScreen currentScreen, TetrisBoard currentBoard) {
        super(currentScreen,currentBoard);

        new TileGenerator(currentScreen.getScreenWidth(),currentScreen.getScreenHeight(),currentBoard.getRows(),currentBoard.getColumns());
        TileGenerator.registerPalette(currentScreen.getPalette());
        TileGenerator.registerTileConfigurations(currentBoard.getConfigurations(),currentBoard.getValues());
    }*/

    public TetrisGame(){
        super();
    }

    public void setScreen(TetrisScreen screen) {
        this.currentScreen = screen;
    }

    public void setBoard(TetrisBoard board) {
        this.currentBoard = board;
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


    @FXML
    private void minimize() {
        Stage stage = (Stage) leftVBox.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void maximize() {
        Stage stage = (Stage) leftVBox.getScene().getWindow();
        stage.setMaximized(true);
    }

    @FXML
    public void exit() {
        Stage stage = (Stage) leftVBox.getScene().getWindow();
        stage.close();
        System.exit(0);
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

    public VBox getLeftVBox() {
        return leftVBox;
    }

    public VBox getRightVBox() {
        return rightVBox;
    }

    public GridPane getGameGrid() {
        return gameGrid;
    }

    public Menu getFileMenu() {
        return fileMenu;
    }

    public Menu getHelpMenu() {
        return helpMenu;
    }

    public int getRows() {
        return currentBoard.getRows();
    }

    public int getColumns() {
        return currentBoard.getColumns();
    }
}
