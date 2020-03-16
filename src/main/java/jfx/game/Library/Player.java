package jfx.game.Library;

public class Player {
    String name;
    int points;

    public Player(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String toString() {
        return (this.name + " " + this.points);
    }

    public int getPoints() {
        return this.points;
    }
}
