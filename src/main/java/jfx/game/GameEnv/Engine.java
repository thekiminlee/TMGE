package jfx.game.GameEnv;

import jfx.game.Library.Scene;

public class Engine {
    private boolean loopContinue;
    private Scene scene;
    Boolean init(){
        return false;
    }

    void MainLoop(Scene incomingScene){
        scene = incomingScene;
        scene.initliaze();
        while(loopContinue){
            scene.update();
            scene.draw();
        }
    }

    void startLoop(Scene scene){
        loopContinue = true;
        this.MainLoop(scene);
    }

    void Update(){

    }

}
