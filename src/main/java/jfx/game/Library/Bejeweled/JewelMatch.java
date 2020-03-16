package jfx.game.Library.Bejeweled;
import java.util.ArrayList;

public interface JewelMatch {
    public ArrayList<Coordinate> match(Jewel location, Jewel[][] gameGrid);
}
