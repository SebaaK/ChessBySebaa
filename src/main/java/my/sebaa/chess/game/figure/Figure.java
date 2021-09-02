package my.sebaa.chess.game.figure;

import my.sebaa.chess.game.board.BoardField;

public abstract class Figure {

    Color color;
    private String name;
    private BoardField boardField;
    private String imageFigure;

    public Figure(String name, Color color) {
        this.name = name;
        this.color = color;
//        this.boardField = boardField;

        imageFigure = "/pawns/" + name + "_" + color.getName() + ".png";
    }

    public String getImageFigure() {
        return imageFigure;
    }
}
