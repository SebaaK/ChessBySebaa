package my.sebaa.chess.game.figure;

import my.sebaa.chess.game.board.BoardField;

public class Bishop extends Figure {

    public Bishop(Color color) {
        super("bishop", color);
    }

    @Override
    public boolean[][] getPossibilityMoves(BoardField boardField) {
        boolean[][] moves = new boolean[8][8];
        final int currentX = boardField.getX();
        final int currentY = boardField.getY();

        moves = generateMovesTypeX(currentX, currentY, moves, boardField);

        return moves;
    }
}
