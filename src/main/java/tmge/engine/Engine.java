package tmge.engine;

import tmge.engine.gameComponents.Board;

public class Engine {
    private boolean loopContinue;
    private Screen screen;
    private Board board;
    
    Boolean init(){
        return false;
    }

    void MainLoop(Screen incomingScreen){
        while(loopContinue) {
    		System.out.println("loop");
    		screen.setReady(false);
    		board.update();
            screen.draw();
        	while (!screen.ready()) {
        		System.out.println("sleep");
	        	try {
					Thread.sleep(board.getDelay());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	}
        }
    }
    
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

}
