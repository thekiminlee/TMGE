package jfx.game.Library;

public abstract class Screen {
    /*This class will be used as a template for the Screen class*/

    public abstract void loadScene();

    //public abstract void loadScene(int ye);

    public abstract void initliaze();

    public abstract void update(); //this will be called when gameLoop updates

    public abstract void draw();

    public abstract void exit();
}
