package tmge.engine;

import tmge.engine.gameComponents.Board;
import tmge.engine.gameComponents.Screen;

public class Engine {
    private boolean loopContinue;
    private Screen screen;
    private Board board;
    
    Boolean init(){
        return false;
    }
    
    public void startLoop(Screen screen) {
        loopContinue = true;
        this.screen = screen;
        board = screen.getBoard();
        screen.initialize();
    }

    public void startFXMLLoop(Screen screen){
        loopContinue = true;
        this.screen = screen;
        board = screen.getBoard();
    }
    
    void endLoop() {
    	loopContinue = false;
    }

}
