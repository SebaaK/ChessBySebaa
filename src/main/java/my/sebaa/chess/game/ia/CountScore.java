package my.sebaa.chess.game.ia;

import my.sebaa.chess.game.board.ChessBoard;
import my.sebaa.chess.game.figure.Color;
import my.sebaa.chess.game.figure.Figure;
import my.sebaa.chess.game.figure.Tower;

import java.util.LinkedList;

public class CountScore {

    private final static int CHECK_MATE_BONUS = 10000;
    private final static int CHECK_BONUS = 145;
    private final static int CASTLE_BONUS = 25;

    public static int evaluate(ChessBoard chessBoard, int depth) {
        return score(chessBoard, Color.WHITE, depth) - score(chessBoard, Color.BLACK, depth);
    }

    private static int score(ChessBoard chessBoard, Color color, int depth) {
        return towers(chessBoard, color) +
                isCheck(chessBoard, color) +
                isCheckMat(chessBoard, color) +
                figuresOnBoard(chessBoard, color);
    }

    private static int towers(final ChessBoard chessBoard, final Color color) {
        int towersCount = searchingFiguresInBoard(color, new Tower(color), chessBoard);
        return towersCount >= 1 ? CASTLE_BONUS * towersCount : 0;
    }

    private static int isCheck(ChessBoard chessBoard, Color color) {
        return chessBoard.testCheck(color) ? CHECK_BONUS : 0;
    }

    private static int isCheckMat(ChessBoard chessBoard, Color color) {
        return chessBoard.testCheckMate(color) ? CHECK_MATE_BONUS : 0;
    }

    private static int figuresOnBoard(ChessBoard chessBoard, Color color) {
        LinkedList<Figure> figures = new LinkedList<>(searchingColorFiguresInBoard(chessBoard, color));
        int totalPoints = figures.stream()
                .map(t -> pointsForFigure(t))
                .reduce(0, Integer::sum);

        return totalPoints;
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

    private static int pointsForFigure(Figure figure) {
        switch (figure.getName()) {
            case "pawn" :
                return 10;
            case "horse" :
            case "bishop":
                return 30;
            case "tower" :
                return 50;
            case "queen" :
                return 90;
            case "king":
                return 900;
        }
        return 0;
    }

    private static LinkedList<Figure> searchingColorFiguresInBoard(ChessBoard chessBoard, Color color) {
        LinkedList<Figure> figures = new LinkedList<>();

        //TODO: Stream!?
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                Figure figure = chessBoard.getFigureFromPoint(row, col);
                if(figure != null) {
                    if(figure.getColor() == color)
                        figures.add(figure);
                }
            }
        }

        return figures;
    }
}
