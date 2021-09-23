package my.sebaa.chess.game.figure;

import my.sebaa.chess.game.board.BoardField;

public class King extends Figure {

    public King(Color color) {
        super("king", color);
    }

    @Override
    public boolean[][] getPossibilityMoves(BoardField boardField) {
        boolean[][] moves = new boolean[8][8];
        final int currentX = boardField.getX();
        final int currentY = boardField.getY();

        for(int squareX = currentX - 1; squareX <= currentX + 1; squareX++) {
            for(int squareY = currentY - 1; squareY <= currentY + 1; squareY++) {
                if(!(currentX == squareX && currentY == squareY)) {
                    if(canMove(squareX, squareY)) {
                        Figure figure = boardField.getChessBoard().getFigureFromPoint(squareX,squareY);
                        if(figure == null)
                            moves[squareX][squareY] = true;
                        else {
                            if(figure.getColor() != color)
                                moves[squareX][squareY] = true;
                        }

                    }
                }

                if(squareX > 8 && squareY > 8)
                    break;
            }
        }

        return moves;
    }
}
