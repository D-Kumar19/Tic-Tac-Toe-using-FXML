package doNotConnectStones.javafx;

import java.util.Objects;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import lombok.extern.slf4j.Slf4j;
import javafx.application.Application;

@Slf4j
public class doNotConnectStonesApplication extends Application {

    @Override
    public void start(Stage mainMenuStage) throws Exception{

        log.info("Loading Main Menu Scene!");
        Parent mainMenuScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/mainMenu.fxml")));
        mainMenuStage.setTitle("Do-not Connect Stones Main Menu");
        mainMenuStage.setScene(new Scene(mainMenuScene));
        mainMenuStage.setResizable(false);
        mainMenuStage.show();
    }
}