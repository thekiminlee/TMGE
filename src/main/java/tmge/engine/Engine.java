package tmge.engine;

import tmge.engine.boardComponents.Board;

public class Engine {
    private boolean loopContinue;
    //This will serve as the view in a MVC
    //private Screen screen;
    //This will serve as the model in a MVC
    //private Board board;
    private Game game;


    public Engine(Game game){
        this.game = game;
        //this.screen = UI;
        //this.board = model;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    /*
    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void setBoard(Board board) {
        this.board = board;
    }*/

    //some methods that help both of them interact without them talking to one another

    Boolean init(){
        return false;
    }

    void MainLoop(Screen incomingScreen){
//        while(loopContinue) {
//    		System.out.println("loop");
//    		screen.setReady(false);
//    		board.update();
//            screen.draw();
//        	while (!screen.ready()) {
//        		System.out.println("sleep");
//	        	try {
//					Thread.sleep(board.getDelay());
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//        	}
//        }
    }
    /*
    public void startLoop(Screen screen) {
        loopContinue = true;
        this.screen = screen;
        board = screen.getBoard();
        
        screen.initialize();
        try {
			Thread.sleep(1000);
	        this.MainLoop(screen);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

    public void startFXMLLoop(Screen screen){
        loopContinue = true;
        this.screen = screen;
        board = screen.getBoard();
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        this.MainLoop(screen);
    }
    
    void endLoop() {
    	loopContinue = false;
    }
     */

}
