package jfx.game.GameEnv;

import jfx.game.Library.Game;
import jfx.game.Library.Screen;

public class Engine {
    private boolean loopContinue = true;
    private Screen screen;
    private Game game;
    Boolean init(){
        return false;
    }

    Engine(Game game){
        this.game = game;
    }


    /* change this up its correct logic, but in the wrong place*/
    /*
    void MainLoop(Screen incomingScreen){
        screen = incomingScreen;
        screen.initliaze();
        while(loopContinue){
            screen.appUpdate();
            screen.draw();
        }
    }*/

    void MainLoop(){
        while(loopContinue){
            game.update();
        }
    }

    void startLoop(Screen screen){
        loopContinue = true;
        MainLoop();
    }



}
