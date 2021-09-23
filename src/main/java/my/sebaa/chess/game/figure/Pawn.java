package my.sebaa.chess.game.figure;

import my.sebaa.chess.game.board.BoardField;

public class Pawn extends Figure {

    public Pawn(Color color) {
        super("pawn", color);
    }

    @Override
    public boolean[][] getPossibilityMoves(BoardField boardField) {
        boolean[][] moves = new boolean[8][8];
        final int currentX = boardField.getX();
        final int currentY = boardField.getY();

        //TODO: Optymalizacja kodu!!

        if(color == Color.WHITE) {
            if(currentX > 4) {
                int rowsToHalfBoard = currentX - 1;
                while (rowsToHalfBoard >= 4) {
                    Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(rowsToHalfBoard, currentY);
                    if(temporaryFigure == null)
                        moves[rowsToHalfBoard][currentY] = true;
                    else
                        break;
                    rowsToHalfBoard--;
                }
            } else {
                Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(currentX - 1, currentY);
                if(temporaryFigure == null)
                    if(canMove(currentX - 1, currentY))
                        moves[currentX - 1][currentY] = true;
            }

            //lewo
            if(canMove(currentX - 1, currentY + 1))
                moves[currentX - 1][currentY + 1] = isCrossBit(boardField, currentX, currentY, -1, 1);

            //prawo
            if(canMove(currentX - 1, currentY - 1))
                moves[currentX - 1][currentY - 1] = isCrossBit(boardField, currentX, currentY, -1, -1);
        } else {
            if(currentX < 3) {
                int rowsToHalfBoard = currentX + 1;
                while (rowsToHalfBoard < 4) {
                    Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(rowsToHalfBoard, currentY);
                    if(temporaryFigure == null)
                        moves[rowsToHalfBoard][currentY] = true;
                    else
                        break;
                    rowsToHalfBoard++;
                }
            } else {
                Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(currentX + 1, currentY);
                if(temporaryFigure == null)
                    if(canMove(currentX + 1, currentY))
                        moves[currentX + 1][currentY] = true;
            }

            //lewo
            if(canMove(currentX + 1,currentY - 1))
                moves[currentX + 1][currentY - 1] = isCrossBit(boardField, currentX, currentY, 1, -1);

            //prawo
            if(canMove(currentX + 1, currentY + 1))
                moves[currentX + 1][currentY + 1] = isCrossBit(boardField, currentX, currentY, 1, 1);
        }

        return moves;
    }

    private boolean isCrossBit(BoardField boardField, int x, int y, int tryX, int tryY) {
        int temporaryX = x + tryX;
        int temporaryY = y + tryY;
        if(!canMove(temporaryX, temporaryY))
            return false;

        Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(temporaryX, temporaryY);
        if(temporaryFigure != null)
            if(temporaryFigure.getColor() != color)
                return true;

        return false;
    }
}
