package my.sebaa.chess.game.figure;

import my.sebaa.chess.game.board.BoardField;

public class Tower extends Figure {

    public Tower(Color color) {
        super("tower", color);
    }

    @Override
    public boolean[][] getPossibilityMoves(BoardField boardField) {
        boolean[][] moves = new boolean[8][8];
        final int currentX = boardField.getX();
        final int currentY = boardField.getY();

        //up & down
        moves = generateVerticalMoves(currentX, currentY, moves, boardField);

        //left & right
        moves = generateHorizontalMove(currentX, currentY, moves, boardField);

        return moves;
    }
}
