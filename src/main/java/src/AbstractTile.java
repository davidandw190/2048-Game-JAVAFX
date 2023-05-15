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

    /**
     * Constructor:
     * - Creates a rectangle and a label and adds it to the Tile.
     * - Adds the style class "tile" to the tile.
     */
    protected AbstractTile() {
        square = new Rectangle(WIDTH, HEIGHT);
        square.getStyleClass().add("tile");       // adds a CSS class named "tile" to the square object
        this.getChildren().addAll(square);        // adds 'square' to the child nodes of the 'Abstract Tile' class
    }





}
