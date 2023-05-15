package src;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Game extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Board board = new Board();
    }

    public static void main(String[] args) {
        launch();
    }
}