package doNotConnectStones.states;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class doNotConnectStonesStatesTest {

    @Test
    void isGameFinished() {

        // isGameFinished Vertical True Test Case at position 1:
        doNotConnectStonesStates stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','R','R','B','R'},
                {'B','-','R','-','B'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertTrue(stonesStates.hasGameFinished(0, 2));

        // isGameFinished Vertical True Test Case at position 2:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','B','R','B','R'},
                {'B','-','R','-','B'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertTrue(stonesStates.hasGameFinished(1, 2));

        // isGameFinished Vertical True Test Case at position 3:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','B','R','B','R'},
                {'B','-','R','-','B'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertTrue(stonesStates.hasGameFinished(2, 2));

        // isGameFinished Vertical False Test Case at position 1:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','R','R','B','R'},
                {'B','-','B','-','B'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertFalse(stonesStates.hasGameFinished(0, 2));

        // isGameFinished Vertical False Test Case at position 2:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','B','R','B','R'},
                {'B','-','B','-','B'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertFalse(stonesStates.hasGameFinished(1, 2));

        // isGameFinished Vertical False Test Case at position 3:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','B','R','B','R'},
                {'B','-','B','-','B'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertFalse(stonesStates.hasGameFinished(2, 2));


        // isGameFinished Horizontal True Test Case at position 1:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','-','R','-','R'},
                {'-','B','B','B','-'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertTrue(stonesStates.hasGameFinished(2, 1));

        // isGameFinished Horizontal True Test Case at position 2:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'B','R','R','-','R'},
                {'-','B','B','B','-'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertTrue(stonesStates.hasGameFinished(2, 2));

        // isGameFinished Horizontal True Test Case at position 3:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','-','R','-','R'},
                {'-','B','B','B','-'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertTrue(stonesStates.hasGameFinished(2, 3));

        // isGameFinished Horizontal False Test Case at position 1:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','B','R','B','R'},
                {'B','B','-','R','B'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertFalse(stonesStates.hasGameFinished(2, 0));

        // isGameFinished Horizontal False Test Case at position 2:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','-','-','B','R'},
                {'B','B','R','B','-'},
                {'B','R','-','-','B'},
                {'R','B','R','B','R'}});
        assertFalse(stonesStates.hasGameFinished(2, 1));

        // isGameFinished Horizontal False Test Case at position 3:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','-','-','-','R'},
                {'B','B','R','B','-'},
                {'B','-','-','-','B'},
                {'R','B','R','B','R'}});
        assertFalse(stonesStates.hasGameFinished(2, 2));


        // isGameFinished Diagonal True Test Case at position 1:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','R','-','-','R'},
                {'-','B','R','B','-'},
                {'B','-','B','-','B'},
                {'R','B','R','B','-'}});
        assertTrue(stonesStates.hasGameFinished(0, 0));

        // isGameFinished Diagonal True Test Case at position 2:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','R','-','-','R'},
                {'-','B','R','B','-'},
                {'B','-','B','-','B'},
                {'R','B','R','B','-'}});
        assertTrue(stonesStates.hasGameFinished(1, 1));

        // isGameFinished Diagonal True Test Case at position 3:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','R','-','-','R'},
                {'-','B','R','B','-'},
                {'B','-','B','-','B'},
                {'R','B','R','B','-'}});
        assertTrue(stonesStates.hasGameFinished(2, 2));

        // isGameFinished Diagonal False Test Case at position 1:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','R','B','-','R'},
                {'B','B','-','R','B'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertFalse(stonesStates.hasGameFinished(0, 0));

        // isGameFinished Diagonal False Test Case at position 2:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','R','B','-','R'},
                {'B','B','-','R','B'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertFalse(stonesStates.hasGameFinished(1, 1));

        // isGameFinished Diagonal False Test Case at position 3:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'-','B','R','B','R'},
                {'R','R','B','-','R'},
                {'B','R','R','B','B'},
                {'B','B','B','B','R'},
                {'R','B','R','B','B'}});
        assertFalse(stonesStates.hasGameFinished(2, 2));


        // isGameFinished Anti-Diagonal True Test Case at position 1:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','R','B','B','R'},
                {'-','B','R','B','B'},
                {'B','R','B','-','B'},
                {'R','B','R','B','-'}});
        assertTrue(stonesStates.hasGameFinished(4, 0));

        // isGameFinished Anti-Diagonal True Test Case at position 2:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','R','B','B','R'},
                {'-','B','R','B','B'},
                {'B','R','B','-','B'},
                {'R','B','R','B','-'}});
        assertTrue(stonesStates.hasGameFinished(3, 1));

        // isGameFinished Anti-Diagonal True Test Case at position 3:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','R','B','B','R'},
                {'-','B','R','B','B'},
                {'B','R','B','-','B'},
                {'R','B','R','B','-'}});
        assertTrue(stonesStates.hasGameFinished(2, 2));

        // isGameFinished Anti-Diagonal False Test Case at position 1:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','R','B','B','R'},
                {'-','B','-','B','B'},
                {'B','R','B','-','B'},
                {'R','B','R','B','-'}});
        assertFalse(stonesStates.hasGameFinished(4, 0));

        // isGameFinished Anti-Diagonal False Test Case at position 2:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','R','B','B','R'},
                {'-','B','-','B','B'},
                {'B','R','B','-','B'},
                {'R','B','R','B','-'}});
        assertFalse(stonesStates.hasGameFinished(3, 1));

        // isGameFinished Anti-Diagonal False Test Case at position 3:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'B','B','R','B','R'},
                {'R','R','B','B','R'},
                {'-','B','R','B','B'},
                {'B','R','B','-','B'},
                {'-','B','R','B','R'}});
        assertFalse(stonesStates.hasGameFinished(2, 2));
    }

    @Test
    void isMoveValid() {
        // Empty Board Case:
        doNotConnectStonesStates stonesStates = new doNotConnectStonesStates();
        assertTrue(stonesStates.isMoveValid(1, 2));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','B','R','B','R'},
                {'B','R','-','R','B'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertTrue(stonesStates.isMoveValid(2, 2));

        // Full Board Case:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','B','R','B','R'},
                {'B','R','B','R','B'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertFalse(stonesStates.isMoveValid(1, 4));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'-','B','R','B','R'},
                {'-','B','R','B','R'},
                {'-','-','-','-','-'},
                {'-','-','-','-','-'},
                {'-','-','-','-','-'}});
        assertFalse(stonesStates.isMoveValid(1, 2));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'-','B','R','B','R'},
                {'-','B','R','B','R'},
                {'-','-','-','-','-'},
                {'-','-','-','-','-'},
                {'-','-','-','-','-'}});
        assertTrue(stonesStates.isMoveValid(2, 3));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'B','B','R','B','R'},
                {'R','R','B','B','R'},
                {'-','B','R','B','B'},
                {'B','R','B','-','B'},
                {'-','B','R','B','R'}});
        assertTrue(stonesStates.isMoveValid(3, 3));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'B','B','R','B','R'},
                {'R','R','B','B','R'},
                {'-','B','R','B','B'},
                {'B','R','B','-','B'},
                {'-','B','R','B','R'}});
        assertFalse(stonesStates.isMoveValid(2, 2));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'B','B','R','B','R'},
                {'R','R','B','B','R'},
                {'-','B','R','B','B'},
                {'B','R','B','-','B'},
                {'-','B','R','B','R'}});
        assertFalse(stonesStates.isMoveValid(1, 1));
    }

    @Test
    void isGameBoardFilled() {
        // Empty Board Case:
        doNotConnectStonesStates stonesStates = new doNotConnectStonesStates();
        assertFalse(stonesStates.isGameBoardFilled());

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','B','R','B','R'},
                {'B','R','B','R','B'},
                {'B','R','B','R','B'},
                {'R','B','-','B','R'}});
        assertFalse(stonesStates.isGameBoardFilled());

        // Filled Board Case:
        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'B','B','R','B','R'},
                {'R','R','B','B','R'},
                {'R','B','R','B','B'},
                {'B','R','B','R','B'},
                {'B','B','R','B','R'}});
        assertTrue(stonesStates.isGameBoardFilled());

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','B','R','B','R'},
                {'B','R','B','R','B'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertTrue(stonesStates.isGameBoardFilled());

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'-','B','R','B','R'},
                {'R','B','R','B','R'},
                {'B','R','-','R','B'},
                {'B','R','B','R','B'},
                {'-','-','-','-','-'}});
        assertFalse(stonesStates.isGameBoardFilled());

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'B','B','-','B','R'},
                {'R','R','B','B','R'},
                {'R','B','R','B','B'},
                {'B','R','B','R','B'},
                {'B','B','R','B','R'}});
        assertFalse(stonesStates.isGameBoardFilled());

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','R','B','B','R'},
                {'R','B','R','B','B'},
                {'B','B','B','R','B'},
                {'B','B','R','B','R'}});
        assertTrue(stonesStates.isGameBoardFilled());
    }

    @Test
    void setOnBoard() {
        doNotConnectStonesStates stonesStates = new doNotConnectStonesStates();
        stonesStates.setOnBoard(1, 2, 'B');
        var arrayOfStones = stonesStates.getGameBoard();
        assertEquals('B', arrayOfStones[1][2]);

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setOnBoard(2, 4, 'R');
        arrayOfStones = stonesStates.getGameBoard();
        assertEquals('R', arrayOfStones[2][4]);

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setOnBoard(2, 2, 'R');
        arrayOfStones = stonesStates.getGameBoard();
        assertEquals('R', arrayOfStones[2][2]);

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setOnBoard(0, 0, 'B');
        arrayOfStones = stonesStates.getGameBoard();
        assertEquals('B', arrayOfStones[0][0]);

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setOnBoard(3, 4, 'R');
        arrayOfStones = stonesStates.getGameBoard();
        assertEquals('R', arrayOfStones[3][4]);
    }
}