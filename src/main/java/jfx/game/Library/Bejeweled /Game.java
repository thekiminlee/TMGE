import java.util.ArrayList;

public class Game{
    Jewel[][] gameGrid;
    private int score;
    private int gridRow;
    private int gridCol;

    // public Game(){
    //     loadGame();
    // }

    public Game(int row, int col){
        this.gridRow = row;
        this.gridCol = col;   
        this.gameGrid = new Jewel[row][col];
    }

    public void setJewel(int x, int y, Jewel j){
        this.gameGrid[x][y] = j;
    }

    public void setJewel(Coordinate c, Jewel j){
        this.gameGrid[c.getX()][c.getY()] = j;
    }

    public Jewel getJewel(int x, int y) {
        return this.gameGrid[x][y];
    }

    // return the jewel at Coordinate coord
    public Jewel getJewel(Coordinate c) {
        return this.gameGrid[c.getX()][c.getY()];
    }

    public int getScore() {
        return this.score;
    }

    //Matching on board
    public int applyMatch(ArrayList<Coordinate> list) {
        int points = 0;
        for (Coordinate coords : list) {
            Jewel jewel = getJewel(coords);
            points += jewel.getPoints();

            // Set empty jewel at the coordinate
            Jewel empty = new Empty(coords);
            setJewel(coords, empty);
        }

        return points;
    }

    //GRAVITY OF THE GAME THAT HANDLES DROPPING
    public void applyGravity(ArrayList<Coordinate> list) {
        for (Coordinate coords : list) {
            int row = coords.getX();
            int col = coords.getY();
            for (int i = row; i > 0; i--) {
                Jewel empty = getJewel(i, col);
                Jewel target = getJewel(i - 1, col);
                setJewel(i - 1, col, empty);
                empty.setCoordinates(i - 1, col);
                setJewel(i, col, target);
                target.setCoordinates(i, col);
            }
        }
    }

//     private void loadGame(){
        
//     }
// }