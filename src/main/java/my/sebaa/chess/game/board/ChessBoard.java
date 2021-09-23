package my.sebaa.chess.game.board;

import my.sebaa.chess.game.figure.*;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    private BoardField[][] fields = new BoardField[8][8];
    private int currentTurn = 0;

    public ChessBoard() {
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                fields[row][col] = new BoardField(this, row, col);
            }
        }
        defaultViewBoard();
    }

    private void setFigureOnField(int row, int col, Figure figure){
        if(!isOutsiteBoard(row, col))
            fields[row][col].setFigure(figure);
    }

    //TODO: Może coś inaczej?
    public boolean moveFigure(int row, int col, int moveToX, int moveToY) {
        Figure figure = getFigureFromPoint(row, col);
        if(figure == null)
            return false;
        boolean[][] moves = getPossibilityMoves(row, col);
        if(moves[moveToX][moveToY]) {
            setFigureOnField(moveToX, moveToY, figure);
            fields[row][col].setFigure(null);
            currentTurn++;
            return true;
        }
        return false;
    }

    public boolean[][] getPossibilityMoves(int row, int col) {
        Figure figure = getFigureFromPoint(row, col);
        if(figure != null)
            return fields[row][col].possibilityMovesFigure();
        else
            return null;
    }

    public void drawBoard() {
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                System.out.print("|");

                BoardField field = fields[row][col];
                if(field.getFigure() != null)
                    if(field.getFigure().getColor() == Color.WHITE)
                        System.out.print(field.getFigure().getName().toUpperCase().charAt(0));
                    else
                        System.out.print(field.getFigure().getName().charAt(0));
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void drawBoardWithMoves(boolean[][] moves) {
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                System.out.print("|");

                BoardField field = fields[row][col];
                if(field.getFigure() != null && moves[row][col])
                    System.out.print("X");
                else if(field.getFigure() != null)
                    System.out.print(field.getFigure().getName().toUpperCase().charAt(0));
                else if(moves[row][col]) {
                    System.out.print("@");
                } else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    public Figure getFigureFromPoint(int row, int col) {
        if(!isOutsiteBoard(row, col)) {
            Figure figure = fields[row][col].getFigure();
            return figure;
        }

        return null;
    }

    public BoardField getFieldFromPoint(int row, int col) {
        if(!isOutsiteBoard(row, col)) {
            BoardField boardField = fields[row][col];
            return boardField;
        }
        return null;
    }

    private void defaultViewBoard() {
        for(int i = 0; i < 8; i++){
            fields[0][i].setFigure(defaultSetFirstRow(Color.BLACK).get(i));
            fields[1][i].setFigure(new Pawn(Color.BLACK));

            fields[7][i].setFigure(defaultSetFirstRow(Color.WHITE).get(i));
            fields[6][i].setFigure(new Pawn(Color.WHITE));
        }
    }

    private boolean isOutsiteBoard(int row, int col){
        if(row < 0 || row > 7 || col < 0 || col > 7 )
            //TODO: Throw exceptions
//            throw new
            return true;

        return false;
    }

    private List<Figure> defaultSetFirstRow(Color color) {
        List<Figure> figures = new ArrayList<>();
        figures.add(new Tower(color));
        figures.add(new Horse(color));
        figures.add(new Bishop(color));
        figures.add(new Queen(color));
        figures.add(new King(color));
        figures.add(new Bishop(color));
        figures.add(new Horse(color));
        figures.add(new Tower(color));
        return figures;
    }

    public boolean testCheck(Color color) {
        List<BoardField> opponents = new ArrayList<>(searchColorsFigures(color));

        BoardField king = getKing(color);

        for(BoardField loop : opponents) {
            boolean[][] opponentMove = loop.possibilityMovesFigure();

            if(opponentMove[king.getX()][king.getY()])
                return true;
        }

        return false;
    }

    public boolean testCheckMate(Color color) {
        if(!testCheck(color))
            return false;

        final int xPosKing = getKing(color).getX();
        final int yPosKing = getKing(color).getY();
        fields[xPosKing][yPosKing].setFigure(null);

        boolean[][] allMovesOpponents = new boolean[8][8];
        List<BoardField> opponents = new ArrayList<>(searchColorsFigures(color));

        for(BoardField loop : opponents) {
            boolean[][] loopPossibilityMove = loop.possibilityMovesFigure();

            for(int row = 0; row < 8; row++) {
                for(int col = 0; col < 8; col++) {
                    if(loopPossibilityMove[row][col])
                        allMovesOpponents[row][col] = true;
                }
            }
        }

        fields[xPosKing][yPosKing].setFigure(new King(color));

        int possibilityMovesKing = 0;
        boolean[][] kingMoves = getKing(color).possibilityMovesFigure();
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                if(kingMoves[row][col]) {
                    if(!allMovesOpponents[row][col])
                        possibilityMovesKing++;
                }
            }
        }

        if(possibilityMovesKing > 0)
            return false;
        else
            return true;
    }

    public Color getTurn() {
        if(currentTurn % 2 != 0)
            return Color.BLACK;
        else
            return Color.WHITE;
    }

    private List<BoardField> searchColorsFigures(Color color) {
        List<BoardField> colorFigures = new ArrayList<>();

        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                BoardField loop = fields[row][col];

                if(loop.getFigure() != null) {
                    if(loop.getFigure().getColor() != color)
                        colorFigures.add(loop);
                }
            }
        }

        return colorFigures;
    }

    private BoardField getKing(Color color) {
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col ++) {
                BoardField field = fields[row][col];
                if(field.getFigure() != null) {
                    if(field.getFigure().getName() == "king" && field.getFigure().getColor() == color)
                        return field;
                }
            }
        }
        return null;
    }

    public ArrayList<BoardField> getAllFiguresThisTurn() {
        ArrayList<BoardField> legalMoves = new ArrayList<>();

        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                Figure figure = fields[row][col].getFigure();
                if(figure != null) {
                    if(figure.getColor() == getTurn())
                        legalMoves.add(fields[row][col]);
                }
            }
        }

        return legalMoves;
    }
}
