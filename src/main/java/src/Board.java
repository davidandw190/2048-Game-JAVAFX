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


    private ObservableList<Slot> getSlotsWithEmptyTiles() {
        ObservableList<Slot> emptySlots = getAllSlots();

        for (int i=0; i<emptySlots.size(); i++) {
            if (emptySlots.get(i).tileValue() != 0) {
                emptySlots.remove(i--);
            }
        }

        return emptySlots;
    }

    private ObservableList<Slot> getAllSlots() {
        ObservableList<Slot> allSlots = FXCollections.observableArrayList();
        for (Node node : this.getChildren()) {
            allSlots.add((Slot) node);
        }

        return allSlots;
    }

    public void moved(KeyEvent key) {
        switch (key.getCode()) {
            case UP:
                moved(UP);
                break;
            case DOWN:
                moved(DOWN);
                break;
            case LEFT:
                moved(LEFT);
                break;
            case RIGHT:
                moved(Direction.RIGHT);
                break;
            case O:
                gameOver();
                break;
            default:
                break;
        }
    }

    public Tile[][] getTileArray() {
        return tileArray;
    }


}
