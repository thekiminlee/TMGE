module jfx.game {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.media;
    requires javafx.fxml;
	requires java.base;
	requires javafx.base;
    opens jfx.game.Library to javafx.graphics;
    opens jfx.game.Library.Bejeweled to javafx.controls, javafx.fxml; 
    opens jfx.game.Library.Tetris to javafx.controls, javafx.fxml;
}