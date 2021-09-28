package my.sebaa.chess.game.ia;

import my.sebaa.chess.game.board.BoardField;

public class IAMove {
    private BoardField prevMove;
    private BoardField nextMove;

    public int getPrevX() {
        return prevMove.getX();
    }

    public int getPrevY() {
        return prevMove.getY();
    }

    public int getNextX() {
        return nextMove.getX();
    }

    public int getNextY() {
        return nextMove.getY();
    }

    public void setPrevMove(BoardField prevMove) {
        this.prevMove = prevMove;
    }

    public void setNextMove(BoardField nextMove) {
        this.nextMove = nextMove;
    }
}
