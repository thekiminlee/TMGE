module jfx.game {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.media;
    requires javafx.fxml;
    requires java.base;
    requires javafx.base;
    opens jfx.game.Library to javafx.graphics;
    opens Games.Bejeweled to javafx.controls, javafx.fxml;
    opens Games.Tetris to javafx.controls, javafx.fxml;
}
