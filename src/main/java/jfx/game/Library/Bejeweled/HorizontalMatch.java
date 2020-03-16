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

    public HorizontalMatch(JewelMatch jewelMatch);

    @Override
    public ArrayList<Coordinate> findMatch(Jewel jewelOrigin, Jewel[][] gameGrid) {

        ArrayList<Coordinate> list;

        if (jewelMatch != null) {
            list = jewelMatch.findMatch(jewelOrigin, gameGrid);
            if (list != null)
                return list;
        }

        list = new ArrayList<Coordinate>();
        int colSize = gameGrid.length;
        int rowSize = gameGrid[0].length;

        int x = jewelOrigin.getCoordinates().getX();
        int y = jewelOrigin.getCoordinates().getY();
        list.add(jewelOrigin.getCoordinates());

        Jewel matchJewel = jewelOrigin;

        // LEFT MATCH
        for (int j = 1; j < 3; j++) {
            y -= 1;
            if (y > -1) {
                Jewel jewel = gameGrid[x][y];
                if (matchJewel.matchJewelNames(jewel.getName())) {
                    Coordinate coords = new Coordinate(x, y);
                    list.add(coords);
                    matchJewel = jewel;
                } else {
                    list.clear();
                    break;
                }
            } else {
                list.clear();
                break;
            }
        }
        if (list.size() == 3) {
            return list;
        }
        list.add(jewelOrigin.getCoordinates());

        y = jewelOrigin.getCoordinates().getY();
        matchJewel = jewelOrigin;

        // RIGHT MATCH
        for (int j = 1; j < 3; j++) {
            y += j;
            if (y < rowSize) {
                Jewel jewel = gameGrid[x][y];
                if (matchJewel.matchJewelNames(jewel.getName())) {
                    Coordinate coords = new Coordinate(x, y);
                    list.add(coords);
                    matchJewel = jewel;
                } else {
                    list.clear();
                    return null;
                }
            } else {
                list.clear();
                return null;
            }
        }
        if (list.size() == 3) {
            return list;
        }
        return null;

    }
}