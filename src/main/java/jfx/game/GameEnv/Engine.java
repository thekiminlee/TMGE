package jfx.game.GameEnv;

import jfx.game.Library.Game;
import jfx.game.Library.Screen;

public class Engine implements Runnable {
    private boolean loopContinue = true;
    private Game game;
    Boolean init(){
        return false;
    }

    Engine(Game game){
        this.game = game;
    }


    void MainLoop(){
        while(loopContinue){
            game.update();
        }
    }

    void startLoop(){
        loopContinue = true;
        MainLoop();
    }

    //this would be used as new Thread(Engine Obj).run();
    //we can set this on an action on when someone clicks on a game and then this ends up running
    public void run(){
        startLoop();
    }
}
