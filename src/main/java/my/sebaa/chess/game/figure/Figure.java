package my.sebaa.chess.game.figure;

import my.sebaa.chess.game.board.BoardField;

public abstract class Figure {

    Color color;
    private String name;
    private String imageFigure;

    public Figure(String name, Color color) {
        this.name = name;
        this.color = color;

        imageFigure = "/pawns/" + name + "_" + color.getName() + ".png";
    }

    public String getImageFigure() {
        return imageFigure;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public abstract boolean[][] getPossibilityMoves(BoardField boardField);

    public boolean canMove(int row, int col) {
        if(row >= 8 || col >= 8)
            return false;

        if(row < 0 || col < 0)
            return false;

        return true;
    }

    public boolean[][] generateHorizontalMove(int posXofFigure, int posYofFigure, boolean[][] moves, BoardField boardField) {
        if(!canMove(posXofFigure, posYofFigure)) {
            //TODO: Throw exceptions
            return null;
        }

        //left
        int temporaryY = posYofFigure - 1;
        while(canMove(posXofFigure, temporaryY)) {
            Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(posXofFigure, temporaryY);
            if(temporaryFigure == null)
                moves[posXofFigure][temporaryY] = true;
            else {
                if(temporaryFigure.getColor() != color)
                    moves[posXofFigure][temporaryY] = true;
                break;
            }
            temporaryY--;
        }

        //right
        temporaryY = posYofFigure + 1;
        while(canMove(posXofFigure, temporaryY)) {
            Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(posXofFigure, temporaryY);
            if(temporaryFigure == null)
                moves[posXofFigure][temporaryY] = true;
            else {
                if(temporaryFigure.getColor() != this.getColor()) {
                    moves[posXofFigure][temporaryY] = true;
                }
                break;
            }
            temporaryY++;
        }

        return moves;
    }

    public boolean[][] generateVerticalMoves(int posXofFigure, int posYofFigure, boolean[][] moves, BoardField boardField) {
        if(!canMove(posXofFigure, posYofFigure)) {
            //TODO: throw exception
            return null;
        }

        //up
        int temporaryX = posXofFigure - 1;
        while(canMove(temporaryX, posYofFigure)) {
            Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(temporaryX, posYofFigure);
            if(temporaryFigure == null)
                moves[temporaryX][posYofFigure] = true;
            else {
                if(temporaryFigure.getColor() != this.getColor()) {
                    moves[temporaryX][posYofFigure] = true;
                }
                break;
            }
            temporaryX--;
        }

        //down
        temporaryX = posXofFigure + 1;
        while(canMove(temporaryX, posYofFigure)) {
            Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(temporaryX, posYofFigure);

            if(temporaryFigure == null)
                moves[temporaryX][posYofFigure] = true;
            else {
                if(temporaryFigure.getColor() != this.getColor()) {
                    moves[temporaryX][posYofFigure] = true;
                }
                break;
            }
            temporaryX++;
        }

        return moves;
    }

    public boolean[][] generateMovesTypeX(int posXofFigure, int posYofFigure, boolean[][] moves, BoardField boardField) {

        //up - left
        int temporaryX = posXofFigure - 1;
        int temporaryY = posYofFigure - 1;
        while(canMove(temporaryX, temporaryY)) {
            Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(temporaryX, temporaryY);
            if(temporaryFigure == null)
                moves[temporaryX][temporaryY] = true;
            else {
                if(temporaryFigure.getColor() != this.getColor())
                    moves[temporaryX][temporaryY] = true;
                break;
            }
            temporaryX--;
            temporaryY--;
        }

        //up - right
        temporaryX = posXofFigure - 1;
        temporaryY = posYofFigure + 1;
        while(canMove(temporaryX, temporaryY)) {
            Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(temporaryX, temporaryY);
            if(temporaryFigure == null)
                moves[temporaryX][temporaryY] = true;
            else {
                if(temporaryFigure.getColor() != this.getColor())
                    moves[temporaryX][temporaryY] = true;
                break;
            }
            temporaryX--;
            temporaryY++;
        }

        //down - left
        temporaryX = posXofFigure + 1;
        temporaryY = posYofFigure - 1;
        while(canMove(temporaryX, temporaryY)) {
            Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(temporaryX, temporaryY);
            if(temporaryFigure == null)
                moves[temporaryX][temporaryY] = true;
            else {
                if(temporaryFigure.getColor() != this.getColor())
                    moves[temporaryX][temporaryY] = true;
                break;
            }
            temporaryX++;
            temporaryY--;
        }

        //down - right
        temporaryX = posXofFigure + 1;
        temporaryY = posYofFigure + 1;
        while(canMove(temporaryX, temporaryY)) {
            Figure temporaryFigure = boardField.getChessBoard().getFigureFromPoint(temporaryX, temporaryY);
            if(temporaryFigure == null)
                moves[temporaryX][temporaryY] = true;
            else {
                if(temporaryFigure.getColor() != this.getColor())
                    moves[temporaryX][temporaryY] = true;
                break;
            }
            temporaryX++;
            temporaryY++;
        }

        return moves;
    }
}
