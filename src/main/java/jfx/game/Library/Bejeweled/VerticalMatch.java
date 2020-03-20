package jfx.game.Library.Bejeweled;

import java.util.ArrayList;

/*
VerticalMatch:
- Source: https://github.com/itzmestar/Bejeweled/blob/master/Bejeweled/src/VerticalMatch.java
- Extends JewelMatch class to match tiles vertically in the game.
 */
public class VerticalMatch extends JewelMatchDecorator {
    public VerticalMatch() {
        super(null);
    }

    public VerticalMatch(JewelMatch jewelMatch) {
        super(jewelMatch);
    }

    @Override
    public ArrayList<Coordinate> findMatch(Jewel originalJewel, Jewel[][] gameGrid) {

    	ArrayList<Coordinate> matchList;

        if (jewelMatch != null) {
            matchList = jewelMatch.findMatch(originalJewel, gameGrid);
            if (matchList != null)
                return matchList;
        }

        matchList = new ArrayList<Coordinate>();
        int colSize = gameGrid.length;

        Coordinate originalCoord = originalJewel.getCoordinates();
        int y = originalCoord.getY();
        int x = originalCoord.getX();
        matchList.add(originalCoord);

        Jewel matchJewel = originalJewel;

        // UP MATCH
        for (int i = 1; i < 3; i++) {
            if (--x > -1) {
                Jewel jewel = gameGrid[x][y];
                if (matchJewel.matchJewelNames(jewel.getName())) {
                    matchList.add(new Coordinate(x, y));
                    matchJewel = jewel;
                } else {
                    matchList.clear();
                    break;
                }
            } else {
                matchList.clear();
                break;
            }
        }
        if (matchList.size() == 3) {
            return matchList;
        }

        matchList.add(originalCoord);

        x = originalCoord.getX();
        matchJewel = originalJewel;

        // DOWN MATCH
        for (int i = 1; i < 3; i++) {
            if (++x < colSize) {
                Jewel jewel = gameGrid[x][y];
                if (matchJewel.matchJewelNames(jewel.getName())) {
                    matchList.add(new Coordinate(x, y));
                    matchJewel = jewel;
                } else {
                    matchList.clear();
                    return null;
                }
            } else {
                matchList.clear();
                return null;
            }
        }
        if (matchList.size() == 3) {
            return matchList;
        }
        return null;
    }
}
