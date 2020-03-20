package Games.Tetris;

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

    TetrisScreen screen;
    TetrisBoard board;

    /*
    public TetrisGame(TetrisScreen currentScreen, TetrisBoard currentBoard) {
        super(currentScreen,currentBoard);

        new TileGenerator(currentScreen.getScreenWidth(),currentScreen.getScreenHeight(),currentBoard.getRows(),currentBoard.getColumns());
        TileGenerator.registerPalette(currentScreen.getPalette());
        TileGenerator.registerTileConfigurations(currentBoard.getConfigurations(),currentBoard.getValues());
    }*/

    public TetrisGame(){}

    public void setScreen(TetrisScreen screen) {
        this.screen = screen;
    }

    public void setBoard(TetrisBoard board) {
        this.board = board;
    }

    @FXML
    public void initialize(){
        System.out.println("hm");
        /*
        setScreen(new TetrisScreen());
        setBoard(new TetrisBoard()); */
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
    VBox leftVBox;
    @FXML
    VBox rightVBox;
    @FXML
    GridPane gameGrid;
    @FXML
    Menu fileMenu;
    @FXML Menu helpMenu;


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


}
