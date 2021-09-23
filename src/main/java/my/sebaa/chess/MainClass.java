package my.sebaa.chess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainClass extends Application {

    private Stage stage = new Stage();

    @Override
    public void start(Stage stage) throws IOException {
        extracted();
    }

    public void extracted() throws IOException {
        GridPane fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/BoardGame.fxml")).load();
        Scene scene = new Scene(fxmlLoader);
        stage.setTitle("Chess by Sebaa");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
