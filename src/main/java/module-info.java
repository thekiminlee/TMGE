module jfx.game {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
	requires java.base;
    exports jfx.game.GameEnv;
    exports jfx.game.Library;
    exports jfx.game.Library.Tetris;
    opens jfx.game.Library.Tetris to javafx.fxml;
}