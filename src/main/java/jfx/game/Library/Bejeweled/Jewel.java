package jfx.game.Library.Bejeweled;

import java.util.ArrayList;

public abstract class Jewel{
    private int points;
    private String name;
    private Coordinate coordinate;
    private ArrayList<String> matchingJewelNames;
    private JewelMatch jewelMatch;

    public String getName(){
        return this.name;
    }

    public int getPoints(){
        return this.points;
    }

    public void setCoordinates(int x, int y) {
        this.coordinate.updateCoordinate(x, y);
    }

    public void setCoordinates(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinates() {
        return this.coordinate;
    }

    public boolean matchJewelNames(String name) {
        for (String jewelName : this.matchingJewelNames) {
            if (name.equals(jewelName)) {
                return true;
            }
        }
        return false;
    }
}