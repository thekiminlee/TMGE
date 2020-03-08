package jfx.game.Library;

public class Game {
	Scene currentScene;
	int rows;
	int columns;
	public Game(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		this.currentScene = null;
	}

	public void setCurrentScene(Scene scene){
		currentScene = scene;
	}

	/* Here is where the call that will be made to update the Scene */
	void update()
	{
		if(currentScene != null)
			currentScene.update();
	}
	
	public String toString() {
		return "";
	}

	public Scene getCurrentScene() {
		return currentScene;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
}
