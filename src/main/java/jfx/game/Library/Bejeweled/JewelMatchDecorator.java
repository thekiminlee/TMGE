package jfx.game.Library.Bejeweled;

import java.util.ArrayList;

/*
JewelMatchDecorator:
- Used for various JewelMatching operations.
    1. HorizontalMatch
    2. VerticalMatch
 */
public class JewelMatchDecorator implements JewelMatch {

    protected JewelMatch jewelMatch;

    public JewelMatchDecorator(JewelMatch jewelMatch) {
        this.jewelMatch = jewelMatch;
    }

    public ArrayList<Coordinate> findMatch(Jewel location, Jewel[][] gameGrid) {
        return null; // placeholder
    }
}