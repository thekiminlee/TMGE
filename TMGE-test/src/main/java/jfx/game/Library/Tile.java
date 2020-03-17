package jfx.game.Library;


/*
    This is a tile class where the board  will be made up of tile pieces
 */
public class Tile {
	private String locationToImage;
	private boolean solid;

	Tile(String locationToImage,boolean solid){
		this.locationToImage = locationToImage;
		this.solid = solid;
	}

	void loadTexture(){
		//.... some code here. This can be removed at some point because we might just end up creating this to be very basic
	}
}
