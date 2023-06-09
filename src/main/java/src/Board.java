package src;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.TilePane;

import java.util.Random;

import static javafx.scene.input.KeyCode.O;
import static src.Direction.*;

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

    public void moved(KeyEvent key) {
        switch (key.getCode()) {
            case UP -> moved(UP);
            case DOWN -> moved(DOWN);
            case LEFT -> moved(LEFT);
            case RIGHT -> moved(Direction.RIGHT);
            case O -> gameOver();
            default -> {
            }
        }
    }

    /**
     * Handles all moves, by sending the `tileArray` to the `Model`, plus the direction.
     * If the model returns true, it loops through the array of tiles, finds the transitions,
     * and adds them to the `TileAnimation` transition array.
     * Then, it calls the `playAnimations` method
     */
    protected void moved(Direction direction)
    {
        if ( Model.move(tileArray, direction))
            for (Tile[] row : tileArray)
                for (Tile tile : row)
                    if (tile.getTransition() > 0) {
                        TileAnimation.moveTile(tile, tile.getTransition(), direction);
                        tile.setTransition(0);
                    }

        TileAnimation.playAnimations();

    }


    public Tile[][] getTileArray() {
        return tileArray;
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
        this.getChildren().add(slot);

    }

    private void initializeBoard() {
        for (int i = 0; i < 2; i++) {
            addNewTile();
        }
    }

    /**
     * Adds a new tile to the board.
     * If there's only one slot left, adds a tile and `checkIfOtherMoveAvailable()`
     */
    private void addNewTile() {
        ObservableList<Slot> emptySlots = getSlotsWithEmptyTiles();

        //assert emptySlots != null;
        int numOfEmptySlots = emptySlots.size();

        if (numOfEmptySlots > 1) {
            Random random = new Random();
            int randomSlot = random.nextInt(numOfEmptySlots);
            // add tile appear animation here

        } else {
            emptySlots.get(0).newTileValue();
            checkIfOtherMoveAvailable();
        }

    }

    private void checkIfOtherMoveAvailable() {
        if (!Model.checkPossibleMerge(tileArray))
        {
            gameOver();
        }
    }

    private void gameOver() {

    }


    private ObservableList<Slot> getSlotsWithEmptyTiles() {

        ObservableList<Slot> emptySlots = getAllSlots();

        for (int i=0; i<emptySlots.size(); i++) {
            if (emptySlots.get(i).tileValue() != 0) {
                emptySlots.remove(i--);
            }
        }

        return emptySlots;
    }

    /**
     * Gets all the nodes of the board cast them into Slots and returns them as an ObservableList
     * @returns all the Slot nodes of the board
     */
    private ObservableList<Slot> getAllSlots() {

        ObservableList<Slot> allSlots = FXCollections.observableArrayList();

        for (Node node : this.getChildren()) {
            allSlots.add((Slot) node);
        }

        return allSlots;
    }

    /**
     * Transfers all the nodes from the game board into a 2D array to
     * facilitate traversal and comparison.
     */
    private void addTilesToArray() {
        ObservableList<Slot> slots = getAllSlots();

        for (int i=0; i < slots.size(); i++) {
            Slot slot = slots.get(i);
            Tile tile = slot.getTile();

            int row;

            if (i < 4) {
                row = 0;
            } else if (i < 8) {
                row = 1;
            } else if (i < 12) {
                row = 2;
            } else {
                row = 3;
            }

            int column = i % 4;
            tileArray[row][column] = tile;
        }
    }

}
