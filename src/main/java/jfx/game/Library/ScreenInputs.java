package jfx.game.Library;


import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class ScreenInputs {

    Scene scene; //will need a scene to call it on or something?
    AbstractMap<KeyEvent,Runnable> keyboardCommandList;

    public ScreenInputs(Scene scene){
        this.scene = scene;
        this.keyboardCommandList = new HashMap<>();
    }

    public void addKeyboardCommand(KeyEvent keyEvent, Runnable func){
        keyboardCommandList.put(keyEvent,func);
    }

}
