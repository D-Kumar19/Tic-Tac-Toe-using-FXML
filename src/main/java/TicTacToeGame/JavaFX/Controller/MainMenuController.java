package TicTacToeGame.JavaFX.Controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import javafx.scene.control.TextField;

/**
 * This Class controls the {@code MainMenu}.
 * And it is responsible to show the {@code MainMenu} to User and make it functional for the User.
 */
@Slf4j
public class MainMenuController {

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    private final FXMLLoader fxmlLoader= new FXMLLoader();

    @FXML
    private TextField player1Name;

    @FXML
    private TextField player2Name;

    /**
     * This Method will Switch the Scene from {@code MainMenu} to {@code MainGame}.
     * But for that it will check if User has inputted both Names or not. If 'Yes' it will start the Game.
     * If not then it will give an Error. If both Names are same it will give a Warning.
     * If there is no Error then it will pass the {@code ActionEvent} to a Method which will Load the {@code MainGame}.
     * @param actionEvent This is the {@code ActionEvent} which will help to switch Scene from the Recent Scene to Next Scene.
     * @throws IOException If Program is not able to find the {@code MainGame} or it is not defined then it will throw an Error.
     */
    public void switchMainMenuToGameScene(ActionEvent actionEvent) throws  IOException {
        log.info("User just clicked Start....");
        Alert alert = new Alert(Alert.AlertType.ERROR);

        if (player1Name.getText().isEmpty() && player2Name.getText().isEmpty()) {
            log.error("You haven't entered any Player's Name!");
            confirmationMessage("YOU HAVEN'T ENTERED ANY NAMES!", "Please Enter Names.");
        }
        else if (player1Name.getText().isEmpty()) {
            log.error("You have entered only Player Two's Name!");
            confirmationMessage("YOU HAVEN'T ENTERED PLAYER # 01 NAME!", "Please Enter Player # 01 Name.");
        }
        else if (player2Name.getText().isEmpty()) {
            log.error("You have entered only Player One's Name!");
            confirmationMessage("YOU HAVEN'T ENTERED PLAYER # 02 NAME!", "Please Enter Player # 02 Name.");
        }
        else if (player1Name.getText().equals(player2Name.getText())) {
            log.warn("You have entered same Names of Both Players!");

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message!");
            alert.setHeaderText("YOU HAVE ENTERED SAME NAMES!");
            alert.setContentText("Are you sure you want to Start Game with same Names?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                changeMainMenuSceneToGameScene(actionEvent);
            }
        }
        else{
            changeMainMenuSceneToGameScene(actionEvent);
        }
    }

    /**
     * This Method will be used for Confirmation Messages of {@code Start} Button.
     * It will get two parameters which are its {@code HeaderText} and {@code ContentText}.
     * When User either doesn't Enter any Names or Enters one name it will show an Error.
     * And User will not be able to Run the Game until he/she adds the Name or Names.
     * @param headerText This is the {@code HeaderText} that we need to put on the Confirmation Message.
     * @param contentText This is the {@code ContentText} that we need to put on the Confirmation Message.
     */
    private void confirmationMessage(String headerText, String contentText){
        alert.setTitle("Confirmation Message!");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    /**
     * This Method will Switch the Scene from {@code MainMenu} to {@code MainGame}.
     * This will also pass two Strings which are names of Player # 01 and Player # 02.
     * @param actionEvent This is the {@code ActionEvent} which will help to switch Scene from the Recent Scene to Next Scene.
     * @throws IOException If Program is not able to find the {@code MainGame} or it is not defined then it will throw an Error.
     */
    public void changeMainMenuSceneToGameScene(ActionEvent actionEvent) throws IOException {
        fxmlLoader.setLocation(getClass().getResource("/fxml/MainGame.fxml"));
        log.info("You have entered both Player's Name correctly....");
        log.info("Name of the Player # 01 is: {}!", player1Name.getText());
        log.info("Name of the Player # 02 is: {}!", player2Name.getText());

        log.info("Loading Game Scene....");
        Parent gameScene = fxmlLoader.load();
        fxmlLoader.<GameController>getController().setPlayer1Name(player1Name.getText());
        fxmlLoader.<GameController>getController().setPlayer2Name(player2Name.getText());
        Stage gameStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        gameStage.setTitle("Tic-Tac-Toe Game Scene");
        gameStage.setScene(new Scene(gameScene));
        gameStage.setResizable(false);
        gameStage.show();
    }

    /**
     * This Method will Switch the Scene from {@code MainMenu} to {@code GameRules}.
     * It will help User to see Rules before the Start of the Game.
     * @param actionEvent This is the {@code ActionEvent} which will help to switch Scene from the Recent Scene to Next Scene.
     * @throws IOException If Program is not able to find the {@code GameRules} or it is not defined then it will throw an Error.
     */
    public void changeMainMenuSceneToRulesScene(ActionEvent actionEvent) throws IOException {
        fxmlLoader.setLocation(getClass().getResource("/fxml/GameRules.fxml"));
        log.info("Rules Button was Clicked....");
        log.info("Loading Game Rules....");

        Parent gameScene = fxmlLoader.load();
        Stage gameStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        gameStage.setTitle("Tic-Tac-Toe Game Rules");
        gameStage.setScene(new Scene(gameScene));
        gameStage.setResizable(false);
        gameStage.show();
    }

    /**
     * This Method will Switch the Scene from {@code MainMenu} to {@code HighScores}.
     * It will show the HighScores Table to the User.
     * @param actionEvent This is the {@code ActionEvent} which will help to switch Scene from the Recent Scene to Next Scene.
     * @throws IOException If Program is not able to find the {@code HighScores} or it is not defined then it will throw an Error.
     */
    public void changeMainMenuSceneToHighScoreScene(ActionEvent actionEvent) throws IOException {
        fxmlLoader.setLocation(getClass().getResource("/fxml/HighScores.fxml"));
        log.info("High Scores Button was Clicked....");
        log.info("Loading High Score Table....");

        Parent gameScene = fxmlLoader.load();
        Stage gameStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        gameStage.setTitle("Tic-Tac-Toe Game High-Score Table");
        gameStage.setScene(new Scene(gameScene));
        gameStage.setResizable(false);
        gameStage.show();
    }
}