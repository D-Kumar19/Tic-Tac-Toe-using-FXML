package doNotConnectStones.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import java.util.Objects;

@Slf4j
public class doNotConnectStonesApplication extends Application {

    @Override
    public void start(Stage mainMenuStage) throws Exception{
        log.info("Loading Main Menu Scene!");
        Parent mainMenuScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/mainMenu.fxml")));
        mainMenuStage.setTitle("Do-not Connect Stones Main Menu");
        mainMenuStage.setScene(new Scene(mainMenuScene));
        mainMenuStage.show();
    }
}