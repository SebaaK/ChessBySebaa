package my.sebaa.chess.game.board;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import my.sebaa.chess.game.figure.Color;
import my.sebaa.chess.game.figure.Figure;
import my.sebaa.chess.game.figure.Pawn;

public class ChessBoard extends GridPane {

    private GridPane gridPane;
    private BoardField[][] fields = new BoardField[8][8];

    public ChessBoard(GridPane gridPane) {
        this.gridPane = gridPane;

        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                BoardField boardField = new BoardField(this, row, col);
                fields[row][col] = boardField;
            }
        }

        drawBoard();

        setFigureOnField(2, 2, new Pawn(Color.WHITE));
        setFigureOnField(1, 6, new Pawn(Color.BLACK));
    }

    private void setFigureOnField(int row, int col, Figure figure){
        if(row < 0 || row > 7 || col < 0 || col > 7 )
            //throw;
            return;

        //test
        fields[row][col].setFigure(figure);
        drawBoard();
    }

    private void drawBoard() {
        gridPane.getChildren().clear();
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                BoardField field = fields[row][col];
                gridPane.add(field, row, col);
            }
        }
    }
}
