package TicTacToeGame.Model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * This is the Class which will Represent the States of the Tic-Tac-Toe Game.
 */
@Data
@Slf4j
public class TicTacToeGameModel {

    private int boardSize = 3;
    private char[][] gameBoard = new char[boardSize][boardSize];

    /**
     * This is Constructor for the {@code TicTacToeGameModel} Class.
     */
    public TicTacToeGameModel(){
        log.debug("Initializing Board Model....");
        for (int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j < gameBoard.length; j++){
                gameBoard[i][j] = '-';
            }
        }
    }

    /**
     * This Method will check if the Game has Finished or not.
     * And for that it will call four Methods to see any condition is {@code true} or {@code false}.
     * First method is {@code doesHorizontalMatches}, Second method is {@code doesVerticalMatches},
     * Third method is {@code doesDiagonalMatches} and last method is {@code doesAntiDiagonalMatches}.
     * @param row This is the Row Number where we placed the Mark.
     * @param col This is the Column Number where we placed the Mark.
     * @return {@code true} If Game has Finished. {@code false} If Game hasn't Finished.
     */
    public boolean hasGameFinished(int row, int col){
        log.debug("Checking if Game has Finished or not....");
        return doesHorizontalMatch(row, col) || doesVerticalMatch(row, col) || doesDiagonalMatch(row, col) || doesAntiDiagonalMatch(row, col);
    }

    private boolean doesHorizontalMatch(int row, int col) {
        if (row - 2 >= 0)
            if (gameBoard[row - 2][col] == gameBoard[row - 1][col] && gameBoard[row - 1][col] == gameBoard[row][col])
                return true;
        if (row + 2 <= boardSize - 1)
            if (gameBoard[row + 2][col] == gameBoard[row + 1][col] && gameBoard[row + 1][col] == gameBoard[row][col])
                return true;
        if (row - 1 >= 0 && row + 1 <= boardSize - 1)
            return gameBoard[row - 1][col] == gameBoard[row][col] && gameBoard[row][col] == gameBoard[row + 1][col];
        return false;
    }

    private boolean doesVerticalMatch(int row, int col){
        if (col - 2 >= 0)
            if (gameBoard[row][col - 2] == gameBoard[row][col - 1] && gameBoard[row][col - 1] == gameBoard[row][col])
                return true;
        if (col + 2 <= boardSize - 1)
            if (gameBoard[row][col + 2] == gameBoard[row][col + 1] && gameBoard[row][col + 1] == gameBoard[row][col])
                return true;
        if (col - 1 >= 0 && col + 1 <= boardSize - 1)
            return gameBoard[row][col - 1] == gameBoard[row][col] && gameBoard[row][col] == gameBoard[row][col + 1];
        return false;
    }

    private boolean doesDiagonalMatch(int row, int col){
        if (row - 2 >= 0 && col - 2 >= 0)
            if (gameBoard[row - 2][col - 2] == gameBoard[row - 1][col - 1] && gameBoard[row - 1][col - 1] == gameBoard[row][col])
                return true;
        if (row + 2 <= boardSize - 1 && col + 2 <= boardSize - 1)
            if (gameBoard[row + 2][col + 2] == gameBoard[row + 1][col + 1] && gameBoard[row + 1][col + 1] == gameBoard[row][col])
                return true;
        if ((row - 1 >= 0 && row + 1 <= boardSize - 1) && (col - 1 >= 0 && col + 1 <= boardSize - 1))
            return gameBoard[row - 1][col - 1] == gameBoard[row][col] && gameBoard[row][col] == gameBoard[row + 1][col + 1];
        return false;
    }

    private boolean doesAntiDiagonalMatch(int row, int col){
        if (row - 2 >= 0 && col + 2 <= boardSize - 1)
            if (gameBoard[row - 2][col + 2] == gameBoard[row - 1][col + 1] && gameBoard[row - 1][col + 1] == gameBoard[row][col])
                return true;
        if (row + 2 <= boardSize - 1 && col - 2 >= 0)
            if (gameBoard[row + 2][col - 2] == gameBoard[row + 1][col - 1] && gameBoard[row + 1][col - 1] == gameBoard[row][col])
                return true;
        if ((row - 1 >= 0 && row + 1 <= boardSize - 1) && (col - 1 >= 0 && col + 1 <= boardSize - 1))
            return gameBoard[row - 1][col + 1] == gameBoard[row][col] && gameBoard[row][col] == gameBoard[row + 1][col - 1];
        return false;
    }

    /**
     * This Method will Check if the Move is Valid or not.
     * @param row This is the Row number where Player wants to put his Mark.
     * @param col This is the Column number where Player wants to put his Mark.
     * @return {@code true} if the Grid is Empty and Mark can be placed. {@code false} if the Grid is not Empty and Mark can't be placed.
     */
    public boolean isMoveValid(int row, int col){
        log.debug("Checking if Grid is Empty or not....");
        return gameBoard[row][col] == '-';
    }

    /**
     * This Method will check if whole {@code gameBoard} is filled or not. Which can check if Game has Tied or not.
     * @return {@code true} if the {@code gameBoard} is full and Game has Tied. {@code false} if the {@code gameBoard} is not full and game hasn't Tied yet.
     */
    public boolean isGameBoardFilled(){
        log.debug("Checking if Board is Empty or not....");
        for (char[] chars : gameBoard) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (chars[j] != 'X' && chars[j] != 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This function will set the specific Mark on {@code gameBoard}.
     * Initially, {@code gameBoard} has dashed which means the place is Empty.
     * If player places a 'X' Mark it will put a 'X' on {@code gameBoard} to indicate this place is taken by 'X' Mark.
     * If player places a 'O' Mark it will put a 'O' on {@code gameBoard} to indicate this place is taken by 'O' Mark.
     * @param row This is the Row number where that Mark will be placed.
     * @param col This is the Column number where that Mark will be placed.
     * @param stone This is the Character of the Mark that will be placed.
     *              If player places a 'X' Mark it will put a 'X' on {@code gameBoard} to indicate this place is taken by 'X' Mark.
     *              If player places a 'O' Mark it will put a 'O' on {@code gameBoard} to indicate this place is taken by 'O' Mark.
     */
    public void setOnBoard(int row, int col, char stone){
        gameBoard[row][col] = stone;
    }
}