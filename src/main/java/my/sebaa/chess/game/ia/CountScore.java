package my.sebaa.chess.game.ia;

import my.sebaa.chess.game.board.ChessBoard;
import my.sebaa.chess.game.figure.Color;
import my.sebaa.chess.game.figure.Figure;
import my.sebaa.chess.game.figure.Tower;

import java.util.Random;

public class CountScore {

    private final static int CHECK_MATE_BONUS = 10000;
    private final static int CHECK_BONUS = 45;
    private final static int CASTLE_BONUS = 25;
    private final static int MOBILITY_MULTIPLIER = 5;
    private final static int ATTACK_MULTIPLIER = 1;
    private final static int TWO_BISHOPS_BONUS = 25;

    public static int evaluate(ChessBoard chessBoard, int depth) {
        return score(chessBoard, Color.WHITE, depth) - score(chessBoard, Color.BLACK, depth);
    }

    private static int score(ChessBoard chessBoard, Color color, int depth) {
        Random random = new Random();

        int rdm = random.nextInt() * 100;
        System.out.println(rdm);
        return rdm;

        //return towers(chessBoard, color);
    }

    private static int towers(final ChessBoard chessBoard, final Color color) {
        int towersCount = searchingFiguresInBoard(color, new Tower(color), chessBoard);
        return towersCount >= 1 ? CASTLE_BONUS * towersCount : 0;
    }

    private static int searchingFiguresInBoard(Color color, Figure figure, ChessBoard chessBoard) {
        int figureCount = 0;

        //TODO: Stream!?
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                Figure fig = chessBoard.getFigureFromPoint(row, col);
                if(fig != null) {
                    if(fig.getName() == figure.getName() && fig.getColor() == color) {
                        figureCount++;
                    }
                }
            }
        }

        return figureCount;
    }
}
