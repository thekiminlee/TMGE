package jfx.game.Library.Bejeweled;

import java.util.ArrayList;

public abstract class Jewel {
    protected int points;
    protected String name;
    protected Coordinate coordinate;
    protected ArrayList<String> matchingJewelNames;
    protected JewelMatch jewelMatch;

    public Jewel(String name, int points, Coordinate coordinate) {
        this.points = points;
        this.coordinate = coordinate;
        this.name = name;
        this.matchingJewelNames = new ArrayList<String>();
    }

    public String getName() {
        return this.name;
    }

    public int getPoints() {
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

    public abstract ArrayList<Coordinate> findMatch(Jewel[][] gameGrid);
}