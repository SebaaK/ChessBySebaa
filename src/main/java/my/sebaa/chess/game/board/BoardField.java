package my.sebaa.chess.game.board;

import my.sebaa.chess.game.figure.Figure;

public class BoardField {

    private int x;
    private int y;
    private ChessBoard chessBoard;
    private Figure figure = null;
    
    public BoardField(ChessBoard chessBoard, int x, int y) {
        this.chessBoard = chessBoard;
        this.x = x;
        this.y = y;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean[][] possibilityMovesFigure() {
        if(figure == null)
            return null;

        return figure.getPossibilityMoves(this);
    }

}
