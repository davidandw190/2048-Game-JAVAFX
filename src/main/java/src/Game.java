package src;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Game extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage window) throws Exception {


        /* The Stage is the window. The Scene is what happens inside the window. The scene has a child node of type Group called root. */

        Board board = new Board();

        StackPane sp = new StackPane(board);
        Group root = new Group(); //root for all the children nodes to be added to the scene

        root.getChildren().addAll(sp);

        final int WIDTH = 503;
        final int HEIGHT = 503;
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        window.setScene(scene);
        scene.getStylesheets().add("stylesheet.css"); //adds the styles in stylesheet.css to game

        scene.setOnKeyPressed(ke -> board.moved(ke));

        board.setOnSwipeUp(se -> board.moved(Direction.UP));
        board.setOnSwipeDown(se -> board.moved(Direction.DOWN));
        board.setOnSwipeLeft(se -> board.moved(Direction.LEFT));
        board.setOnSwipeRight(se -> board.moved(Direction.RIGHT));





        window.setTitle("2048 | Java");

        window.setResizable(false);

        window.show();
    }
}
