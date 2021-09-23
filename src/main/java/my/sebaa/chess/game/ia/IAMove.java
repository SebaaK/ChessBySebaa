package my.sebaa.chess.game.ia;

import my.sebaa.chess.game.board.BoardField;

public class IAMove {
    private BoardField prevMove;
    private BoardField nextMove;

    public BoardField getPrevMove() {
        return prevMove;
    }

    public BoardField getNextMove() {
        return nextMove;
    }

    public void setPrevMove(BoardField prevMove) {
        this.prevMove = prevMove;
    }

    public void setNextMove(BoardField nextMove) {
        this.nextMove = nextMove;
    }
}
