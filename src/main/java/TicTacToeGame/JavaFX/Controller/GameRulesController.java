package TicTacToeGame.JavaFX.Controller;

import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

/**
 * This Class controls the {@code GameRules}.
 * And it is responsible to show the {@code GameRules} to User and make it functional for the User.
 */
@Slf4j
public class GameRulesController {

    FXMLLoader fxmlLoader = new FXMLLoader();

    /**
     * This Method will Switch the Scene from {@code GameRules} to {@code MainMenu}.
     * @param actionEvent This is the {@code ActionEvent} which will help to switch Scene from the Recent Scene to Next Scene.
     * @throws IOException If Program is not able to find the {@code MainMenu} or it is not defined then it will throw an Error.
     */
    public void previousPageButtonController(ActionEvent actionEvent) throws IOException {
        log.info("Previous Page Button was Clicked....");
        log.info("Loading Main-Menu Scene....");

        fxmlLoader.setLocation(getClass().getResource("/fxml/MainMenu.fxml"));
        Parent mainMenuScene = fxmlLoader.load();
        Stage mainMenuStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        mainMenuStage.setTitle("Do-not Connect Stones Main Menu");
        mainMenuStage.setScene(new Scene(mainMenuScene));
        mainMenuStage.setResizable(false);
        mainMenuStage.show();
    }
}