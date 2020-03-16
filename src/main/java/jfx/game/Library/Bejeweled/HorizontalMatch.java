package jfx.game.Library.Bejeweled;
import java.util.ArrayList;

public class HorizontalMatch extends JewelMatch {

    public HorizontalMatch(JewelMatch jewelMatch){
        super(jewelMatch);
    }

    @Override
    public ArrayList<Coordinate> findMatch(Jewel jewelLocation, Jewel[][] gameGrid) {
        ArrayList<Coordinate> coordinateList;

        if (jewelMatch != null) {
            coordinateList = jewelMatch.findMatch(jewelLocation, gameGrid);
            if (coordinateList != null) {
                return coordinateList;
            }
        }
    }
}