package my.sebaa.chess.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import my.sebaa.chess.MainClass;
import my.sebaa.chess.game.board.ChessBoard;

import java.io.IOException;

public class BoardGameController {

    @FXML
    private GridPane chessGame;
    private static ChessBoard chessBoard;

    public void initialize() {
        new ChessBoard(chessGame);
    }

    @FXML
    public void stage() throws IOException {
        MainClass helloApplication = new MainClass();
        helloApplication.extracted();
    }
}
