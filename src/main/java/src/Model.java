package src;

import java.util.Random;

public class Model {

    // dimension of the game board
    final static int ROWS = 4;
    final static int COLUMNS = 4;

    // the int matrix representing the board, each elem storing the value of the tile
    static int[][] board = new int[ROWS][COLUMNS];

    /**
     * Constructor:
     * - adds two random tiles to the board
     */
    public Model() {
        for (int i = 0; i < 2; i++) {
            addNewLogicalTile();
        }
    }

    /**
     * Entry point for moving the tiles in the specified direction.
     * @param logicBoard the 2D array containing the logical tiles
     * @param direction enum value representing the desired direction of movement
     * @returns result of the movement( if it was possible or not)
     */
    static boolean move(Tile[][] logicBoard, Direction direction) {
        return switch (direction) {
            case DOWN -> moveDown(logicBoard);
            case UP -> moveUp(logicBoard);
            case LEFT -> moveLeft(logicBoard);
            case RIGHT -> moveRight(logicBoard);
        };
    }

    static boolean moveLeft(Tile[][] logicBoard) {
        System.out.println("Before move:");
        printBoard(logicBoard);
        System.out.println();
        boolean shifted = shiftBoard(logicBoard);
        System.out.println("After move:");
        printBoard(logicBoard);
        System.out.println();
        return shifted;
    }

    /**
     * The 'moveDown()', 'moveRight()', and 'moveUp()' methods use the
     * 'rotateLogicBoard()' method to rotate the logic board 90 degrees
     * clockwise multiple times before and after calling the 'shiftBoard()' method.
     * ------------------------------------------------
     * The purpose of rotating the board is to simplify the implementation of
     * tile movements in those directions.
     */
    static boolean moveDown(Tile[][] logicBoard) {
        boolean shifted;
        logicBoard = rotateLogicBoard(logicBoard);
        assert logicBoard != null;
        shifted = shiftBoard(logicBoard);
        logicBoard = rotateLogicBoard(logicBoard);
        logicBoard = rotateLogicBoard(logicBoard);
        logicBoard = rotateLogicBoard(logicBoard);
        return shifted;
    }

    static boolean moveRight(Tile[][] logicBoard) {
        boolean shifted;
        logicBoard = rotateLogicBoard(logicBoard);
        logicBoard = rotateLogicBoard(logicBoard);
        assert logicBoard != null;
        shifted = shiftBoard(logicBoard);
        logicBoard = rotateLogicBoard(logicBoard);
        logicBoard = rotateLogicBoard(logicBoard);
        return shifted;
    }

    static boolean moveUp(Tile[][] logicBoard) {
        boolean shifted;
        logicBoard = rotateLogicBoard(logicBoard);
        logicBoard = rotateLogicBoard(logicBoard);
        logicBoard = rotateLogicBoard(logicBoard);
        assert logicBoard != null;
        shifted = shiftBoard(logicBoard);
        logicBoard = rotateLogicBoard(logicBoard);
        return shifted;
    }

    /**
     * Checks if there is any possibility of merging two tiles on the board.
     * It iterates over each row and column of the logicBoard and compares the values of adjacent tiles.
     * If any pair of adjacent tiles has the same value, it sets check to true.
     * @returns the boolean value of the check
     */
    static boolean checkPossibleMerge(Tile[][] logicBoard) {
        boolean check = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (logicBoard[i][j].getValue() == logicBoard[i][j + 1].getValue()) {
                    check = true;
                    System.out.println(check);
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (logicBoard[j][i].getValue() == logicBoard[j + 1][i].getValue()) {
                    check = true;
                    System.out.println(check);
                }
            }
        }

