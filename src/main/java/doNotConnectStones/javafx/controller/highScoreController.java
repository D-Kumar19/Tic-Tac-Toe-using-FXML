package doNotConnectStones.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import lombok.extern.slf4j.Slf4j;
import javafx.application.Platform;
import javafx.scene.control.Button;

@Slf4j
public class highScoreController {

    private final FXMLLoader fxmlLoader = new FXMLLoader();

    @FXML
    private Button finishButton;
    @FXML
    private Button newGameButton;

    public void newGameButtonController(ActionEvent actionEvent) throws IOException {
        log.debug("New Game Button was Clicked!");
        log.info("Restarting the Game....");

        fxmlLoader.setLocation(getClass().getResource("/fxml/mainMenu.fxml"));
        Parent mainMenuScene = fxmlLoader.load();
        Stage mainMenuStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        mainMenuStage.setTitle("Do-not Connect Stones Main Menu");
        mainMenuStage.setScene(new Scene(mainMenuScene));
        mainMenuStage.setResizable(false);
        mainMenuStage.show();
    }

    public void finishButtonController(ActionEvent actionEvent) throws IOException {
        log.debug("Finish Button was Clicked!");
        log.info("The Game has Finished....");
        log.info("Closing Application....");
        Platform.exit();
    }
}
