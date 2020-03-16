package jfx.game.Library;


/*MAIN GAME CLASS THAT WILL HANDLE THE DIFFERENT GAMES */
public class Game {
	GameInput input;
	Screen currentScreen;
	int rows;
	int columns;

	/* this might be the most idea scenario. simplifies shit the most */
	//Scene scene javafx
	//public Game(int rows,int columns, Scene scene) -> so that the scene can be defined since the start
	// potential problems can be there will only ever be one screen created for the whole program


	public Game(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		this.currentScreen = null;
	}

	public void setCurrentScene(Screen scene){
		currentScreen = scene;
	}

	/* Here is where the call that will be made to update the Scene */
	void update()
	{
		if(currentScreen != null)
			currentScreen.update();
	}
	
	public String toString() {
		return "";
	}

	public Screen getCurrentScene() {
		return currentScreen;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

}
