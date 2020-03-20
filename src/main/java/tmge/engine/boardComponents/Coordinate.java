package tmge.engine.boardComponents;

public class Coordinate{
    private int x;
    private int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Coordinate clone() {
        return new Coordinate(x, y);
    }

    public void updateCoordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void getY(int y){
        this.y = y;
    }

    public String toString() {
        return "<" + Integer.toString(x) + ", " + Integer.toString(y) + ">";
    }
}