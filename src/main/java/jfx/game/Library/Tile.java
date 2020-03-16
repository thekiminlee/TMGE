package jfx.game.Library;


/*
    This is a tile class where the board  will be made up of tile pieces. This class is expected to be extended for the use
    of the user.
 */
public class Tile {
	private String locationToImage;
	Tile(String locationToImage){
		this.locationToImage = locationToImage;
	}

	void loadTexture(){
		//.... some code here. This can be removed at some point because we might just end up creating this to be very basic
	}
}
