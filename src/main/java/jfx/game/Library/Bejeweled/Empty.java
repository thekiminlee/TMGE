package jfx.game.Library.Bejeweled;

import java.util.ArrayList;
import jfx.game.Library.Bejeweled.Jewel;

public class Empty extends Jewel {

    public Empty(Coordinate coordinate) {
        super(" ", 0, coordinate);
        this.jewelMatch = null;
    }

    @Override
    public ArrayList<Coordinate> findMatch(Jewel[][] gameGrid) {
        return null;
    }
}