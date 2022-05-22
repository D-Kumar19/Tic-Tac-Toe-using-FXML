package doNotConnectStones.javafx.controller;

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

@Slf4j
public class mainMenuController {

    private final FXMLLoader fxmlLoader= new FXMLLoader();

    @FXML
    private TextField player1Name;

    @FXML
    private TextField player2Name;

    public void switchMainMenuToGameScene(ActionEvent actionEvent) throws  IOException {
        log.debug("User just clicked Start!");
        Alert alert = new Alert(Alert.AlertType.ERROR);

        if (player1Name.getText().isEmpty() && player2Name.getText().isEmpty()) {
            log.warn("You haven't entered any Player's Name!");

            alert.setTitle("Error!");
            alert.setHeaderText("YOU HAVEN'T ENTERED ANY NAMES!");
            alert.setContentText("Please Enter Names: ");
            alert.showAndWait();
        }
        else if (player1Name.getText().isEmpty()) {
            log.warn("You have entered only Player Two's Name!");

            alert.setTitle("Error!");
            alert.setHeaderText("YOU HAVEN'T ENTERED PLAYER # 01 NAME!");
            alert.setContentText("Please Enter Player # 01 Name: ");
            alert.showAndWait();
        }
        else if (player2Name.getText().isEmpty()) {
            log.warn("You have entered only Player One's Name!");

            alert.setTitle("Error!");
            alert.setHeaderText("YOU HAVEN'T ENTERED PLAYER # 02 NAME!");
            alert.setContentText("Please Enter Player # 02 Name: ");
            alert.showAndWait();
        }
        else if (player1Name.getText().equals(player2Name.getText())) {
            log.warn("You have entered same Names of Both Players!");

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error!");
            alert.setHeaderText("YOU HAVE ENTERED SAME NAMES!");
            alert.setContentText("Are you sure you want to start game with same names: ");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                changeMainMenuSceneToGameScene(actionEvent);
            }
        }
        else{
            changeMainMenuSceneToGameScene(actionEvent);
        }
    }

    public void changeMainMenuSceneToGameScene(ActionEvent actionEvent) throws IOException {
        fxmlLoader.setLocation(getClass().getResource("/fxml/game.fxml"));
        log.info("You have entered both Player's Name correctly....");
        log.info("Name of the Player # 01 is: {}!", player1Name.getText());
        log.info("Name of the Player # 02 is: {}!", player2Name.getText());

        log.info("Loading gameScene....");
        Parent gameScene = fxmlLoader.load();
        fxmlLoader.<gameController>getController().setPlayer1Name(player1Name.getText());
        fxmlLoader.<gameController>getController().setPlayer2Name(player2Name.getText());
        Stage gameStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        gameStage.setTitle("Do-not Connect Stones Game Scene");
        gameStage.setScene(new Scene(gameScene));
        gameStage.setResizable(false);
        gameStage.show();
    }
}