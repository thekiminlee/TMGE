package jfx.game.Library.Bejeweled;

import java.util.ArrayList;
import jfx.game.Library.Bejeweled.Jewel;
import jfx.game.Library.Bejeweled.HorizontalMatch;
import jfx.game.Library.Bejeweled.VerticalMatch;
import jfx.game.Library.Bejeweled.Coordinate;
import jfx.game.Library.Bejeweled.JewelMatch;

public class Triangle extends Jewel {
    public Triangle(int x, int y) {
        super("T", 40, new Coordinate(x, y));
        this.jewelHorizontalMatch = new HorizontalMatch(new VerticalMatch());
        addMatchingJewelNames(this.name);
    }

    @Override
    public ArrayList<Coordinate> findMatch(Jewel[][] gameGrid) {
        return jewelMatch.findMatch(this, gameGrid);
    }
}