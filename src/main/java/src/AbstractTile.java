package src;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 * The template for all the tile types in the game
 */
public abstract class AbstractTile extends StackPane {

    protected static final double WIDTH = 100.0;
    protected static final double HEIGHT = 100.0;
    protected Rectangle square;

    protected AbstractTile() {
        square = new Rectangle(WIDTH, HEIGHT);
        square.getStyleClass().add("tile");
        this.getChildren().addAll(square);
    }

}
