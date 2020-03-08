package jfx.game.Library;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.concurrent.Callable;

public class ScreenInputs {
    public <T> void createEventForKeyboard(Scene scene, KeyEvent keyEvent, Callable<T> func) throws Exception {
        scene.addEventFilter(KeyEvent.ANY,  (event) -> {
            System.out.println("no");
        });
    }

}
