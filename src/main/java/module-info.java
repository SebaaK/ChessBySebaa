module my.sebaa.chess {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens my.sebaa.chess.controllers to javafx.fxml;
    opens my.sebaa.chess to javafx.fxml;
    exports my.sebaa.chess;
}