package jfx.game.GameEnv;

import javafx.scene.control.Alert;

/* This class needs to be implemented because it is what will allows different kind of screens to be created. This allows
    for flexibility on what can happen. This will be left up to the user on how they want to implement a Scene.
 */
public interface Screen {

    /* This is where
    void loadScene();

    //public abstract void loadScene(int ye);

    /* Any important data initialization that will be used in this Scene in the future.*/
    void initialize();

    /* Required components that will be redrawn after every update() method is called */
    void draw();

    /* Some actions that will be done at the end of the exit. This can be applying a new screen and calling Engine.startLoop on it */
    void exit();
    
	static void sendError(String err) {
		Alert alertMessage = new Alert(Alert.AlertType.ERROR);
        alertMessage.setHeaderText(null);
        alertMessage.setContentText(err);
        alertMessage.showAndWait();
	}
}
