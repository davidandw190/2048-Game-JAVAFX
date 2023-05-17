package src;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableList;
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

    public void moved(Direction key) {
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

    private ObservableList<Slot> getSlotsWithEmptyTiles() {
        return null;
    }




}
