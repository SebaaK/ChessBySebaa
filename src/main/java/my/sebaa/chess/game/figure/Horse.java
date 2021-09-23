package my.sebaa.chess.game.figure;

import my.sebaa.chess.game.board.BoardField;

public class Horse extends Figure {

    public Horse(Color color) {
        super("horse", color);
    }

    @Override
    public boolean[][] getPossibilityMoves(BoardField boardField) {
        boolean[][] moves = new boolean[8][8];
        final int currentX = boardField.getX();
        final int currentY = boardField.getY();

        int temporaryX;
        int temporaryY;

        //TODO: Optymalizacja kodu!

        //góra -> lewo
        temporaryX = currentX - 2;
        temporaryY = currentY - 1;
        if(canMove(temporaryX, temporaryY)) {
            Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(temporaryX, temporaryY);
            if(temporaryFigure == null)
                moves[temporaryX][temporaryY] = true;
            else {
                if(temporaryFigure.getColor() != this.getColor())
                    moves[temporaryX][temporaryY] = true;
            }
        }

        //góra -> prawo
        temporaryX = currentX - 2;
        temporaryY = currentY + 1;
        if(canMove(temporaryX, temporaryY)) {
            Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(temporaryX, temporaryY);
            if(temporaryFigure == null)
                moves[temporaryX][temporaryY] = true;
            else {
                if(temporaryFigure.getColor() != this.getColor())
                    moves[temporaryX][temporaryY] = true;
            }
        }

        //lewo -> góra
        temporaryX = currentX - 1;
        temporaryY = currentY + 2;
        if(canMove(temporaryX, temporaryY)) {
            Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(temporaryX, temporaryY);
            if(temporaryFigure == null)
                moves[temporaryX][temporaryY] = true;
            else {
                if(temporaryFigure.getColor() != this.getColor())
                    moves[temporaryX][temporaryY] = true;
            }
        }

        //lewo -> dół
        temporaryX = currentX + 1;
        temporaryY = currentY + 2;
        if(canMove(temporaryX, temporaryY)) {
            Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(temporaryX, temporaryY);
            if(temporaryFigure == null)
                moves[temporaryX][temporaryY] = true;
            else {
                if(temporaryFigure.getColor() != this.getColor())
                    moves[temporaryX][temporaryY] = true;
            }
        }

        //dół -> prawo
        temporaryX = currentX + 2;
        temporaryY = currentY + 1;
        if(canMove(temporaryX, temporaryY)) {
            Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(temporaryX, temporaryY);
            if(temporaryFigure == null)
                moves[temporaryX][temporaryY] = true;
            else {
                if(temporaryFigure.getColor() != this.getColor())
                    moves[temporaryX][temporaryY] = true;
            }
        }

        //dół -> lewo
        temporaryX = currentX + 2;
        temporaryY = currentY - 1;
        if(canMove(temporaryX, temporaryY)) {
            Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(temporaryX, temporaryY);
            if(temporaryFigure == null)
                moves[temporaryX][temporaryY] = true;
            else {
                if(temporaryFigure.getColor() != this.getColor())
                    moves[temporaryX][temporaryY] = true;
            }
        }

        //lewo -> dół
        temporaryX = currentX + 1;
        temporaryY = currentY - 2;
        if(canMove(temporaryX, temporaryY)) {
            Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(temporaryX, temporaryY);
            if(temporaryFigure == null)
                moves[temporaryX][temporaryY] = true;
            else {
                if(temporaryFigure.getColor() != this.getColor())
                    moves[temporaryX][temporaryY] = true;
            }
        }

        //lewo -> góra
        temporaryX = currentX - 1;
        temporaryY = currentY - 2;
        if(canMove(temporaryX, temporaryY)) {
            Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(temporaryX, temporaryY);
            if(temporaryFigure == null)
                moves[temporaryX][temporaryY] = true;
            else {
                if(temporaryFigure.getColor() != this.getColor())
                    moves[temporaryX][temporaryY] = true;
            }
        }

        return moves;
    }
}
