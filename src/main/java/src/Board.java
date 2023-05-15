package src;

import javafx.scene.layout.TilePane;

public class Board extends TilePane {

    final int ROWS = 4;
    final int COLUMNS = 4;
    Tile[][] tileArray = new Tile[ROWS][COLUMNS];
    Score score = new Score();          // To be implemented later

    /**
     * Constructor:
     * - Builds the graphical components of the board
     */
    public Board() {

        addSlots();

        this.getStyleClass().add("board");

        initializeBoard();

    }

    /**
     * Fills the board with Slots.
     */
    private void addSlots() {
        final int NUM_OF_TILES = 16;

        for (int i=0; i < NUM_OF_TILES; i++) {
            addSlot(new Slot());
        }
    }

    private void addSlot(Slot slot) {
    }


    private void initializeBoard() {
    }


}
