package my.sebaa.chess.game.ia;

import my.sebaa.chess.game.board.BoardField;
import my.sebaa.chess.game.board.ChessBoard;
import my.sebaa.chess.game.figure.Color;

public class MiniMax {

    private final BoardField[][] boardFields;
    private ChessBoard chessBoard = new ChessBoard();
    private int depth;

    public MiniMax(final BoardField[][] chessBoard, int depth) {
        boardFields = chessBoard;
        this.depth = depth;

        this.chessBoard.setCurrentTurn(1);
    }

    public IAMove execute() {
        IAMove bestMove = new IAMove();
        int lowValue = Integer.MIN_VALUE;

        chessBoard.setFieldFromOutsiteConfiguration(boardFields);
        for(BoardField loopField : chessBoard.getAllFiguresThisTurn()) {
            boolean[][] legalMovesLoopFigure = loopField.possibilityMovesFigure();
            for(int rowMove = 0; rowMove < 8; rowMove++) {
                for(int colMove = 0; colMove < 8; colMove++) {
                    if(legalMovesLoopFigure[rowMove][colMove]) {
                        chessBoard.setCurrentTurn(1);
                        chessBoard.moveFigure(loopField.getX(), loopField.getY(), rowMove, colMove);
                        int currentValueMove = minimax(chessBoard, depth, true);

                        if(currentValueMove >= lowValue) {
                            lowValue = currentValueMove;
                            BoardField boardField = chessBoard.getFieldFromPoint(rowMove, colMove);
                            bestMove.setPrevMove(loopField);
                            bestMove.setNextMove(boardField);
                        }

                    }
                    chessBoard.setFieldFromOutsiteConfiguration(boardFields);
                }
            }
        }
        return bestMove;
    }

    private static int minimax(ChessBoard board, int depth, boolean isMax) {
        if(depth == 0 || isCheck(board))
            return CountScore.evaluate(board, depth);

        int bestValue = Integer.MIN_VALUE;
        if(isMax) {
            for(BoardField loopField : board.getAllFiguresThisTurn()) {
                boolean[][] legalMovesField = loopField.possibilityMovesFigure();

                for(int rowMove = 0; rowMove < 8; rowMove++) {
                    for(int colMove = 0; colMove < 8; colMove++) {
                        if(legalMovesField[rowMove][colMove]) {
                            ChessBoard chessBoardLoop = new ChessBoard();
                            chessBoardLoop.setFieldFromOutsiteConfiguration(board.getFields());
                            board.moveFigure(loopField.getX(), loopField.getY(), rowMove, colMove);

                            int value = minimax(board, depth-1, false);
                            bestValue = Math.max(value, bestValue);

                            board.setFieldFromOutsiteConfiguration(chessBoardLoop.getFields());
                        }
                    }
                }
            }
        } else {
            bestValue = Integer.MAX_VALUE;
            for(BoardField loopField : board.getAllFiguresThisTurn()) {
                boolean[][] legalMovesField = loopField.possibilityMovesFigure();

                for(int rowMove = 0; rowMove < 8; rowMove++) {
                    for(int colMove = 0; colMove < 8; colMove++) {
                        if(legalMovesField[rowMove][colMove]) {
                            ChessBoard chessBoardLoop = new ChessBoard();
                            chessBoardLoop.setFieldFromOutsiteConfiguration(board.getFields());

                            board.moveFigure(loopField.getX(), loopField.getY(), rowMove, colMove);

                            int value = minimax(board, depth-1, true);
                            bestValue = Math.min(value, bestValue);

                            board.setFieldFromOutsiteConfiguration(chessBoardLoop.getFields());
                        }
                    }
                }
            }
        }
        return bestValue;
    }

    private static boolean isCheck(ChessBoard board) {
        return board.testCheckMate(Color.WHITE) || board.testCheckMate(Color.BLACK) || board.testCheck(Color.WHITE) || board.testCheck(Color.BLACK);
    }
}
