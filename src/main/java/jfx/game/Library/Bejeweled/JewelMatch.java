package jfx.game.Library.Bejeweled;

import java.util.ArrayList;

public interface JewelMatch {
    public ArrayList<Coordinate> findMatch(Jewel location, Jewel[][] gameGrid);
}
