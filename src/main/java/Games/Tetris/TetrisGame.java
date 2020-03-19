package Games.Tetris;

import tmge.engine.Game;
import tmge.engine.Screen;
import tmge.engine.boardComponents.Board;
import tmge.engine.boardComponents.Tile;
import tmge.engine.boardComponents.TileGenerator;

public class TetrisGame extends Game {
    double screenWidth;
    double screenHeight;

    public TetrisGame(TetrisScreen currentScreen, TetrisBoard currentBoard) {
        super(currentScreen,currentBoard);

        new TileGenerator(currentScreen.getScreenWidth(),currentScreen.getScreenHeight(),currentBoard.getRows(),currentBoard.getColumns());
        TileGenerator.registerPalette(currentScreen.getPalette());
        TileGenerator.registerTileConfigurations(currentBoard.getConfigurations(),currentBoard.getValues());
    }


    protected void updateScreen(){

    }

    protected void updateBoard(){


    }

    /*in theory this will end up calling both the screen and board update methods */
    protected void update(){
        System.out.println("no");
    }
}
