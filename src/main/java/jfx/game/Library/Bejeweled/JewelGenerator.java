package jfx.game.Library.Bejeweled;

import jfx.game.Library.Bejeweled.Jewels.*;

public class JewelGenerator {
    public JewelGenerator() {

    }

    public Circle generateCircle(int x, int y) {
        return new Circle(x, y);
    }

    public Diamond generateDiamond(int x, int y) {
        return new Diamond(x, y);
    }

    public Square generateSquare(int x, int y) {
        return new Square(x, y);
    }

    public Triangle generateTriangle(int x, int y) {
        return new Triangle(x, y);
    }
}