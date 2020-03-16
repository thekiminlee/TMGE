package jfx.game.GameEnv;

import jfx.game.Library.Screen;

public class Engine {
    private boolean loopContinue;
    private Screen screen;

    Boolean init() {
        return false;
    }

    void MainLoop(Screen incomingScreen) {
        screen = incomingScreen;
        screen.initliaze();
        while (loopContinue) {
            screen.update();
            screen.draw();
        }
    }

    void startLoop(Screen screen) {
        loopContinue = true;
        this.MainLoop(screen);
    }

    void update() {
        screen.update();
    }

}
