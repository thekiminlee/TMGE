package jfx.game.Library;

import jfx.game.Library.Entity.Actor;

import java.util.ArrayList;

/* This class needs to be implemented because it is what will allows different kind of screens to be created. This allows
    for flexibility on what can happen. This will be left up to the user on how they want to implement a Scene.
 */
public abstract class Screen {
    ArrayList<Actor> actors;
    Board board;

    Screen(Board board){
        this.board = board;
        this.actors = new ArrayList<Actor>();
    }
    /* This is where the scene is initialized. Scene is the actual game board*/
    abstract void loadScene();

    //public abstract void loadScene(int ye);

    /* Any important data initialization that will be used in this Scene in the future.*/
    abstract void initliaze();

    //this will be called when gameLoop updates
    protected void update(){
        appUpdate();
        if(!actors.isEmpty()) {
            for(Actor each: actors)
                each.update();
        }
    }

    /* This is where any changes to the Application data will go. */
    abstract void appUpdate();
    /* Required components that will be redrawn after every update() method is called */
    /* maybe use FXElementFactory here? Idk */
    abstract void draw();

    //void draw(Scene scene) //javafx.scene || this way we can reset the scene and then redraw the components needed for this screen

    /* Some actions that will be done at the end of the exit. This can be applying a new screen and calling Engine.startLoop on it */
    abstract void exit();

    void addAdctor(Actor a){
        actors.add(a);
    }
}
