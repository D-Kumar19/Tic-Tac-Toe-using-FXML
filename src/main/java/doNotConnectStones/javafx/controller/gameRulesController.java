package doNotConnectStones.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class gameRulesController {

    FXMLLoader fxmlLoader = new FXMLLoader();
    @FXML
    private Button previousPageButton;

    public void previousPageButtonController(ActionEvent actionEvent) throws IOException {
        log.info("Previous Page Button was Clicked....");
        log.info("Loading Main-Menu Scene....");

        fxmlLoader.setLocation(getClass().getResource("/fxml/mainMenu.fxml"));
        Parent mainMenuScene = fxmlLoader.load();
        Stage mainMenuStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        mainMenuStage.setTitle("Do-not Connect Stones Main Menu");
        mainMenuStage.setScene(new Scene(mainMenuScene));
        mainMenuStage.setResizable(false);
        mainMenuStage.show();
    }
}