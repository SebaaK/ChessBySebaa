package my.sebaa.chess.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstViewController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button startGame;

    public void getChecked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/BoardGame.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {

    }

    public void endButton() {
        Platform.exit();
        System.exit(0);
    }

}
