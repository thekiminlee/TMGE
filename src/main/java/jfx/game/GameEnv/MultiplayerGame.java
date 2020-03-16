package jfx.game.GameEnv;

import jfx.game.Library.Game;
import jfx.game.Library.GameInput;


/* this doesn't need to end up running different threads*/ //MAYBE NOT? unsure still .-.

/* can just run within the same window of the game */
/* can just create a Stage and put in the two different scene UI */
// https://www.tutorialspoint.com/javafx/javafx_application.htm link to the JavaFX structure


public class MultiplayerGame{

    /*A way to keep the inputs for keyboards in here */
    GameInput playerOne;
    GameInput playerTwo;

    //create two different games because they will both be running different things
    Game gameP1;
    Game gameP2;

    MultiplayerGame(Game game){
        this.gameP1 = game;
        this.gameP2 = game;
    }

    void createMultiplayerGame(){
        //create copy of game
        //create different threads? i don't think multithreading is req
    }


    

}
