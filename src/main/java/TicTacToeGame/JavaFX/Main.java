package TicTacToeGame.JavaFX;

import lombok.extern.slf4j.Slf4j;

import javafx.application.Application;

/**
 *  This is the Main Class from which we will Load the Application.
 */
@Slf4j
public class Main {

    /**
     * This is the Method that will Launch the Application.
     * @param args String[] args is an array of sequence of Characters that are passed to the "Main" Method.
     */
    public static void main(String[] args) {

        log.info("Starting Application....");
        Application.launch(TicTacToeApplication.class, args);
    }
}