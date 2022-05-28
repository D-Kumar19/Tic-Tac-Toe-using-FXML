package TicTacToeGame.JavaFX.Controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.time.format.FormatStyle;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.cell.PropertyValueFactory;

import TicTacToeGame.ResultsController.ResultsRepository;
import TicTacToeGame.ResultsController.HighScoreTableColumns;

/**
 * This Class controls the {@code HighScore}.
 * And it is responsible to show the {@code HighScore} to User and make it functional for the User.
 */
@Data
@Slf4j
public class HighScoreController {

    private final int n = 5;
    private final FXMLLoader fxmlLoader = new FXMLLoader();
    List<HighScoreTableColumns> highScoreTableList = new ArrayList<>();

    @FXML
    private Button exitButton;
    @FXML
    private Button newGameButton;
    @FXML
    private TableView<HighScoreTableColumns> tableView;
    @FXML
    private TableColumn<HighScoreTableColumns, String> nameOfWinner;
    @FXML
    private TableColumn<HighScoreTableColumns, Integer> numberOfMoves;
    @FXML
    private TableColumn<HighScoreTableColumns, Long> numberOfWins;
    @FXML
    private TableColumn<HighScoreTableColumns, String> zonedDateTime;

    /**
     * This Method is called when we Switch the Scene.
     * We will use this Method to Populate the {@code tableView} Columns.
     * It will call a Method {@code getTop5Players} which will extract Top 5 Players and show it to the User.
     * @throws IOException If Program is not able to find the {@code MainMenu} or it is not defined then it will throw an Error.
     */
    @FXML
    private void initialize() throws IOException{
        ResultsRepository resultsRepository = new ResultsRepository();

        nameOfWinner.setCellValueFactory(new PropertyValueFactory<>("nameOfWinner"));
        numberOfMoves.setCellValueFactory(new PropertyValueFactory<>("numberOfMoves"));
        numberOfWins.setCellValueFactory(new PropertyValueFactory<>("numberOfWins"));
        zonedDateTime.setCellValueFactory(
                cellData -> new ReadOnlyStringWrapper(cellData.getValue().getZonedDateTime()
                        .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG))));

        log.info("Extracting Top 5 Players....");
        highScoreTableList = resultsRepository.getTop5Players(n);
        ObservableList<HighScoreTableColumns> observableList = FXCollections.observableArrayList();
        observableList.addAll(highScoreTableList);
        tableView.setItems(observableList);
    }

    /**
     * This Method will Switch the Scene from {@code HighScores} to {@code MainMenu}.
     * This Method is responsible for Restarting the Game.
     * @param actionEvent This is the {@code ActionEvent} which will help to switch Scene from the Recent Scene to Next Scene.
     * @throws IOException If Program is not able to find the {@code MainMenu} or it is not defined then it will throw an Error.
     */
    public void newGameButtonController(ActionEvent actionEvent) throws IOException {
        log.info("New Game Button was Clicked....");
        log.info("Restarting the Game....");

        fxmlLoader.setLocation(getClass().getResource("/fxml/MainMenu.fxml"));
        Parent mainMenuScene = fxmlLoader.load();
        Stage mainMenuStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        mainMenuStage.setTitle("Do-not Connect Stones Main Menu");
        mainMenuStage.setScene(new Scene(mainMenuScene));
        mainMenuStage.setResizable(false);
        mainMenuStage.show();
    }

    /**
     * This Method will be used to Exit the Game.
     * If User clicks the {@code Exit}, it will ask for Confirmation.
     * If User clicks 'OK' it will End the Game otherwise not.
     */
    public void exitButtonController(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        log.info("Exit Button was Clicked....");

        alert.setTitle("Confirmation Message!");
        alert.setHeaderText("ARE YOU SURE YOU WANT TO EXIT?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            log.info("The Game has been Exited....");
            log.warn("Closing Application....");
            log.info("Thanks for Playing this Application!");
            Platform.exit();
        }
        else{
            log.warn("Game is still Running....");
        }
    }
}