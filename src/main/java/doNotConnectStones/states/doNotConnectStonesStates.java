package doNotConnectStones.states;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * This is the Class which will represent the states of the game.
 */
@Data
@Slf4j
public class doNotConnectStonesStates {

    private int arraySize = 5;
    private char[][] gameBoard = new char[arraySize][arraySize];

    /**
     * This is Constructor for the {@code doNotConnectStates} class.
     */
    public doNotConnectStonesStates(){
        log.debug("Model of the Board Initialized....");
        for (int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j < gameBoard.length; j++){
                gameBoard[i][j] = '-';
            }
        }
    }

    /**
     * This Method will output the states after each move.
     */
    public void stateAfterMove(){
        System.out.print("Game States: " );
        for (char[] chars : gameBoard) {
            for (int j = 0; j < gameBoard.length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.print("   ");
        }
        System.out.println();
    }

    /**
     * This Method will check if the game has finished or not.
     * And for that it will call four methods to see any condition is {@code true} or {@code false}.
     * First method is {@code doesHorizontalMatches}, Second method is {@code doesVerticalMatches},
     * Third method is {@code doesDiagonalMatches} and last method is {@code doesAntiDiagonalMatches}.
     * @param row This is the Row Number where we placed the stone.
     * @param col This is the Column Number where we placed the stone.
     * @return {@code true} If game has finished. {@code false} If game hasn't finished.
     */
    public boolean isGameFinished(int row, int col){
        log.debug("Checking if Game has Finished or not....");
        return doesHorizontalMatches(row, col) || doesVerticalMatches(row, col) || doesDiagonalMatches(row, col) || doesAntiDiagonalMatches(row, col);
    }

    private boolean doesHorizontalMatches(int row, int col) {
        if (row - 2 >= 0)
            if (gameBoard[row - 2][col] == gameBoard[row - 1][col] && gameBoard[row - 1][col] == gameBoard[row][col])
                return true;
        if (row + 2 <= arraySize - 1)
            if (gameBoard[row + 2][col] == gameBoard[row + 1][col] && gameBoard[row + 1][col] == gameBoard[row][col])
                return true;
        if (row - 1 >= 0 && row + 1 <= arraySize - 1)
            return gameBoard[row - 1][col] == gameBoard[row][col] && gameBoard[row][col] == gameBoard[row + 1][col];
        return false;
    }

    private boolean doesVerticalMatches(int row, int col){
        if (col - 2 >= 0)
            if (gameBoard[row][col - 2] == gameBoard[row][col - 1] && gameBoard[row][col - 1] == gameBoard[row][col])
                return true;
        if (col + 2 <= arraySize - 1)
            if (gameBoard[row][col + 2] == gameBoard[row][col + 1] && gameBoard[row][col + 1] == gameBoard[row][col])
                return true;
        if (col - 1 >= 0 && col + 1 <= arraySize - 1)
            return gameBoard[row][col - 1] == gameBoard[row][col] && gameBoard[row][col] == gameBoard[row][col + 1];
        return false;
    }

    private boolean doesDiagonalMatches(int row, int col){
        if (row - 2 >= 0 && col - 2 >= 0)
            if (gameBoard[row - 2][col - 2] == gameBoard[row - 1][col - 1] && gameBoard[row - 1][col - 1] == gameBoard[row][col])
                return true;
        if (row + 2 <= arraySize - 1 && col + 2 <= arraySize - 1)
            if (gameBoard[row + 2][col + 2] == gameBoard[row + 1][col + 1] && gameBoard[row + 1][col + 1] == gameBoard[row][col])
                return true;
        if ((row - 1 >= 0 && row + 1 <= arraySize - 1) && (col - 1 >= 0 && col + 1 <= arraySize - 1))
            return gameBoard[row - 1][col - 1] == gameBoard[row][col] && gameBoard[row][col] == gameBoard[row + 1][col + 1];
        return false;
    }

    private boolean doesAntiDiagonalMatches(int row, int col){
        if (row - 2 >= 0 && col + 2 <= arraySize - 1)
            if (gameBoard[row - 2][col + 2] == gameBoard[row - 1][col + 1] && gameBoard[row - 1][col + 1] == gameBoard[row][col])
                return true;
        if (row + 2 <= arraySize - 1 && col - 2 >= 0)
            if (gameBoard[row + 2][col - 2] == gameBoard[row + 1][col - 1] && gameBoard[row + 1][col - 1] == gameBoard[row][col])
                return true;
        if ((row - 1 >= 0 && row + 1 <= arraySize - 1) && (col - 1 >= 0 && col + 1 <= arraySize - 1))
            return gameBoard[row - 1][col + 1] == gameBoard[row][col] && gameBoard[row][col] == gameBoard[row + 1][col - 1];
        return false;
    }

    /**
     * This Method will check if the move is valid or not.
     * @param row This is the Row number where stone will be placed.
     * @param col This is the Column number where stone will be placed.
     * @return {@code true} if the grid is empty and stone can be placed. {@code false} if the grid is not empty and stone can't be placed.
     */
    public boolean isMoveValid(int row, int col){
        log.debug("Checking if Grid is Empty or not....");
        return gameBoard[row][col] == '-';
    }

    /**
     * This Method will check if whole {@code gameBoard} is filled or not. Which will check if game has Tied or not.
     * @return {@code true} if the {@code gameBoard} is full and game has Tied. {@code false} if the grid is not full and game hasn't Tied yet.
     */
    public boolean isGameBoardFilled(){
        log.debug("Checking if Board is Empty or not....");
        for (char[] chars : gameBoard) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (chars[j] != 'B' && chars[j] != 'R') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This function will set the specific stone on {@code gameBoard}.
     * Initially, {@code gameBoard} has dashed which states the place is empty.
     * If player places a Red stone it will put a 'R' on {@code gameBoard} to indicate this place is taken by Red stone.
     * If player places a Blue stone it will put a 'B' on {@code gameBoard} to indicate this place is taken by Blue stone.
     * @param row This is the Row number where stone will be placed.
     * @param col This is the Column number where stone will be placed.
     * @param stone This is the stone that will be placed.
     *              If player places a Red stone it will put a 'R' on {@code gameBoard} to indicate this place is taken by Red stone.
     *              If player places a Blue stone it will put a 'B' on {@code gameBoard} to indicate this place is taken by Blue stone.
     */
    public void setOnBoard(int row, int col, char stone){
        if(stone == 'R') {
            log.info("Setting a Red Stone on Board!");
        }
        else if(stone == 'B'){
            log.info("Setting a Blue Stone on Board!");
        }
        gameBoard[row][col] = stone;
    }
}