public class Coordinate{
    private int x;
    private int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void updateCoordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
        
    }
}