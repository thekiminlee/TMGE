package jfx.game.GameEnv;

import jfx.game.Library.Game;
import jfx.game.Library.ScreenInputs;


/* this doesn't need to end up running different threads*/ //MAYBE NOT? unsure still .-.

/* can just run within the same window of the game */
/* can just create a Stage and put in the two different scene UI */
// https://www.tutorialspoint.com/javafx/javafx_application.htm link to the JavaFX structure


public class MultiplayerGame{

    /*A way to keep the inputs for keyboards in here */
    ScreenInputs playerOne;
    ScreenInputs playerTwo;

    //create a Stage


    Game game;

    MultiplayerGame(Game game){
        this.game = game;
    }

    void createMultiplayerGame(){
        //create copy of game
        //create different threads? i don't think multithreading is req
    }


    

}
