package doNotConnectStones.states;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class doNotConnectStonesStatesTest {

    @Test
    void isGameFinished() {
        doNotConnectStonesStates stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','B','R','B','R'},
                {'B','-','R','-','B'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertTrue(stonesStates.hasGameFinished(1, 2));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','B','R','B','R'},
                {'B','-','R','-','B'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertTrue(stonesStates.hasGameFinished(2, 2));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','R','R','B','R'},
                {'B','-','R','-','B'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertTrue(stonesStates.hasGameFinished(0, 2));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','B','R','B','R'},
                {'B','-','B','-','B'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertFalse(stonesStates.hasGameFinished(1, 2));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','B','R','B','R'},
                {'B','-','B','-','B'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertFalse(stonesStates.hasGameFinished(2, 2));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','R','R','B','R'},
                {'B','-','B','-','B'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertFalse(stonesStates.hasGameFinished(0, 2));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','-','R','-','R'},
                {'-','B','B','B','-'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertTrue(stonesStates.hasGameFinished(2, 2));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','-','R','-','R'},
                {'-','B','B','B','-'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertTrue(stonesStates.hasGameFinished(2, 1));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','-','R','-','R'},
                {'-','B','B','B','-'},
                {'B','R','B','R','B'},
                {'R','B','R','B','R'}});
        assertTrue(stonesStates.hasGameFinished(2, 3));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','-','-','-','R'},
                {'B','B','R','B','-'},
                {'B','R','-','-','B'},
                {'R','B','R','B','R'}});
        assertFalse(stonesStates.hasGameFinished(2, 1));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','B','-','B','R'},
                {'B','B','R','-','B'},
                {'B','R','-','-','B'},
                {'R','B','R','B','R'}});
        assertFalse(stonesStates.hasGameFinished(2, 0));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','-','-','-','R'},
                {'B','B','R','B','-'},
                {'B','-','-','-','B'},
                {'R','B','R','B','R'}});
        assertFalse(stonesStates.hasGameFinished(2, 2));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','R','-','-','R'},
                {'-','B','R','B','-'},
                {'B','-','B','-','B'},
                {'R','B','R','B','-'}});
        assertTrue(stonesStates.hasGameFinished(0, 0));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','R','-','-','R'},
                {'-','B','R','B','-'},
                {'B','-','B','-','B'},
                {'R','B','R','B','-'}});
        assertTrue(stonesStates.hasGameFinished(1, 1));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','R','-','-','R'},
                {'-','B','R','B','-'},
                {'B','-','B','-','B'},
                {'R','B','R','B','-'}});
        assertTrue(stonesStates.hasGameFinished(2, 2));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','-','B','-','R'},
                {'-','-','R','-','-'},
                {'B','-','B','-','B'},
                {'R','B','R','B','-'}});
        assertFalse(stonesStates.hasGameFinished(1, 2));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','-','B','-','R'},
                {'-','-','R','-','-'},
                {'B','-','B','-','B'},
                {'R','B','R','B','-'}});
        assertFalse(stonesStates.hasGameFinished(3, 2));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','-','-','-','R'},
                {'B','B','-','B','-'},
                {'B','-','-','R','B'},
                {'R','B','R','B','R'}});
        assertFalse(stonesStates.hasGameFinished(4, 4));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','-','-','-','R'},
                {'-','B','R','B','-'},
                {'B','R','B','-','B'},
                {'R','B','R','B','-'}});
        assertTrue(stonesStates.hasGameFinished(4, 0));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','-','-','-','R'},
                {'-','B','R','B','-'},
                {'B','R','B','-','B'},
                {'R','B','R','B','-'}});
        assertTrue(stonesStates.hasGameFinished(3, 1));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','-','-','-','R'},
                {'-','B','R','B','-'},
                {'B','R','B','-','B'},
                {'R','B','R','B','-'}});
        assertTrue(stonesStates.hasGameFinished(2, 2));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','-','-','-','R'},
                {'-','B','R','B','-'},
                {'B','-','B','-','B'},
                {'R','B','R','B','-'}});
        assertFalse(stonesStates.hasGameFinished(4, 0));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','-','-','-','R'},
                {'-','B','R','B','-'},
                {'B','R','B','-','B'},
                {'-','B','R','B','-'}});
        assertFalse(stonesStates.hasGameFinished(3, 1));

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','-','-','-','R'},
                {'-','B','R','B','-'},
                {'B','R','B','-','B'},
                {'-','B','R','B','-'}});
        assertFalse(stonesStates.hasGameFinished(2, 2));
    }

    @Test
    void isMoveValid() {
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
    }

    @Test
    void isGameBoardFilled() {
        doNotConnectStonesStates stonesStates = new doNotConnectStonesStates();
        assertFalse(stonesStates.isGameBoardFilled());

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
                {'R','B','R','B','R'},
                {'R','B','R','B','R'},
                {'B','R','B','R','B'},
                {'B','R','B','R','B'},
                {'R','B','-','B','R'}});
        assertFalse(stonesStates.isGameBoardFilled());

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','B','R','B','R'},
                {'B','R','B','R','B'},
                {'B','R','B','R','B'},
                {'-','-','-','-','-'}});
        assertFalse(stonesStates.isGameBoardFilled());

        stonesStates = new doNotConnectStonesStates();
        stonesStates.setGameBoard(new char[][]{
                {'R','B','R','B','R'},
                {'R','B','R','B','R'},
                {'R','B','R','B','R'},
                {'R','B','R','B','R'},
                {'R','B','R','B','R'}});
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