        return check;
    }


    /**
     * Adds a new logical tile to the game board.
     */
    private void addNewLogicalTile() {
        int[][] emptyTiles = getEmptyTiles(); // returns a 2D array containing the coordinates of all the empty tiles
        if (emptyTiles.length > 0) {
            Random rand = new Random();
            int randNum = rand.nextInt(emptyTiles.length);

            int row = emptyTiles[randNum][0];  // The row and column of the selected empty tile
            int column = emptyTiles[randNum][1]; // are extracted

            board[row][column] = newValue();
        }

    }

    /**
     * @returns a 2D array with the coordinates of all the empty tiles on the game board.
     */
    private int[][] getEmptyTiles() {
        int i = 0;
        int[][] emptyArray = new int[16][2];
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (board[row][column] == 0) {
                    emptyArray[i] = new int[]{row, column};
                }
                i++;
            }
        }
        int[][] emptyTiles = new int[i][2];
        System.arraycopy(emptyArray, 0, emptyTiles, 0, i);

        return emptyTiles;
    }

    private int newValue() {
        if (Math.random() < 0.9) {
            return 2;
        }
        else {
            return 4;
        }

    }

    /**
     * Shifts the tiles on the board in the specified direction.
     * Iterates over each row of the `logicBoard` and calls the `mergeRow()` method
     * to merge similar values and shift them towards the respective wall.
     * If any tiles were merged, the boardShifted return variable is set to true.
     */
    private static boolean shiftBoard(Tile[][] logicBoard) {
        boolean boardShifted = false;
        for (Tile[] row : logicBoard) {
            if (mergeRow(row)) {
                boardShifted = true;
            }
        }
        return boardShifted;
    }

    /**
     * Merges similar values and shifts to the respective wall.
     * It iterates over the row from left to right and checks each tile's value.
     */
    private static boolean mergeRow(Tile[] row) {

        // used to track the pos where the curr tile should be moved and the stopping point
        boolean merged = false;
        int targetPosition = -1, stop = 0;

        for (int i = 1; i < row.length; i++) {
            if (row[i].getValue() != 0) {
                // if the val of curr tile is not zero, we determine the appropriate pos for the tile
                targetPosition = findTargetPosition(row, i, stop);
                if (targetPosition != i && targetPosition != -1) {
                    // if a valid pos exists, we perform the necessary updates and set `merged` true
                    updateTiles(row, i, targetPosition);
                    merged = true;
                }
            }
        }
        return merged;
    }

    /**
     * Responsible for finding the target position for the current tile
     */
    private static int findTargetPosition(Tile[] row, int currentIndex, int stop) {
        int targetPosition = -1; // an invalid target position
        for (int j = currentIndex - 1; j >= stop; j--) {
            if (row[j].getValue() == 0) {
                if (j == 0 || j == stop) {
                    targetPosition = j;
                    stop = j;
                    break;
                }
            } else {
                if (row[j].getValue() == row[currentIndex].getValue()) {
                    targetPosition = j;
                    stop = j + 1;
                } else {
                    targetPosition = j + 1;
                    stop = targetPosition;
                }
                break;
            }
        }
        return targetPosition;
    }

    /**
     * Handles the update and merging of the tiles based on the target position.
     */
    private static void updateTiles(Tile[] row, int currentIndex, int targetPosition) {
        if (row[targetPosition].getValue() != 0) {
            // if tile at target pos not emoty, a merge will occur
            row[targetPosition].wasCombinated();
        }
        row[targetPosition].setValue(row[currentIndex].getValue() + row[targetPosition].getValue());
        row[currentIndex].setTransition(row[currentIndex].getTransition() + Math.abs(targetPosition - currentIndex));
        row[currentIndex].setValue(0);
    }


    private static Tile[][] rotateLogicBoard(Tile[][] logicBoard) {
        int i = board.length;
        int j = board[0].length;

        var rotatedArray = new Tile[i][j];
        for(int r = 0; r < i; r++) {
            for (int c = 0; c < j; c++) {
                rotatedArray[c][i - 1 - r] = logicBoard[r][c];
            }
        }
        return rotatedArray;
    }

    private static void printBoard(Tile[][] logicBoard) {
        System.out.println();
        for (Tile[] row : logicBoard) {
            for (Tile j: row) {
                System.out.print("["+j.getValue() + "] "+j.getTransition() + " | ");
            }

            System.out.println();
        }
    }

}


//    private static boolean mergeRow(Tile[] row) {
//        boolean merged = false;
//        int targetPosition = -1, stop = 0;
//
//        for (int i = 1; i < row.length; i++) {   // starting from the 2nd tile since there are
//            if (row[i].getValue() != 0) {          // no tiles to the left of the 1st tile
//                for (int j = i - 1; j >= stop; j--) {
//
//                    if (row[j].getValue() == 0) {
//
//                        if (j == 0 || j == stop) {
//                            targetPosition = j;
//                            stop = j;
//                            break;
//                        }
//
//                    } else {
//                        if (row[j].getValue() == row[i].getValue()) {
//                            targetPosition = j;
//                            stop = j + 1;
//
//                        } else {
//                            targetPosition = j + 1;
//                            stop = targetPosition;
//                        }
//                        break;
//                    }
//                }
//
//                if (targetPosition != i && targetPosition != -1) {
//
//                    if (row[targetPosition].getValue() != 0) {
//                        row[targetPosition].wasCombinated();
//                    }
//
//                    row[targetPosition].setValue(row[i].getValue() + row[targetPosition].getValue());
//                    row[i].setTransition(row[i].getTransition() + Math.abs(targetPosition - i));
//                    row[i].setValue(0);
//
//                    targetPosition = -1;
//                    merged = true;
//                }
//            }
//        }
//        return merged;
//
//    }
