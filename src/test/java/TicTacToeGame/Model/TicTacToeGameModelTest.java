package TicTacToeGame.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeGameModelTest {

    @Test
    void hasGameFinished() {
        // isGameFinished Horizontal True Test Case at position 1:
        TicTacToeGameModel stonesStates = new TicTacToeGameModel();
        stonesStates.setGameBoard(new char[][]{
                {'X','X','X'},
                {'-','-','-'},
                {'-','-','-'}});
        assertTrue(stonesStates.hasGameFinished(0, 0));

        // isGameFinished Horizontal  True Test Case at position 2:
        assertTrue(stonesStates.hasGameFinished(0, 1));

        // isGameFinished Horizontal  True Test Case at position 3:
        assertTrue(stonesStates.hasGameFinished(0, 2));

        // isGameFinished Horizontal  False Test Case at position 1:
        stonesStates.setGameBoard(new char[][]{
                {'-','-','-'},
                {'O','X','X'},
                {'-','-','-'}});
        assertFalse(stonesStates.hasGameFinished(1, 0));

        // isGameFinished Horizontal  False Test Case at position 2:
        assertFalse(stonesStates.hasGameFinished(1, 1));

        // isGameFinished Horizontal False Test Case at position 3:
        assertFalse(stonesStates.hasGameFinished(1, 2));


        // isGameFinished Vertical True Test Case at position 1:
        stonesStates.setGameBoard(new char[][]{
                {'X','-','-'},
                {'X','-','-'},
                {'X','-','-'}});
        assertTrue(stonesStates.hasGameFinished(0, 0));

        // isGameFinished Vertical True Test Case at position 2:
        assertTrue(stonesStates.hasGameFinished(1, 0));

        // isGameFinished Vertical True Test Case at position 3:
        assertTrue(stonesStates.hasGameFinished(2, 0));

        // isGameFinished Vertical False Test Case at position 1:
        stonesStates.setGameBoard(new char[][]{
                {'X','-','-'},
                {'X','-','-'},
                {'O','-','-'}});
        assertFalse(stonesStates.hasGameFinished(0, 0));

        // isGameFinished Vertical False Test Case at position 2:
        assertFalse(stonesStates.hasGameFinished(1, 0));

        // isGameFinished Vertical False Test Case at position 3:
        stonesStates.setGameBoard(new char[][]{
                {'X','-','-'},
                {'O','-','-'},
                {'O','-','-'}});
        assertFalse(stonesStates.hasGameFinished(2, 0));


        // isGameFinished Diagonal True Test Case at position 1:
        stonesStates.setGameBoard(new char[][]{
                {'X','-','-'},
                {'-','X','-'},
                {'-','-','X'}});
        assertTrue(stonesStates.hasGameFinished(0, 0));

        // isGameFinished Diagonal True Test Case at position 2:
        assertTrue(stonesStates.hasGameFinished(1, 1));

        // isGameFinished Diagonal True Test Case at position 3:
        assertTrue(stonesStates.hasGameFinished(2, 2));

        // isGameFinished Diagonal False Test Case at position 1:
        stonesStates.setGameBoard(new char[][]{
                {'X','-','-'},
                {'-','X','-'},
                {'-','-','O'}});
        assertFalse(stonesStates.hasGameFinished(0, 0));

        // isGameFinished Diagonal False Test Case at position 2:
        stonesStates.setGameBoard(new char[][]{
                {'O','-','-'},
                {'-','X','-'},
                {'-','-','X'}});
        assertFalse(stonesStates.hasGameFinished(1, 1));

        // isGameFinished Diagonal False Test Case at position 3:
        assertFalse(stonesStates.hasGameFinished(2, 2));

        // isGameFinished Anti-Diagonal True Test Case at position 1:
        stonesStates.setGameBoard(new char[][]{
                {'-','-','X'},
                {'-','X','-'},
                {'X','-','-'}});
        assertTrue(stonesStates.hasGameFinished(0, 2));

        // isGameFinished Anti-Diagonal True Test Case at position 2:
        assertTrue(stonesStates.hasGameFinished(1, 1));

        // isGameFinished Anti-Diagonal True Test Case at position 3:
        assertTrue(stonesStates.hasGameFinished(2, 0));

        // isGameFinished Anti-Diagonal False Test Case at position 1:
        stonesStates.setGameBoard(new char[][]{
                {'-','-','X'},
                {'-','X','-'},
                {'O','-','-'}});
        assertFalse(stonesStates.hasGameFinished(0, 2));

        // isGameFinished Anti-Diagonal False Test Case at position 2:
        assertFalse(stonesStates.hasGameFinished(1, 1));

        // isGameFinished Anti-Diagonal False Test Case at position 3:
        stonesStates.setGameBoard(new char[][]{
                {'-','-','X'},
                {'-','O','-'},
                {'O','-','-'}});
        assertFalse(stonesStates.hasGameFinished(2, 0));
    }

    @Test
    void isMoveValid() {
        // Empty Board Case:
        TicTacToeGameModel stonesStates = new TicTacToeGameModel();
        assertTrue(stonesStates.isMoveValid(1, 2));

        stonesStates.setGameBoard(new char[][]{
                {'-','-','X'},
                {'-','X','-'},
                {'X','-','-'}});
        assertTrue(stonesStates.isMoveValid(0, 0));
        assertTrue(stonesStates.isMoveValid(0, 1));
        assertTrue(stonesStates.isMoveValid(1, 0));
        assertTrue(stonesStates.isMoveValid(1, 2));
        assertTrue(stonesStates.isMoveValid(2, 1));
        assertTrue(stonesStates.isMoveValid(2, 2));

        // Full Board Case:
        stonesStates.setGameBoard(new char[][]{
                {'X','O','X'},
                {'O','O','X'},
                {'X','X','O'}});
        assertFalse(stonesStates.isMoveValid(0, 0));
        assertFalse(stonesStates.isMoveValid(0, 2));
        assertFalse(stonesStates.isMoveValid(1, 0));
        assertFalse(stonesStates.isMoveValid(1, 2));
        assertFalse(stonesStates.isMoveValid(2, 0));
        assertFalse(stonesStates.isMoveValid(2, 2));
    }

    @Test
    void isGameBoardFilled() {
        // Empty Board Case:
        TicTacToeGameModel stonesStates = new TicTacToeGameModel();
        assertFalse(stonesStates.isGameBoardFilled());

        stonesStates.setGameBoard(new char[][]{
                {'X','O','X'},
                {'O','O','X'},
                {'X','-','O'}});
        assertFalse(stonesStates.isGameBoardFilled());

        stonesStates.setGameBoard(new char[][]{
                {'X','-','X'},
                {'-','O','X'},
                {'X','-','O'}});
        assertFalse(stonesStates.isGameBoardFilled());

        // Filled Board Case:
        stonesStates.setGameBoard(new char[][]{
                {'X','O','X'},
                {'O','O','X'},
                {'X','X','O'}});
        assertTrue(stonesStates.isGameBoardFilled());

        stonesStates.setGameBoard(new char[][]{
                {'X','O','X'},
                {'X','O','X'},
                {'O','X','O'}});
        assertTrue(stonesStates.isGameBoardFilled());

        stonesStates.setGameBoard(new char[][]{
                {'X','O','X'},
                {'X','O','O'},
                {'O','X','O'}});
        assertTrue(stonesStates.isGameBoardFilled());
    }

    @Test
    void setOnBoard() {
        TicTacToeGameModel stonesStates = new TicTacToeGameModel();
        stonesStates.setOnBoard(1, 2, 'X');
        var arrayOfStones = stonesStates.getGameBoard();
        assertEquals('X', arrayOfStones[1][2]);

        stonesStates.setOnBoard(2, 2, 'O');
        arrayOfStones = stonesStates.getGameBoard();
        assertEquals('O', arrayOfStones[2][2]);

        stonesStates.setOnBoard(0, 2, 'X');
        arrayOfStones = stonesStates.getGameBoard();
        assertEquals('X', arrayOfStones[0][2]);

        stonesStates.setOnBoard(2, 1, 'X');
        arrayOfStones = stonesStates.getGameBoard();
        assertEquals('X', arrayOfStones[2][1]);
    }
}