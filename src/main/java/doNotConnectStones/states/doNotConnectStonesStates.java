package doNotConnectStones.states;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class doNotConnectStonesStates {
    private final char[][] gameBoard = new char[5][5];

    public void initializeBoard(){
        log.info("Inside initializeBoard method!");
        for (int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j < gameBoard.length; j++){
                gameBoard[i][j] = '-';
            }
        }
    }

    public void stateAfterMove(){
        log.info("Inside stateAfterMove method!");
        for (char[] chars : gameBoard) {
            for (int j = 0; j < gameBoard.length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isGameFinished(int row, int col){
        log.info("Inside isGameFinished method!");
        return doesHorizontalMatches(row, col) || doesVerticalMatches(row, col) || doesDiagonalMatches(row, col) || doesAntiDiagonalMatches(row, col);
    }

    private boolean doesHorizontalMatches(int row, int col) {
        log.info("Inside doesHorizontalMatches method!");
        if (row - 2 >= 0)
            if (gameBoard[row - 2][col] == gameBoard[row - 1][col] && gameBoard[row - 1][col] == gameBoard[row][col])
                return true;
        if (row + 2 <= 4)
            if (gameBoard[row + 2][col] == gameBoard[row + 1][col] && gameBoard[row + 1][col] == gameBoard[row][col])
                return true;
        if (row - 1 >= 0 && row + 1 <= 4)
            return gameBoard[row - 1][col] == gameBoard[row][col] && gameBoard[row][col] == gameBoard[row + 1][col];
        return false;
    }

    private boolean doesVerticalMatches(int row, int col){
        log.info("Inside doesVerticalMatches method!");
        if (col - 2 >= 0)
            if (gameBoard[row][col - 2] == gameBoard[row][col - 1] && gameBoard[row][col - 1] == gameBoard[row][col])
                return true;
        if (col + 2 <= 4)
            if (gameBoard[row][col + 2] == gameBoard[row][col + 1] && gameBoard[row][col + 1] == gameBoard[row][col])
                return true;
        if (col - 1 >= 0 && col + 1 <= 4)
            return gameBoard[row][col - 1] == gameBoard[row][col] && gameBoard[row][col] == gameBoard[row][col + 1];
        return false;
    }
    private boolean doesDiagonalMatches(int row, int col){
        log.info("Inside doesDiagonalMatches method!");
        if (row - 2 >= 0 && col - 2 >= 0)
            if (gameBoard[row - 2][col - 2] == gameBoard[row - 1][col - 1] && gameBoard[row - 1][col - 1] == gameBoard[row][col])
                return true;
        if (row + 2 <= 4 && col + 2 <= 4)
            if (gameBoard[row + 2][col + 2] == gameBoard[row + 1][col + 1] && gameBoard[row + 1][col + 1] == gameBoard[row][col])
                return true;
        if ((row - 1 >= 0 && row + 1 <= 4) && (col - 1 >= 0 && col + 1 <= 4))
            return gameBoard[row - 1][col - 1] == gameBoard[row][col] && gameBoard[row][col] == gameBoard[row + 1][col + 1];
        return false;
    }
    private boolean doesAntiDiagonalMatches(int row, int col){
        log.info("Inside doesAntiDiagonalMatches method!");
        if (row - 2 >= 0 && col + 2 <= 4)
            if (gameBoard[row - 2][col + 2] == gameBoard[row - 1][col + 1] && gameBoard[row - 1][col + 1] == gameBoard[row][col])
                return true;
        if (row + 2 <= 4 && col - 2 >= 0)
            if (gameBoard[row + 2][col - 2] == gameBoard[row + 1][col - 1] && gameBoard[row + 1][col - 1] == gameBoard[row][col])
                return true;
        if ((row - 1 >= 0 && row + 1 <= 4) && (col - 1 >= 0 && col + 1 <= 4))
            return gameBoard[row - 1][col + 1] == gameBoard[row][col] && gameBoard[row][col] == gameBoard[row + 1][col - 1];
        return false;
    }

    public boolean isMoveValid(int row, int col){
        log.info("Inside isMoveValid method!");
        return gameBoard[row][col] == '-';
    }

    public boolean isGameBoardFilled(){
        log.info("Inside isGameBoardFilled method!");
        for (char[] chars : gameBoard) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (chars[j] != 'B' && chars[j] != 'R') {
                    return false;
                }
            }
        }
        return true;
    }

    public void setOnBoard(int row, int col, char stone){
        log.info("Inside setOnBoard method!");
        gameBoard[row][col] = stone;
    }

    public static void main(String[] args) throws IOException, IOException {
        Scanner scan = new Scanner(System.in);
        int row = 0; int col = 0, i = 1;

        doNotConnectStonesStates testModel = new doNotConnectStonesStates();
        testModel.initializeBoard();

        do{
            testModel.stateAfterMove();

            if(i % 2 != 0){ System.out.println("\nPlayer # 01 Move: ");}
            else{System.out.println("Player # 02 Move: ");}

            do {
                do {
                    System.out.print("Enter Row: ");
                    row = scan.nextInt();
                    System.out.print("Enter Column: ");
                    col = scan.nextInt();
                } while ((row < 0 || row > 4) || (col < 0 || col > 4));

                if (!testModel.isGameBoardFilled() && testModel.isMoveValid(row, col)) {
                    if (i % 2 == 1) {testModel.setOnBoard(row, col, 'R');
                    } else {testModel.setOnBoard(row, col, 'B');}
                    i++;
                } else {System.out.println("Invalid Move! Enter again: ");}
            }while(testModel.isGameBoardFilled() || testModel.isMoveValid(row, col));

        }while(testModel.isGameBoardFilled() || !testModel.isGameFinished(row, col));
        log.info("Game Over!");
        log.info("Board after gameOver state!");
        testModel.stateAfterMove();
    }
}