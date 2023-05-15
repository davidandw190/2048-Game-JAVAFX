package src;

import javafx.scene.Node;

public class Slot extends AbstractTile{

    Tile tile = new Tile();

    public Slot() {

        super();

        square.getStyleClass().add("tile-empty");
        this.getChildren().add(tile);

    }

    /**
     * Creates a new tile for after a tile was moved
     */
    public void newTile() {

        this.getChildren().remove(tile);

        if  (tileValue() == 0) {
            tile = new Tile();
        } else {
            tile = new Tile(tileValue());
        }

        this.getChildren().add(tile);

    }

    /**
     * Checks if a slot contains a tile
     * @return true of slot contains tile, else false
     */
    public boolean containsTile() {

        for (Node node : this.getChildren()) {
            if (node instanceof Tile) {
                return true;
            }
        }
        return false;

    }

    /**
     * Returns the value of a tile  living inside  a slot
     * @return the value of the tile or -1 if something went wrong
     */
    public int tileValue() {
        return tile.getValue();

    }

    /**
     * Adds a new tile to the slot
     * @param tile to be added
     */
    public void add(Tile tile) {
        this .getChildren().add(tile);
    }


    public Tile getTile() {
        return tile;
    }

    public void newTileValue() {

    }
}
