package TicTacToeGame.JavaFX;

import java.util.Objects;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

import lombok.extern.slf4j.Slf4j;

import javafx.application.Application;

/**
 * This is the Class which will Launch the first Scene of the Game which is {@code MainMenu}.
 */
@Slf4j
public class TicTacToeApplication extends Application {

    /**
     * This is the Method which will Load the first Scene of the Game which is {@code MainMenu} Scene.
     * @param mainMenuStage This is the Stage on which we need to Load the {@code MainMenu} Scene.
     * @throws Exception This Method will throw an Exception if Program is not able to Find the Scene.
     */
    @Override
    public void start(Stage mainMenuStage) throws Exception{

        log.info("Loading Main Menu Scene....");
        Parent mainMenuScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MainMenu.fxml")));
        mainMenuStage.setTitle("Tic-Tac-Toe Main Menu");
        mainMenuStage.setScene(new Scene(mainMenuScene));
        mainMenuStage.setResizable(false);
        mainMenuStage.show();
    }
}