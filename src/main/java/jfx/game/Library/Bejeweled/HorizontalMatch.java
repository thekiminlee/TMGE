package jfx.game.Library.Bejeweled;

import java.util.ArrayList;

/*
HorizontalMatch Class:
- Source: https://github.com/itzmestar/Bejeweled/blob/master/Bejeweled/src/HorizontalMatch.java
- Matches tiles in the left direction, right direction.
 */
public class HorizontalMatch extends JewelMatchDecorator {

    // HorizontalMatch constructor:
    public HorizontalMatch() {
        super(null);
    }

    public HorizontalMatch(JewelMatch jewelMatch) {
        super(jewelMatch);
    };

    @Override
    public ArrayList<Coordinate> findMatch(Jewel originalJewel, Jewel[][] gameGrid) {

        ArrayList<Coordinate> matchList;

        if (jewelMatch != null) {
            matchList = jewelMatch.findMatch(originalJewel, gameGrid);
            if (matchList != null)
                return matchList;
        }

        matchList = new ArrayList<Coordinate>();
        int rowSize = gameGrid[0].length;

        Coordinate originalCoord = originalJewel.getCoordinates();
        int x = originalCoord.getX();
        int y = originalCoord.getY();
        matchList.add(originalCoord);

        Jewel matchJewel = originalJewel;

        // LEFT MATCH
        for (int i = 1; i < 3; ++i) {
            if (--y > -1) {
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

        y = originalCoord.getY();
        matchJewel = originalJewel;

        // RIGHT MATCH
        for (int i = 1; i < 3; ++i) {
            if (++y < rowSize) {
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
