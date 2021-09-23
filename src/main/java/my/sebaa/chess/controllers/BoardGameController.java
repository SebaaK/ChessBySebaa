package my.sebaa.chess.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import my.sebaa.chess.game.board.BoardField;
import my.sebaa.chess.game.board.ChessBoard;
import my.sebaa.chess.game.figure.Color;
import my.sebaa.chess.game.figure.Figure;
import my.sebaa.chess.game.ia.IAMove;
import my.sebaa.chess.game.ia.MiniMax;

public class BoardGameController {

    @FXML
    private GridPane chessGame;

    private final int SIZE = 100;
    private final String BLACK_COLOR = "-fx-background-color: black";
    private final String WHITE_COLOR = "-fx-background-color: white";
    private final String NORMAL_MOVE = "-fx-background-color: green";
    private final String KILL_MOVE = "-fx-background-color: red";
    private BoardField selectField = null;

    private ChessBoard chessBoard = new ChessBoard();

    public void initialize() {
        drawBoard();
    }

    private void drawBoard() {
        chessGame.getChildren().clear();

        if(chessBoard.getTurn() == Color.BLACK) {
            IAMove iaMove = MiniMax.execute(chessBoard, 0);
            int prevX = iaMove.getPrevMove().getX();
            int prevY = iaMove.getPrevMove().getY();
            chessBoard.moveFigure(prevX, prevY, iaMove.getNextMove().getX(), iaMove.getNextMove().getY());
        }

        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                Label label = new Label();

                if((row + col) % 2 == 0)
                    label.setStyle(BLACK_COLOR);
                else
                    label.setStyle(WHITE_COLOR);

                label.setGraphic(getImageField(row, col));

                addEvents(label);

                label.setPrefSize(SIZE, SIZE);
                chessGame.add(label, col, row);
            }
        }
    }

    private void addEvents(Label label) {
        label.setOnMouseClicked(this::mouseClicked);
    }

    @FXML
    private void mouseClicked(MouseEvent e) {
        Node source = (Node) e.getSource();
        int colIndex = chessGame.getColumnIndex(source).intValue();
        int rowIndex = chessGame.getRowIndex(source).intValue();

        boolean[][] figureMove = chessBoard.getPossibilityMoves(rowIndex, colIndex);

        if(selectField != null) {
            if(selectField != chessBoard.getFieldFromPoint(rowIndex, colIndex)) {
                chessBoard.moveFigure(selectField.getX(), selectField.getY(), rowIndex, colIndex);
            }
            selectField = null;
            drawBoard();
        } else if(figureMove != null) {
            if(chessBoard.getFigureFromPoint(rowIndex, colIndex).getColor() == Color.WHITE && chessBoard.getTurn() == Color.WHITE) {
                drawPossibilityMoves(figureMove);
                selectField = chessBoard.getFieldFromPoint(rowIndex, colIndex);
            }
        }
    }

    private void drawPossibilityMoves(boolean[][] moves) {
        chessGame.getChildren().clear();
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                Label label = new Label();

                if((row + col) % 2 == 0)
                    label.setStyle(BLACK_COLOR);
                else
                    label.setStyle(WHITE_COLOR);

                if(moves[row][col]) {
                    Figure figure = chessBoard.getFigureFromPoint(row, col);

                    if(figure != null) {
                        if(figure.getColor() != chessBoard.getTurn())
                            label.setStyle(KILL_MOVE);
                    } else
                        label.setStyle(NORMAL_MOVE);
                }

                label.setGraphic(getImageField(row, col));

                addEvents(label);

                label.setPrefSize(SIZE, SIZE);
                chessGame.add(label, col, row);
            }
        }
    }

    private ImageView getImageField(int row, int col) {
        Figure figure = chessBoard.getFigureFromPoint(row, col);
        if(figure == null)
            return null;

        ImageView imageView = new ImageView(figure.getImageFigure());
        imageView.setFitHeight(SIZE);
        imageView.setFitWidth(SIZE);

        return imageView;
    }
}
