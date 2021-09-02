package my.sebaa.chess.game.board;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import my.sebaa.chess.game.figure.Color;
import my.sebaa.chess.game.figure.Figure;
import my.sebaa.chess.game.figure.Pawn;

public class BoardField extends Label {

    private final String BLACK_COLOR = "-fx-background-color: black";
    private final String WHITE_COLOR = "-fx-background-color: white";

    private int x;
    private int y;
    private ChessBoard chessBoard;
    private Figure figure;
    
    public BoardField(ChessBoard chessBoard, int x, int y) {
        this.chessBoard = chessBoard;
        this.x = x;
        this.y = y;

        if((x + y) % 2 == 0)
            setStyle(BLACK_COLOR);
        else
            setStyle(WHITE_COLOR);

        setOnDragDropped(event -> {
            System.out.println(event.getTransferMode());
            System.out.println("DROPED");
        });
        setOnDragDetected(event -> {
            System.out.println("Detected");
        });
        setOnDragDone(event -> {
            System.out.println("DONE");
        });

        setOnMouseClicked(event -> getMouseClicked());
        setPrefSize(100, 100);
    }

    private void setMouseOver() {
        System.out.println(x + "  " + y);
    }

    private void setMouseDragged(DragEvent event) {
            System.out.println("S");
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
        ImageView imageView = new ImageView(figure.getImageFigure());
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        setGraphic(imageView);
    }

    private void getMouseClicked() {
        Pawn pawn = new Pawn(Color.BLACK);
        ImageView imageView = new ImageView(pawn.getImageFigure());
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        setGraphic(imageView);
    }
}
