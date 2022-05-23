package doNotConnectStones.javafx.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.time.format.FormatStyle;
import java.io.FileNotFoundException;
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

import doNotConnectStones.resultsController.highScoreTable;
import doNotConnectStones.resultsController.resultsRepository;

@Data
@Slf4j
public class highScoreController {

    private final int n = 5;
    private final FXMLLoader fxmlLoader = new FXMLLoader();
    List<highScoreTable> highScoreTableList = new ArrayList<>();

    @FXML
    private Button exitButton;
    @FXML
    private Button newGameButton;
    @FXML
    private TableView<highScoreTable> tableView;
    @FXML
    private TableColumn<highScoreTable, String> nameOfWinner;
    @FXML
    private TableColumn<highScoreTable, Integer> numberOfMoves;
    @FXML
    private TableColumn<highScoreTable, Long> numberOfWins;
    @FXML
    private TableColumn<highScoreTable, String> zonedDateTime;

    @FXML
    private void initialize() throws IOException{
        nameOfWinner.setCellValueFactory(new PropertyValueFactory<>("nameOfWinner"));
        numberOfMoves.setCellValueFactory(new PropertyValueFactory<>("numberOfMoves"));
        numberOfWins.setCellValueFactory(new PropertyValueFactory<>("numberOfWins"));
        zonedDateTime.setCellValueFactory(
                cellData -> new ReadOnlyStringWrapper(cellData.getValue().getZonedDateTime()
                        .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG))));

        log.info("Extracting Top 5 Players....");
        getTop5Players();
        ObservableList<highScoreTable> observableList = FXCollections.observableArrayList();
        observableList.addAll(highScoreTableList);
        tableView.setItems(observableList);
    }

    private void getTop5Players() throws IOException {
        var repository = new resultsRepository();
        try {
            repository.loadFromFile(resultsRepository.file);
            var mapPlayersAndWins = repository.mapAllWinningPlayers();
            mapPlayersAndWins.forEach((nameOfPlayer, numberOfWins)->{
                highScoreTable singlePlayerScore = highScoreTable.builder()
                        .nameOfWinner(nameOfPlayer)
                        .numberOfMoves(repository.getLatestMoves(nameOfPlayer))
                        .numberOfWins(numberOfWins)
                        .zonedDateTime(repository.getLatestTime(nameOfPlayer))
                        .build();
                highScoreTableList.add(singlePlayerScore);
            });

            log.info("Sorting the List....");
            sortArrayList();
        } catch (FileNotFoundException e) {
            log.error("No Previous record Found!");
        }
    }

    private void sortArrayList(){
        Comparator<highScoreTable> sortPlayersWithWinsAndMoves = (entity1, entity2) -> {
            if(entity2.getNumberOfWins().equals(entity1.getNumberOfWins())){
                return Long.compare(entity1.getNumberOfMoves(), entity2.getNumberOfMoves());
            }
            else{
                return Long.compare(entity2.getNumberOfWins(), entity1.getNumberOfWins());
            }
        };

        highScoreTableList.sort(sortPlayersWithWinsAndMoves);
        highScoreTableList = highScoreTableList.stream()
                .limit(n)
                .collect(Collectors.toList());
    }

    public void newGameButtonController(ActionEvent actionEvent) throws IOException {
        log.info("New Game Button was Clicked!");
        log.info("Restarting the Game....");

        fxmlLoader.setLocation(getClass().getResource("/fxml/mainMenu.fxml"));
        Parent mainMenuScene = fxmlLoader.load();
        Stage mainMenuStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        mainMenuStage.setTitle("Do-not Connect Stones Main Menu");
        mainMenuStage.setScene(new Scene(mainMenuScene));
        mainMenuStage.setResizable(false);
        mainMenuStage.show();
    }

    public void exitButtonController(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        log.info("Exit Button was Clicked....");

        alert.setTitle("Error!");
        alert.setHeaderText("ARE YOU SURE YOU WANT TO EXIT!");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            log.info("The Game has been Exited....");
            log.warn("Closing Application....");
            log.info("Thanks for Playing the Game!");
            Platform.exit();
        }
        else{
            log.warn("Game is still Running....");
        }
    }
}