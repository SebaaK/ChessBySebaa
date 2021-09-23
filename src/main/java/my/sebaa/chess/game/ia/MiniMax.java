package my.sebaa.chess.game.ia;

import my.sebaa.chess.game.board.BoardField;
import my.sebaa.chess.game.board.ChessBoard;
import my.sebaa.chess.game.figure.Color;

import java.util.ArrayList;

public class MiniMax {

    public static IAMove execute(final ChessBoard board, int depth) {
        IAMove bestMove = new IAMove();
        int lowValue = Integer.MIN_VALUE;

        for(final BoardField loopField : board.getAllFiguresThisTurn()) {
            boolean[][] legalMovesLoopFigure = loopField.possibilityMovesFigure();
            for(int rowMove = 0; rowMove < 8; rowMove++) {
                for(int colMove = 0; colMove < 8; colMove++) {
                    if(legalMovesLoopFigure[rowMove][colMove]) {
                        BoardField temporaryField = loopField;
                        board.moveFigure(loopField.getX(), loopField.getY(), rowMove, colMove);
                        int currentValueMove = max(board, depth);

                        if(currentValueMove >= lowValue) {
                            lowValue = currentValueMove;
                            BoardField boardField = board.getFieldFromPoint(rowMove, colMove);
                            bestMove.setPrevMove(temporaryField);
                            bestMove.setNextMove(boardField);
                        }
                    }
                }
            }
        }

        System.out.println(bestMove.getPrevMove().getX());
        System.out.println(bestMove.getPrevMove().getY());

        System.out.println(bestMove.getNextMove().getX());
        System.out.println(bestMove.getNextMove().getY());

        return bestMove;
    }

    private static int max(ChessBoard board, int depth) {
        if(depth == 0 || board.testCheck(Color.WHITE))
            return CountScore.evaluate(board, depth);

        int lowValue = Integer.MIN_VALUE;
        for(final BoardField loopField : board.getAllFiguresThisTurn()) {
            boolean[][] legalMovesField = loopField.possibilityMovesFigure();
            for(int rowMove = 0; rowMove < 8; rowMove++) {
                for(int colMove = 0; colMove < 8; colMove++) {
                    if(legalMovesField[rowMove][colMove]) {
                        board.moveFigure(loopField.getX(), loopField.getY(), rowMove, colMove);
                        final int score = min(board, depth - 1);
                        if(score > lowValue)
                            lowValue = score;
                    }
                }
            }
        }

        return lowValue;
    }

    private static int min(ChessBoard board, int depth) {
        if(depth == 0 || board.testCheck(Color.WHITE))
            return CountScore.evaluate(board, depth);

        int highValue = Integer.MIN_VALUE;
        for(final BoardField loopField : board.getAllFiguresThisTurn()) {
            boolean[][] legalMovesField = loopField.possibilityMovesFigure();

            if(legalMovesField == null) {
                ArrayList<BoardField> fieldsX = new ArrayList<>(board.getAllFiguresThisTurn());

                board.getAllFiguresThisTurn().stream()
                        .map(t -> {
                            String name = t.getFigure().getName();
                            Color color = t.getFigure().getColor();

                            return name + " " + color;
                        })
                        .forEach(System.out::println);
            }

            for(int rowMove = 0; rowMove < 8; rowMove++) {
                for(int colMove = 0; colMove < 8; colMove++) {
                    if(legalMovesField[rowMove][colMove]) {
                        board.moveFigure(loopField.getX(), loopField.getY(), rowMove, colMove);
                        final int score = max(board, depth - 1);
                        if(score < highValue)
                            highValue = score;
                    }
                }
            }
        }

        return highValue;
    }

    private boolean isCheckAnyColor(ChessBoard board) {
        return board.testCheckMate(Color.WHITE) || board.testCheckMate(Color.BLACK);
    }
}
