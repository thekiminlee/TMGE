package Tetris;

import tmge.engine.Configuration;
import Tetris.logic.Tetris;

public class App {
    public static void main(String args[]) {
        Configuration config = new Configuration();
        Tetris tetris = new Tetris(config);
        tetris.run();
    }
}