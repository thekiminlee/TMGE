module jfx.game {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.media;
    requires javafx.fxml;
	requires java.base;
	requires javafx.base;
    exports jfx.game.GameEnv;
    exports jfx.game.Library;
    opens jfx.game.Library.Tetris to javafx.controls, javafx.fxml;
    
}