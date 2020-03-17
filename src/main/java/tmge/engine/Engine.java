package tmge.engine;

import tmge.engine.gameComponents.Board;

public class Engine {
    private boolean loopContinue;
    private Screen screen;
    private Board board;
    
    Boolean init(){
        return false;
    }

    void MainLoop(Screen incomingScreen, Board incomingBoard){
        screen = incomingScreen;
        board = incomingBoard;
        screen.initialize();
        while(loopContinue){
            board.update();
            screen.draw();
        }
    }

    void startLoop(Screen screen, Board incomingBoard){
        loopContinue = true;
        this.MainLoop(screen, incomingBoard);
    }
    
    void endLoop() {
    	loopContinue = false;
    }

}
