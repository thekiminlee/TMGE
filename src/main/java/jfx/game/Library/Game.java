package jfx.game.Library;

public class Game {
	Screen currentScreen;
	int rows;
	int columns;
	
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
