package jfx.game.Library.Entity;

/*A class that is expected to be extended for the use of either Players or AI turn taking. This class can also be implemented
    in such a way where turns are not a thing. An example of this is for Tetris. Actor would wait to listen for a command, but if no
    command is found it wouldn't stop the flow of the code from continuing to function. This an also work in such a way where
    the game does not need to include a Vs a turn based game in the update function
    it would stop until the Actor made a choice. Resembling the turn based aspect of the game.
 */
public abstract class Actor {
    abstract public void update();
}
